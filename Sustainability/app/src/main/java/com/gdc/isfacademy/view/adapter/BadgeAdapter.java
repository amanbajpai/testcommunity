package com.gdc.isfacademy.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.model.BadgeStudentResponse;
import com.gdc.isfacademy.utils.AppConstants;

import java.util.List;

/**
 * Created by ashishthakur on 15/8/18.
 */

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
        this.badgeStudentResponses = rewardListResponses;
        notifyDataSetChanged();
    }


    @Override
    public BadgeAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_items_badge, null);
        BadgeAdapter.Holder holder = new BadgeAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BadgeAdapter.Holder holder, final int position) {

        if (badgeStudentResponses.get(position).getBadgesType().equalsIgnoreCase(AppConstants.ID_ENERGY_SAVING)) {
            if (badgeStudentResponses.get(position).getValue() != null) {
                float value = Float.parseFloat(badgeStudentResponses.get(position).getValue());
                if (value < AppConstants.VALUE_ENERGY_SAVING_BRNOZE) {
                    holder.iv_badge.setVisibility(View.GONE);
                    isAnyBadgeAllot = false;
                } else if (value >= AppConstants.VALUE_ENERGY_SAVING_BRNOZE && value < AppConstants.VALUE_ENERGY_SAVING_SILVER) {
                    holder.iv_badge.setImageResource(R.drawable.energy_saving_bronze_badge);
                    isAnyBadgeAllot = true;

                } else if (value >= AppConstants.VALUE_ENERGY_SAVING_SILVER && value < AppConstants.VALUE_ENERGY_SAVING_GOLD) {
                    holder.iv_badge.setImageResource(R.drawable.energy_saving_silver_badge);
                    isAnyBadgeAllot = true;

                } else if (value >= AppConstants.VALUE_ENERGY_SAVING_GOLD && value < AppConstants.VALUE_ENERGY_SAVING_HIDDEN) {
                    holder.iv_badge.setImageResource(R.drawable.energy_saving_gold_badge);
                    isAnyBadgeAllot = true;

                } else if (value >= AppConstants.VALUE_ENERGY_SAVING_HIDDEN) {
                    holder.iv_badge.setImageResource(R.drawable.energy_saving_hidden_badge);
                    isAnyBadgeAllot = true;

                }
            }


        } else if (badgeStudentResponses.get(position).getBadgesType().equalsIgnoreCase(AppConstants.ID_QUIZHOLIC)) {
            if (badgeStudentResponses.get(position).getValue() != null) {
                float value = Float.parseFloat(badgeStudentResponses.get(position).getValue());
                if (value < AppConstants.VALUE_QUIZHOLIC_BRONZE) {
                    holder.iv_badge.setVisibility(View.GONE);
                    isAnyBadgeAllot = false;

                } else if (value >= AppConstants.VALUE_QUIZHOLIC_BRONZE && value < AppConstants.VALUE_QUIZHOLIC_SILVER) {
                    holder.iv_badge.setImageResource(R.drawable.quiz_holic_bronze_badge);
                    isAnyBadgeAllot = true;

                } else if (value >= AppConstants.VALUE_QUIZHOLIC_SILVER && value < AppConstants.VALUE_QUIZHOLIC_GOLDE) {
                    holder.iv_badge.setImageResource(R.drawable.quiz_holic_silver_badge);
                    isAnyBadgeAllot = true;

                } else if (value >= AppConstants.VALUE_QUIZHOLIC_GOLDE && value < AppConstants.VALUE_QUIZHOLIC_HIDDEN) {
                    holder.iv_badge.setImageResource(R.drawable.quiz_holic_gold_badge);
                    isAnyBadgeAllot = true;

                } else if (value >= AppConstants.VALUE_QUIZHOLIC_HIDDEN) {
                    holder.iv_badge.setImageResource(R.drawable.quiz_holic_hidden_badge);
                    isAnyBadgeAllot = true;

                }
            }


        } else if (badgeStudentResponses.get(position).getBadgesType().equalsIgnoreCase(AppConstants.ID_PERSISTANT)) {
            float value = Float.parseFloat(badgeStudentResponses.get(position).getValue());
            if (value < AppConstants.VALUE_PERSISTANT_BRONZE) {
                holder.iv_badge.setVisibility(View.GONE);
                isAnyBadgeAllot = false;

            } else if (value >= AppConstants.VALUE_PERSISTANT_BRONZE && value < AppConstants.VALUE_PERSISTANT_SILVER) {
                holder.iv_badge.setImageResource(R.drawable.persistent_bronze_badge);
                isAnyBadgeAllot = true;

            } else if (value >= AppConstants.VALUE_PERSISTANT_SILVER && value < AppConstants.VALUE_PERSISTANT_GOLD) {
                holder.iv_badge.setImageResource(R.drawable.persistent_silver_badge);
                isAnyBadgeAllot = true;

            } else if (value >= AppConstants.VALUE_PERSISTANT_GOLD && value < AppConstants.VALUE_PERSISTANT_HIDDEN) {
                holder.iv_badge.setImageResource(R.drawable.persitent_gold_badge);
                isAnyBadgeAllot = true;

            } else if (value >= AppConstants.VALUE_PERSISTANT_HIDDEN) {
                holder.iv_badge.setImageResource(R.drawable.persistent_hidden_badge);
                isAnyBadgeAllot = true;

            }

        } else if (badgeStudentResponses.get(position).getBadgesType().equalsIgnoreCase(AppConstants.ID_PERFECTION)) {
            float value = Float.parseFloat(badgeStudentResponses.get(position).getValue());
            if (value < AppConstants.VALUE_PERFECTION_BRONZE) {
                holder.iv_badge.setVisibility(View.GONE);
                isAnyBadgeAllot = false;

            } else if (value >= AppConstants.VALUE_PERFECTION_BRONZE && value < AppConstants.VALUE_PERFECTION_SILVER) {
                holder.iv_badge.setImageResource(R.drawable.perfection_bronze_badge);
                isAnyBadgeAllot = true;

            } else if (value >= AppConstants.VALUE_PERFECTION_SILVER && value < AppConstants.VALUE_PERFECTION_GOLD) {
                holder.iv_badge.setImageResource(R.drawable.perfection_silver_badge);
                isAnyBadgeAllot = true;

            } else if (value >= AppConstants.VALUE_PERFECTION_GOLD && value < AppConstants.VALUE_PERFECTION_HIDDEN) {
                holder.iv_badge.setImageResource(R.drawable.perfection_gold_badge);
                isAnyBadgeAllot = true;

            } else if (value >= AppConstants.VALUE_PERFECTION_HIDDEN) {
                holder.iv_badge.setImageResource(R.drawable.perfection_hidden_badge);
                isAnyBadgeAllot = true;

            }

        } else if (badgeStudentResponses.get(position).getBadgesType().equalsIgnoreCase(AppConstants.ID_BROADCASTER)) {
            float value = Float.parseFloat(badgeStudentResponses.get(position).getValue());
            if (value < AppConstants.VALUE_BROADCASTER_BRONZE) {
                holder.iv_badge.setVisibility(View.GONE);
                isAnyBadgeAllot = false;

            } else if (value >= AppConstants.VALUE_BROADCASTER_BRONZE && value < AppConstants.VALUE_BROADCASTER_SILVER) {
                holder.iv_badge.setImageResource(R.drawable.broadcaster_bronze_badge);
                isAnyBadgeAllot = true;

            } else if (value >= AppConstants.VALUE_BROADCASTER_SILVER && value < AppConstants.VALUE_BROADCASTER_GOLD) {
                holder.iv_badge.setImageResource(R.drawable.broadcaster_silver_badge);
                isAnyBadgeAllot = true;

            } else if (value >= AppConstants.VALUE_BROADCASTER_GOLD && value < AppConstants.VALUE_BROADCASTER_HIDDEN) {
                holder.iv_badge.setImageResource(R.drawable.brodcaster_gold_badge);
                isAnyBadgeAllot = true;

            } else if (value >= AppConstants.VALUE_BROADCASTER_HIDDEN) {
                holder.iv_badge.setImageResource(R.drawable.broadcaster_hidden_badge);
                isAnyBadgeAllot = true;

            }
        } else if (badgeStudentResponses.get(position).getBadgesType().equalsIgnoreCase(AppConstants.ID_FRIENDLY)) {
            float value = Float.parseFloat(badgeStudentResponses.get(position).getValue());
            if (value < AppConstants.VALUE_FRIENDLY_BRONZE) {
                holder.iv_badge.setVisibility(View.GONE);
                isAnyBadgeAllot = false;

            } else if (value >= AppConstants.VALUE_FRIENDLY_BRONZE && value < AppConstants.VALUE_FRIENDLY_SILVER) {
                holder.iv_badge.setImageResource(R.drawable.friendly_bronze_badge);
                isAnyBadgeAllot = true;

            } else if (value >= AppConstants.VALUE_FRIENDLY_SILVER && value < AppConstants.VALUE_FRIENDLY_GOLD) {
                holder.iv_badge.setImageResource(R.drawable.friendly_silver_badge);
                isAnyBadgeAllot = true;

            } else if (value >= AppConstants.VALUE_FRIENDLY_GOLD && value < AppConstants.VALUE_FRIENDLY_HIDDEN) {
                holder.iv_badge.setImageResource(R.drawable.friendly_gold_badge);
                isAnyBadgeAllot = true;

            } else if (value >= AppConstants.VALUE_FRIENDLY_HIDDEN) {
                holder.iv_badge.setImageResource(R.drawable.friendly_hidden_badge);
                isAnyBadgeAllot = true;

            }
        } else if (badgeStudentResponses.get(position).getBadgesType().equalsIgnoreCase(AppConstants.ID_LEGENDARY)) {
            float value = Float.parseFloat(badgeStudentResponses.get(position).getValue());
            if (value < AppConstants.VALUE_LEGENDARY_BRONZE) {
                holder.iv_badge.setVisibility(View.GONE);
                isAnyBadgeAllot = false;

            } else if (value >= AppConstants.VALUE_LEGENDARY_BRONZE && value < AppConstants.VALUE_LEGENDARY_SILVER) {
                holder.iv_badge.setImageResource(R.drawable.legendary_bronze_badge);
                isAnyBadgeAllot = true;

            } else if (value >= AppConstants.VALUE_LEGENDARY_SILVER && value < AppConstants.VALUE_LEGENDARY_GOLD) {
                holder.iv_badge.setImageResource(R.drawable.legendary_silver_badge);
                isAnyBadgeAllot = true;

            } else if (value >= AppConstants.VALUE_LEGENDARY_GOLD && value < AppConstants.VALUE_LEGENDARY_HIDDEN) {
                holder.iv_badge.setImageResource(R.drawable.legendary_gold_badge);
                isAnyBadgeAllot = true;

            } else if (value >= AppConstants.VALUE_LEGENDARY_HIDDEN) {
                holder.iv_badge.setImageResource(R.drawable.legendary_hidden_badge);
                isAnyBadgeAllot = true;

            }
        } else if (badgeStudentResponses.get(position).getBadgesType().equalsIgnoreCase(AppConstants.ID_SUPERIOR_SPECLIST)) {
            float value = Float.parseFloat(badgeStudentResponses.get(position).getValue());
            if (value < AppConstants.VALUE_SUPERIOR_SPECLIST_BRONZE) {
                holder.iv_badge.setVisibility(View.GONE);
                isAnyBadgeAllot = false;

            } else if (value >= AppConstants.VALUE_SUPERIOR_SPECLIST_BRONZE && value < AppConstants.VALUE_SUPERIOR_SPECLIST_SILVER) {
                holder.iv_badge.setImageResource(R.drawable.superior_brnoze_badge);
                isAnyBadgeAllot = true;

            } else if (value >= AppConstants.VALUE_SUPERIOR_SPECLIST_SILVER && value < AppConstants.VALUE_SUPERIOR_SPECLIST_GOLD) {
                holder.iv_badge.setImageResource(R.drawable.superior_silver_badge);
                isAnyBadgeAllot = true;

            } else if (value >= AppConstants.VALUE_SUPERIOR_SPECLIST_GOLD && value < AppConstants.VALUE_SUPERIOR_SPECLIST_HIDDEN) {
                holder.iv_badge.setImageResource(R.drawable.superior_gold_badge);
                isAnyBadgeAllot = true;

            } else if (value >= AppConstants.VALUE_SUPERIOR_SPECLIST_HIDDEN) {
                holder.iv_badge.setImageResource(R.drawable.superior_hidden_badge);
                isAnyBadgeAllot = true;

            }
        } else if (badgeStudentResponses.get(position).getBadgesType().equalsIgnoreCase(AppConstants.ID_CHAMPION)) {
            float value = Float.parseFloat(badgeStudentResponses.get(position).getValue());
            if (value < AppConstants.VALUE_CHAMPION_BRONZE) {
                holder.iv_badge.setVisibility(View.GONE);
                isAnyBadgeAllot = false;

            } else if (value >= AppConstants.VALUE_CHAMPION_BRONZE && value < AppConstants.VALUE_CHAMPION_SILVER) {
                holder.iv_badge.setImageResource(R.drawable.champion_bronze_badge);
                isAnyBadgeAllot = true;

            } else if (value >= AppConstants.VALUE_CHAMPION_SILVER && value < AppConstants.VALUE_CHAMPION_GOLD) {
                holder.iv_badge.setImageResource(R.drawable.champion_silver_badge);
                isAnyBadgeAllot = true;

            } else if (value >= AppConstants.VALUE_CHAMPION_GOLD && value < AppConstants.VALUE_CHAMPION_HIDDEN) {
                holder.iv_badge.setImageResource(R.drawable.champion_gold_badge);
                isAnyBadgeAllot = true;

            } else if (value >= AppConstants.VALUE_CHAMPION_HIDDEN) {
                holder.iv_badge.setImageResource(R.drawable.champion_hidden_badge);
                isAnyBadgeAllot = true;

            }
        }
        else if (badgeStudentResponses.get(position).getBadgesType().equalsIgnoreCase("10")) {
            holder.iv_badge.setVisibility(View.GONE);
            isAnyBadgeAllot = false;


        }

    }

    @Override
    public int getItemCount() {
        return badgeStudentResponses.size();
    }

    public boolean isBadgeAlloted() {
        return isAnyBadgeAllot;
    }

    class Holder extends RecyclerView.ViewHolder {
        public ImageView iv_badge;

        public Holder(View itemView) {
            super(itemView);
            iv_badge = (ImageView) itemView.findViewById(R.id.iv_badge);
        }
    }
}