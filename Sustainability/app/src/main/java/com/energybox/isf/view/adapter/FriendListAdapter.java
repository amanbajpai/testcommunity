package com.energybox.isf.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.energybox.isf.R;
import com.energybox.isf.view.customs.customfonts.OpenSansLightTextview;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by akshaydashore on 3/4/18
 */
public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.Holder> {

    Context context;

    public FriendListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.add_friend_item, null);
//        View view1 =LayoutInflater.from(context).inflate(R.layout.add_friend_item,null);
        Holder holder = new Holder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(Holder holder, int position) {

        if (position % 4 == 0) {
            holder.action_tv.setBackgroundResource(R.drawable.gray_rect_crop_bg);
            holder.action_tv.setText("try latter");
        }
    }

    @Override
    public int getItemCount() {
        return 15;
    }


    class Holder extends RecyclerView.ViewHolder {

        CircleImageView profile_image;
        OpenSansLightTextview action_tv, profile_name;

        public Holder(View itemView) {
            super(itemView);
            profile_image = (CircleImageView) itemView.findViewById(R.id.profile_image);
            action_tv = (OpenSansLightTextview) itemView.findViewById(R.id.action_tv);
            profile_name = (OpenSansLightTextview) itemView.findViewById(R.id.profile_name);
        }
    }

}
