package com.gdc.isfacademy.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.application.ISFApp;
import com.gdc.isfacademy.model.ChallangeRankList;
import com.gdc.isfacademy.model.EnergySavingResponse;
import com.gdc.isfacademy.model.RankingParentResponse;
import com.gdc.isfacademy.model.StudentFootPrintResponse;
import com.gdc.isfacademy.netcom.CheckNetworkState;
import com.gdc.isfacademy.utils.AppConstants;
import com.gdc.isfacademy.utils.MyPref;
import com.gdc.isfacademy.utils.ProjectUtil;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HowMuchSaveFragment extends BaseFragment {

    public static final String TAG = "HowMuchSaveFragment";
    AppCompatTextView currentWeekConsumption, energyUnit, valueCarbon, unitCarbon, valueTree;
    EnergySavingResponse.CurrentCons currentCons;


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
        View view = inflater.inflate(R.layout.how_much_saved_layout, null);
        init(view);
        Bundle bundle = getArguments();
        currentCons = (EnergySavingResponse.CurrentCons) bundle.getSerializable(AppConstants.CURRENT_ENERGY_UNIT);

        if (currentCons != null) {
            Log.d("conumption", "" + currentCons.getValue() + " " + currentCons.getUnit());
            currentWeekConsumption.setText(String.format("%.2f", currentCons.getValue()));
            energyUnit.setText(currentCons.getUnit());
        }
        getStudentFootPrint();

        return view;
    }

    public void init(View view) {
        currentWeekConsumption = (AppCompatTextView) view.findViewById(R.id.currentWeekConsumption);
        energyUnit = (AppCompatTextView) view.findViewById(R.id.energyUnit);
        valueCarbon = (AppCompatTextView) view.findViewById(R.id.value_carbon);
        unitCarbon = (AppCompatTextView) view.findViewById(R.id.unitCarbon);
        valueTree = (AppCompatTextView) view.findViewById(R.id.valueTree);
    }


    private void getStudentFootPrint() {
        if (!CheckNetworkState.isOnline(getActivity())) {
            ProjectUtil.showToast(getActivity(), getString(R.string.txt_network_error));
            return;
        }

        showProgressDialog(getActivity());
        Call<StudentFootPrintResponse> call = ISFApp.getAppInstance()
                .getApi()
                .getStudentFootPrint(AppConstants.API_KEY,
                        AppConstants.CONTENT_TYPE,
                        MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_KEY));

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
                                unitCarbon.setText(response.body().getCo2().getUnit());
                                String value = new BigDecimal(response.body().getCo2().getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).toString();
                                valueCarbon.setText(value);


                            }
                            if (response.body().getTree().getValue() != null && !response.body().getTree().getValue().equalsIgnoreCase("")) {
                                String value = new BigDecimal(response.body().getTree().getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).toString();
                                valueTree.setText("x" + value);
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


}
