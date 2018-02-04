package com.example.navigation.navbar;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

/**
 * Created by Moxtra on 2018/2/3.
 */

public interface NavigationManager {

    void setup(FragmentManager fm, @IdRes int stackResId, Toolbar navbar);

    void push(Class<? extends Fragment> fragmentClass);

    void pop();

    void onResume();

    void onPause();

    void onDestroy();

    boolean onBackPressed();
}
