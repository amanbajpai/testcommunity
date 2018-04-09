package com.energybox.isf.utils;

import android.support.v4.app.FragmentManager;

import com.energybox.isf.view.activity.BaseActivity;


/**
 * Class is used to maintain backstack of application.
 */
public class BackManager {




/*    public static void onBackPressed(Activity activity, HomeActivity context) {
        Fragment fragment = context.getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        int count = context.getSupportFragmentManager().getBackStackEntryCount();
        if (fragment instanceof HomeFragment) {
            ProjectUtil.logoutAlert(context);
        } else if (count == 1 && !(fragment instanceof HomeFragment)) {
            ((HomeActivity) activity).callHome();
        } else {

        }
    }*/

    public static void manageBackStack(BaseActivity activity, String fragmentName) {
        try {
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            for (int i = 0; i < activity.getSupportFragmentManager().getBackStackEntryCount(); i++) {
                if (fragmentName.equalsIgnoreCase(fragmentManager.getBackStackEntryAt(i).getName())) {
                    fragmentManager.popBackStack(fragmentName, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}


















