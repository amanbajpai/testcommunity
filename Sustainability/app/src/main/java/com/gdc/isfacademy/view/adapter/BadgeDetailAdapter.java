package com.gdc.isfacademy.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.model.BadgeStudentResponse;
import com.gdc.isfacademy.utils.AppConstants;
import com.gdc.isfacademy.utils.ProjectUtil;

import java.math.BigDecimal;
import java.util.List;



@SuppressWarnings("ALL")
public class BadgeDetailAdapter extends RecyclerView.Adapter<BadgeDetailAdapter.Holder> {

    Context context;
    List<BadgeStudentResponse> badgeStudentResponses;
    BadgeDetailAdapter.OnRewardItemClickListner onRewardItemClickListner;

    public BadgeDetailAdapter(Context context) {
        this.context = context;
    }

    public BadgeDetailAdapter(Context context, List<BadgeStudentResponse> badgeStudentResponses) {
        this.context = context;
        this.badgeStudentResponses = badgeStudentResponses;
    }

  /*  public void setList(Context context, List<RewardStudentResponse> rewardListResponses) {
        this.context = context;
        this.rewardListResponses = rewardListResponses;
        notifyDataSetChanged();
    }*/


    @Override
    public BadgeDetailAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.list_items_badge_detail, null);
        BadgeDetailAdapter.Holder holder = new BadgeDetailAdapter.Holder(view);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BadgeDetailAdapter.Holder holder, final int position) {
        holder.seekbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        if (badgeStudentResponses.get(position).getBadgesType().equalsIgnoreCase(AppConstants.ID_ENERGY_SAVING)) {
            holder.titleBadge.setText(context.getString(R.string.title_badge_energy_saving));
            holder.discriptionBadge.setText(context.getString(R.string.txt_badge_energy_saving_description));
            holder.hiddenBadgeView.setVisibility(View.GONE);
            holder.seekbar.setVisibility(View.VISIBLE);
            if (badgeStudentResponses.get(position).getValue() != null) {
                float value = Float.parseFloat(badgeStudentResponses.get(position).getValue());
                if (value < AppConstants.VALUE_ENERGY_SAVING_BRNOZE) {
                    holder.iv_badge.setImageResource(R.drawable.energy_saver_locked);
                    Log.e("print",""+badgeStudentResponses.get(position).getValue());
                    float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
                    holder.seekBarText.setText("" + valueCheck + "/" + ProjectUtil.getValue(AppConstants.VALUE_ENERGY_SAVING_BRNOZE));
                    holder.seekbar.setMax((int) AppConstants.VALUE_ENERGY_SAVING_BRNOZE);
                    holder.seekbar.setProgress((int) valueCheck);
                } else if (value >= AppConstants.VALUE_ENERGY_SAVING_BRNOZE && value < AppConstants.VALUE_ENERGY_SAVING_SILVER) {
                    holder.iv_badge.setImageResource(R.drawable.energy_saving_bronze_badge);
                    float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
                    holder.seekBarText.setText("" + valueCheck + "/" + ProjectUtil.getValue(AppConstants.VALUE_ENERGY_SAVING_SILVER));
                    holder.seekbar.setMax((int) AppConstants.VALUE_ENERGY_SAVING_SILVER);
                    holder.seekbar.setProgress((int) valueCheck);
                } else if (value >= AppConstants.VALUE_ENERGY_SAVING_SILVER && value < AppConstants.VALUE_ENERGY_SAVING_GOLD) {
                    holder.iv_badge.setImageResource(R.drawable.energy_saving_silver_badge);
                    float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
                    holder.seekBarText.setText("" + valueCheck + "/" + ProjectUtil.getValue(AppConstants.VALUE_ENERGY_SAVING_GOLD));
                    holder.seekbar.setMax((int) AppConstants.VALUE_ENERGY_SAVING_GOLD);
                    holder.seekbar.setProgress((int) valueCheck);

                } else if (value >= AppConstants.VALUE_ENERGY_SAVING_GOLD && value < AppConstants.VALUE_ENERGY_SAVING_HIDDEN) {
                    holder.iv_badge.setImageResource(R.drawable.energy_saving_gold_badge);
                    float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
                    holder.seekBarText.setText("" + valueCheck + "/" + ProjectUtil.getValue(AppConstants.VALUE_ENERGY_SAVING_HIDDEN));
                    holder.seekbar.setMax((int) AppConstants.VALUE_ENERGY_SAVING_HIDDEN);
                    holder.seekbar.setProgress((int) valueCheck);

                } else if (value >= AppConstants.VALUE_ENERGY_SAVING_HIDDEN) {
                    holder.hiddenBadgeView.setVisibility(View.VISIBLE);
                    holder.seekbar.setVisibility(View.GONE);
                    holder.iv_badge.setImageResource(R.drawable.energy_saving_hidden_badge);
                    float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
                    holder.seekBarText.setText("" + valueCheck+"/"+"?????");
                 /*   holder.seekbar.setMax((int) AppConstants.VALUE_ENERGY_SAVING_HIDDEN);
                    holder.seekbar.setProgress((int) valueCheck);*/

                }
            }


        } else if (badgeStudentResponses.get(position).getBadgesType().equalsIgnoreCase(AppConstants.ID_QUIZHOLIC)) {
            holder.titleBadge.setText(context.getString(R.string.title_badge_quizholic));
            holder.discriptionBadge.setText(context.getString(R.string.txt_badge_quizholic_description));
            holder.hiddenBadgeView.setVisibility(View.GONE);
            holder.seekbar.setVisibility(View.VISIBLE);
            if (badgeStudentResponses.get(position).getValue() != null) {
                float value = Float.parseFloat(badgeStudentResponses.get(position).getValue());
                if (value < AppConstants.VALUE_QUIZHOLIC_BRONZE) {
                    holder.iv_badge.setImageResource(R.drawable.quizholic_locked);
                    float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(0, BigDecimal.ROUND_HALF_UP).floatValue();
                    holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_QUIZHOLIC_BRONZE));
                    holder.seekbar.setMax((int) AppConstants.VALUE_QUIZHOLIC_BRONZE);
                    holder.seekbar.setProgress((int) valueCheck);
                } else if (value >= AppConstants.VALUE_QUIZHOLIC_BRONZE && value < AppConstants.VALUE_QUIZHOLIC_SILVER) {
                    holder.iv_badge.setImageResource(R.drawable.quiz_holic_bronze_badge);
                    float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(0, BigDecimal.ROUND_HALF_UP).floatValue();
                    holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_QUIZHOLIC_SILVER));
                    holder.seekbar.setMax((int) AppConstants.VALUE_QUIZHOLIC_SILVER);
                    holder.seekbar.setProgress((int) valueCheck);

                } else if (value >= AppConstants.VALUE_QUIZHOLIC_SILVER && value < AppConstants.VALUE_QUIZHOLIC_GOLDE) {
                    holder.iv_badge.setImageResource(R.drawable.quiz_holic_silver_badge);
                    float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(0, BigDecimal.ROUND_HALF_UP).floatValue();
                    holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_QUIZHOLIC_GOLDE));
                    holder.seekbar.setMax((int) AppConstants.VALUE_QUIZHOLIC_GOLDE);
                    holder.seekbar.setProgress((int) valueCheck);

                } else if (value >= AppConstants.VALUE_QUIZHOLIC_GOLDE && value < AppConstants.VALUE_QUIZHOLIC_HIDDEN) {
                    holder.iv_badge.setImageResource(R.drawable.quiz_holic_gold_badge);
                    float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(0, BigDecimal.ROUND_HALF_UP).floatValue();
                    holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_QUIZHOLIC_HIDDEN));
                    holder.seekbar.setMax((int) AppConstants.VALUE_QUIZHOLIC_HIDDEN);
                    holder.seekbar.setProgress((int) valueCheck);

                } else if (value >= AppConstants.VALUE_QUIZHOLIC_HIDDEN) {
                    holder.hiddenBadgeView.setVisibility(View.VISIBLE);
                    holder.seekbar.setVisibility(View.GONE);
                    holder.iv_badge.setImageResource(R.drawable.quiz_holic_hidden_badge);
                    float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(0, BigDecimal.ROUND_HALF_UP).floatValue();
                    holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + "?????");
                 /*   holder.seekbar.setMax((int) AppConstants.VALUE_QUIZHOLIC_HIDDEN);
                    holder.seekbar.setProgress((int) valueCheck);*/
                }
            }


        } else if (badgeStudentResponses.get(position).getBadgesType().equalsIgnoreCase(AppConstants.ID_PERSISTANT)) {
            holder.titleBadge.setText(context.getString(R.string.title_badge_persistent));
            holder.discriptionBadge.setText(context.getString(R.string.txt_badge_persistent_description));
            holder.hiddenBadgeView.setVisibility(View.GONE);
            holder.seekbar.setVisibility(View.VISIBLE);
            float value = Float.parseFloat(badgeStudentResponses.get(position).getValue());
            if (value < AppConstants.VALUE_PERSISTANT_BRONZE) {
                holder.iv_badge.setImageResource(R.drawable.persistent_locked);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_PERSISTANT_BRONZE));
                holder.seekbar.setMax((int) AppConstants.VALUE_PERSISTANT_BRONZE);
                holder.seekbar.setProgress((int) valueCheck);

            } else if (value >= AppConstants.VALUE_PERSISTANT_BRONZE && value < AppConstants.VALUE_PERSISTANT_SILVER) {
                holder.iv_badge.setImageResource(R.drawable.persistent_bronze_badge);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_PERSISTANT_SILVER));
                holder.seekbar.setMax((int) AppConstants.VALUE_PERSISTANT_SILVER);
                holder.seekbar.setProgress((int) valueCheck);

            } else if (value >= AppConstants.VALUE_PERSISTANT_SILVER && value < AppConstants.VALUE_PERSISTANT_GOLD) {
                holder.iv_badge.setImageResource(R.drawable.persistent_silver_badge);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_PERSISTANT_GOLD));
                holder.seekbar.setMax((int) AppConstants.VALUE_PERSISTANT_GOLD);
                holder.seekbar.setProgress((int) valueCheck);

            } else if (value >= AppConstants.VALUE_PERSISTANT_GOLD && value < AppConstants.VALUE_PERSISTANT_HIDDEN) {
                holder.iv_badge.setImageResource(R.drawable.persitent_gold_badge);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_PERSISTANT_HIDDEN));
                holder.seekbar.setMax((int) AppConstants.VALUE_PERSISTANT_HIDDEN);
                holder.seekbar.setProgress((int) valueCheck);

            } else if (value >= AppConstants.VALUE_PERSISTANT_HIDDEN) {
                holder.hiddenBadgeView.setVisibility(View.VISIBLE);
                holder.seekbar.setVisibility(View.GONE);
                holder.iv_badge.setImageResource(R.drawable.persistent_hidden_badge);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + "?????");
              /*  holder.seekbar.setMax((int) AppConstants.VALUE_PERSISTANT_HIDDEN);
                holder.seekbar.setProgress((int) valueCheck);*/

            }

        } else if (badgeStudentResponses.get(position).getBadgesType().equalsIgnoreCase(AppConstants.ID_PERFECTION)) {
            holder.titleBadge.setText(context.getString(R.string.title_badge_perfection));
            holder.discriptionBadge.setText(context.getString(R.string.txt_badge_perfection_description));
            holder.hiddenBadgeView.setVisibility(View.GONE);
            holder.seekbar.setVisibility(View.VISIBLE);
            float value = Float.parseFloat(badgeStudentResponses.get(position).getValue());
            if (value < AppConstants.VALUE_PERFECTION_BRONZE) {
                holder.iv_badge.setImageResource(R.drawable.perfection_locked);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_PERFECTION_BRONZE));
                holder.seekbar.setMax((int) AppConstants.VALUE_PERFECTION_BRONZE);
                holder.seekbar.setProgress((int) valueCheck);

            } else if (value >= AppConstants.VALUE_PERFECTION_BRONZE && value < AppConstants.VALUE_PERFECTION_SILVER) {
                holder.iv_badge.setImageResource(R.drawable.perfection_bronze_badge);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_PERFECTION_SILVER));
                holder.seekbar.setMax((int) AppConstants.VALUE_PERFECTION_SILVER);
                holder.seekbar.setProgress((int) valueCheck);

            } else if (value >= AppConstants.VALUE_PERFECTION_SILVER && value < AppConstants.VALUE_PERFECTION_GOLD) {
                holder.iv_badge.setImageResource(R.drawable.perfection_silver_badge);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_PERFECTION_GOLD));
                holder.seekbar.setMax((int) AppConstants.VALUE_PERFECTION_GOLD);
                holder.seekbar.setProgress((int) valueCheck);

            } else if (value >= AppConstants.VALUE_PERFECTION_GOLD && value < AppConstants.VALUE_PERFECTION_HIDDEN) {
                holder.iv_badge.setImageResource(R.drawable.perfection_gold_badge);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_PERFECTION_HIDDEN));
                holder.seekbar.setMax((int) AppConstants.VALUE_PERFECTION_HIDDEN);
                holder.seekbar.setProgress((int) valueCheck);

            } else if (value >= AppConstants.VALUE_PERFECTION_HIDDEN) {
                holder.hiddenBadgeView.setVisibility(View.VISIBLE);
                holder.seekbar.setVisibility(View.GONE);
                holder.iv_badge.setImageResource(R.drawable.perfection_hidden_badge);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + "?????");
               /* holder.seekbar.setMax((int) AppConstants.VALUE_PERFECTION_HIDDEN);
                holder.seekbar.setProgress((int) valueCheck);*/

            }

        } else if (badgeStudentResponses.get(position).getBadgesType().equalsIgnoreCase(AppConstants.ID_BROADCASTER)) {
            holder.titleBadge.setText(context.getString(R.string.title_badge_broadcaster));
            holder.discriptionBadge.setText(context.getString(R.string.txt_badge_broadcaster_description));
            holder.hiddenBadgeView.setVisibility(View.GONE);
            holder.seekbar.setVisibility(View.VISIBLE);
            float value = Float.parseFloat(badgeStudentResponses.get(position).getValue());
            if (value < AppConstants.VALUE_BROADCASTER_BRONZE) {
                holder.iv_badge.setImageResource(R.drawable.broadcaster_locked);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_BROADCASTER_BRONZE));
                holder.seekbar.setMax((int) AppConstants.VALUE_BROADCASTER_BRONZE);
                holder.seekbar.setProgress((int) valueCheck);
            } else if (value >= AppConstants.VALUE_BROADCASTER_BRONZE && value < AppConstants.VALUE_BROADCASTER_SILVER) {
                holder.iv_badge.setImageResource(R.drawable.broadcaster_bronze_badge);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_BROADCASTER_SILVER));
                holder.seekbar.setMax((int) AppConstants.VALUE_BROADCASTER_SILVER);
                holder.seekbar.setProgress((int) valueCheck);

            } else if (value >= AppConstants.VALUE_BROADCASTER_SILVER && value < AppConstants.VALUE_BROADCASTER_GOLD) {
                holder.iv_badge.setImageResource(R.drawable.broadcaster_silver_badge);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_BROADCASTER_GOLD));
                holder.seekbar.setMax((int) AppConstants.VALUE_BROADCASTER_GOLD);
                holder.seekbar.setProgress((int) valueCheck);

            } else if (value >= AppConstants.VALUE_BROADCASTER_GOLD && value < AppConstants.VALUE_BROADCASTER_HIDDEN) {
                holder.iv_badge.setImageResource(R.drawable.brodcaster_gold_badge);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_BROADCASTER_HIDDEN));
                holder.seekbar.setMax((int) AppConstants.VALUE_BROADCASTER_HIDDEN);
                holder.seekbar.setProgress((int) valueCheck);
            } else if (value >= AppConstants.VALUE_BROADCASTER_HIDDEN) {
                holder.hiddenBadgeView.setVisibility(View.VISIBLE);
                holder.seekbar.setVisibility(View.GONE);
                holder.iv_badge.setImageResource(R.drawable.broadcaster_hidden_badge);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + "?????");
              /*  holder.seekbar.setMax((int) AppConstants.VALUE_BROADCASTER_HIDDEN);
                holder.seekbar.setProgress((int) valueCheck);*/

            }
        } else if (badgeStudentResponses.get(position).getBadgesType().equalsIgnoreCase(AppConstants.ID_FRIENDLY)) {
            holder.titleBadge.setText(context.getString(R.string.title_badge_friendly));
            holder.discriptionBadge.setText(context.getString(R.string.txt_badge_friendly_description));
            holder.hiddenBadgeView.setVisibility(View.GONE);
            holder.seekbar.setVisibility(View.VISIBLE);
            float value = Float.parseFloat(badgeStudentResponses.get(position).getValue());
            if (value < AppConstants.VALUE_FRIENDLY_BRONZE) {
                holder.iv_badge.setImageResource(R.drawable.friendly_locked);
                if (value < AppConstants.VALUE_BROADCASTER_BRONZE) {
                    holder.iv_badge.setImageResource(R.drawable.friendly_locked);
                    float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                    holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_FRIENDLY_BRONZE));
                    holder.seekbar.setMax((int) AppConstants.VALUE_FRIENDLY_BRONZE);
                    holder.seekbar.setProgress((int) valueCheck);
                }
            } else if (value >= AppConstants.VALUE_FRIENDLY_BRONZE && value < AppConstants.VALUE_FRIENDLY_SILVER) {
                holder.iv_badge.setImageResource(R.drawable.friendly_bronze_badge);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_FRIENDLY_SILVER));
                holder.seekbar.setMax((int) AppConstants.VALUE_FRIENDLY_SILVER);
                holder.seekbar.setProgress((int) valueCheck);
            } else if (value >= AppConstants.VALUE_FRIENDLY_SILVER && value < AppConstants.VALUE_FRIENDLY_GOLD) {
                holder.iv_badge.setImageResource(R.drawable.friendly_silver_badge);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_FRIENDLY_GOLD));
                holder.seekbar.setMax((int) AppConstants.VALUE_FRIENDLY_GOLD);
                holder.seekbar.setProgress((int) valueCheck);

            } else if (value >= AppConstants.VALUE_FRIENDLY_GOLD && value < AppConstants.VALUE_FRIENDLY_HIDDEN) {
                holder.iv_badge.setImageResource(R.drawable.friendly_gold_badge);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_FRIENDLY_HIDDEN));
                holder.seekbar.setMax((int) AppConstants.VALUE_FRIENDLY_HIDDEN);
                holder.seekbar.setProgress((int) valueCheck);

            } else if (value >= AppConstants.VALUE_FRIENDLY_HIDDEN) {
                holder.hiddenBadgeView.setVisibility(View.VISIBLE);
                holder.seekbar.setVisibility(View.GONE);
                holder.iv_badge.setImageResource(R.drawable.friendly_hidden_badge);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + "?????");
               /* holder.seekbar.setMax((int) AppConstants.VALUE_FRIENDLY_HIDDEN);
                holder.seekbar.setProgress((int) valueCheck);*/

            }
        } else if (badgeStudentResponses.get(position).getBadgesType().equalsIgnoreCase(AppConstants.ID_LEGENDARY)) {
            holder.titleBadge.setText(context.getString(R.string.title_badge_legendary));
            holder.discriptionBadge.setText(context.getString(R.string.txt_badge_legendary_description));
            holder.hiddenBadgeView.setVisibility(View.GONE);
            holder.seekbar.setVisibility(View.VISIBLE);
            float value = Float.parseFloat(badgeStudentResponses.get(position).getValue());
            if (value < AppConstants.VALUE_LEGENDARY_BRONZE) {
                holder.iv_badge.setImageResource(R.drawable.legendary_locked);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_LEGENDARY_BRONZE));
                holder.seekbar.setMax((int) AppConstants.VALUE_LEGENDARY_BRONZE);
                holder.seekbar.setProgress((int) valueCheck);

            } else if (value >= AppConstants.VALUE_LEGENDARY_BRONZE && value < AppConstants.VALUE_LEGENDARY_SILVER) {
                holder.iv_badge.setImageResource(R.drawable.legendary_bronze_badge);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_LEGENDARY_SILVER));
                holder.seekbar.setMax((int) AppConstants.VALUE_LEGENDARY_SILVER);
                holder.seekbar.setProgress((int) valueCheck);

            } else if (value >= AppConstants.VALUE_LEGENDARY_SILVER && value < AppConstants.VALUE_LEGENDARY_GOLD) {
                holder.iv_badge.setImageResource(R.drawable.legendary_silver_badge);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_LEGENDARY_GOLD));
                holder.seekbar.setMax((int) AppConstants.VALUE_LEGENDARY_GOLD);
                holder.seekbar.setProgress((int) valueCheck);

            } else if (value >= AppConstants.VALUE_LEGENDARY_GOLD && value < AppConstants.VALUE_LEGENDARY_HIDDEN) {
                holder.iv_badge.setImageResource(R.drawable.legendary_gold_badge);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_LEGENDARY_HIDDEN));
                holder.seekbar.setMax((int) AppConstants.VALUE_LEGENDARY_HIDDEN);
                holder.seekbar.setProgress((int) valueCheck);

            } else if (value >= AppConstants.VALUE_LEGENDARY_HIDDEN) {
                holder.hiddenBadgeView.setVisibility(View.VISIBLE);
                holder.seekbar.setVisibility(View.GONE);
                holder.iv_badge.setImageResource(R.drawable.legendary_hidden_badge);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + "?????");
                /*holder.seekbar.setMax((int) AppConstants.VALUE_LEGENDARY_HIDDEN);
                holder.seekbar.setProgress((int) valueCheck);*/

            }
        } else if (badgeStudentResponses.get(position).getBadgesType().equalsIgnoreCase(AppConstants.ID_SUPERIOR_SPECLIST)) {
            holder.titleBadge.setText(context.getString(R.string.title_badge_superior));
            holder.discriptionBadge.setText(context.getString(R.string.txt_badge_superior_description));

            float value = Float.parseFloat(badgeStudentResponses.get(position).getValue());
            holder.hiddenBadgeView.setVisibility(View.GONE);
            holder.seekbar.setVisibility(View.VISIBLE);
            if (value < AppConstants.VALUE_SUPERIOR_SPECLIST_BRONZE) {
                holder.iv_badge.setImageResource(R.drawable.superior_locked);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_SUPERIOR_SPECLIST_BRONZE));
                holder.seekbar.setMax((int) AppConstants.VALUE_SUPERIOR_SPECLIST_BRONZE);
                holder.seekbar.setProgress((int) valueCheck);

            } else if (value >= AppConstants.VALUE_SUPERIOR_SPECLIST_BRONZE && value < AppConstants.VALUE_SUPERIOR_SPECLIST_SILVER) {
                holder.iv_badge.setImageResource(R.drawable.superior_brnoze_badge);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_SUPERIOR_SPECLIST_SILVER));
                holder.seekbar.setMax((int) AppConstants.VALUE_SUPERIOR_SPECLIST_SILVER);
                holder.seekbar.setProgress((int) valueCheck);

            } else if (value >= AppConstants.VALUE_SUPERIOR_SPECLIST_SILVER && value < AppConstants.VALUE_SUPERIOR_SPECLIST_GOLD) {
                holder.iv_badge.setImageResource(R.drawable.superior_silver_badge);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_SUPERIOR_SPECLIST_GOLD));
                holder.seekbar.setMax((int) AppConstants.VALUE_SUPERIOR_SPECLIST_GOLD);
                holder.seekbar.setProgress((int) valueCheck);

            } else if (value >= AppConstants.VALUE_SUPERIOR_SPECLIST_GOLD && value < AppConstants.VALUE_SUPERIOR_SPECLIST_HIDDEN) {
                holder.iv_badge.setImageResource(R.drawable.superior_gold_badge);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_SUPERIOR_SPECLIST_HIDDEN));
                holder.seekbar.setMax((int) AppConstants.VALUE_SUPERIOR_SPECLIST_HIDDEN);
                holder.seekbar.setProgress((int) valueCheck);

            } else if (value >= AppConstants.VALUE_SUPERIOR_SPECLIST_HIDDEN) {
                holder.hiddenBadgeView.setVisibility(View.VISIBLE);
                holder.seekbar.setVisibility(View.GONE);
                holder.iv_badge.setImageResource(R.drawable.superior_hidden_badge);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + "?????");
          /*      holder.seekbar.setMax((int) AppConstants.VALUE_SUPERIOR_SPECLIST_HIDDEN);
                holder.seekbar.setProgress((int) valueCheck);*/

            }
        } else if (badgeStudentResponses.get(position).getBadgesType().equalsIgnoreCase(AppConstants.ID_CHAMPION)) {
            holder.titleBadge.setText(context.getString(R.string.title_badge_champion));
            holder.discriptionBadge.setText(context.getString(R.string.txt_badge_champion_description));
            float value = Float.parseFloat(badgeStudentResponses.get(position).getValue());
            holder.hiddenBadgeView.setVisibility(View.GONE);
            holder.seekbar.setVisibility(View.VISIBLE);
            if (value < AppConstants.VALUE_CHAMPION_BRONZE) {
                holder.iv_badge.setImageResource(R.drawable.champion_locked);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_CHAMPION_BRONZE));
                holder.seekbar.setMax((int) AppConstants.VALUE_CHAMPION_BRONZE);
                holder.seekbar.setProgress((int) valueCheck);

            } else if (value >= AppConstants.VALUE_CHAMPION_BRONZE && value < AppConstants.VALUE_CHAMPION_SILVER) {
                holder.iv_badge.setImageResource(R.drawable.champion_bronze_badge);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_CHAMPION_SILVER));
                holder.seekbar.setMax((int) AppConstants.VALUE_CHAMPION_SILVER);
                holder.seekbar.setProgress((int) valueCheck);

            } else if (value >= AppConstants.VALUE_CHAMPION_SILVER && value < AppConstants.VALUE_CHAMPION_GOLD) {
                holder.iv_badge.setImageResource(R.drawable.champion_silver_badge);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_CHAMPION_GOLD));
                holder.seekbar.setMax((int) AppConstants.VALUE_CHAMPION_GOLD);
                holder.seekbar.setProgress((int) valueCheck);

            } else if (value >= AppConstants.VALUE_CHAMPION_GOLD && value < AppConstants.VALUE_CHAMPION_HIDDEN) {
                holder.iv_badge.setImageResource(R.drawable.champion_gold_badge);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + ProjectUtil.getValue(AppConstants.VALUE_CHAMPION_HIDDEN));
                holder.seekbar.setMax((int) AppConstants.VALUE_CHAMPION_HIDDEN);
                holder.seekbar.setProgress((int) valueCheck);

            } else if (value >= AppConstants.VALUE_CHAMPION_HIDDEN) {
                holder.hiddenBadgeView.setVisibility(View.VISIBLE);
                holder.seekbar.setVisibility(View.GONE);
                holder.iv_badge.setImageResource(R.drawable.champion_hidden_badge);
                float valueCheck = new BigDecimal(badgeStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                holder.seekBarText.setText("" + ProjectUtil.getValue(valueCheck) + "/" + "?????");
               /* holder.seekbar.setMax((int) AppConstants.VALUE_CHAMPION_HIDDEN);
                holder.seekbar.setProgress((int) valueCheck);*/

            }
        }


    }

    @Override
    public int getItemCount() {
        return badgeStudentResponses.size();
    }

    public interface OnRewardItemClickListner {
        void onClick(int id, int pos);
    }

    class Holder extends RecyclerView.ViewHolder {
        public SeekBar seekbar;
        public CardView card_view;
        public ImageView iv_badge;
        public AppCompatTextView titleBadge, discriptionBadge, seekBarText;
        public RelativeLayout hiddenBadgeView;

        public Holder(View itemView) {
            super(itemView);
            card_view = (CardView) itemView.findViewById(R.id.card_view);
            iv_badge = (ImageView) itemView.findViewById(R.id.iv_badge);
            seekbar = (SeekBar) itemView.findViewById(R.id.seekbar);
            titleBadge = (AppCompatTextView) itemView.findViewById(R.id.titleBadge);
            discriptionBadge = (AppCompatTextView) itemView.findViewById(R.id.descriptionbadge);
            seekBarText = (AppCompatTextView) itemView.findViewById(R.id.seekBarText);
            hiddenBadgeView=(RelativeLayout)itemView.findViewById(R.id.hiddenBadgeView);

        }
    }
}
