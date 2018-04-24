package com.gdc.isfacademy.view.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.model.Options;
import com.gdc.isfacademy.model.Question;

import java.util.List;

/**
 * Created by ashishthakur on 16/4/18.
 */

public class QuizeListAdapter extends RecyclerView.Adapter<QuizeListAdapter.Holder> {

    Context context;
    List<Options> optionList;
    QuizeListAdapter.OnListItemClick onListItemClick;
    Question question;

    public QuizeListAdapter(Context context, List<Options> optionList) {
        this.context = context;
        this.optionList = optionList;
    }

    public void setQuestion(Question question)
    {
        this.question = question;
    }

    public void setOnListItemClick(QuizeListAdapter.OnListItemClick onListItemClick) {
        this.onListItemClick = onListItemClick;
    }

    @Override
    public QuizeListAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_items_quize, null);
        QuizeListAdapter.Holder holder = new QuizeListAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(QuizeListAdapter.Holder holder, final int position) {

        holder.columnName.setText(getPrefix(position));

        holder.answerText.setText(optionList.get(position).getOption());

        holder.answerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onListItemClick.onClick(v.getId(),position);
            }
        });

        if (optionList.get(position).isSelected())
        {
            holder.answerView.setBackgroundColor(context.getResources().getColor(R.color.white_transparent_color));
        }
        else
        {
            holder.answerView.setBackgroundColor(context.getResources().getColor(R.color.white));
        }
    }

    @Override
    public int getItemCount() {
        return optionList.size();
    }

    public interface OnListItemClick {
        void onClick(int id, int pos);
    }

    class Holder extends RecyclerView.ViewHolder {
        CardView answerView;
        AppCompatTextView columnName,answerText;

        public Holder(View itemView) {
            super(itemView);
            answerView = (CardView) itemView.findViewById(R.id.answerView);
            columnName=(AppCompatTextView)itemView.findViewById(R.id.columnName);
            answerText=(AppCompatTextView)itemView.findViewById(R.id.answerText);
        }
    }

    private String getPrefix(int position)
    {
        if (position==0){
            return "A.";
        }
        else if(position==1){
            return "B.";
        }
        else if(position==2){
            return "C.";
        }
        else if(position==3){
            return "D.";
        }
        return "";
    }
}