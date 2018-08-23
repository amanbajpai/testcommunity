package com.gdc.isfacademy.view.fragment;

import android.animation.ValueAnimator;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.application.ISFApp;
import com.gdc.isfacademy.model.BuildingEnergySaving;
import com.gdc.isfacademy.model.CommonResponse;
import com.gdc.isfacademy.model.EnergySavingResponse;
import com.gdc.isfacademy.netcom.CheckNetworkState;
import com.gdc.isfacademy.utils.AppConstants;
import com.gdc.isfacademy.utils.MyPref;
import com.gdc.isfacademy.utils.ProjectUtil;
import com.gdc.isfacademy.view.activity.HomeActivity;
import com.gdc.isfacademy.view.adapter.HighLightArrayAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("ALL")
public class HomeFragment extends BaseFragment implements View.OnClickListener{
    public static final String TAG = "HomeFragment";
    public static boolean isQuizSubmited = false;
    public SeekBar seekbar;
    AppCompatTextView studentHouse, currentCosumptionDate;
    EnergySavingResponse.CurrentCons currentCons;
    ImageView percentArrow, buildingEnergyStatusArrow;
    AppCompatTextView zerpPercentSave, zeroPercentSavedBuilding;
    AppCompatSpinner spinner;
    private RelativeLayout how_much_save_rl;
    private TextView thisWeekStatus, lastWeekStatus, buildingThisWeekStatus,
            buildingLastWeekStatus, percentTextview, buildingPercentTextview, comparison_tv;

    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.home_fragment, container, false);
        initView(layout);
        getEnergySaving(getString(R.string.txt_daily));
        if (!isQuizSubmited) {
            getQuizSubmittedStatus();
        }
        setItemForSpinner();
        return layout;
    }


    @Override
    public void onResume() {
        super.onResume();
        ISFApp.getAppInstance().trackScreenView("Home Screen");

    }

    private void initView(View layout) {
        spinner = (AppCompatSpinner) layout.findViewById(R.id.spinner);
        comparison_tv = (AppCompatTextView) layout.findViewById(R.id.comparuison_tv);
        currentCosumptionDate = (AppCompatTextView) layout.findViewById(R.id.currentCosumptionDate);
        zerpPercentSave = (AppCompatTextView) layout.findViewById(R.id.zeroPercentSaved);
        zeroPercentSavedBuilding = (AppCompatTextView) layout.findViewById(R.id.zeroPercentSavedBuilding);
        how_much_save_rl = (RelativeLayout) layout.findViewById(R.id.how_much_save_rl);
        thisWeekStatus = (TextView) layout.findViewById(R.id.kwh_text_status);
        lastWeekStatus = (TextView) layout.findViewById(R.id.full_status_text);
        percentTextview = (TextView) layout.findViewById(R.id.percentTextview);
        percentArrow = (ImageView) layout.findViewById(R.id.percentArrow);
        seekbar = (SeekBar) layout.findViewById(R.id.seekbar);
        buildingEnergyStatusArrow = (ImageView) layout.findViewById(R.id.buildingEnergyStatusArrow);
        buildingPercentTextview = (TextView) layout.findViewById(R.id.buildingPercentTextview);
        buildingThisWeekStatus = (TextView) layout.findViewById(R.id.school_kwh_text_status);
        buildingLastWeekStatus = (TextView) layout.findViewById(R.id.school_full_status_text);
        studentHouse = (AppCompatTextView) layout.findViewById(R.id.studentHouse);
        studentHouse.setText(MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_HOUSE));
        comparison_tv.setText(getString(R.string.txt_compariosn_today_cycle));

        how_much_save_rl.setOnClickListener(this);
        seekbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        setSeekbarValue(0.0f);

      /*  Calendar cal = Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("dd MMMM yyyy");
        String currentDate = month_date.format(cal.getTime());*/
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.how_much_save_rl:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(AppConstants.CURRENT_ENERGY_UNIT, currentCons);
                        bundle.putString(AppConstants.PICK_ENERGY_SAVING_DATE,currentCosumptionDate.getText().toString().trim());
                        ((HomeActivity) getActivity()).pushFragments(new HowMuchSaveFragment(), bundle, true);
                    }
                },500);

                break;



        }

    }


    public void setItemForSpinner() {
        final ArrayList<String> spinnerItemList = new ArrayList<>();
        spinnerItemList.add(getString(R.string.txt_today));
        spinnerItemList.add(getString(R.string.txt_cycle));
        spinnerItemList.add(getString(R.string.txt_monthly));
        final CharSequence[] charSequenceItems = spinnerItemList.toArray(new CharSequence[spinnerItemList.size()]);


        HighLightArrayAdapter adapter = new HighLightArrayAdapter(getActivity(), R.layout.spinner_selcted_item,
                charSequenceItems, spinner);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0, false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    getEnergySaving(getString(R.string.txt_daily));
                    comparison_tv.setText(getString(R.string.txt_compariosn_today_cycle));
                } else if (i == 1) {
                    comparison_tv.setText(getString(R.string.txt_compariosn_today_cycle));
                    getEnergySaving(getString(R.string.txt_cycle_small));
                } else if (i == 2) {
                    comparison_tv.setText(getString(R.string.txt_compariosn_month));
                    getEnergySaving(getString(R.string.txt_monthly_small));

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    private void getEnergySaving(String type) {
        if (!CheckNetworkState.isOnline(getActivity())) {
            ProjectUtil.showToast(getActivity(), getResources().getString(R.string.something_went_wrong));
            return;
        }
        try {
            showProgressDialog(getActivity());
            Call<EnergySavingResponse> call = ISFApp.getAppInstance()
                    .getApi()
                    .getStudentEnergySaving(AppConstants.API_KEY,
                            AppConstants.CONTENT_TYPE,
                            MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_KEY),
                            type);

            ProjectUtil.showLog(AppConstants.REQUEST, "" + call.request().url(), AppConstants.ERROR_LOG);

            call.enqueue(new Callback<EnergySavingResponse>() {
                @Override
                public void onResponse(Call<EnergySavingResponse> call, Response<EnergySavingResponse> response) {
                    ProjectUtil.showLog(AppConstants.RESPONSE, "" + new Gson().toJson(response.body()), AppConstants.ERROR_LOG);
                    if (response.body() != null) {
                        if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.RESPONSE_CODE_SUCCUSS)) {
                            currentCons = response.body().getCurrentCons();
                            setView(response.body());
                        } else if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.ERROR_CODE_STUDENT_KEY_NOT_MATCHED)) {
                            ProjectUtil.logoutFromApp(getActivity());
                        } else {
                            percentTextview.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                            percentArrow.setColorFilter(ContextCompat.getColor(getActivity(), android.R.color.holo_red_light), android.graphics.PorterDuff.Mode.SRC_IN);
                            percentArrow.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.up_arrow));
                            percentArrow.setVisibility(View.GONE);
                            zerpPercentSave.setVisibility(View.VISIBLE);
                            ProjectUtil.showToast(ISFApp.getAppInstance().getApplicationContext(), response.body().getResponseMessage());
                        }
                    }
                    getEnergySavingBuildingConsumption();

                }

                @Override
                public void onFailure(Call<EnergySavingResponse> call, Throwable t) {
                    if (getActivity() != null) {
                        ProjectUtil.showToast(getActivity(), getResources().getString(R.string.something_went_wrong));
                    }
                    t.printStackTrace();
                    getEnergySavingBuildingConsumption();

                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void getEnergySavingBuildingConsumption() {
        try {
            Call<BuildingEnergySaving> call = ISFApp.getAppInstance()
                    .getApi()
                    .getBuildingStudentEnergySaving(AppConstants.API_KEY,
                            AppConstants.CONTENT_TYPE,
                            MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_KEY));

            ProjectUtil.showLog(AppConstants.REQUEST, "" + call.request().url(), AppConstants.ERROR_LOG);

            call.enqueue(new Callback<BuildingEnergySaving>() {
                @Override
                public void onResponse(Call<BuildingEnergySaving> call, Response<BuildingEnergySaving> response) {
                    ProjectUtil.showLog(AppConstants.RESPONSE, "" + new Gson().toJson(response.body()), AppConstants.ERROR_LOG);
                    hideProgressDialog();
                    if (response.body() != null) {
                        if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.RESPONSE_CODE_SUCCUSS)) {
                            MyPref.getInstance(getActivity()).writeFloatPrefs(MyPref.TOTAL_CURRENT_CONSUMPTION_BUILDING, response.body().getCurrentCons().getValue());
                            MyPref.getInstance(getActivity()).writeFloatPrefs(MyPref.TOTAL_WEEK_CONSUMPTION_BUILDING, response.body().getLastWeekCons().getValue());
                            MyPref.getInstance(getActivity()).writePrefs(MyPref.TOTAL_CURRENT_CONSUMPTION_BUILDING_UNIT, response.body().getCurrentCons().getUnit());
                            setViewforBuilding(response.body());
                        } else {
                            buildingPercentTextview.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                            buildingEnergyStatusArrow.setVisibility(View.GONE);
                            buildingEnergyStatusArrow.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.up_arrow));
                            zeroPercentSavedBuilding.setVisibility(View.VISIBLE);
                            ProjectUtil.showToast(ISFApp.getAppInstance().getApplicationContext(), response.body().getResponseMessage());
                        }
                    }

                }

                @Override
                public void onFailure(Call<BuildingEnergySaving> call, Throwable t) {
                    ProjectUtil.showToast(getActivity(), getResources().getString(R.string.something_went_wrong));
                    t.printStackTrace();
                    hideProgressDialog();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void setViewforBuilding(BuildingEnergySaving response) {
        try {
            String unit = response.getCurrentCons().getUnit();

            Float currentValue = response.getCurrentCons().getValue();
            Float lastValue = response.getLastWeekCons().getValue();


            String currentCons = String.format(Locale.getDefault(), "%.2f %s", currentValue, unit);
            String lastWeekCons = String.format(Locale.getDefault(), "/ %.2f %s", lastValue, unit);

            Typeface externalFont = Typeface.createFromAsset(ISFApp.getAppInstance().getApplicationContext().getAssets(), "fonts/OpenSans-Semibold_0.ttf");
            buildingThisWeekStatus.setTypeface(externalFont, Typeface.BOLD);
            buildingThisWeekStatus.setText(currentCons);
            buildingLastWeekStatus.setText(lastWeekCons);

            float actualSaving;
            if (lastValue > currentValue) // Energy Saved
            {
                float percentage = (currentValue / lastValue) * 100;

                actualSaving = 100 - percentage;
                buildingEnergyStatusArrow.setColorFilter(ContextCompat.getColor(ISFApp.getAppInstance().getApplicationContext(), R.color.home_bottom_card_text_color), android.graphics.PorterDuff.Mode.SRC_IN);
                buildingPercentTextview.setTextColor(ContextCompat.getColor(ISFApp.getAppInstance().getApplicationContext(), R.color.home_bottom_card_text_color));
                buildingEnergyStatusArrow.setBackground(ContextCompat.getDrawable(ISFApp.getAppInstance().getApplicationContext(), R.drawable.down_arrow));
                buildingEnergyStatusArrow.setVisibility(View.VISIBLE);
                zeroPercentSavedBuilding.setVisibility(View.GONE);
            } else // Energy Lost
            {
                actualSaving = 0.0f;
                buildingPercentTextview.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                buildingEnergyStatusArrow.setVisibility(View.GONE);
                buildingEnergyStatusArrow.setBackground(ContextCompat.getDrawable(ISFApp.getAppInstance().getApplicationContext(), R.drawable.up_arrow));
                zeroPercentSavedBuilding.setVisibility(View.VISIBLE);

            }

            String actualConsSave = String.format(Locale.getDefault(), "%d%s", (ProjectUtil.math(actualSaving)), "%");
            buildingPercentTextview.setText(actualConsSave);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void setView(EnergySavingResponse response) {
        try {
            String unit = response.getCurrentCons().getUnit();

            Float currentValue = response.getCurrentCons().getValue();
            Float lastValue = response.getLastWeekCons().getValue();

            currentCosumptionDate.setText(getString(R.string.txt_up_to) + " " + response.getCurrentCons().getLastUpdateDate());



           /* Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(response.getLastWeekCons().getLastUpdateTs());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/M/yyyy HH:mm:ss");

            Date date1=dateFormat.parse("06/5/2018 19:55:00");
            TimeAgo timeAgo = new TimeAgo().locale(getActivity()).with(dateFormat);
            String result = timeAgo.getTimeAgo(date1);
            Log.d("time is",result);*/


            String currentCons = String.format(Locale.getDefault(), "%.2f %s", currentValue, unit);
            String lastWeekCons = String.format(Locale.getDefault(), "/ %.2f %s", lastValue, unit);

            thisWeekStatus.setText(currentCons);
            lastWeekStatus.setText(lastWeekCons);

            float actualSaving;
            if (lastValue > currentValue) // Energy Saved
            {
                float percentage = (currentValue / lastValue) * 100;
                actualSaving = 100 - percentage;
                percentArrow.setBackground(ContextCompat.getDrawable(ISFApp.getAppInstance().getApplicationContext(), R.drawable.down_arrow_energy));
                percentTextview.setTextColor(ContextCompat.getColor(ISFApp.getAppInstance().getApplicationContext(), R.color.color_text_and_spinner));
                zerpPercentSave.setVisibility(View.GONE);
                percentArrow.setVisibility(View.VISIBLE);


            } else // Energy Lost
            {
                actualSaving = 0.0f;
                percentTextview.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                percentArrow.setColorFilter(ContextCompat.getColor(ISFApp.getAppInstance().getApplicationContext(), android.R.color.holo_red_light), android.graphics.PorterDuff.Mode.SRC_IN);
                percentArrow.setBackground(ContextCompat.getDrawable(ISFApp.getAppInstance().getApplicationContext(), R.drawable.up_arrow));
                percentArrow.setVisibility(View.GONE);
                zerpPercentSave.setVisibility(View.VISIBLE);

            }

            String actualConsSave = String.format(Locale.getDefault(), "%d%s", (ProjectUtil.math(actualSaving)), "%");
            percentTextview.setText(actualConsSave);

            setSeekbarValue(actualSaving);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void setSeekbarValue(final float value) {
        try {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ValueAnimator anim = ValueAnimator.ofFloat(0, value);
                    anim.setDuration(1000);
                    anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            float animProgress = (Float) animation.getAnimatedValue();
                            seekbar.setProgress((int) animProgress);
                        }
                    });
                    anim.start();
                }
            }, 500);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void getQuizSubmittedStatus() {
        if (!CheckNetworkState.isOnline(getActivity())) {
            ProjectUtil.showToast(getActivity(), getString(R.string.txt_network_error));
            return;
        }
        //      showProgressDialog(getActivity());
        Call<CommonResponse> call = ISFApp.getAppInstance()
                .getApi()
                .checkStudenQuestions(AppConstants.API_KEY,
                        AppConstants.CONTENT_TYPE,
                        MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_KEY));

        ProjectUtil.showLog(AppConstants.REQUEST, "" + call.request().url(), AppConstants.ERROR_LOG);

        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                ProjectUtil.showLog(AppConstants.RESPONSE, "" + new Gson().toJson(response.body()), AppConstants.ERROR_LOG);
                hideProgressDialog();
                isQuizSubmited = true;
                if (response.body() != null) {

                    if (response.body().getResponseCode() != null) {
                        if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.RESPONSE_CODE_SUCCUSS)) {
                            if (response.body().isSubmitted().equalsIgnoreCase("true")) {
                                if (response.body().getCorrect() != null) {
                                    MyPref.getInstance(getActivity()).writeIntegerPrefs(MyPref.QUIZ_COUNT, Integer.parseInt(response.body().getCorrect()));
                                }
                            } else if (response.body().isSubmitted().equalsIgnoreCase("false")) {
                                MyPref.getInstance(getActivity()).writeIntegerPrefs(MyPref.QUIZ_COUNT, -1);

                            }
                        }
                    }

                }


            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                //  ProjectUtil.showToast(getActivity(), getResources().getString(R.string.something_went_wrong));
                t.printStackTrace();
                isQuizSubmited = false;

                //  hideProgressDialog();
            }
        });

    }


}
