package com.gdc.isfacademy.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.model.FriendsBeanList;
import com.gdc.isfacademy.utils.ProjectUtil;
import com.gdc.isfacademy.view.adapter.FriendListAdapter;
import com.gdc.isfacademy.view.customs.customfonts.OpenSansLightEditText;

import java.util.ArrayList;


@SuppressWarnings("ALL")
public class AddFriendFragment extends BaseFragment implements FriendListAdapter.OnItemClick {
    public static final String TAG = "AddFriendFragment";
    ArrayList<FriendsBeanList> friendsBeanLists;
    OpenSansLightEditText search_edit_text;
    Context context;
    FriendListAdapter adapter;
    private RecyclerView recycler_view;

    public static AddFriendFragment newInstance() {
        AddFriendFragment addFriendFragment = new AddFriendFragment();
        return addFriendFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.add_friend_fragment, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        friendsBeanLists = new ArrayList<>();
        friendsBeanLists = getList();
        search_edit_text = (OpenSansLightEditText) view.findViewById(R.id.search_edit_text);
        recycler_view = (RecyclerView) view.findViewById(R.id.recycler_view);
        adapter = new FriendListAdapter(context, friendsBeanLists);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        recycler_view.setAdapter(adapter);
        recycler_view.setLayoutManager(manager);
        adapter.setOnItemClick(this);
        final Editable[] filterChar = new Editable[1];

        search_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
                filterChar[0] = s;
                adapter.getFilter().filter(s);
                adapter.notifyDataSetChanged();
            }
        });

    }


    /*
    *
    * Dummy List
    * */
    public ArrayList<FriendsBeanList> getList() {

        friendsBeanLists.add(new FriendsBeanList("Isabelle"));
        friendsBeanLists.add(new FriendsBeanList("Sebrina"));
        friendsBeanLists.add(new FriendsBeanList("Kelsie"));
        friendsBeanLists.add(new FriendsBeanList("Sean"));
        friendsBeanLists.add(new FriendsBeanList("Eliz"));
        friendsBeanLists.add(new FriendsBeanList("Jose"));
        friendsBeanLists.add(new FriendsBeanList("Michal"));
        friendsBeanLists.add(new FriendsBeanList("Rimmy"));
        friendsBeanLists.add(new FriendsBeanList("John"));
        friendsBeanLists.add(new FriendsBeanList("Rogerr"));
        friendsBeanLists.add(new FriendsBeanList("Nathan"));
        friendsBeanLists.add(new FriendsBeanList("Kangiso"));
        friendsBeanLists.add(new FriendsBeanList("Dale"));


        return friendsBeanLists;
    }

    @Override
    public void onClick(int id, int pos) {
        switch (id) {
            case R.id.action_tv:
                if(adapter.getData().get(pos).isRequestSend()){
                    adapter.getData().get(pos).setRequestSend(false);
                }
                else {
                    adapter.getData().get(pos).setRequestSend(true);

                }
                ProjectUtil.showToast(getActivity(), adapter.getData().get(pos).getName());
                break;
        }
    }
}
