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
import android.widget.Button;

import com.example.navigation.navbar.NavigationManager;
import com.example.navigation.navbar.NavBarConfig;
import com.example.navigation.navbar.NavBarConfigFactory;

/**
 * Created by Moxtra on 2018/1/28.
 */

public class SecondFragment extends Fragment implements NavBarConfigFactory {

    private NavigationManager mNavBarManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnNext = view.findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNavBarManager.push(ThirdFragment.class);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_second, menu);
        super.onCreateOptionsMenu(menu, inflater);
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
                actionbar.setTitle(R.string.second);
                actionbar.setNavigationIcon(R.mipmap.ic_back);
            }

            @Override
            public boolean hasOptionsMenu() {
                return true;
            }
        };
    }
}
