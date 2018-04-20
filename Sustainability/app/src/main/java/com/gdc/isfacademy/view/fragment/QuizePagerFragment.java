package com.gdc.isfacademy.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.model.Options;
import com.gdc.isfacademy.model.Question;
import com.gdc.isfacademy.view.adapter.QuizeListAdapter;
import com.gdc.isfacademy.view.customs.customfonts.OpenSansLightTextview;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by akshaydashore on 3/4/18
 */
public class QuizePagerFragment extends BaseFragment implements QuizeListAdapter.OnListItemClick {
    public static final String TAG = "QuizePagerFragment";
    QuizeListAdapter quizeListAdapter;
    List<Options> optionList;
    OpenSansLightTextview questionTv, questionNoTv;
    // private CardView answer_three, answer_two, answer_one;
    private XRecyclerView pager_recylerview;

    Question question;
    String questionNo, totalQuestion;

    QuizeFragment quizeFragment;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            Log.d("print",""+getArguments().getString("data"));

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quize_pager_fragment, null);
        initView(view);
        quizeFragment = (QuizeFragment) getParentFragment();
        return view;
    }

    private void initView(View view) {

        question = getArguments().getParcelable("question");
        questionNo = getArguments().getString("questionNo");
        totalQuestion = getArguments().getString("totalQuestion");
        optionList = question.getOptions();

        quizeFragment = ((QuizeFragment)QuizePagerFragment.this.getParentFragment());

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
      /*  answer_three = (CardView) view.findViewById(R.id.answer_three);
        answer_two = (CardView) view.findViewById(R.id.answer_three);
        answer_one = (CardView) view.findViewById(R.id.answer_three);

        answer_three.setOnClickListener(this);
        answer_two.setOnClickListener(this);
        answer_one.setOnClickListener(this);*/
        questionTv.setText(question.getQuestion());
        questionNoTv.setText(questionNo+"/"+totalQuestion);
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);

        int id = view.getId();
        switch (id) {

          /*  case R.id.answer_three:
            case R.id.answer_two:
            case R.id.answer_one:
                ((HomeActivity) getActivity()).pushFragments(new QuizeCompletedFragement(), null, true);
                break;*/
        }
    }

    @Override
    public void onClick(int id, int pos) {
        switch (id) {
            case R.id.answerView:

                boolean isCorrect = question.getAnswer().equalsIgnoreCase(String.valueOf(pos+1));

                for (int i = 0; i < optionList.size(); i++) {
                    optionList.get(i).setSelected(false);
                }

                question.setAnswered(true);
                question.setCorrect(isCorrect);
                optionList.get(pos).setSelected(true);
                quizeListAdapter.notifyDataSetChanged();

                quizeFragment.setView();

//                if(pos==optionList.size())
//                {
//                    ((HomeActivity) getActivity()).pushFragments(new QuizeCompletedFragement(), null, true);
//                }
                break;
        }

    }
}
