package com.gdc.isfacademy.view.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.model.ChallangeRankList;
import com.gdc.isfacademy.utils.AppConstants;
import com.gdc.isfacademy.utils.MyPref;
import com.gdc.isfacademy.view.customs.customfonts.OpenSansLightTextview;

import java.util.ArrayList;

/**
 * Created by ashishthakur on 6/4/18.
 */

public class ChallengeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Context context;
    ArrayList<ChallangeRankList> challangeRankLists;


    public ChallengeAdapter(Context context, ArrayList<ChallangeRankList> challangeRankLists) {
        this.context = context;
        this.challangeRankLists = challangeRankLists;
    }

    public void updateList(Context context, ArrayList<ChallangeRankList> challangeRankLists) {
        this.context = context;
        this.challangeRankLists = challangeRankLists;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root;
        switch (viewType) {
            case AppConstants.TOP_RANKED_VIEW:
                root = inflater.inflate(R.layout.list_item_top_ranked, parent, false);
                return new TopRankedViewHolder(root);
            case AppConstants.OTHERS_RANK_VIEW:
                root = inflater.inflate(R.layout.list_item_others_rank, parent, false);
                return new OtheresRankViewHolder(root);

            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {

            case AppConstants.TOP_RANKED_VIEW:
                final TopRankedViewHolder topRankedViewHolder = (TopRankedViewHolder) holder;
                if (position == 0) {
                    if (challangeRankLists.get(0).isCheckIsme()) {
                        topRankedViewHolder.postion.setVisibility(View.INVISIBLE);
                        topRankedViewHolder.rankIcon.setVisibility(View.VISIBLE);
                        topRankedViewHolder.rankIcon.setImageResource(R.drawable.first_place);
                        topRankedViewHolder.points.setText(challangeRankLists.get(position).getValue());

                    } else {
                        topRankedViewHolder.postion.setVisibility(View.INVISIBLE);
                        topRankedViewHolder.rankIcon.setVisibility(View.VISIBLE);
                        topRankedViewHolder.rankIcon.setImageResource(R.drawable.first_place);
                        topRankedViewHolder.points.setText(challangeRankLists.get(position).getValue());
                        topRankedViewHolder.name.setText(challangeRankLists.get(position).getStudentName());

                    }

                    setMyView(position, topRankedViewHolder);
                }
                break;
            case AppConstants.OTHERS_RANK_VIEW:
                final OtheresRankViewHolder otheresRankViewHolder = (OtheresRankViewHolder) holder;
                if (position == 1) {
                    if (challangeRankLists.get(1).isCheckIsme()) {
                        otheresRankViewHolder.postion.setVisibility(View.INVISIBLE);
                        otheresRankViewHolder.rankIcon.setVisibility(View.VISIBLE);
                        otheresRankViewHolder.rankIcon.setImageResource(R.drawable.second_place);
                        otheresRankViewHolder.points.setText(challangeRankLists.get(position).getValue());
                        otheresRankViewHolder.name.setText(challangeRankLists.get(position).getStudentName());
                    } else {
                        otheresRankViewHolder.postion.setVisibility(View.INVISIBLE);
                        otheresRankViewHolder.rankIcon.setVisibility(View.VISIBLE);
                        otheresRankViewHolder.rankIcon.setImageResource(R.drawable.second_place);
                        otheresRankViewHolder.points.setText(challangeRankLists.get(position).getValue());
                        otheresRankViewHolder.name.setText(challangeRankLists.get(position).getStudentName());

                    }

                } else if (position == 2) {
                    if (challangeRankLists.get(2).isCheckIsme()) {
                        otheresRankViewHolder.postion.setVisibility(View.INVISIBLE);
                        otheresRankViewHolder.rankIcon.setVisibility(View.VISIBLE);
                        otheresRankViewHolder.rankIcon.setImageResource(R.drawable.third_place);
                        otheresRankViewHolder.points.setText(challangeRankLists.get(position).getValue());
                        otheresRankViewHolder.name.setText(challangeRankLists.get(position).getStudentName());
                    } else {
                        otheresRankViewHolder.postion.setVisibility(View.INVISIBLE);
                        otheresRankViewHolder.rankIcon.setVisibility(View.VISIBLE);
                        otheresRankViewHolder.rankIcon.setImageResource(R.drawable.third_place);
                        otheresRankViewHolder.points.setText(challangeRankLists.get(position).getValue());
                        otheresRankViewHolder.name.setText(challangeRankLists.get(position).getStudentName());

                    }
                } else {

                    int a = position + 1;
                    String rankIs = String.valueOf(a);
                    if (challangeRankLists.get(position).isCheckIsme()) {
                        otheresRankViewHolder.postion.setVisibility(View.VISIBLE);
                        otheresRankViewHolder.rankIcon.setVisibility(View.INVISIBLE);
                        otheresRankViewHolder.postion.setText(rankIs);
                        otheresRankViewHolder.points.setText(challangeRankLists.get(position).getValue());
                        otheresRankViewHolder.name.setText(challangeRankLists.get(position).getStudentName());

                    } else {


                        otheresRankViewHolder.postion.setVisibility(View.VISIBLE);
                        otheresRankViewHolder.rankIcon.setVisibility(View.INVISIBLE);
                        otheresRankViewHolder.postion.setText(rankIs);
                        otheresRankViewHolder.points.setText(challangeRankLists.get(position).getValue());
                        otheresRankViewHolder.name.setText(challangeRankLists.get(position).getStudentName());
                    }

                }

                setMyView(position, otheresRankViewHolder);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return challangeRankLists.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return AppConstants.TOP_RANKED_VIEW;
        } else {
            return AppConstants.OTHERS_RANK_VIEW;

        }

    }

    private void setMyView(int position, RecyclerView.ViewHolder holder) {
        if (challangeRankLists.get(position).isCheckIsme()) {
            if (holder instanceof TopRankedViewHolder) {
                TopRankedViewHolder topRankedViewHolder = (TopRankedViewHolder) holder;
                topRankedViewHolder.name.setText(challangeRankLists.get(position).getStudentName() + " " + "(You)");
                topRankedViewHolder.name.setTypeface(topRankedViewHolder.name.getTypeface(), Typeface.BOLD);
                topRankedViewHolder.name.setTextColor(context.getResources().getColor(android.R.color.black));
                topRankedViewHolder.points.setTypeface(topRankedViewHolder.points.getTypeface(), Typeface.BOLD);
                topRankedViewHolder.points.setTextColor(context.getResources().getColor(android.R.color.holo_blue_light));
            } else if (holder instanceof OtheresRankViewHolder) {
                OtheresRankViewHolder otheresRankViewHolder = (OtheresRankViewHolder) holder;
                switch (position) {
                    case 1:
                        otheresRankViewHolder.name.setText(challangeRankLists.get(position).getStudentName() + " " + "(You)");
                        otheresRankViewHolder.name.setTypeface(otheresRankViewHolder.name.getTypeface(), Typeface.BOLD);
                        otheresRankViewHolder.points.setTypeface(otheresRankViewHolder.points.getTypeface(), Typeface.BOLD);
                        otheresRankViewHolder.points.setTextColor(context.getResources().getColor(android.R.color.holo_blue_light));
                        otheresRankViewHolder.name.setTextColor(context.getResources().getColor(android.R.color.black));

                        break;
                    case 2:
                        otheresRankViewHolder.name.setText(challangeRankLists.get(position).getStudentName() + " " + "(You)");
                        otheresRankViewHolder.name.setTypeface(otheresRankViewHolder.name.getTypeface(), Typeface.BOLD);
                        otheresRankViewHolder.points.setTypeface(otheresRankViewHolder.points.getTypeface(), Typeface.BOLD);
                        otheresRankViewHolder.points.setTextColor(context.getResources().getColor(android.R.color.holo_blue_light));
                        otheresRankViewHolder.name.setTextColor(context.getResources().getColor(android.R.color.black));

                        break;
                    default:
                        otheresRankViewHolder.name.setTextColor(context.getResources().getColor(android.R.color.black));
                        otheresRankViewHolder.name.setText(challangeRankLists.get(position).getStudentName() + " " + "(You)");
                        otheresRankViewHolder.name.setTypeface(otheresRankViewHolder.name.getTypeface(), Typeface.BOLD);
                        otheresRankViewHolder.points.setTypeface(otheresRankViewHolder.points.getTypeface(), Typeface.BOLD);
                        otheresRankViewHolder.points.setTextColor(context.getResources().getColor(android.R.color.holo_blue_light));
                        otheresRankViewHolder.postion.setTextColor(context.getResources().getColor(android.R.color.holo_blue_light));

                }
            }
        } else {
            if (holder instanceof TopRankedViewHolder) {
                TopRankedViewHolder topRankedViewHolder = (TopRankedViewHolder) holder;
                topRankedViewHolder.name.setTypeface(topRankedViewHolder.name.getTypeface(), Typeface.NORMAL);
                topRankedViewHolder.points.setTypeface(topRankedViewHolder.points.getTypeface(), Typeface.NORMAL);
                topRankedViewHolder.points.setTextColor(context.getResources().getColor(R.color.challange_text_color));
                topRankedViewHolder.name.setTextColor(context.getResources().getColor(R.color.challange_text_color));

            } else if (holder instanceof OtheresRankViewHolder) {
                OtheresRankViewHolder otheresRankViewHolder = (OtheresRankViewHolder) holder;
                switch (position) {
                    case 1:
                        otheresRankViewHolder.name.setTypeface(otheresRankViewHolder.name.getTypeface(), Typeface.NORMAL);
                        otheresRankViewHolder.points.setTypeface(otheresRankViewHolder.points.getTypeface(), Typeface.NORMAL);
                        otheresRankViewHolder.points.setTextColor(context.getResources().getColor(R.color.challange_text_color));
                        otheresRankViewHolder.name.setTextColor(context.getResources().getColor(R.color.challange_text_color));

                        break;
                    case 2:
                        otheresRankViewHolder.name.setTypeface(otheresRankViewHolder.name.getTypeface(), Typeface.NORMAL);
                        otheresRankViewHolder.points.setTypeface(otheresRankViewHolder.points.getTypeface(), Typeface.NORMAL);
                        otheresRankViewHolder.points.setTextColor(context.getResources().getColor(R.color.challange_text_color));
                        otheresRankViewHolder.name.setTextColor(context.getResources().getColor(R.color.challange_text_color));

                        break;
                    default:
                        otheresRankViewHolder.name.setTextColor(context.getResources().getColor(R.color.challange_text_color));

                        otheresRankViewHolder.name.setTypeface(otheresRankViewHolder.name.getTypeface(), Typeface.NORMAL);
                        otheresRankViewHolder.points.setTypeface(otheresRankViewHolder.points.getTypeface(), Typeface.NORMAL);
                        otheresRankViewHolder.points.setTextColor(context.getResources().getColor(R.color.challange_text_color));
                        otheresRankViewHolder.postion.setTextColor(context.getResources().getColor(R.color.challange_text_color));

                }
            }
        }
    }

    public class TopRankedViewHolder extends RecyclerView.ViewHolder {

        OpenSansLightTextview name, postion, points;
        ImageView rankIcon;

        public TopRankedViewHolder(View itemView) {
            super(itemView);
            name = (OpenSansLightTextview) itemView.findViewById(R.id.name_text);
            postion = (OpenSansLightTextview) itemView.findViewById(R.id.people_rank_text);
            points = (OpenSansLightTextview) itemView.findViewById(R.id.rank_points_text);
            rankIcon = (ImageView) itemView.findViewById(R.id.rank_icon);

        }
    }

    public class OtheresRankViewHolder extends RecyclerView.ViewHolder {
        OpenSansLightTextview name, postion, points;
        ImageView rankIcon;

        public OtheresRankViewHolder(View itemView) {
            super(itemView);
            name = (OpenSansLightTextview) itemView.findViewById(R.id.name_text);
            postion = (OpenSansLightTextview) itemView.findViewById(R.id.people_rank_text);
            points = (OpenSansLightTextview) itemView.findViewById(R.id.rank_points_text);
            rankIcon = (ImageView) itemView.findViewById(R.id.rank_icon);
        }
    }

}
