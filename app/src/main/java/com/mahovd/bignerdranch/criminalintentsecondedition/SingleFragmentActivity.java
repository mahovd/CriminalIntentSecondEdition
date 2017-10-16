package com.mahovd.bignerdranch.criminalintentsecondedition;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by dmitriymakhov on 21/06/2017.
 *
 * Contains declaration of abstract method createFragment
 * and implementation of method onCreate
 *
 */

public abstract class SingleFragmentActivity extends AppCompatActivity{

    protected abstract Fragment createFragment();

    /**
     * Initializes some fragment container, makes a new fragment instance and
     * begins a transaction through fragment manager
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null){
            fragment = createFragment();
            fm.beginTransaction().
                    add(R.id.fragment_container,fragment).
                    commit();
        }
    }
}
