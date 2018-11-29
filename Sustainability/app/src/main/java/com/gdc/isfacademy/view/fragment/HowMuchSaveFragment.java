package com.gdc.isfacademy.view.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.application.ISFApp;
import com.gdc.isfacademy.model.EnergySavingResponse;
import com.gdc.isfacademy.model.RetrieveDailyConsResponse;
import com.gdc.isfacademy.model.StudentFootPrintResponse;
import com.gdc.isfacademy.model.StudentSavedCostResponse;
import com.gdc.isfacademy.netcom.CheckNetworkState;
import com.gdc.isfacademy.utils.AppConstants;
import com.gdc.isfacademy.utils.MyPref;
import com.gdc.isfacademy.utils.ProjectUtil;
import com.gdc.isfacademy.view.activity.HomeActivity;
import com.gdc.isfacademy.view.adapter.HighLightArrayAdapter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.google.gson.Gson;
import java.math.BigDecimal;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("ALL")
public class HowMuchSaveFragment extends BaseFragment implements
        OnChartGestureListener,
        OnChartValueSelectedListener {

    public static final String TAG = "HowMuchSaveFragment";
    public static int countInstruction = 0;
    public String typeCheck;
    AppCompatTextView currentWeekConsumption, upto_date_tv, energyUnit, cost_tv, valueCarbon, hotChoclateText, unitCarbon, valueTree, how_much_save_label;
    EnergySavingResponse.CurrentCons currentCons;
    AppCompatSpinner costSaveSpinner, spinnerfootPrint, spinnerChart;
    AppCompatTextView randomSaveInstructionText;
    ArrayList<String> xVals;
    ArrayList<Entry> yaxisValueForStudent, getYaxisValueForSchoolAvg, targetValue;
    String aFloat;
    private LineChart mChart;
    private String dateEnergySaving;
    private LinearLayout chartView;

    public static HowMuchSaveFragment newInstance() {
        HowMuchSaveFragment howMuchSaveFragment = new HowMuchSaveFragment();
        return howMuchSaveFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.how_much_saved_layout, null);

        init(view);
        // add data


        return view;
    }

    public void init(View view) {
        spinnerChart = (AppCompatSpinner) view.findViewById(R.id.spinnerChart);
        chartView = (LinearLayout) view.findViewById(R.id.chartView);
        mChart = new LineChart(getActivity());//just populating data, etc
        chartView.addView(mChart);
        mChart.getLayoutParams().height = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
        mChart.getLayoutParams().width = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
        mChart.invalidate();

        upto_date_tv = (AppCompatTextView) view.findViewById(R.id.upto_date_tv);
        spinnerfootPrint = (AppCompatSpinner) view.findViewById(R.id.spinnerfootPrint);
        cost_tv = (AppCompatTextView) view.findViewById(R.id.cost_tv);
        how_much_save_label = (AppCompatTextView) view.findViewById(R.id.how_much_save_label);
        hotChoclateText = (AppCompatTextView) view.findViewById(R.id.hotChoclateText);
        costSaveSpinner = (AppCompatSpinner) view.findViewById(R.id.spinner);
        currentWeekConsumption = (AppCompatTextView) view.findViewById(R.id.currentWeekConsumption);
        energyUnit = (AppCompatTextView) view.findViewById(R.id.energyUnit);
        valueCarbon = (AppCompatTextView) view.findViewById(R.id.value_carbon);
        unitCarbon = (AppCompatTextView) view.findViewById(R.id.unitCarbon);
        valueTree = (AppCompatTextView) view.findViewById(R.id.valueTree);
        // mChart = (LineChart) view.findViewById(R.id.linechart);
        randomSaveInstructionText = (AppCompatTextView) view.findViewById(R.id.randomSaveInstructionText);

        xVals = new ArrayList<>();
        yaxisValueForStudent = new ArrayList<>();
        getYaxisValueForSchoolAvg = new ArrayList<>();
        targetValue = new ArrayList<>();
        mChart.setDragEnabled(false);
        mChart.setClickable(false);
        mChart.getLegend().setForm(Legend.LegendForm.SQUARE);
        mChart.getLegend().setTextSize(12);
        mChart.setDoubleTapToZoomEnabled(false);
        setItemForSpinner();
        setRandomInstruction();
        Bundle bundle = getArguments();
        currentCons = (EnergySavingResponse.CurrentCons) bundle.getSerializable(AppConstants.CURRENT_ENERGY_UNIT);
        dateEnergySaving = bundle.getString(AppConstants.PICK_ENERGY_SAVING_DATE);
        if (currentCons != null) {
            Log.d("conumption", "" + currentCons.getValue() + " " + currentCons.getUnit());
            //  currentWeekConsumption.setText(String.format("%.2f", currentCons.getValue()));
            // energyUnit.setText(currentCons.getUnit());
        }
        upto_date_tv.setText(dateEnergySaving);


        getGraphData(getString(R.string.txt_month_graph));
        getStudentCostSaving(getString(R.string.txt_daily));
        getStudentFootPrint(getString(R.string.txt_daily));
        setItemForFootPrintSpinner();
        setItemForSpinnerChart();
    }


    public void setRandomInstruction() {
        if (countInstruction == 6) {
            countInstruction = 1;
        } else {
            countInstruction = countInstruction + 1;
        }
        if (countInstruction == 1) {
            randomSaveInstructionText.setText(getString(R.string.txt_one));
        } else if (countInstruction == 2) {
            randomSaveInstructionText.setText(getString(R.string.txt_two));
        } else if (countInstruction == 3) {
            randomSaveInstructionText.setText(getString(R.string.txt_three));
        } else if (countInstruction == 4) {
            randomSaveInstructionText.setText(getString(R.string.txt_four));
        } else if (countInstruction == 5) {
            randomSaveInstructionText.setText(getString(R.string.txt_five));
        } else if (countInstruction == 6) {
            randomSaveInstructionText.setText(getString(R.string.txt_six));
        }

    }


    public void setItemForSpinner() {
        final ArrayList<String> spinnerItemList = new ArrayList<>();
        spinnerItemList.add(getString(R.string.txt_today));
        spinnerItemList.add(getString(R.string.txt_cycle));
        spinnerItemList.add(getString(R.string.txt_monthly));
        final CharSequence[] charSequenceItems = spinnerItemList.toArray(new CharSequence[spinnerItemList.size()]);


        HighLightArrayAdapter adapter = new HighLightArrayAdapter(getActivity(), R.layout.spinner_selcted_item,
                charSequenceItems, costSaveSpinner);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        costSaveSpinner.setAdapter(adapter);
        costSaveSpinner.setSelection(0, false);
        costSaveSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    xVals = new ArrayList<>();
                    yaxisValueForStudent = new ArrayList<>();
                    getYaxisValueForSchoolAvg = new ArrayList<>();
                    targetValue = new ArrayList<>();
                    getStudentCostSaving(getString(R.string.txt_daily));
                    //   getGraphData(getString(R.string.txt_month_graph));

                } else if (i == 1) {
                    xVals = new ArrayList<>();
                    yaxisValueForStudent = new ArrayList<>();
                    getYaxisValueForSchoolAvg = new ArrayList<>();
                    targetValue = new ArrayList<>();
                    getStudentCostSaving(getString(R.string.txt_cycle_small));
                    //  getGraphData(getString(R.string.txt_month_graph));

                } else if (i == 2) {
                    xVals = new ArrayList<>();
                    yaxisValueForStudent = new ArrayList<>();
                    getYaxisValueForSchoolAvg = new ArrayList<>();
                    targetValue = new ArrayList<>();
                    getStudentCostSaving(getString(R.string.txt_monthly_small));
                    //   getGraphData(getString(R.string.txt_month_graph));

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    public void setItemForSpinnerChart() {
        final ArrayList<String> spinnerItemList = new ArrayList<>();
        spinnerItemList.add(getString(R.string.txt_monthly));
        spinnerItemList.add(getString(R.string.txt_cycle));
        final CharSequence[] charSequenceItems = spinnerItemList.toArray(new CharSequence[spinnerItemList.size()]);


        HighLightArrayAdapter adapter = new HighLightArrayAdapter(getActivity(), R.layout.text_chart_layout,
                charSequenceItems, spinnerChart);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerChart.setAdapter(adapter);
        spinnerChart.setSelection(0, false);
        spinnerChart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    xVals = new ArrayList<>();
                    yaxisValueForStudent = new ArrayList<>();
                    getYaxisValueForSchoolAvg = new ArrayList<>();
                    targetValue = new ArrayList<>();
                    getGraphData(getString(R.string.txt_month_graph));

                } else if (i == 1) {
                    xVals = new ArrayList<>();
                    yaxisValueForStudent = new ArrayList<>();
                    getYaxisValueForSchoolAvg = new ArrayList<>();
                    targetValue = new ArrayList<>();
                    getGraphData(getString(R.string.txt_cycle_small));

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    public void setItemForFootPrintSpinner() {
        final ArrayList<String> spinnerItemList = new ArrayList<>();
        spinnerItemList.add(getString(R.string.txt_today));
        spinnerItemList.add(getString(R.string.txt_cycle));
        spinnerItemList.add(getString(R.string.txt_monthly));
        final CharSequence[] charSequenceItems = spinnerItemList.toArray(new CharSequence[spinnerItemList.size()]);


        HighLightArrayAdapter adapter = new HighLightArrayAdapter(getActivity(), R.layout.spinner_selcted_item,
                charSequenceItems, spinnerfootPrint);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerfootPrint.setAdapter(adapter);
        spinnerfootPrint.setSelection(0, false);
        spinnerfootPrint.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    getStudentFootPrint(getString(R.string.txt_daily));
                } else if (i == 1) {
                    getStudentFootPrint(getString(R.string.txt_cycle_small));
                } else if (i == 2) {
                    getStudentFootPrint(getString(R.string.txt_monthly_small));

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    private void getStudentCostSaving(String type) {
        if (!CheckNetworkState.isOnline(getActivity())) {
            ProjectUtil.showToast(getActivity(), getString(R.string.txt_network_error));
            return;
        }

        showProgressDialog(getActivity());
        Call<StudentSavedCostResponse> call = ISFApp.getAppInstance()
                .getApi()
                .getStudentCostSaved(AppConstants.API_KEY,
                        AppConstants.CONTENT_TYPE,
                        MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_KEY),
                        type);
        ProjectUtil.showLog(AppConstants.REQUEST, "" + call.request().url(), AppConstants.ERROR_LOG);

        call.enqueue(new Callback<StudentSavedCostResponse>() {
            @Override
            public void onResponse(Call<StudentSavedCostResponse> call, Response<StudentSavedCostResponse> response) {
                ProjectUtil.showLog(AppConstants.RESPONSE, "" + new Gson().toJson(response.body()), AppConstants.ERROR_LOG);
                hideProgressDialog();
                if (response.body() != null) {
                    if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.RESPONSE_CODE_SUCCUSS)) {
                        energyUnit.setText(response.body().getUnit());
                        if (response.body().getSaveEnergy() != null && !response.body().getSaveEnergy().equalsIgnoreCase("")) {
                            String value = new BigDecimal(response.body().getSaveEnergy()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
                            try {
                                currentWeekConsumption.setText(value);

                            } catch (Throwable e) {
                                e.printStackTrace();
                            }
                        }
                        if (response.body().getComparison() != null && !response.body().getComparison().equalsIgnoreCase("")) {
                            String value = new BigDecimal(response.body().getComparison()).setScale(1, BigDecimal.ROUND_HALF_UP).toString();
                            try {
                                hotChoclateText.setText(getString(R.string.txt_x) + "" + value);

                            } catch (Throwable e) {
                                e.printStackTrace();
                            }
                        }

                        if (response.body().getSaveCost() != null && !response.body().getSaveCost().equalsIgnoreCase("")) {
                            String value = new BigDecimal(response.body().getSaveCost()).setScale(1, BigDecimal.ROUND_HALF_UP).toString();
                            cost_tv.setText(value);
                        }
                    } else {
                        ProjectUtil.showToast(ISFApp.getAppInstance().getApplicationContext(), response.body().getResponseMessage());

                    }
                }


            }

            @Override
            public void onFailure(Call<StudentSavedCostResponse> call, Throwable t) {
                ProjectUtil.showToast(getActivity(), getResources().getString(R.string.something_went_wrong));
                t.printStackTrace();
                hideProgressDialog();
            }
        });


    }


    private void getStudentFootPrint(String type) {
        if (!CheckNetworkState.isOnline(getActivity())) {
            ProjectUtil.showToast(getActivity(), getString(R.string.txt_network_error));
            return;
        }

        showProgressDialog(getActivity());
        Call<StudentFootPrintResponse> call = ISFApp.getAppInstance()
                .getApi()
                .getStudentFootPrint(AppConstants.API_KEY,
                        AppConstants.CONTENT_TYPE,
                        MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_KEY),
                        type);

        ProjectUtil.showLog(AppConstants.REQUEST, "" + call.request().url(), AppConstants.ERROR_LOG);

        call.enqueue(new Callback<StudentFootPrintResponse>() {
            @Override
            public void onResponse(Call<StudentFootPrintResponse> call, Response<StudentFootPrintResponse> response) {
                ProjectUtil.showLog(AppConstants.RESPONSE, "" + new Gson().toJson(response.body()), AppConstants.ERROR_LOG);
                hideProgressDialog();
                if (response.body() != null) {
                    if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.RESPONSE_CODE_SUCCUSS)) {
                        if (response.body().getCo2() != null) {

                            if (response.body().getCo2().getValue() != null && !response.body().getCo2().getValue().equalsIgnoreCase("")) {
                                try {
                                    unitCarbon.setText(response.body().getCo2().getUnit());
                                    String value = new BigDecimal(response.body().getCo2().getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).toString();
                                    valueCarbon.setText(value);

                                } catch (Throwable e) {
                                    e.printStackTrace();
                                }


                            }
                            if (response.body().getTree().getValue() != null && !response.body().getTree().getValue().equalsIgnoreCase("")) {
                                try {
                                    String value = new BigDecimal(response.body().getTree().getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).toString();
                                    valueTree.setText(getString(R.string.txt_x) + "" + value);
                                } catch (Throwable e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                    } else {
                        ProjectUtil.showToast(ISFApp.getAppInstance().getApplicationContext(), response.body().getResponseMessage());

                    }
                }


            }

            @Override
            public void onFailure(Call<StudentFootPrintResponse> call, Throwable t) {

                ProjectUtil.showToast(getActivity(), getResources().getString(R.string.something_went_wrong));
                t.printStackTrace();
                hideProgressDialog();
            }
        });


    }

    /*
    *
    *
    * Calling api for graph ploting
    *
    *
    * */


    private void getGraphData(String type) {
        if (!CheckNetworkState.isOnline(getActivity())) {
            ProjectUtil.showToast(getActivity(), getString(R.string.txt_network_error));
            return;
        }

        showProgressDialog(getActivity());
        Call<RetrieveDailyConsResponse> call = ISFApp.getAppInstance()
                .getApi()
                .getDailyConsumptionGraphList(AppConstants.API_KEY,
                        AppConstants.CONTENT_TYPE,
                        MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_KEY),
                        type);

        ProjectUtil.showLog(AppConstants.REQUEST, "" + call.request().url(), AppConstants.ERROR_LOG);

        call.enqueue(new Callback<RetrieveDailyConsResponse>() {
            @Override
            public void onResponse(Call<RetrieveDailyConsResponse> call, Response<RetrieveDailyConsResponse> response) {
                ProjectUtil.showLog(AppConstants.RESPONSE, "" + new Gson().toJson(response.body()), AppConstants.ERROR_LOG);
                hideProgressDialog();
                if (response.body() != null) {
                    if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.RESPONSE_CODE_SUCCUSS)) {
                        if (response.body().getStudent() != null && response.body().getStudent().size() > 0
                                && response.body().getAvg() != null && response.body().getAvg().size() > 0) {

                            if (response.body().getStudent().size() >= response.body().getAvg().size()) {
                                for (int i = 0; i < response.body().getStudent().size(); i++) {
                                    if(i==response.body().getStudent().size()-1){
                                        xVals.add(ProjectUtil.toDate(Long.parseLong(response.body().getStudent().get(i).getTs())));
                                        xVals.add(ProjectUtil.toDate(Long.parseLong(response.body().getStudent().get(i).getTs())+84600000L));

                                    }
                                    else {
                                        xVals.add(ProjectUtil.toDate(Long.parseLong(response.body().getStudent().get(i).getTs())));

                                    }
                                    Log.e("xAxisDateFromStudent", "" + ProjectUtil.toDate(Long.parseLong(response.body().getStudent().get(i).getTs())));
                                }
                            } else {
                                for (int i = 0; i < response.body().getAvg().size(); i++) {
                                    xVals.add(ProjectUtil.toDate(Long.parseLong(response.body().getAvg().get(i).getTs())));
                                    Log.e("xAxisDateFromAvg", "" + ProjectUtil.toDate(Long.parseLong(response.body().getAvg().get(i).getTs())));


                                }
                            }




                            if (response.body().getStudent() != null && response.body().getStudent().size() > 0) {
                                if (response.body().getTarget() != null) {
                                    //    float value = new BigDecimal(response.body().getTarget()).setScale(1, BigDecimal.ROUND_UP).floatValue();
                                    if (response.body().getTarget().contains(".")) {
                                        String targetValues = response.body().getTarget().substring(0, response.body().getTarget().indexOf("."));
                                        targetValue.add(new Entry(0, 0));
                                        Log.e("target", "" + targetValues);
                                        targetValue.add(new Entry(Float.parseFloat(targetValues), response.body().getStudent().size() - 1));
                                    } else {
                                        targetValue.add(new Entry(0, 0));
                                        Log.e("target", "" + response.body().getTarget());
                                        targetValue.add(new Entry(Float.parseFloat(response.body().getTarget()), response.body().getStudent().size() - 1));

                                    }

                                }
                                for (int i = 0; i < response.body().getStudent().size(); i++) {
                                    float value = new BigDecimal(response.body().getStudent().get(i).getValue()).setScale(2, BigDecimal.ROUND_DOWN).floatValue();
                                    Log.e("valuesForAvg", "" + value);
                                    yaxisValueForStudent.add(new Entry(value, i));
                                }





                            }
                            if (response.body().getAvg() != null && response.body().getAvg().size() > 0) {
                                for (int i = 0; i < response.body().getAvg().size(); i++) {
                                    float value = new BigDecimal(response.body().getAvg().get(i).getValue()).setScale(2, BigDecimal.ROUND_DOWN).floatValue();
                                    Log.e("valuesForSchool", "" + value);
                                    getYaxisValueForSchoolAvg.add(new Entry(value, i));
                                }
                            }
                            setData();

                        }
                    } else if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.ERROR_CODE_STUDENT_KEY_NOT_MATCHED)) {
                        ProjectUtil.logoutFromApp(getActivity());
                    } else {
                        ProjectUtil.showToast(ISFApp.getAppInstance().getApplicationContext(), response.body().getResponseMessage());

                    }
                }


            }

            @Override
            public void onFailure(Call<RetrieveDailyConsResponse> call, Throwable t) {
                t.printStackTrace();
                hideProgressDialog();
            }
        });


    }

    private void setData() {

        YAxis rightYAxis = mChart.getAxisRight();
        mChart.setDescription("");
        rightYAxis.setEnabled(false);

        YAxis leftYAxis = mChart.getAxisLeft();
        leftYAxis.setAxisMinValue(0);


        // mChart.getXAxis().setAxisMaxValue(aFloat);

        LineDataSet set1, set2, set3;

        // create a dataset and give it a type
        set1 = new LineDataSet(yaxisValueForStudent, "me");
        set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
       /* set1.enableDashedLine(10f, 5f, 0f);
        set1.enableDashedHighlightLine(10f, 5f, 0f);*/
        set1.setColor(Color.parseColor("#00DCCF"));
        set1.setCircleColor(Color.parseColor("#00DCCF"));
        set1.setLineWidth(1f);
        set1.setCircleRadius(3f);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(9f);
        set1.setDrawFilled(false);
        set1.setDrawCubic(true);
        set1.setDrawCircles(true);
        set1.setDrawValues(true);

        set2 = new LineDataSet(getYaxisValueForSchoolAvg, "school avg");
       /* set2.enableDashedLine(10f, 5f, 0f);
        set2.enableDashedHighlightLine(10f, 5f, 0f);*/
        set2.setColor(Color.parseColor("#BF1CD9"));
        set2.setCircleColor(Color.parseColor("#BF1CD9"));
        set2.setLineWidth(1f);
        set2.setCircleRadius(3f);
        set2.setDrawCircleHole(false);
        set2.setValueTextSize(9f);
        set2.setDrawFilled(false);
        set2.setDrawCubic(true);
        set2.setDrawCircles(true);
        set2.setDrawValues(true);


        /*set3 = new LineDataSet(targetValue, "Target");
        set3.setFillAlpha(110);
        set3.setLabel("Target");
        set3.setColor(Color.GRAY);
        set3.setCircleColor(Color.GRAY);
        set3.setLineWidth(1f);
        set3.setCircleRadius(3f);
        set3.setDrawCircleHole(false);
        set3.setValueTextSize(9f);
        set3.setDrawFilled(false);
        set3.setDrawCubic(true);
        set3.setDrawCircles(true);
        set3.setDrawValues(true);*/


        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1);
        dataSets.add(set2);
        //dataSets.add(set3);
        // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);
      /*  data.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
               DecimalFormat mFormat = new DecimalFormat("###,###,##0.0"); // use one decimal
                return mFormat.format(value);
            }
        });*/

        // set data
        mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        mChart.fitScreen();
        mChart.setTouchEnabled(true);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setScaleXEnabled(true);
        mChart.setScaleYEnabled(true);
        mChart.setPinchZoom(true);
        mChart.setDoubleTapToZoomEnabled(false);
      //  mChart.setVisibleXRange(10,100);
        mChart.getXAxis().setLabelsToSkip(1);
        mChart.setData(data);
        mChart.invalidate();



    }


    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).backBtn.setVisibility(View.VISIBLE);
        ((HomeActivity) getActivity()).sliderIcon.setVisibility(View.GONE);
    }

}
