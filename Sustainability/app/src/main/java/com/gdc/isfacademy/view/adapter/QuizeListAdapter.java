package com.gdc.isfacademy.view.adapter;

import android.annotation.SuppressLint;
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
import com.gdc.isfacademy.model.QuestionAnswerBean;

import java.util.List;

/**
 * Created by ashishthakur on 16/4/18.
 */

@SuppressWarnings("ALL")
public class QuizeListAdapter extends RecyclerView.Adapter<QuizeListAdapter.Holder> {

    final Context context;
    final List<Options> optionList;
    QuizeListAdapter.OnListItemClick onListItemClick;
    QuizeListAdapter.Holder mHolder = null;
    QuizeListAdapter.Holder correctAnswermHolder = null;
    QuestionAnswerBean questionAnswerBean;

    public QuizeListAdapter(Context context, List<Options> optionList) {
        this.context = context;
        this.optionList = optionList;
    }

    public void setQuestionAnswerBean(QuestionAnswerBean questionAnswerBean) {
        this.questionAnswerBean = questionAnswerBean;
    }

    public void setOnListItemClick(QuizeListAdapter.OnListItemClick onListItemClick) {
        this.onListItemClick = onListItemClick;
    }

    @Override
    public QuizeListAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.list_items_quize, null);
        QuizeListAdapter.Holder holder = new QuizeListAdapter.Holder(view);
        return holder;
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(final QuizeListAdapter.Holder holder, final int position) {

        holder.columnName.setText(getPrefix(position));
        holder.answerText.setTextColor(R.color.black);
        holder.answerText.setText(optionList.get(position).getOption());

        String valueholder="";
        if(questionAnswerBean.getAnswer().equalsIgnoreCase("A")){
            valueholder="1";
        }
        else if(questionAnswerBean.getAnswer().equalsIgnoreCase("B")){
            valueholder="2";

        }
        else if(questionAnswerBean.getAnswer().equalsIgnoreCase("C")){
            valueholder="3";

        }
        else if(questionAnswerBean.getAnswer().equalsIgnoreCase("D")){
            valueholder="4";

        }
        if (valueholder.equalsIgnoreCase(String.valueOf(position + 1))) {
            correctAnswermHolder = holder;
        }
        holder.answerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionAnswerBean.isQuestionChecked()) {
                    return;
                }
                mHolder = holder;
                mHolder.answerText.setTextColor(context.getResources().getColor(R.color.black));
                mHolder.columnName.setTextColor(context.getResources().getColor(R.color.black));
                onListItemClick.onClick(v.getId(), position);
            }
        });

       /* if(optionList.get(position).isSelected()){
            holder.answerView.setBackgroundColor(context.getResources().getColor(R.color.white_transparent_color));
        }
        else {
            holder.answerView.setBackgroundColor(context.getResources().getColor(R.color.white_transparent));

        }*/

        if (!questionAnswerBean.isQuestionChecked()) {
            holder.answerView.setEnabled(true);
            if (optionList.get(position).isSelected()) {
                holder.answerView.setBackgroundColor(context.getResources().getColor(R.color.white_transparent_color));
            } else {
                holder.answerView.setBackgroundColor(context.getResources().getColor(R.color.white));
            }
        } else {
            holder.answerView.setEnabled(false);
            if (questionAnswerBean.getUserSelectedAnswer() == position) {
                if (questionAnswerBean.getUserSelectedAnswer() == Integer.parseInt(questionAnswerBean.getAnswer())) {
                    holder.answerText.setTextColor(context.getResources().getColor(R.color.white));
                    holder.columnName.setTextColor(context.getResources().getColor(R.color.white));
                    holder.answerView.setBackgroundColor(context.getResources().getColor(R.color.white_transparent_color));
                } else {
                    holder.answerText.setTextColor(context.getResources().getColor(R.color.white));
                    holder.columnName.setTextColor(context.getResources().getColor(R.color.white));
                    holder.answerView.setBackgroundColor(context.getResources().getColor(R.color.red_light));
                }

            } else {
                String value="";
                if(questionAnswerBean.getAnswer().equalsIgnoreCase("A")){
                    value="1";
                }
                else if(questionAnswerBean.getAnswer().equalsIgnoreCase("B")){
                    value="2";

                }
                else if(questionAnswerBean.getAnswer().equalsIgnoreCase("C")){
                    value="3";

                }
                else if(questionAnswerBean.getAnswer().equalsIgnoreCase("D")){
                    value="4";

                }
                if (value!=null&&!value.equalsIgnoreCase("")&&position == Integer.parseInt(value)) {
                    holder.answerText.setTextColor(context.getResources().getColor(R.color.white));
                    holder.columnName.setTextColor(context.getResources().getColor(R.color.white));
                    holder.answerView.setBackgroundColor(context.getResources().getColor(R.color.white_transparent_color));
                } else {
                    holder.answerText.setTextColor(context.getResources().getColor(R.color.black));
                    holder.columnName.setTextColor(context.getResources().getColor(R.color.black));
                    holder.answerView.setBackgroundColor(context.getResources().getColor(R.color.white));
                }

            }
        }

    }

    @Override
    public int getItemCount() {
        return optionList.size();
    }

    private String getPrefix(int position) {
        if (position == 0) {
            return "A.";
        } else if (position == 1) {
            return "B.";
        } else if (position == 2) {
            return "C.";
        } else if (position == 3) {
            return "D.";
        }
        return "";
    }

    public void setWrongAnswerView() {
        mHolder.answerText.setTextColor(context.getResources().getColor(R.color.white));
        mHolder.columnName.setTextColor(context.getResources().getColor(R.color.white));
        mHolder.answerView.setBackgroundColor(context.getResources().getColor(R.color.red_light));
    }

    public void setRightAnswerView() {
        correctAnswermHolder.answerText.setTextColor(context.getResources().getColor(R.color.white));
        correctAnswermHolder.columnName.setTextColor(context.getResources().getColor(R.color.white));
        correctAnswermHolder.answerView.setBackgroundColor(context.getResources().getColor(R.color.white_transparent_color));
    }


    public interface OnListItemClick {
        void onClick(int id, int pos);
    }

    class Holder extends RecyclerView.ViewHolder {
        final CardView answerView;
        final AppCompatTextView columnName;
        final AppCompatTextView answerText;

        public Holder(View itemView) {
            super(itemView);
            answerView = (CardView) itemView.findViewById(R.id.answerView);
            columnName = (AppCompatTextView) itemView.findViewById(R.id.columnName);
            answerText = (AppCompatTextView) itemView.findViewById(R.id.answerText);
        }
    }
}
