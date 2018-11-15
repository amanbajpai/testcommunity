package com.gdc.isfacademy.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gdc.isfacademy.R;
import com.gdc.isfacademy.model.FriendsBeanList;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


@SuppressWarnings("ALL")
public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.Holder> {

    final Context context;
    final ArrayList<FriendsBeanList> fillterdFriendsBeanLists;
    ArrayList<FriendsBeanList> friendsBeanLists;
    ArrayList<FriendsBeanList> filterList;
    XRecyclerView xRecyclerView;


    public FriendListAdapter(Context context, ArrayList<FriendsBeanList> friendsBeanLists) {
        this.context = context;
        this.friendsBeanLists = friendsBeanLists;
        fillterdFriendsBeanLists = friendsBeanLists;
    }

    public void setList(Context context,final ArrayList<FriendsBeanList> friendsBeanLists, final XRecyclerView xRecyclerView) {
        this.friendsBeanLists = friendsBeanLists;
        this.xRecyclerView = xRecyclerView;
        notifyDataSetChanged();


    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.add_friend_item, null);
//        View view1 =LayoutInflater.from(context).inflate(R.layout.add_friend_item,null);
        Holder holder = new Holder(view);
        return holder;
    }

    public ArrayList<FriendsBeanList> getData() {
        return friendsBeanLists;
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        holder.profile_name.setText(friendsBeanLists.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return friendsBeanLists.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }



    class Holder extends RecyclerView.ViewHolder {

        final CircleImageView profile_image;
        final AppCompatTextView accept_tv;
        final AppCompatTextView profile_name;

        public Holder(View itemView) {
            super(itemView);
            profile_image = (CircleImageView) itemView.findViewById(R.id.profile_image);
            accept_tv = (AppCompatTextView) itemView.findViewById(R.id.accept_tv);
            profile_name = (AppCompatTextView) itemView.findViewById(R.id.profile_name);
        }
    }

}
