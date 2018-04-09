package com.gdc.isfacademy.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdc.isfacademy.R;

/**
 * Created by akshaydashore on 4/4/18
 */

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.Holder> {

    Context context;

    public ProfileAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

         View view=inflater.inflate(R.layout.list_item_post_profile_layout, null);

        Holder holder = new Holder(view);
        return holder;
    }




    @Override
    public void onBindViewHolder(Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }


    class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);
        }
    }
}
