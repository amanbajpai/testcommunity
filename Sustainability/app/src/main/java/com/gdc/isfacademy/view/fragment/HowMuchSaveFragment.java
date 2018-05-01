package com.gdc.isfacademy.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.model.EnergySavingResponse;
import com.gdc.isfacademy.utils.AppConstants;

import java.util.Locale;

public class HowMuchSaveFragment extends BaseFragment {

    public static final String TAG = "HowMuchSaveFragment";
    AppCompatTextView currentWeekConsumption, energyUnit;
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
        Bundle bundle = getArguments();
        currentCons= (EnergySavingResponse.CurrentCons) bundle.getSerializable(AppConstants.CURRENT_ENERGY_UNIT);

        if(currentCons!=null){
            Log.d("conumption", "" + currentCons.getValue() + " " + currentCons.getUnit());
            currentWeekConsumption = (AppCompatTextView) view.findViewById(R.id.currentWeekConsumption);
            energyUnit = (AppCompatTextView) view.findViewById(R.id.energyUnit);
            currentWeekConsumption.setText(String.format("%.2f",currentCons.getValue()));
            energyUnit.setText(currentCons.getUnit());
        }

        return view;
    }

}
