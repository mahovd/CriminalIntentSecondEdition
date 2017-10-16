package com.mahovd.bignerdranch.criminalintentsecondedition;

import android.support.v4.app.Fragment;

/**
 * Created by dmitriymakhov on 21/06/2017.
 *
 * Entry point of the application
 * Because there is no onCreate method in the class,
 * system is starting one from the SingleFragmentActivity
 */

public class CrimeListActivity extends SingleFragmentActivity {

    /**
     *Implements abstract method createFragment
     * @return the instance of CrimeListFragment
     */
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
