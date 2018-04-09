package com.energybox.isf.view.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.energybox.isf.R;
import com.energybox.isf.utils.AppConstants;
import com.energybox.isf.view.customs.customfonts.OpenSansLightTextview;

import java.util.ArrayList;

/**
 * Created by ashishthakur on 6/4/18.
 */

public class ChallengeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Context context;


    public ChallengeAdapter(Context context) {
        this.context = context;
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
                   // topRankedViewHolder.postion.setCompoundDrawablesWithIntrinsicBounds(R.drawable.first_place,0,0,0);
                }
                break;
            case AppConstants.OTHERS_RANK_VIEW:
                final OtheresRankViewHolder otheresRankViewHolder = (OtheresRankViewHolder) holder;
                if(position==1){
                   // otheresRankViewHolder.postion.setCompoundDrawablesWithIntrinsicBounds(R.drawable.second_place,0,0,0);

                }
                else if(position==2){
                  //  otheresRankViewHolder.postion.setCompoundDrawablesWithIntrinsicBounds(R.drawable.third_place,0,0,0);
                }
                else {
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 20;
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

        public TopRankedViewHolder(View itemView) {
            super(itemView);
            name = (OpenSansLightTextview) itemView.findViewById(R.id.name_text);
            postion = (OpenSansLightTextview) itemView.findViewById(R.id.people_rank_text);
            points = (OpenSansLightTextview) itemView.findViewById(R.id.rank_points_text);

        }
    }

    public class OtheresRankViewHolder extends RecyclerView.ViewHolder {
        OpenSansLightTextview name, postion, points;


        public OtheresRankViewHolder(View itemView) {
            super(itemView);
            name = (OpenSansLightTextview) itemView.findViewById(R.id.name_text);
            postion = (OpenSansLightTextview) itemView.findViewById(R.id.people_rank_text);
            points = (OpenSansLightTextview) itemView.findViewById(R.id.rank_points_text);

        }
    }

}
