package com.gdc.isfacademy.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.model.RewardStudentResponse;
import com.gdc.isfacademy.utils.AppConstants;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by ashishthakur on 5/4/18.
 */

@SuppressWarnings("ALL")
public class RewardsAdapter extends RecyclerView.Adapter<RewardsAdapter.Holder> {

    Context context;
    List<RewardStudentResponse> rewardListResponses;
    OnRewardItemClickListner onRewardItemClickListner;
    XRecyclerView xRecyclerView;

    public RewardsAdapter(Context context) {
        this.context = context;
    }

    public RewardsAdapter(Context context, List<RewardStudentResponse> rewardListResponses,XRecyclerView xRecyclerView) {
        this.context = context;
        this.rewardListResponses = rewardListResponses;
        this.xRecyclerView=xRecyclerView;
    }

    public void setList(Context context, List<RewardStudentResponse> rewardListResponses) {
        this.context = context;
        this.rewardListResponses = rewardListResponses;
        notifyDataSetChanged();
    }

    public void setOnRewardItemClickListner(OnRewardItemClickListner onRewardItemClickListner) {
        this.onRewardItemClickListner = onRewardItemClickListner;
    }

    @Override
    public RewardsAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.list_items_rewards_adapter, null);
        RewardsAdapter.Holder holder = new RewardsAdapter.Holder(view);
        return holder;
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(final RewardsAdapter.Holder holder, final int position) {
        holder.rewards_relative_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRewardItemClickListner.onClick(v.getId(), position,"");
            }
        });

        if (rewardListResponses.get(position).isItemOpen()) {
            holder.qr_code_relative_layout.setVisibility(View.VISIBLE);

        } else {
            holder.qr_code_relative_layout.setVisibility(View.GONE);

        }
        holder.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.staffCode.setText("");
                onRewardItemClickListner.onClick(v.getId(), position,"");

            }
        });
        holder.redeemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRewardItemClickListner.onClick(v.getId(), position,holder.staffCode.getText().toString());

            }
        });
        holder.closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRewardItemClickListner.onClick(v.getId(), position,"");

            }
        });

        holder.staffCode.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                char[] cArr = s.toString().trim().toCharArray();
                holder.codeTextOne.setText("");
                holder. codeTextTwo.setText("");
                holder.codeTextThree.setText("");
                holder.  codeTextFour.setText("");
                holder.sepOne.setVisibility(View.VISIBLE);
                holder.sepTwo.setVisibility(View.VISIBLE);
                holder.sepThree.setVisibility(View.VISIBLE);
                holder.sepFour.setVisibility(View.VISIBLE);
                for (int i = 0; i < s.length(); i++) {
                    if (i == 0) {
                        holder. codeTextOne.setText(String.valueOf(cArr[i]));
                        holder. sepOne.setVisibility(View.GONE);
                    }
                    if (i == 1) {
                        holder.    codeTextTwo.setText(String.valueOf(cArr[i]));
                        holder.sepTwo.setVisibility(View.GONE);
                    }
                    if (i == 2) {
                        holder.    codeTextThree.setText(String.valueOf(cArr[i]));
                        holder.sepThree.setVisibility(View.GONE);
                    }
                    if (i == 3) {
                        holder.codeTextFour.setText(String.valueOf(cArr[i]));
                        holder. sepFour.setVisibility(View.GONE);
                    }
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
            }
        });

        if (rewardListResponses.get(position).getStatus().equalsIgnoreCase(AppConstants.USED_REWARD)){
            holder.redeemLayout.setVisibility(View.GONE);
            holder.alreadyRedeemLayout.setVisibility(View.VISIBLE);
            holder.expires_days_text.setText(context.getString(R.string.txt_flag_redeemed));
        }
        else {
            holder.redeemLayout.setVisibility(View.VISIBLE);
            holder.alreadyRedeemLayout.setVisibility(View.GONE);
            holder.expires_days_text.setText(context.getString(R.string.txt_expires_in)+" "+
                    getDaysDifference(mymethod(new Date().getTime()),
                            mymethod(Long.parseLong(rewardListResponses.get(position).getExpTs())))+"d" );

        }
        holder.rewardType.setText(rewardListResponses.get(position).getType());



    }


    public Date mymethod(long timeInMilisecond){
        Date futuredate=null;
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        try {
             futuredate = format.parse(getDate(timeInMilisecond));
            System.out.println(futuredate);
            return futuredate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return futuredate;
    }


    @Override
    public int getItemCount() {
        return rewardListResponses.size();
    }

    /*
    *
    * Reward Item Call back listner
    *
    * */
    public interface OnRewardItemClickListner {
        void onClick(int id, int pos,String staffCode);
    }

    /*
    *
    * Method to get date from timestamap
    *
    * */
    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        String date = DateFormat.format("MM/dd/yyyy", cal).toString();
        return date;
    }

   /*
   *
   * Method returns days remaining to redeem reward before expired.
   *
   * */
    private int getDaysDifference(Date fromDate, Date toDate)
    {
        if(fromDate==null||toDate==null)
            return 0;

        return (int)( (toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24));
    }


   public class Holder extends RecyclerView.ViewHolder {
        final RelativeLayout rewards_relative_layout;
        final LinearLayout qr_code_relative_layout;
        final EditText staffCode;
        final View sepOne, sepTwo, sepThree, sepFour;
        final TextView codeTextOne, codeTextTwo, codeTextThree,
                codeTextFour,cancelBtn,redeemBtn,expires_days_text;
        final FrameLayout redeemLayout;
        final LinearLayout alreadyRedeemLayout;
        final TextView rewardType,closeBtn;

        public Holder(View itemView) {
            super(itemView);
            rewards_relative_layout = (RelativeLayout) itemView.findViewById(R.id.rewards_relative_layout);
            qr_code_relative_layout = (LinearLayout) itemView.findViewById(R.id.qr_code_relative_layout);
            staffCode = (EditText) itemView.findViewById(R.id.et_staff_code);
            sepOne = (View) itemView.findViewById(R.id.seprator_one);
            sepTwo = (View) itemView.findViewById(R.id.seprator_two);
            sepThree = (View) itemView.findViewById(R.id.seprator_three);
            sepFour = (View) itemView.findViewById(R.id.seprator_four);
            codeTextOne = (TextView) itemView.findViewById(R.id.tv_code_one);
            codeTextTwo = (TextView) itemView.findViewById(R.id.tv_code_two);
            codeTextThree = (TextView) itemView.findViewById(R.id.tv_code_three);
            codeTextFour = (TextView) itemView.findViewById(R.id.tv_code_four);
            cancelBtn = (TextView) itemView.findViewById(R.id.cancelBtn);
            redeemBtn = (TextView) itemView.findViewById(R.id.redeemBtn);
            redeemLayout = (FrameLayout) itemView.findViewById(R.id.redeemLayout);
            expires_days_text = (TextView) itemView.findViewById(R.id.expires_days_text);
            alreadyRedeemLayout=(LinearLayout)itemView.findViewById(R.id.alreadyRedeemLayout);
            rewardType=(TextView)itemView.findViewById(R.id.reward_type);
            closeBtn=(TextView)itemView.findViewById(R.id.closeBtn);

        }
    }
}
