package com.gdc.isfacademy.view.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.model.ChallangeRankList;
import com.gdc.isfacademy.utils.AppConstants;
import com.gdc.isfacademy.view.customs.customfonts.OpenSansLightTextview;

import java.util.ArrayList;

/**
 * Created by ashishthakur on 6/4/18.
 */

public class ChallengeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Context context;
    ArrayList<ChallangeRankList>challangeRankLists;


    public ChallengeAdapter(Context context,ArrayList<ChallangeRankList>challangeRankLists) {
        this.context = context;
        this.challangeRankLists=challangeRankLists;
    }
/*

    public void setList(ArrayList<DataList> list, Context context) {
        this.list = list;
        this.context = context;
        notifyDataSetChanged();
    }
*/


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
                if(position==0){
                    topRankedViewHolder.postion.setVisibility(View.INVISIBLE);
                    topRankedViewHolder.rankIcon.setVisibility(View.VISIBLE);
                    topRankedViewHolder.rankIcon.setImageResource(R.drawable.first_place);
                    topRankedViewHolder.points.setText(challangeRankLists.get(position).getRankPoints());
                    topRankedViewHolder.name.setText(challangeRankLists.get(position).getRankerName());
                   // topRankedViewHolder.postion.setCompoundDrawablesWithIntrinsicBounds(R.drawable.first_place,0,0,0);
                }
                break;
            case AppConstants.OTHERS_RANK_VIEW:
                final OtheresRankViewHolder otheresRankViewHolder = (OtheresRankViewHolder) holder;
                if(position==1){
                    otheresRankViewHolder.postion.setVisibility(View.INVISIBLE);
                    otheresRankViewHolder.rankIcon.setVisibility(View.VISIBLE);
                    otheresRankViewHolder.rankIcon.setImageResource(R.drawable.second_place);
                    otheresRankViewHolder.points.setText(challangeRankLists.get(position).getRankPoints());
                    otheresRankViewHolder.name.setText(challangeRankLists.get(position).getRankerName());
                   // otheresRankViewHolder.postion.setCompoundDrawablesWithIntrinsicBounds(R.drawable.second_place,0,0,0);

                }
                else if(position==2){
                    otheresRankViewHolder.postion.setVisibility(View.INVISIBLE);
                    otheresRankViewHolder.rankIcon.setVisibility(View.VISIBLE);
                    otheresRankViewHolder.rankIcon.setImageResource(R.drawable.third_place);
                    otheresRankViewHolder.points.setText(challangeRankLists.get(position).getRankPoints());
                    otheresRankViewHolder.name.setText(challangeRankLists.get(position).getRankerName());
                  //  otheresRankViewHolder.postion.setCompoundDrawablesWithIntrinsicBounds(R.drawable.third_place,0,0,0);
                }
                else if(position==6){
                    otheresRankViewHolder.postion.setVisibility(View.VISIBLE);
                    otheresRankViewHolder.rankIcon.setVisibility(View.INVISIBLE);
                    otheresRankViewHolder.postion.setText(challangeRankLists.get(position).getRankPostion());
                    otheresRankViewHolder.points.setText(challangeRankLists.get(position).getRankPoints());
                    otheresRankViewHolder.name.setText(challangeRankLists.get(position).getRankerName()+" "+"(You)");
                    otheresRankViewHolder.postion.setTextColor(ContextCompat.getColor(context,R.color.home_bottom_card_text_color));
                    otheresRankViewHolder.name.setTextColor(ContextCompat.getColor(context,R.color.black));
                    otheresRankViewHolder.points.setTextColor(ContextCompat.getColor(context,R.color.home_bottom_card_text_color));
                    Typeface externalFont= Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Semibold_0.ttf");
                    otheresRankViewHolder.name.setTypeface(externalFont);

                }
                else {
                    otheresRankViewHolder.postion.setVisibility(View.VISIBLE);
                    otheresRankViewHolder.rankIcon.setVisibility(View.INVISIBLE);
                    otheresRankViewHolder.postion.setText(challangeRankLists.get(position).getRankPostion());
                    otheresRankViewHolder.points.setText(challangeRankLists.get(position).getRankPoints());
                    otheresRankViewHolder.name.setText(challangeRankLists.get(position).getRankerName());
                }
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


    public class TopRankedViewHolder extends RecyclerView.ViewHolder {

        OpenSansLightTextview name, postion, points;
        ImageView rankIcon;

        public TopRankedViewHolder(View itemView) {
            super(itemView);
            name = (OpenSansLightTextview) itemView.findViewById(R.id.name_text);
            postion = (OpenSansLightTextview) itemView.findViewById(R.id.people_rank_text);
            points = (OpenSansLightTextview) itemView.findViewById(R.id.rank_points_text);
            rankIcon=(ImageView)itemView.findViewById(R.id.rank_icon);

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
            rankIcon=(ImageView)itemView.findViewById(R.id.rank_icon);


        }
    }

}
