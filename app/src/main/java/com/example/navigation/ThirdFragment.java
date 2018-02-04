package com.example.navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.navigation.navbar.NavigationManager;
import com.example.navigation.navbar.NavBarConfig;
import com.example.navigation.navbar.NavBarConfigFactory;

/**
 * Created by Moxtra on 2018/1/28.
 */

public class ThirdFragment extends Fragment implements NavBarConfigFactory {

    private NavigationManager mNavBarManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setNavBarManager(NavigationManager manager) {
        mNavBarManager = manager;
    }

    @Override
    public NavBarConfig getConfig() {
        return new NavBarConfig() {
            @Override
            public void configure(Toolbar actionbar) {
                actionbar.setTitle(R.string.last);
                actionbar.setNavigationIcon(R.mipmap.ic_back);
                actionbar.getMenu().clear();
            }

            @Override
            public boolean hasOptionsMenu() {
                return true;
            }
        };
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_third, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
