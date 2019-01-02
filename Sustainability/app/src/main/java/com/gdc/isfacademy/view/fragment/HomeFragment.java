package com.gdc.isfacademy.view.fragment;

import android.animation.ValueAnimator;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("ALL")
public class HomeFragment extends BaseFragment implements View.OnClickListener {
    public static final String TAG = "HomeFragment";
    public static boolean isQuizSubmited = false;
    public SeekBar seekbar, seekbar_last_cycle;
    AppCompatTextView studentHouse, currentCosumptionDate;
    EnergySavingResponse.CurrentCons currentCons;
    ImageView percentArrow, buildingEnergyStatusArrow;
    AppCompatTextView zerpPercentSave, zeroPercentSavedBuilding;
    AppCompatSpinner spinner;
    String thisWeekStatusValue, lastWeekStatusValue;
    LinearLayout chartView;
    BarChart mChart;
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
        seekbar_last_cycle = (SeekBar) layout.findViewById(R.id.seekbar_last_cycle);
        chartView = (LinearLayout) layout.findViewById(R.id.chartView);
        mChart = new BarChart(getActivity());//just populating data, etc
        chartView.addView(mChart);
        mChart.getLayoutParams().height = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
        mChart.getLayoutParams().width = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
        mChart.invalidate();

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
        seekbar_last_cycle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });


       /* setSeekbarValue(1f);
        setSeekbarforLastCycle(1f);*/

      /*  Calendar cal = Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("dd MMMM yyyy");
        String currentDate = month_date.format(cal.getTime());*/


        // l.setExtra(ColorTemplate.VORDIPLOM_COLORS, new String[] { "abc",
        // "def", "ghj", "ikl", "mno" });
        // l.setCustom(ColorTemplate.VORDIPLOM_COLORS, new String[] { "abc",
        // "def", "ghj", "ikl", "mno" });

    }


    public void setDataForChart(float currentCycle, float lastCycle, String currentValue, String lastCycleValue) {
        mChart.setDrawBarShadow(false);
        mChart.setTouchEnabled(false);
        mChart.setDragEnabled(false);
        mChart.setScaleEnabled(true);
        mChart.setScaleXEnabled(false);
        mChart.setScaleYEnabled(false);
        mChart.setPinchZoom(false);
        mChart.setDoubleTapToZoomEnabled(false);

        mChart.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawGridBackground(false);
        // mChart.setDrawYLabels(false);

        mChart.getAxisRight().setDrawGridLines(false);
        mChart.getAxisLeft().setDrawGridLines(false);
        mChart.getXAxis().setDrawGridLines(false);


        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));

        xAxis.setSpaceBetweenLabels(2);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setLabelCount(5, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setAxisMinValue(0f);// this replaces setStartAtZero(true)
        if (currentCycle > lastCycle) {

            leftAxis.setAxisMaxValue(currentCycle + setRatio(currentCycle));

        } else {
            leftAxis.setAxisMaxValue(lastCycle + setRatio(lastCycle));

        }
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);

        Legend l = mChart.getLegend();

        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(1f);
        l.setTextSize(1f);
        l.setXEntrySpace(1f);
        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add(currentValue);
        xVals.add(lastCycleValue);

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        yVals1.add(new BarEntry(currentCycle, 0));
        yVals1.add(new BarEntry(lastCycle, 1));


        BarDataSet set1 = new BarDataSet(yVals1, "");
        set1.setDrawValues(false);
        set1.setBarSpacePercent(35f);
        set1.setColors(new int[]{ContextCompat.getColor(getActivity(), R.color.color_text_and_spinner),
                ContextCompat.getColor(getActivity(), R.color.dark_gray)});
         set1.setHighlightEnabled(false);


        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(xVals, dataSets);
        data.setValueTextSize(10f);
        data.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                DecimalFormat mFormat = new DecimalFormat("###,###,##0.00"); // use two decimal

                float value1 = new BigDecimal(value).setScale(2, BigDecimal.ROUND_DOWN).floatValue();

                return mFormat.format(value1);
            }
        });



        mChart.setData(data);
        mChart.invalidate();
        mChart.getLegend().setEnabled(false);


    }


    public float setRatio(float value) {
        if (value >= 0 && value <= 5) {
            return  5;
        } else if (value > 5 && value <= 10) {
            return  10;
        } else if (value > 10 && value <= 20) {
            return  20;
        } else if (value > 20 && value <= 50) {
            return 25;
        } else if (value > 50 && value <= 100) {
            return 50;
        } else if (value > 100 && value <= 1000) {
            return  200;
        }  else if (value > 1000 && value <= 2000) {
            return   200;
        }
        return  500;
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
                        bundle.putString(AppConstants.PICK_ENERGY_SAVING_DATE, currentCosumptionDate.getText().toString().trim());
                        ((HomeActivity) getActivity()).pushFragments(new HowMuchSaveFragment(), bundle, true);
                    }
                }, 500);

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
                            zerpPercentSave.setVisibility(View.GONE);
                            ProjectUtil.showToast(ISFApp.getAppInstance().getApplicationContext(), response.body().getResponseMessage());
                        }
                    }
                    getEnergySavingBuildingConsumption();

                }

                @Override
                public void onFailure(Call<EnergySavingResponse> call, Throwable t) {
                    ProjectUtil.showToast(ISFApp.getAppInstance(), ISFApp.getAppInstance().getString(R.string.something_went_wrong));
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
                            zeroPercentSavedBuilding.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                            ProjectUtil.showToast(ISFApp.getAppInstance().getApplicationContext(), response.body().getResponseMessage());
                        }
                    }

                }

                @Override
                public void onFailure(Call<BuildingEnergySaving> call, Throwable t) {
                    ProjectUtil.showToast(ISFApp.getAppInstance(), ISFApp.getAppInstance().getString(R.string.something_went_wrong));
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
            float currentValueFinal = new BigDecimal(response.getCurrentCons().getValue()).setScale(2, BigDecimal.ROUND_DOWN).floatValue();
            float lastValueFinal = new BigDecimal(response.getLastWeekCons().getValue()).setScale(2, BigDecimal.ROUND_DOWN).floatValue();


            String currentCons = String.format(Locale.getDefault(), "%.2f %s", currentValueFinal, unit);
            String lastWeekCons = String.format(Locale.getDefault(), "%.2f %s", lastValueFinal, unit);

            Typeface externalFont = Typeface.createFromAsset(ISFApp.getAppInstance().getApplicationContext().getAssets(), "fonts/OpenSans-Semibold_0.ttf");
            buildingThisWeekStatus.setTypeface(externalFont, Typeface.BOLD);
            buildingThisWeekStatus.setText(currentCons);
            buildingLastWeekStatus.setText(lastWeekCons);

            float actualSaving;
            if (lastValue > currentValue) // Energy Saved
            {
                float percentage = (currentValue / lastValue) * 100;

                actualSaving = 100 - percentage;
                buildingEnergyStatusArrow.setColorFilter(ContextCompat.getColor(ISFApp.getAppInstance().getApplicationContext(), R.color.color_text_and_spinner), android.graphics.PorterDuff.Mode.SRC_IN);
                buildingPercentTextview.setTextColor(ContextCompat.getColor(ISFApp.getAppInstance().getApplicationContext(), R.color.color_text_and_spinner));
                buildingEnergyStatusArrow.setBackground(ContextCompat.getDrawable(ISFApp.getAppInstance().getApplicationContext(), R.drawable.down_arrow_energy));
                buildingEnergyStatusArrow.setVisibility(View.GONE);
                zeroPercentSavedBuilding.setVisibility(View.VISIBLE);
                zeroPercentSavedBuilding.setTextColor(ContextCompat.getColor(ISFApp.getAppInstance().getApplicationContext(), R.color.color_text_and_spinner));
            } else // Energy Lost
            {
                actualSaving = 0.0f;
                buildingPercentTextview.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                buildingEnergyStatusArrow.setVisibility(View.GONE);
                buildingEnergyStatusArrow.setBackground(ContextCompat.getDrawable(ISFApp.getAppInstance().getApplicationContext(), R.drawable.up_arrow));
                zeroPercentSavedBuilding.setVisibility(View.VISIBLE);
                zeroPercentSavedBuilding.setTextColor(getResources().getColor(android.R.color.holo_red_light));


            }

            String actualConsSave = String.format(Locale.getDefault(), "%d%s", (ProjectUtil.math(actualSaving)), "%");
            buildingPercentTextview.setText(actualConsSave);

            setDataForChart(currentValue, lastValue, currentCons, lastWeekCons);

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


            thisWeekStatusValue = String.format(Locale.getDefault(), "%.2f %s", currentValue, unit);
            lastWeekStatusValue = String.format(Locale.getDefault(), "%.2f %s", lastValue, unit);

            thisWeekStatus.setText(thisWeekStatusValue);
            lastWeekStatus.setText(lastWeekStatusValue);


            if (lastValue > currentValue) {
                seekbar_last_cycle.setProgress(100);
                float percentage = (currentValue * 100 / lastValue);
                setSeekbarValue(percentage);

            } else if (currentValue > lastValue) {
                seekbar.setProgress(100);
                float percentage = (lastValue * 100 / currentValue);
                setSeekbarforLastCycle(percentage);
            } else {
                setSeekbarValue(1f);
                setSeekbarforLastCycle(1f);


            }


           /* float actualSaving,savingLastCycle;
            if (lastValue > currentValue) // Energy Saved
            {
                float percentage = (currentValue / lastValue) * 100;
                actualSaving = 100 - percentage;
                Log.e("this cycle",""+actualSaving);
                percentArrow.setBackground(ContextCompat.getDrawable(ISFApp.getAppInstance().getApplicationContext(), R.drawable.down_arrow_energy));
                percentTextview.setTextColor(ContextCompat.getColor(ISFApp.getAppInstance().getApplicationContext(), R.color.color_text_and_spinner));
                zerpPercentSave.setVisibility(View.GONE);
                percentArrow.setVisibility(View.GONE);
                seekbar_last_cycle.setProgress(100);
                setSeekbarValue(actualSaving);


            } else // Energy Lost
            {
                float percentage = (lastValue / currentValue) * 100;
                savingLastCycle = 100 - percentage;
                Log.e("LAST cycle",""+savingLastCycle);

                percentTextview.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                percentArrow.setColorFilter(ContextCompat.getColor(ISFApp.getAppInstance().getApplicationContext(), android.R.color.holo_red_light), android.graphics.PorterDuff.Mode.SRC_IN);
                percentArrow.setBackground(ContextCompat.getDrawable(ISFApp.getAppInstance().getApplicationContext(), R.drawable.up_arrow));
                percentArrow.setVisibility(View.GONE);
                zerpPercentSave.setVisibility(View.GONE);
                seekbar.setProgress(100);
                setSeekbarforLastCycle(savingLastCycle);

            }*/

            //
        /*    String actualConsSave = String.format(Locale.getDefault(), "%d%s", (ProjectUtil.math(actualSaving)), "%");
            percentTextview.setText(actualConsSave);*/

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
                            thisWeekStatus.setText(thisWeekStatusValue);
                        }
                    });
                    anim.start();

                }
            }, 500);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    private void setSeekbarforLastCycle(final float value) {
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
                            seekbar_last_cycle.setProgress((int) animProgress);
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
