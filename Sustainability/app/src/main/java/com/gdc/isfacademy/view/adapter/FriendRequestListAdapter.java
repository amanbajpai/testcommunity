package com.gdc.isfacademy.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.model.FriendRequestResponse;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


@SuppressWarnings("ALL")
public class FriendRequestListAdapter extends RecyclerView.Adapter<FriendRequestListAdapter.Holder> {

    final Context context;
    public OnFriendRequestListItemClick onFriendRequestListItemClick;
    ArrayList<FriendRequestResponse> friendsBeanLists;
    XRecyclerView xRecyclerView;


    public FriendRequestListAdapter(Context context, ArrayList<FriendRequestResponse> friendsBeanLists, final XRecyclerView xRecyclerView) {
        this.context = context;
        this.friendsBeanLists = friendsBeanLists;
        this.xRecyclerView = xRecyclerView;

    }

    public void setOnFriendRequestListItemClick(OnFriendRequestListItemClick onFriendRequestListItemClick) {
        this.onFriendRequestListItemClick = onFriendRequestListItemClick;
    }

    public void setList(Context context, final ArrayList<FriendRequestResponse> friendsBeanLists, final XRecyclerView xRecyclerView) {
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

    public ArrayList<FriendRequestResponse> getData() {
        return friendsBeanLists;
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        holder.profile_name.setText(friendsBeanLists.get(position).getStudentName());
        holder.declinedRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFriendRequestListItemClick.onItemClick(v.getId(), position);
            }
        });

        holder.acceptRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFriendRequestListItemClick.onItemClick(v.getId(), position);
            }
        });

    }

    public void removeItemAtPostion(int postion) {
        if (friendsBeanLists.size() == 1) {
            xRecyclerView.removeAllHeaderView();
            friendsBeanLists.remove(postion);
            notifyDataSetChanged();
        } else {
            friendsBeanLists.remove(postion);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return friendsBeanLists.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public interface OnFriendRequestListItemClick {
        void onItemClick(int id, int postion);

    }

    class Holder extends RecyclerView.ViewHolder {

        final CircleImageView profile_image;
        final AppCompatTextView accept_tv;
        final AppCompatTextView profile_name, declinedRequestBtn, acceptRequestBtn;

        public Holder(View itemView) {
            super(itemView);
            profile_image = (CircleImageView) itemView.findViewById(R.id.profile_image);
            accept_tv = (AppCompatTextView) itemView.findViewById(R.id.accept_tv);
            profile_name = (AppCompatTextView) itemView.findViewById(R.id.profile_name);
            acceptRequestBtn = (AppCompatTextView) itemView.findViewById(R.id.accept_tv);
            declinedRequestBtn = (AppCompatTextView) itemView.findViewById(R.id.declined);
        }
    }

}
