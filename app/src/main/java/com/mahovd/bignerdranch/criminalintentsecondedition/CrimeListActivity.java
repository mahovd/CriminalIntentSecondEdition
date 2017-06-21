package com.mahovd.bignerdranch.criminalintentsecondedition;

import android.support.v4.app.Fragment;

/**
 * Created by dmitriymakhov on 21/06/2017.
 */

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
