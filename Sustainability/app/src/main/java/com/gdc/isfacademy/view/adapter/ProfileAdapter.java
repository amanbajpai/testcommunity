package com.gdc.isfacademy.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.model.LogStudentResponse;
import com.gdc.isfacademy.utils.AppConstants;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@SuppressWarnings("ALL")
public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.Holder> {

    Context context;
    List<LogStudentResponse> logStudentResponses;


    public ProfileAdapter(Context context) {
        this.context = context;
    }

    public ProfileAdapter(Context context, List<LogStudentResponse> logStudentResponses) {
        this.context = context;
        this.logStudentResponses = logStudentResponses;
    }

    public void setList(Context context, List<LogStudentResponse> logStudentResponses) {
        this.context = context;
        this.logStudentResponses = logStudentResponses;
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.list_item_post_profile_layout, null);

        Holder holder = new Holder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(Holder holder, int position) {
        if (position == 0) {
            holder.labelStudengLog.setVisibility(View.VISIBLE);
        } else {
            holder.labelStudengLog.setVisibility(View.GONE);
        }

        if (logStudentResponses.get(position).getType().equalsIgnoreCase(AppConstants.typeStudenLogUser)) {
            String value = new BigDecimal(logStudentResponses.get(position).getCount()).setScale(1, BigDecimal.ROUND_HALF_UP).toString();
            holder.studentMessageTv.setText( ""+value + context.getString(R.string.profile_you_have_saved_user));
            holder.type_log_imagview.setImageResource(R.drawable.user_building_type);
        } else if (logStudentResponses.get(position).getType().equalsIgnoreCase(AppConstants.typeStudenLogBuilding)) {
            String value = new BigDecimal(logStudentResponses.get(position).getCount()).setScale(1, BigDecimal.ROUND_HALF_UP).toString();
            holder.studentMessageTv.setText(""+value + context.getString(R.string.profile_you_have_saved_building));
            holder.type_log_imagview.setImageResource(R.drawable.user_building_type);

        }
        else if (logStudentResponses.get(position).getType().equalsIgnoreCase(AppConstants.typeStudenLogShare)) {
            //String value = new BigDecimal(logStudentResponses.get(position).getCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
            double value = logStudentResponses.get(position).getCount();
            int count=(int)value;
            holder.studentMessageTv.setText(context.getString(R.string.txt_shared_daily_result));
            holder.type_log_imagview.setImageResource(R.drawable.share_type);

        }
        else if (logStudentResponses.get(position).getType().equalsIgnoreCase(AppConstants.typeStudenLogChallange)) {
            double value = logStudentResponses.get(position).getCount();
            int count=(int)value;
            if(count==1){
                holder
                        .studentMessageTv.setText(context.getString(R.string.txt_points_in_your_daily_quiz)+" "+
                        count+" "+
                        context.getString(R.string. txt_correct_answer_if_less_then_one));
            }
            else {
                holder.studentMessageTv.setText(context.getString(R.string.txt_points_in_your_daily_quiz)+" "+
                        count+" "+
                        context.getString(R.string.txt_correct_answers));
            }

            holder.type_log_imagview.setImageResource(R.drawable.challnge_type);

        }

        if (logStudentResponses.get(position).getValue() != null) {
            //String points = new BigDecimal(logStudentResponses.get(position).getValue()).setScale(1, BigDecimal.ROUND_HALF_UP).toString();
            String value=String.format("%.0f", logStudentResponses.get(position).getValue());
            holder.points_log_tv.setText("+"+value+context.getString(R.string.txt_pts));
        }

        holder.date_log_tv.setText(toDate(Long.parseLong(logStudentResponses.get(position).getTs())));

    }

    @Override
    public int getItemCount() {
        return logStudentResponses.size();
    }


    class Holder extends RecyclerView.ViewHolder {
        final LinearLayout labelStudengLog;
        final AppCompatTextView studentMessageTv;
        final AppCompatTextView date_log_tv;
        final AppCompatTextView points_log_tv;
        final ImageView type_log_imagview;

        public Holder(View itemView) {
            super(itemView);
            labelStudengLog = (LinearLayout) itemView.findViewById(R.id.labelStudengLog);
            studentMessageTv = (AppCompatTextView) itemView.findViewById(R.id.studentMessageTv);
            date_log_tv = (AppCompatTextView) itemView.findViewById(R.id.date_log_tv);
            points_log_tv = (AppCompatTextView) itemView.findViewById(R.id.points_log_tv);
            type_log_imagview = (ImageView) itemView.findViewById(R.id.type_image);
        }
    }



      /*
    *
    * Get date from timestamp
    *
    * */

    @SuppressLint("SimpleDateFormat")
    public static String toDate(long timestamp) {
        Date date = new Date(timestamp);
        return new SimpleDateFormat("dd/MMM/yyyy").format(date);

    }
}
