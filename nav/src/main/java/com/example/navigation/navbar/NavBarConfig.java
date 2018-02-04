package com.example.navigation.navbar;


import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

public interface NavBarConfig {
	
	void configure(Toolbar actionbar);

	boolean hasOptionsMenu();
}
