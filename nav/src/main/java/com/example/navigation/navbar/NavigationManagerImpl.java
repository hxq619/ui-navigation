package com.example.navigation.navbar;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

/**
 * Created by Moxtra on 2018/2/3.
 */

public class NavigationManagerImpl implements NavigationManager {

    private static final String TAG = "NavBarMgr";

    private static final String FRAGMENT_TAG_PREFIX = "nav_";

    private final Context mContext;

    private FragmentManager mFM;

    private int mStackHolderId;

    private Toolbar mNavbar;

    private FragmentManager.OnBackStackChangedListener mBackStackChangedListener = new FragmentManager.OnBackStackChangedListener() {

        @Override
        public void onBackStackChanged() {
            Log.i(TAG, "onBackStackChanged");
            configPrimaryFragment();
        }
    };

    public NavigationManagerImpl(Context context) {
        mContext = context;
    }

    @Override
    public void setup(FragmentManager fm, @IdRes int stackResId, Toolbar navbar) {
        mFM = fm;
        mFM.addOnBackStackChangedListener(mBackStackChangedListener);
        mStackHolderId = stackResId;
        mNavbar = navbar;
        mNavbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pop();
            }
        });
    }

    @Override
    public void push(Class<? extends Fragment> fragmentClass) {
        Fragment fragment = mFM.findFragmentByTag(FRAGMENT_TAG_PREFIX + fragmentClass.hashCode());
        if (fragment == null) {
            fragment = Fragment.instantiate(mContext, fragmentClass.getName());
            if (fragment instanceof NavBarConfigFactory) {
                ((NavBarConfigFactory) fragment).setNavBarManager(this);
                ((NavBarConfigFactory) fragment).getConfig().configure(mNavbar);
                if (((NavBarConfigFactory) fragment).getConfig().hasOptionsMenu()) {
                    fragment.setHasOptionsMenu(true);
                }
                push(fragment, null);
            } else {
                throw new IllegalStateException("The fragment must implement <NavBarConfigFactory>!");
            }
        } else {
            Log.e(TAG, String.format("push: %s already exist!", fragmentClass));
        }
    }

    @Override
    public void pop() {
        Log.i(TAG, "pop");
        mFM.popBackStack();
    }

    @Override
    public void onResume() {
        configPrimaryFragment();
    }

    @Override
    public void onPause() {
        // do nothing
    }

    @Override
    public void onDestroy() {
        mFM.removeOnBackStackChangedListener(mBackStackChangedListener);
    }

    @Override
    public boolean onBackPressed() {
        return mFM.getBackStackEntryCount() > 1;
    }

    private void push(Fragment fragment, String tag) {
        FragmentTransaction ft = mFM.beginTransaction();
        ft.add(mStackHolderId, fragment, tag);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.setPrimaryNavigationFragment(fragment);
        ft.addToBackStack(tag);
        ft.commit();
    }

    private void configPrimaryFragment() {
        Fragment primary = mFM.getPrimaryNavigationFragment();
        Log.i(TAG, String.format("primary=[%s]", primary));
        if (primary != null) {
            ((NavBarConfigFactory) primary).getConfig().configure(mNavbar);
            if (((NavBarConfigFactory) primary).getConfig().hasOptionsMenu()) {
                primary.setHasOptionsMenu(true);
                primary.getActivity().invalidateOptionsMenu();
            }
        }
    }
}
