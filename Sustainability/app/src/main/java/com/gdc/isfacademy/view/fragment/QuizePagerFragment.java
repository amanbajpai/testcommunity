package com.gdc.isfacademy.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.model.Options;
import com.gdc.isfacademy.model.Question;
import com.gdc.isfacademy.utils.AppConstants;
import com.gdc.isfacademy.view.activity.HomeActivity;
import com.gdc.isfacademy.view.adapter.QuizeListAdapter;
import com.gdc.isfacademy.view.customs.customfonts.OpenSansLightTextview;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;


@SuppressWarnings("ALL")
public class QuizePagerFragment extends BaseFragment implements QuizeListAdapter.OnListItemClick {
    public static final String TAG = "QuizePagerFragment";
    int selectedPosition = 0;
    private QuizeListAdapter quizeListAdapter;
    private List<Options> optionList;
    private OpenSansLightTextview questionTv, questionNoTv;
    public Question question;
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

        question = getArguments().getParcelable(AppConstants.QUESTION);
        questionNo = getArguments().getString(AppConstants.QUESTION_NUMBER);
        optionList = question.getOptions();
        totalQuestion = getArguments().getString(AppConstants.TOTAL_QUESTION);

        quizeFragment = ((QuizeFragment) QuizePagerFragment.this.getParentFragment());

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
        quizeListAdapter.setQuestion(question);
        pager_recylerview.setAdapter(quizeListAdapter);
        quizeListAdapter.setOnListItemClick(this);
        questionTv.setText(question.getQuestion());
        questionNoTv.setText(questionNo + "/" + totalQuestion);
    }


    @Override
    public void onClick(int id, int pos) {
        switch (id) {
            case R.id.answerView:
                selectedPosition = pos;
                boolean isCorrect = question.getAnswer().equalsIgnoreCase(String.valueOf(pos + 1));

                for (int i = 0; i < optionList.size(); i++) {
                    optionList.get(i).setSelected(false);
                }
                question.setAnswered(true);
                question.setCorrect(isCorrect);
                question.setUserSelectedAnswer(pos);
                optionList.get(pos).setSelected(true);
                quizeListAdapter.notifyDataSetChanged();
                quizeFragment.setView();

                break;
        }

    }

    public void showWrongAnswerView() {
        quizeListAdapter.setWrongAnswerView();
        quizeListAdapter.setRightAnswerView();
    }

    public void showRightAnswerView() {
        quizeListAdapter.setRightAnswerView();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).backBtn.setVisibility(View.VISIBLE);
        ((HomeActivity) getActivity()).sliderIcon.setVisibility(View.GONE);
    }

}
