package com.energybox.isf.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.energybox.isf.R;
import com.energybox.isf.view.adapter.FriendListAdapter;

/**
 * Created by akshaydashore on 3/4/18
 */
public class AddFriendFragment extends BaseFragment {
    public static final String TAG = "AddFriendFragment";


    private RecyclerView recycler_view;
    Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }
    public static AddFriendFragment newInstance() {
        AddFriendFragment addFriendFragment = new AddFriendFragment();
        return addFriendFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_friend_fragment, null);
        initView(view);
        return view;
    }

    private void initView(View view) {

        recycler_view = (RecyclerView) view.findViewById(R.id.recycler_view);
        FriendListAdapter adapter = new FriendListAdapter(context);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        recycler_view.setAdapter(adapter);

        recycler_view.setLayoutManager(manager);

    }

}
