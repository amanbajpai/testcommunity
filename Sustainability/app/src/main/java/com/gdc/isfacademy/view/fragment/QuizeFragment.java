package com.gdc.isfacademy.view.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.application.ISFApp;
import com.gdc.isfacademy.model.CommonResponse;
import com.gdc.isfacademy.model.QuestionsResponse;
import com.gdc.isfacademy.utils.AppConstants;
import com.gdc.isfacademy.utils.MyPref;
import com.gdc.isfacademy.utils.ProjectUtil;
import com.gdc.isfacademy.view.activity.HomeActivity;
import com.gdc.isfacademy.view.adapter.QuizePagerAdapter;
import com.gdc.isfacademy.view.customs.otherlibs.CustomViewPager;
import com.google.gson.Gson;

import net.alexandroid.utils.indicators.IndicatorsView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by akshaydashore on 3/4/18
 */
public class QuizeFragment extends BaseFragment implements View.OnClickListener {

    public static final String TAG = "QuizeFragment";
    List<QuizePagerFragment> fragmentList;
    QuizePagerAdapter quizePagerAdapter;
    Button previousBt, nextBt, finishBt;
    private CustomViewPager viewpager;
    private IndicatorsView indicatorsView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.quize_fragment_layout, null);
        initView(rootView);

        createList();

        return rootView;
    }

    private void initView(View view) {

        fragmentList = new ArrayList<>();
        viewpager = (CustomViewPager) view.findViewById(R.id.viewpager);
        previousBt = (Button) view.findViewById(R.id.pager_previous_bt);
        nextBt = (Button) view.findViewById(R.id.pager_next_bt);
        finishBt = (Button) view.findViewById(R.id.pager_finish_bt);
        quizePagerAdapter = new QuizePagerAdapter(getChildFragmentManager(), fragmentList);
        viewpager.setAdapter(quizePagerAdapter);
        indicatorsView = (IndicatorsView) view.findViewById(R.id.indicatorsView);
        viewpager.setOffscreenPageLimit(0);
        viewpager.setEnabled(false);

        indicatorsView.setViewPager(viewpager);

        indicatorsView.setSmoothTransition(true);

        indicatorsView.setIndicatorsClickChangePage(true);

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                setView();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        previousBt.setOnClickListener(this);
        nextBt.setOnClickListener(this);
        finishBt.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
//        initView(rootView);
    }

    private void createList() {
        try {
            int answeredCount = MyPref.getInstance(getActivity()).getAnsweredCount();

            QuestionsResponse response = new Gson().fromJson(ProjectUtil.loadJSONFromAsset(getActivity()), QuestionsResponse.class);

            fragmentList.clear();

            for (int i = 0; i < 5; i++, answeredCount++) {

                QuizePagerFragment fragment = new QuizePagerFragment();

                Bundle bundle = new Bundle();
                bundle.putParcelable("question", response.getQuestions().get(answeredCount));
                bundle.putString("questionNo", String.valueOf(i + 1));
                bundle.putString("totalQuestion", String.valueOf(5));

                fragment.setArguments(bundle);

                fragmentList.add(fragment);
            }

            quizePagerAdapter.notifyDataSetChanged();
            indicatorsView.setViewPager(viewpager);

            addToDb(response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addToDb(QuestionsResponse response) {
        try {
            ISFApp.getAppInstance().getDaoSession().getQuestionDao().deleteAll();
            ISFApp.getAppInstance().getDaoSession().getQuestionDao().insertInTx(response.getQuestions());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

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
                nextBt.setVisibility(View.GONE);
                finishBt.setVisibility(View.VISIBLE);

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


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.pager_previous_bt:

                int position1 = viewpager.getCurrentItem();
                if (position1 > 0)
                    viewpager.setCurrentItem(position1 - 1, true);
                break;

            case R.id.pager_next_bt:

                int position2 = viewpager.getCurrentItem();
                if (position2 < fragmentList.size() - 1)
                    viewpager.setCurrentItem(position2 + 1, true);
                break;

            case R.id.pager_finish_bt:
                submitQuestion();
                break;
        }
    }

    private void submitQuestion() {
        try {
            int correct = 0;
            for (int i = 0; i < fragmentList.size(); i++) {
                if (fragmentList.get(i).question.isCorrect())
                    correct++;
            }

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
                    } else if (response.body().getResponseCode().equalsIgnoreCase("C0701")) {
                        finishQuiz(finalCorrect, false);
                    }
                    ProjectUtil.showToast(ISFApp.getAppInstance().getApplicationContext(), response.body().getResponseMessage());
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

    private void finishQuiz(int answerCount, boolean submitStatus) {
        try {
            MyPref.getInstance(getActivity()).setAnsweredDate(ProjectUtil.getTodayDate());
            int answeredCount = MyPref.getInstance(getActivity()).getAnsweredCount();

            if (answeredCount == 20)
                answeredCount = 0;
            else
                answeredCount += 5;

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
}
