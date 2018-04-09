package com.energybox.isf.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.energybox.isf.R;
import com.energybox.isf.model.RewardListResponse;

import java.util.ArrayList;

/**
 * Created by ashishthakur on 5/4/18.
 */

public class RewardsAdapter extends RecyclerView.Adapter<RewardsAdapter.Holder> {

    Context context;
    ArrayList<RewardListResponse> rewardListResponses;
    OnRewardItemClickListner onRewardItemClickListner;

    public RewardsAdapter(Context context) {
        this.context = context;
    }

    public RewardsAdapter(Context context, ArrayList<RewardListResponse> rewardListResponses) {
        this.context = context;
        this.rewardListResponses = rewardListResponses;
    }

    public void setOnRewardItemClickListner(OnRewardItemClickListner onRewardItemClickListner) {
        this.onRewardItemClickListner = onRewardItemClickListner;
    }

    @Override
    public RewardsAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_items_rewards_adapter, null);
        RewardsAdapter.Holder holder = new RewardsAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RewardsAdapter.Holder holder, final int position) {
        holder.rewards_relative_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRewardItemClickListner.onClick(v.getId(),position);
            }
        });

        if(rewardListResponses.get(position).isItemOpen()){
            holder.qr_code_relative_layout.setVisibility(View.VISIBLE);

        }
        else {
            holder.qr_code_relative_layout.setVisibility(View.GONE);

        }
    }

    @Override
    public int getItemCount() {
        return rewardListResponses.size();
    }

    public interface OnRewardItemClickListner {
        public void onClick(int id, int pos);
    }

    class Holder extends RecyclerView.ViewHolder {
        RelativeLayout rewards_relative_layout, qr_code_relative_layout;

        public Holder(View itemView) {
            super(itemView);
            rewards_relative_layout = (RelativeLayout) itemView.findViewById(R.id.rewards_relative_layout);
            qr_code_relative_layout = (RelativeLayout) itemView.findViewById(R.id.qr_code_relative_layout);
        }
    }
}
