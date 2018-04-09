package com.energybox.isf.view.fragment;

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

import com.energybox.isf.model.SliderBean;
import com.energybox.isf.R;
import com.energybox.isf.view.activity.HomeActivity;
import com.energybox.isf.view.adapter.LeftMenuAdapter;

import java.util.ArrayList;

/**
 * Created by ashishthakur on 29/3/18.
 */

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


    ImageView cancelDrawer;
    ArrayList<SliderBean>sliderBeanArrayList;
    LeftMenuAdapter leftMenuAdapter;
    private RecyclerView recyclerView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private View containerView;
    private FragmentDrawerListener drawerListener;
    private AppCompatTextView txt_menu_label;
    private  ImageView cancel;

    public LeftMenuFragment() {

    }

    public void setDrawerListener(FragmentDrawerListener listener) {
        this.drawerListener = listener;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // drawer labels

    }



    public void setIconList() {
        sliderBeanArrayList=new ArrayList<>();
        sliderBeanArrayList.add(new SliderBean(getResources().getString(R.string.txt_real_time)));
        sliderBeanArrayList.add(new SliderBean(getResources().getString(R.string.txt_about_isf)));
        sliderBeanArrayList.add(new SliderBean(getResources().getString(R.string.txt_terms_condition)));
        sliderBeanArrayList.add(new SliderBean(getResources().getString(R.string.txt_privacy_policy)));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflating view layout
        View layout = inflater.inflate(R.layout.fragment_right_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.slide_menu_recylerview);
        txt_menu_label = (AppCompatTextView) layout.findViewById(R.id.menu_label);
        cancel = (ImageView) layout.findViewById(R.id.cancelDrawer);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity)getActivity()).slideDrawer();

            }
        });
        txt_menu_label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity)getActivity()).slideDrawer();
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

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                //  toolbar.setAlpha(1 - slideOffset / 2);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    public static interface ClickListener {
        public void onClick(View view, int position);
        public void onLongClick(View view, int position);
    }

    public interface FragmentDrawerListener {
        public void onLeftMenuDrawerSelected(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

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
