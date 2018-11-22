package com.gdc.isfacademy.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.application.ISFApp;
import com.gdc.isfacademy.utils.BackManager;
import com.gdc.isfacademy.utils.ProjectUtil;
import com.gdc.isfacademy.view.fragment.AboutISfFragment;
import com.gdc.isfacademy.view.fragment.AddFriendFragment;
import com.gdc.isfacademy.view.fragment.BadgeDetailFragment;
import com.gdc.isfacademy.view.fragment.ChallengeFragment;
import com.gdc.isfacademy.view.fragment.HomeFragment;
import com.gdc.isfacademy.view.fragment.HowMuchSaveFragment;
import com.gdc.isfacademy.view.fragment.LeftMenuFragment;
import com.gdc.isfacademy.view.fragment.PrivacyPolicyFragment;
import com.gdc.isfacademy.view.fragment.ProfileFragment;
import com.gdc.isfacademy.view.fragment.QuizeCompletedFragement;
import com.gdc.isfacademy.view.fragment.QuizeFragment;
import com.gdc.isfacademy.view.fragment.RealTimeSchoolFragment;
import com.gdc.isfacademy.view.fragment.RewardsFragment;
import com.gdc.isfacademy.view.fragment.SendFriendRequestFragment;
import com.gdc.isfacademy.view.fragment.TermsConditionFragment;

@SuppressWarnings("ALL")
public class HomeActivity extends BaseActivity implements
        LeftMenuFragment.FragmentDrawerListener,
        View.OnClickListener,
        FragmentManager.OnBackStackChangedListener {
    public BottomNavigationView bottomNavigationView;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public DrawerLayout drawerLayout;
    public ImageView sliderIcon, searchIcon;
    public LeftMenuFragment leftMenuFragment;
    public ImageView backBtn;
    public Toolbar toolbar;
    View containerFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setUpToolbar();
        init();
        setNavagionDrawer();
        setBottomNavigation();
        ProjectUtil.setAlarmReminder(this);


    }

    public void setUpToolbar() {
        // Set a toolbar to replace the action bar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportFragmentManager().addOnBackStackChangedListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        ISFApp.getAppInstance().trackScreenView("Home Screen");

    }

    public void init() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        sliderIcon = (ImageView) findViewById(R.id.sliderIcon);
        searchIcon = (ImageView) findViewById(R.id.serachIcon);
        backBtn = (ImageView) findViewById(R.id.backBtn);
        sliderIcon.setOnClickListener(this);
        backBtn.setOnClickListener(this);

    }


    public void setNavagionDrawer() {
        leftMenuFragment = (LeftMenuFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_right_drawer);
        leftMenuFragment.setUp(R.id.fragment_right_drawer, drawerLayout);
        leftMenuFragment.setDrawerListener(this);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });

    }


    /*
       *
       * method used to open and close drawer..
       *
       * */

    public void slideDrawer() {
        containerFrag = findViewById(R.id.fragment_right_drawer);
        if (!drawerLayout.isDrawerOpen(containerFrag))
            drawerLayout.openDrawer(containerFrag);
        else
            drawerLayout.closeDrawer(containerFrag);
    }


    public void setBottomNavigation() {
        bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                selectedFragment = HomeFragment.newInstance();
                                break;
                            case R.id.navigation_challenge:
                                selectedFragment = ChallengeFragment.newInstance();
                                break;
                            case R.id.navigation_rewards:
                                selectedFragment = RewardsFragment.newInstance();
                                break;
                            case R.id.navigation_profile:
                                selectedFragment = ProfileFragment.newInstance();
                                break;
                        }
                        pushFragments(selectedFragment, null, true);
                        return true;
                    }
                });

        pushFragments(HomeFragment.newInstance(), null, false);
        ProjectUtil.disableShiftMode(bottomNavigationView);
    }


    public void pushFragments(Fragment fragment, Bundle bundle, boolean isAddbackStack) {
        try {
            BackManager.manageBackStack(this, fragment.getClass().getSimpleName());
            if (bundle != null)
                fragment.setArguments(bundle);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (!(fragment.getClass().getSimpleName().equalsIgnoreCase(HomeFragment.class.getSimpleName())
                    && fragmentManager.getBackStackEntryCount() == 0)) {
                fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
            }
            fragmentTransaction.add(R.id.fragment_container, fragment, fragment.getClass().getSimpleName());
            fragmentTransaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onLeftMenuDrawerSelected(View view, int position) {
        drawerLayout.closeDrawer(containerFrag);
        switch (position) {
            case 0:
                pushFragments(RealTimeSchoolFragment.newInstance(),null,false);

              /*  Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.Visionaries777.isfacademy");
                if (launchIntent != null) {
                    startActivity(launchIntent);//null pointer check in case package name was not found
                }
                else {
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.Visionaries777.isfacademy")));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.Visionaries777.isfacademy&hl=en")));
                    }
                }*/
                break;
            case 1:
                pushFragments(AboutISfFragment.newInstance(),null,false);
                break;
            case 2:
                pushFragments(TermsConditionFragment.newInstance(),null,false);
                break;
            case 3:
                pushFragments(PrivacyPolicyFragment.newInstance(),null,false);
                break;
            case 4:
                ProjectUtil.logoutFromAppMenu(HomeActivity.this);
                break;

        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sliderIcon:
                slideDrawer();
                break;
            case R.id.backBtn:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (fragment instanceof HomeFragment || fragment instanceof ChallengeFragment ||
                fragment instanceof RewardsFragment || fragment instanceof ProfileFragment) {
            ProjectUtil.logoutAlert(this);
        } else {
            super.onBackPressed();

        }
    }

    @Override
    public void onBackStackChanged() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment != null) {
            fragment.onResume();
            updateActionBarView(fragment);
            updateBottomMenuView(fragment);
        }

    }

    public void updateBottomMenuView(Fragment fragment) {
        String name = fragment.getClass().getSimpleName();
        switch (name) {
            case "HomeFragment":
                break;
            case "ChallengeFragment":
                break;
            case "RewardsFragment":
                break;
            case "ProfileFragment":
                break;
        }
    }


    public void updateActionBarView(Fragment fragment) {
        String name = fragment.getClass().getSimpleName();
        switch (name) {
            case HomeFragment.TAG:
                sliderIcon.setVisibility(View.VISIBLE);
                // searchIcon.setVisibility(View.VISIBLE);
                backBtn.setVisibility(View.GONE);
                break;
            case RewardsFragment.TAG:
                sliderIcon.setVisibility(View.VISIBLE);
                //  searchIcon.setVisibility(View.VISIBLE);
                backBtn.setVisibility(View.GONE);
                break;
            case ChallengeFragment.TAG:
                sliderIcon.setVisibility(View.VISIBLE);
                // searchIcon.setVisibility(View.VISIBLE);
                backBtn.setVisibility(View.GONE);
                break;
            case ProfileFragment.TAG:
                sliderIcon.setVisibility(View.VISIBLE);
                // searchIcon.setVisibility(View.VISIBLE);
                backBtn.setVisibility(View.GONE);
                break;
            case HowMuchSaveFragment.TAG:
                sliderIcon.setVisibility(View.GONE);
                //   searchIcon.setVisibility(View.VISIBLE);
                backBtn.setVisibility(View.VISIBLE);
                break;
            case AddFriendFragment.TAG:
                sliderIcon.setVisibility(View.GONE);
                //searchIcon.setVisibility(View.VISIBLE);
                backBtn.setVisibility(View.VISIBLE);
                break;
            case QuizeFragment.TAG:
                sliderIcon.setVisibility(View.GONE);
                //  searchIcon.setVisibility(View.VISIBLE);
                backBtn.setVisibility(View.VISIBLE);
                break;
            case QuizeCompletedFragement.TAG:
                sliderIcon.setVisibility(View.GONE);
                // searchIcon.setVisibility(View.VISIBLE);
                backBtn.setVisibility(View.VISIBLE);
                break;
            case BadgeDetailFragment.TAG:
                sliderIcon.setVisibility(View.GONE);
                // searchIcon.setVisibility(View.VISIBLE);
                backBtn.setVisibility(View.VISIBLE);
                break;
            case AboutISfFragment.TAG:
                sliderIcon.setVisibility(View.GONE);
                // searchIcon.setVisibility(View.VISIBLE);
                backBtn.setVisibility(View.VISIBLE);
                break;
            case SendFriendRequestFragment.TAG:
                sliderIcon.setVisibility(View.GONE);
                // searchIcon.setVisibility(View.VISIBLE);
                backBtn.setVisibility(View.VISIBLE);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        fragment.onActivityResult(requestCode, resultCode, data);
    }




}
