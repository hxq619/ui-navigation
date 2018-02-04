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
import com.example.navigation.navbar.NavBarConfigFactory;
import com.example.navigation.navbar.NavBarConfig;

/**
 * Created by Moxtra on 2018/1/28.
 */

public class RootFragment extends Fragment implements NavBarConfigFactory {

    private NavigationManager mNavManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_root, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnNext = view.findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNavManager.push(SecondFragment.class);
            }
        });
    }

    @Override
    public void setNavBarManager(NavigationManager manager) {
        mNavManager = manager;
    }

    @Override
    public NavBarConfig getConfig() {
        return new NavBarConfig() {
            @Override
            public void configure(Toolbar actionbar) {
                actionbar.setTitle(R.string.root);
                actionbar.setNavigationIcon(null);
            }

            @Override
            public boolean hasOptionsMenu() {
                return false;
            }
        };
    }
}
