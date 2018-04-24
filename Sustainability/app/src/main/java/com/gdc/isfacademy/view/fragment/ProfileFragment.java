package com.gdc.isfacademy.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.utils.AppConstants;
import com.gdc.isfacademy.utils.MyPref;
import com.gdc.isfacademy.view.adapter.ProfileAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;



public class ProfileFragment extends BaseFragment {
    public static final String TAG = "ProfileFragment";

    private XRecyclerView recycler_view;
    private android.content.Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }
    public static ProfileFragment newInstance() {
        ProfileFragment profileFragment = new ProfileFragment();
        return profileFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, null);
        initView(view);
        return view;
    }

    private void initView(View view) {

        recycler_view = (XRecyclerView) view.findViewById(R.id.recycler_profile);
        recycler_view.setLoadingMoreEnabled(false);
        recycler_view.setPullRefreshEnabled(false);
        View voucherHeader = LayoutInflater.from(getActivity()).inflate(R.layout.profile_header,
                (ViewGroup) view.findViewById(android.R.id.content), false);
        recycler_view.addHeaderView(voucherHeader);
        AppCompatTextView studentHouse=(AppCompatTextView)voucherHeader.findViewById(R.id.studentHouse);
        AppCompatTextView studentname=(AppCompatTextView)voucherHeader.findViewById(R.id.studentName);
        studentname.setText(MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_NAME));
        studentHouse.setText(MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_HOUSE));
        ProfileAdapter adapter = new ProfileAdapter(context);
        recycler_view.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayout.VERTICAL,false);
        recycler_view.setLayoutManager(manager);
    }
}