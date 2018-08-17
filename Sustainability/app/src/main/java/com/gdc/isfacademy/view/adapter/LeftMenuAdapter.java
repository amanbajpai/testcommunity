package com.gdc.isfacademy.view.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdc.isfacademy.model.SliderBean;
import com.gdc.isfacademy.R;

import java.util.List;

/**
 * Created by ashishthakur on 29/3/18.
 */

@SuppressWarnings("ALL")
public class LeftMenuAdapter extends RecyclerView.Adapter<LeftMenuAdapter.MyViewHolder> {
    final List<SliderBean> data;
    private final LayoutInflater inflater;
    private final Context context;

    public LeftMenuAdapter(Context context, List<SliderBean> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_left_slider, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        /*
        * this condition set the selected menu item as hover image and text
        * and other as the way they are..
        *
        * */

        holder.list_item.setText(data.get(position).getSliderItem());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        final AppCompatTextView list_item;

        public MyViewHolder(View itemView) {
            super(itemView);
            list_item = (AppCompatTextView) itemView.findViewById(R.id.list_item);

        }
    }
}
