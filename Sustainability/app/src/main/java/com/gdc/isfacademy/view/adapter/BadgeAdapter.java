package com.gdc.isfacademy.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.model.BadgeStudentResponse;
import com.gdc.isfacademy.utils.AppConstants;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@SuppressWarnings("ALL")
public class BadgeAdapter extends RecyclerView.Adapter<BadgeAdapter.Holder> {

    public boolean isAnyBadgeAllot = false;
    Context context;
    List<BadgeStudentResponse> badgeStudentResponses;

    public BadgeAdapter(Context context) {
        this.context = context;
    }

    public BadgeAdapter(Context context, List<BadgeStudentResponse> rewardListResponses) {
        this.context = context;
        this.badgeStudentResponses = rewardListResponses;
    }

    public void setList(Context context, List<BadgeStudentResponse> rewardListResponses) {
        this.context = context;
        Collections.sort(rewardListResponses, new Comparator<BadgeStudentResponse>() {
            @Override
            public int compare(BadgeStudentResponse s1, BadgeStudentResponse s2) {
                return s1.getBadgeAllotedFor().compareTo(s2.getBadgeAllotedFor());
            }
        });
        this.badgeStudentResponses = rewardListResponses;
        notifyDataSetChanged();
    }


    @Override
    public BadgeAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.list_items_badge, null);
        BadgeAdapter.Holder holder = new BadgeAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BadgeAdapter.Holder holder, final int position) {

        /*
        *
        *
        * Here we check all  badge type with @AppConstants.ID_ENERGY_SAVING,@AppConstants.ID_QUIZHOLIC
        * @AppConstants.ID_PERSISTANT,@AppConstants.ID_PERFECTION,@AppConstants.ID_BROADCASTER,@AppConstants.ID_FRIENDLY,
        * @AppConstants.ID_PERFECTION,@AppConstants.ID_LEGENDARY,@AppConstants.ID_SUPERIOR_SPECLIST,@AppConstants.ID_CHAMPION.
        * according to that we allote badge to the stduents.
        *
        * */


        if(badgeStudentResponses.get(position).getBadgeAllotedFor().equalsIgnoreCase(AppConstants.HIDDEN)||
                badgeStudentResponses.get(position).getBadgeAllotedFor().equalsIgnoreCase(AppConstants.GOLD)||
                badgeStudentResponses.get(position).getBadgeAllotedFor().equalsIgnoreCase(AppConstants.SILVER)||
                badgeStudentResponses.get(position).getBadgeAllotedFor().equalsIgnoreCase(AppConstants.BRONZE)){
            isAnyBadgeAllot=true;
        }

        if(!badgeStudentResponses.get(position).getBadgeAllotedFor().equalsIgnoreCase(AppConstants.LOCKED)){
            holder.iv_badge.setImageResource(badgeStudentResponses.get(position).getResourceIdImage());
        }



    }

    @Override
    public int getItemCount() {
        if(badgeStudentResponses.size()<=5){
            return badgeStudentResponses.size();

        }
        else {
            return 5;
        }
    }

    public boolean isBadgeAlloted() {
        return isAnyBadgeAllot;
    }

    class Holder extends RecyclerView.ViewHolder {
        public final ImageView iv_badge;

        public Holder(View itemView) {
            super(itemView);
            iv_badge = (ImageView) itemView.findViewById(R.id.iv_badge);
        }
    }
}