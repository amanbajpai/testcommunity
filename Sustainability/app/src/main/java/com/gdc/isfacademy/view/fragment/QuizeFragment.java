package com.gdc.isfacademy.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.application.ISFApp;
import com.gdc.isfacademy.model.CommonResponse;
import com.gdc.isfacademy.model.QuestionAnswerBean;
import com.gdc.isfacademy.model.QuestionsResponse;
import com.gdc.isfacademy.model.QuizParentResponse;
import com.gdc.isfacademy.model.StudentRewardResponse;
import com.gdc.isfacademy.netcom.CheckNetworkState;
import com.gdc.isfacademy.utils.AppConstants;
import com.gdc.isfacademy.utils.MyPref;
import com.gdc.isfacademy.utils.ProjectUtil;
import com.gdc.isfacademy.view.activity.HomeActivity;
import com.gdc.isfacademy.view.adapter.QuizePagerAdapter;
import com.gdc.isfacademy.view.customs.otherlibs.CustomViewPager;
import com.gdc.isfacademy.view.fragment.challange.NewQuizFragment;
import com.google.gson.Gson;

import net.alexandroid.utils.indicators.IndicatorsView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
*
* Class Responsible for Quiz section,start daily Quiz.
*
* */
@SuppressWarnings("ALL")
public class QuizeFragment extends BaseFragment /*implements View.OnClickListener */{

    public static final String TAG = "QuizeFragment";
    private List<NewQuizFragment> fragmentList;
    private QuizePagerAdapter quizePagerAdapter;
    private Button previousBt, nextBt, finishBt;
    private CustomViewPager viewpager;
    private IndicatorsView indicatorsView;
    public ArrayList<QuestionAnswerBean>questionAnswerBeanArrayList;

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.quize_fragment_layout, null);
        initView(rootView);
       // createList();
        return rootView;
    }

    /**
     *
     *
     * Initailizing screen views.
     * @param view used to get the xml objects.
     *
     */
    private void initView(View view) {
        questionAnswerBeanArrayList=new ArrayList<>();
        fragmentList = new ArrayList<>();
        viewpager = (CustomViewPager) view.findViewById(R.id.viewpager);
        previousBt = (Button) view.findViewById(R.id.pager_previous_bt);
        nextBt = (Button) view.findViewById(R.id.pager_next_bt);
        nextBt.setText(getString(R.string.txt_check));
        finishBt = (Button) view.findViewById(R.id.pager_finish_bt);
        quizePagerAdapter = new QuizePagerAdapter(getChildFragmentManager(), fragmentList);
        viewpager.setAdapter(quizePagerAdapter);
        indicatorsView = (IndicatorsView) view.findViewById(R.id.indicatorsView);
        viewpager.setOffscreenPageLimit(0);
       // viewpager.setEnabled(false);
        indicatorsView.setViewPager(viewpager);
        indicatorsView.setSmoothTransition(true);
        indicatorsView.setIndicatorsClickChangePage(false);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                //setView();
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
       /* previousBt.setOnClickListener(this);
        nextBt.setOnClickListener(this);
        finishBt.setOnClickListener(this);*/
        getQuestAnswerListFromServer();
    }


    public void getQuestAnswerListFromServer(){
            if (!CheckNetworkState.isOnline(getActivity())) {
                ProjectUtil.showToast(getActivity(), getResources().getString(R.string.something_went_wrong));
                return;
            }
            try {
                showProgressDialog(getActivity());
                Call<QuizParentResponse> call = ISFApp.getAppInstance()
                        .getApi()
                        .getQuizQuestionAnswer(AppConstants.API_KEY,
                                AppConstants.CONTENT_TYPE,
                                MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_KEY));

                ProjectUtil.showLog(AppConstants.REQUEST, "" + call.request().url(), AppConstants.ERROR_LOG);

                call.enqueue(new Callback<QuizParentResponse>() {
                    @Override
                    public void onResponse(Call<QuizParentResponse> call, Response<QuizParentResponse> response) {
                        hideProgressDialog();
                        ProjectUtil.showLog(AppConstants.RESPONSE, "" + new Gson().toJson(response.body()), AppConstants.ERROR_LOG);
                        if (response.body() != null) {
                            if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.RESPONSE_CODE_SUCCUSS)) {
                                questionAnswerBeanArrayList=response.body().getQnas();
                                if(questionAnswerBeanArrayList!=null&&questionAnswerBeanArrayList.size()>0){
                                    createList(questionAnswerBeanArrayList);
                                }
                            } else if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.ERROR_CODE_STUDENT_KEY_NOT_MATCHED)) {
                                ProjectUtil.logoutFromApp(getActivity());
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<QuizParentResponse> call, Throwable t) {
                        if (getActivity() != null) {
                            ProjectUtil.showToast(getActivity(), getResources().getString(R.string.something_went_wrong));
                        }
                        t.printStackTrace();
                    }
                });
            } catch (Exception ex) {
                ex.printStackTrace();
            }


    }




    /**
     * Creating list to show question and answer for daily quiz.
     */
    private void createList(ArrayList<QuestionAnswerBean>questionAnswerBeen) {
        try {
            fragmentList.clear();

            for (int i = 0; i < questionAnswerBeen.size();i++ ) {
                NewQuizFragment fragment = new NewQuizFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable(AppConstants.QUESTION, questionAnswerBeen.get(i));
                bundle.putString(AppConstants.QUESTION_NUMBER, String.valueOf(i + 1));
                bundle.putString(AppConstants.TOTAL_QUESTION, String.valueOf(3));
                bundle.putSerializable(AppConstants.OPTIONS, questionAnswerBeen.get(i).getQuestion());

                fragment.setArguments(bundle);
                fragmentList.add(fragment);
            }

            quizePagerAdapter.notifyDataSetChanged();
            indicatorsView.setViewPager(viewpager);
            try {
                viewpager.setOffscreenPageLimit(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

  /*  *//**
     *
     * Inserting
     * @param view used to get the xml objects.
     *
     *//*
    private void addToDb(QuestionsResponse response) {
        try {
            ISFApp.getAppInstance().getDaoSession().getQuestionDao().deleteAll();
            ISFApp.getAppInstance().getDaoSession().getQuestionDao().insertInTx(response.getQuestions());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }*/

/*
    public void setView() {
        try {
            int position = viewpager.getCurrentItem();
            if (fragmentList.get(position).question.isAnswered()) {
                nextBt.setEnabled(true);
                nextBt.setClickable(true);
            } else {
                nextBt.setEnabled(false);
                nextBt.setClickable(false);
            }

            if (position + 1 == fragmentList.size()) {
                if (fragmentList.get(position).question.isQuestionChecked()) {
                    nextBt.setVisibility(View.GONE);
                    finishBt.setVisibility(View.VISIBLE);
                }
                if (fragmentList.get(position).question.isAnswered()) {
                    finishBt.setEnabled(true);
                    finishBt.setClickable(true);
                } else {

                    finishBt.setEnabled(false);
                    finishBt.setClickable(false);
                }
            } else {
                nextBt.setVisibility(View.VISIBLE);
                finishBt.setVisibility(View.GONE);
            }

            if (position > 0) {
                previousBt.setVisibility(View.VISIBLE);
            } else {
                previousBt.setVisibility(View.GONE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
*/


/*
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.pager_previous_bt:
                nextBt.setText(getString(R.string.txt_next));
                int position1 = viewpager.getCurrentItem();
                if (position1 > 0)
                    viewpager.setCurrentItem(position1 - 1, true);
                break;

            case R.id.pager_next_bt:
                //Check if Answer is correct locally
                QuizePagerFragment questionQuizePagerFragment = fragmentList.get(viewpager.getCurrentItem());
                if (questionQuizePagerFragment.question.isQuestionChecked()) {
                    fragmentList.get(viewpager.getCurrentItem()).showRightAnswerView();
                    int position2 = viewpager.getCurrentItem();
                    if (position2 < fragmentList.size() - 1)
                        viewpager.setCurrentItem(position2 + 1, true);
                    if (position2 + 1 == fragmentList.size()) {
                        if (fragmentList.get(position2).question.isQuestionChecked()) {
                            nextBt.setVisibility(View.GONE);
                            finishBt.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (fragmentList.get(position2 + 1).question.isQuestionChecked()) {
                            nextBt.setText(getString(R.string.txt_next));
                        } else {
                            nextBt.setText(getString(R.string.txt_check));
                        }
                    }
                } else {
                    questionQuizePagerFragment.question.setQuestionChecked(true);
                    boolean isCorrect = questionQuizePagerFragment.question.isCorrect();
                    nextBt.setText(getString(R.string.txt_check));
                    if (isCorrect) {
                        nextBt.setText(getString(R.string.txt_next));
                        fragmentList.get(viewpager.getCurrentItem()).showRightAnswerView();
                        if (viewpager.getCurrentItem() + 1 == fragmentList.size()) {
                            if (fragmentList.get(viewpager.getCurrentItem()).question.isQuestionChecked()) {
                                nextBt.setVisibility(View.GONE);
                                finishBt.setVisibility(View.VISIBLE);
                            }
                        } else {
                            nextBt.setVisibility(View.VISIBLE);
                            finishBt.setVisibility(View.GONE);
                            nextBt.setText(getString(R.string.txt_next));
                        }
                    } else {
                        if (viewpager.getCurrentItem() + 1 == fragmentList.size()) {
                            if (fragmentList.get(viewpager.getCurrentItem()).question.isQuestionChecked()) {
                                nextBt.setVisibility(View.GONE);
                                finishBt.setVisibility(View.VISIBLE);
                            }
                        } else {
                            nextBt.setVisibility(View.VISIBLE);
                            finishBt.setVisibility(View.GONE);
                            nextBt.setText(getString(R.string.txt_next));
                        }

                        fragmentList.get(viewpager.getCurrentItem()).showWrongAnswerView();
                    }
                }


                break;

            case R.id.pager_finish_bt:
               // submitQuestion();
                break;
        }
    }
*/

/*
    private void submitQuestion() {
        try {
            int correct = 0;
            for (int i = 0; i < fragmentList.size(); i++) {
                if (fragmentList.get(i).question.isCorrect())
                    correct++;
            }
            Log.d("correct", "" + correct);

            showProgressDialog(getActivity());
            Call<CommonResponse> call = ISFApp.getAppInstance()
                    .getApi()
                    .submitQuestion(AppConstants.API_KEY,
                            AppConstants.CONTENT_TYPE,
                            MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_KEY),
                            String.valueOf(correct));

            ProjectUtil.showLog(AppConstants.REQUEST, "" + call.request().url(), AppConstants.ERROR_LOG);

            final int finalCorrect = correct;
            call.enqueue(new Callback<CommonResponse>() {
                @Override
                public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                    ProjectUtil.showLog(AppConstants.RESPONSE, "" + new Gson().toJson(response.body()), AppConstants.ERROR_LOG);
                    hideProgressDialog();
                    if (response.body().getResponseCode().equalsIgnoreCase("success")) {
                        finishQuiz(finalCorrect, true);
                        MyPref.getInstance(getActivity()).writeIntegerPrefs(MyPref.QUIZ_COUNT, finalCorrect);
                    } else if (response.body().getResponseCode().equalsIgnoreCase("C0701")) {
                        MyPref.getInstance(getActivity()).writeIntegerPrefs(MyPref.QUIZ_COUNT, finalCorrect);
                        finishQuiz(finalCorrect, false);
                        ProjectUtil.showToast(ISFApp.getAppInstance().getApplicationContext(), response.body().getResponseMessage());

                    }
                }

                @Override
                public void onFailure(Call<CommonResponse> call, Throwable t) {

                    ProjectUtil.showToast(getActivity(), getResources().getString(R.string.something_went_wrong));
                    t.printStackTrace();
                    hideProgressDialog();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
*/

/*
    private void finishQuiz(int answerCount, boolean submitStatus) {
        try {

            MyPref.getInstance(getActivity())
                    .writePrefs(MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_ID)
                            , ProjectUtil.getTodayDate());

            int answeredCount = MyPref.getInstance(getActivity()).getAnsweredCount();

            if (answeredCount == 20)
                answeredCount = 0;
            else
                answeredCount += 3;

            MyPref.getInstance(getActivity()).setAnsweredCount(answeredCount);

            if (!submitStatus) {
                getActivity().onBackPressed();

            } else {
                Bundle bundle = new Bundle();
                bundle.putInt(AppConstants.ANSWER_COUNT, answerCount);
                ((HomeActivity) getActivity()).pushFragments(QuizeCompletedFragement.newInstance(), bundle, true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
*/

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).backBtn.setVisibility(View.VISIBLE);
        ((HomeActivity) getActivity()).sliderIcon.setVisibility(View.GONE);
    }
}
