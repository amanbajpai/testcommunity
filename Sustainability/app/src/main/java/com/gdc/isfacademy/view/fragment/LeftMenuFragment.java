package com.gdc.isfacademy.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gdc.isfacademy.model.SliderBean;
import com.gdc.isfacademy.R;
import com.gdc.isfacademy.view.activity.HomeActivity;
import com.gdc.isfacademy.view.activity.SplashActivity;
import com.gdc.isfacademy.view.adapter.LeftMenuAdapter;

import java.util.ArrayList;


@SuppressWarnings("ALL")
public class LeftMenuFragment extends BaseFragment {

/*    RecyclerView recyclerView;
    ImageView cancelDrawer;
    ArrayList<SliderBean>sliderBeanArrayList;
    LeftMenuAdapter leftMenuAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflating view layout
        View layout = inflater.inflate(R.layout.fragment_right_drawer, container, false);
        sliderBeanArrayList=new ArrayList<>();
        recyclerView = (RecyclerView) layout.findViewById(R.id.slide_menu_recylerview);
        cancelDrawer = (ImageView) layout.findViewById(R.id.cancelDrawer);
        setIconList();
        leftMenuAdapter = new LeftMenuAdapter(getActivity(), sliderBeanArrayList);
        recyclerView.setAdapter(leftMenuAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    public void setIconList() {
        sliderBeanArrayList.add(new SliderBean(getResources().getString(R.string.txt_real_time)));
        sliderBeanArrayList.add(new SliderBean(getResources().getString(R.string.txt_about_isf)));
        sliderBeanArrayList.add(new SliderBean(getResources().getString(R.string.txt_terms_condition)));
        sliderBeanArrayList.add(new SliderBean(getResources().getString(R.string.txt_privacy_policy)));
    }

    public LeftMenuAdapter getAdapter(){
        return leftMenuAdapter;
    }*/


    public AppCompatTextView privacyPolicy, termsCondition, logoutAppTv;
    ImageView cancelDrawer;
    ArrayList<SliderBean> sliderBeanArrayList;
    LeftMenuAdapter leftMenuAdapter;
    private RecyclerView recyclerView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private View containerView;
    private FragmentDrawerListener drawerListener;
    private AppCompatTextView txt_menu_label;
    private ImageView cancel;


    public LeftMenuFragment() {

    }

    public void setDrawerListener(FragmentDrawerListener listener) {
        this.drawerListener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // drawer labels

    }


    public void setIconList() {
        sliderBeanArrayList = new ArrayList<>();

        if (SplashActivity.isMapStatusFromSplash) {
            privacyPolicy.setVisibility(View.GONE);
            termsCondition.setVisibility(View.GONE);
            logoutAppTv.setVisibility(View.VISIBLE);
            sliderBeanArrayList.add(new SliderBean(getResources().getString(R.string.txt_real_time)));
        } else {
            privacyPolicy.setVisibility(View.VISIBLE);
            termsCondition.setVisibility(View.VISIBLE);
            logoutAppTv.setVisibility(View.VISIBLE);
            sliderBeanArrayList.add(new SliderBean(getResources().getString(R.string.txt_real_time)));
            sliderBeanArrayList.add(new SliderBean(getResources().getString(R.string.txt_about_isf)));
            sliderBeanArrayList.add(new SliderBean(getResources().getString(R.string.txt_slider_faq)));
        }

       /* sliderBeanArrayList.add(new SliderBean(getResources().getString(R.string.txt_terms_condition)));
        sliderBeanArrayList.add(new SliderBean(getResources().getString(R.string.txt_privacy_policy)));
        sliderBeanArrayList.add(new SliderBean(getResources().getString(R.string.txt_logout)));*/

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflating view layout
        View layout = inflater.inflate(R.layout.fragment_right_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.slide_menu_recylerview);
        txt_menu_label = (AppCompatTextView) layout.findViewById(R.id.menu_label);
        cancel = (ImageView) layout.findViewById(R.id.cancelDrawer);
        privacyPolicy = (AppCompatTextView) layout.findViewById(R.id.privacyPolicy);
        termsCondition = (AppCompatTextView) layout.findViewById(R.id.termsCondition);
        logoutAppTv = (AppCompatTextView) layout.findViewById(R.id.logoutAppTv);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity) getActivity()).slideDrawer();

            }
        });
        txt_menu_label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity) getActivity()).slideDrawer();
            }
        });
        setIconList();
        leftMenuAdapter = new LeftMenuAdapter(getActivity(), sliderBeanArrayList);
        recyclerView.setAdapter(leftMenuAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                drawerListener.onLeftMenuDrawerSelected(view, position);
                mDrawerLayout.closeDrawer(containerView);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

        return layout;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

        };
        mDrawerToggle.setDrawerIndicatorEnabled(false);

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();

            }
        });
    }


    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public interface FragmentDrawerListener {
        void onLeftMenuDrawerSelected(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private final GestureDetector gestureDetector;
        private final ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
