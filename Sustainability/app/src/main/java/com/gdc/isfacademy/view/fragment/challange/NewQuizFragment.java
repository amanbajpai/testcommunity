package com.gdc.isfacademy.view.fragment.challange;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.model.Options;
import com.gdc.isfacademy.model.QuestionAnswerBean;
import com.gdc.isfacademy.utils.AppConstants;
import com.gdc.isfacademy.view.activity.HomeActivity;
import com.gdc.isfacademy.view.adapter.QuizeListAdapter;
import com.gdc.isfacademy.view.customs.customfonts.OpenSansLightTextview;
import com.gdc.isfacademy.view.fragment.BaseFragment;
import com.gdc.isfacademy.view.fragment.QuizeFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashishthakur on 28/11/18.
 */

public class NewQuizFragment extends BaseFragment implements QuizeListAdapter.OnListItemClick {
    public static final String TAG = "QuizePagerFragment";
    public QuestionAnswerBean questionAnswerBeen;
    public ArrayList<Options> optionList;
    private QuizeListAdapter quizeListAdapter;
    private QuestionAnswerBean.Options option;
    private OpenSansLightTextview questionTv, questionNoTv;
    private String questionNo, totalQuestion;
    private QuizeFragment quizeFragment;
    // private CardView answer_three, answer_two, answer_one;
    private XRecyclerView pager_recylerview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.quize_pager_fragment, null);
        initView(view);
        quizeFragment = (QuizeFragment) getParentFragment();
        return view;
    }

    private void initView(View view) {
        optionList = new ArrayList<>();
        questionAnswerBeen = (QuestionAnswerBean) getArguments().getSerializable(AppConstants.QUESTION);
        questionNo = getArguments().getString(AppConstants.QUESTION_NUMBER);
        option = questionAnswerBeen.getOptions();
        totalQuestion = getArguments().getString(AppConstants.TOTAL_QUESTION);
        quizeFragment = ((QuizeFragment) NewQuizFragment.this.getParentFragment());

        if(option!=null){
            if(option.getA()!=null&&!option.getA().equalsIgnoreCase("")){
                optionList.add(new Options(option.getA(),false));
            }
            if(option.getB()!=null&&!option.getB().equalsIgnoreCase("")){
                optionList.add(new Options(option.getB(),false));
            }
            if(option.getC()!=null&&!option.getC().equalsIgnoreCase("")){
                optionList.add(new Options(option.getC(),false));
            }
            if(option.getD()!=null&&!option.getD().equalsIgnoreCase("")){
                optionList.add(new Options(option.getD(),false));
            }
        }

        pager_recylerview = (XRecyclerView) view.findViewById(R.id.pager_recylerview);
        pager_recylerview.setLoadingMoreEnabled(false);
        pager_recylerview.setPullRefreshEnabled(false);
        View voucherHeader = LayoutInflater.from(getActivity()).inflate(R.layout.header_pager_question,
                (ViewGroup) view.findViewById(android.R.id.content), false);
        pager_recylerview.addHeaderView(voucherHeader);
        questionTv = (OpenSansLightTextview) voucherHeader.findViewById(R.id.pager_header_question);
        questionNoTv = (OpenSansLightTextview) voucherHeader.findViewById(R.id.pager_header_questionNo);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        pager_recylerview.setLayoutManager(linearLayoutManager);
        quizeListAdapter = new QuizeListAdapter(getActivity(), optionList);
        pager_recylerview.setAdapter(quizeListAdapter);
        quizeListAdapter.setOnListItemClick(this);
        questionTv.setText(questionAnswerBeen.getQuestion());
        questionNoTv.setText(questionNo + "/" + totalQuestion);
        quizeListAdapter.setQuestionAnswerBean(questionAnswerBeen);
    }


    @Override
    public void onClick(int id, int pos) {
        switch (id) {
            case R.id.answerView:

                String value="-1";

                if(questionAnswerBeen.getAnswer().equalsIgnoreCase("A")){
                    value="1";
                }
                else if(questionAnswerBeen.getAnswer().equalsIgnoreCase("B")){
                    value="2";

                }
                else if(questionAnswerBeen.getAnswer().equalsIgnoreCase("C")){
                    value="3";

                }
                else if(questionAnswerBeen.getAnswer().equalsIgnoreCase("D")){
                    value="4";

                }
                boolean isCorrect = value.equalsIgnoreCase(String.valueOf(pos + 1));

                for (int i = 0; i < optionList.size(); i++) {
                    optionList.get(i).setSelected(false);
                }
                questionAnswerBeen.setAnswered(true);
                questionAnswerBeen.setCorrect(isCorrect);
                questionAnswerBeen.setUserSelectedAnswer(pos);
                optionList.get(pos).setSelected(true);
                quizeListAdapter.notifyDataSetChanged();
                // quizeFragment.setView();

                break;
        }

    }

/*    public void showWrongAnswerView() {
        quizeListAdapter.setWrongAnswerView();
        quizeListAdapter.setRightAnswerView();
    }

    public void showRightAnswerView() {
        quizeListAdapter.setRightAnswerView();
    }*/

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).backBtn.setVisibility(View.VISIBLE);
        ((HomeActivity) getActivity()).sliderIcon.setVisibility(View.GONE);
    }

}