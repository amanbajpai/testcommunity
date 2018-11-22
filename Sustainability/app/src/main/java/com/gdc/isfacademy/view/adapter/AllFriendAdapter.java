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
import com.gdc.isfacademy.model.TotalFriendResponse;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

public class AllFriendAdapter extends RecyclerView.Adapter<AllFriendAdapter.Holder> {

    final Context context;
    private ArrayList<TotalFriendResponse> friendsBeanLists;
    private XRecyclerView xRecyclerView;
    private OnAllFriendItemClickListner onAllFriendItemClickListner;


    public AllFriendAdapter(Context context, ArrayList<TotalFriendResponse> friendsBeanLists,XRecyclerView xRecyclerView) {
        this.context = context;
        this.friendsBeanLists = friendsBeanLists;
        this.xRecyclerView=xRecyclerView;

    }
    public interface  OnAllFriendItemClickListner{
        void onAllFriendItemClick(int id,int postion);
    }

    public void setOnAllFriendItemClickListner(OnAllFriendItemClickListner onAllFriendItemClickListner) {
        this.onAllFriendItemClickListner = onAllFriendItemClickListner;
    }

    public void setFriendsBeanLists(ArrayList<TotalFriendResponse> friendsBeanLists, XRecyclerView xRecyclerView) {
        this.friendsBeanLists = friendsBeanLists;
        this.xRecyclerView=xRecyclerView;
        notifyDataSetChanged();
    }

    @Override
    public AllFriendAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.all_friend_item, null);
//        View view1 =LayoutInflater.from(context).inflate(R.layout.add_friend_item,null);
        return new Holder(view);
    }

    public ArrayList<TotalFriendResponse> getData() {
        return friendsBeanLists;
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(AllFriendAdapter.Holder holder, final int position) {
        holder.profile_name.setText(friendsBeanLists.get(position).getStudentName());
        holder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAllFriendItemClickListner.onAllFriendItemClick(v.getId(),position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return friendsBeanLists.size();
    }

    /*public interface OnItemClick {
        void onClick(int id, int pos);
    }*/
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
    class Holder extends RecyclerView.ViewHolder {

        final CircleImageView profile_image;
        final AppCompatTextView accept_tv;
        final AppCompatTextView profile_name,removeBtn;

         Holder(View itemView) {
            super(itemView);
            profile_image = (CircleImageView) itemView.findViewById(R.id.profile_image);
            accept_tv = (AppCompatTextView) itemView.findViewById(R.id.accept_tv);
            profile_name = (AppCompatTextView) itemView.findViewById(R.id.profile_name);
             removeBtn=(AppCompatTextView)itemView.findViewById(R.id.removeTv);
        }
    }

}

