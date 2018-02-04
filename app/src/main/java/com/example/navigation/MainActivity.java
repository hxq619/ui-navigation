package com.example.navigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.navigation.navbar.NavigationManager;
import com.example.navigation.navbar.NavigationManagerImpl;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private NavigationManager mNavManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = super.findViewById(R.id.toolbar);
        super.setSupportActionBar(mToolbar);
        mNavManager = new NavigationManagerImpl(this);
        mNavManager.setup(super.getSupportFragmentManager(), R.id.container, mToolbar);
        mNavManager.push(RootFragment.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNavManager.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mNavManager.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mNavManager.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (!mNavManager.onBackPressed()) {
            super.finish();
        } else {
            super.onBackPressed();
        }
    }
}
