package com.mahovd.bignerdranch.criminalintentsecondedition;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by dmitriymakhov on 20/06/2017.
 * Temporary model-class, stores and retrieves the crimes list
 */

public class CrimeLab {

    private static CrimeLab sCrimeLab;

    private List<Crime> mCrimes;

    //the main method of the class
    //retrieves the instance of the crimes-list
    public static CrimeLab get(Context context){

        if (sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }

        return sCrimeLab;

    }

    //the constructor method
    private CrimeLab(Context context){
        mCrimes = new ArrayList<>();


        //populate the list with 100 crimes
        for (int i = 0; i<100; i++){

            Crime crime = new Crime();
            crime.setTitle("Crime # "+i);
            crime.setSolved(i % 2 == 0); //Every other one
            crime.setRequiresPolicy(i % 2 != 0);
            mCrimes.add(crime);

        }

    }

    //retrieves the list of crimes
    public List<Crime> getCrimes(){
        return mCrimes;
    }

    //retrieves a crime by id
    public Crime getCrime(UUID id){

        for (Crime crime:mCrimes){
            if (crime.getId().equals(id)){
                return crime;
            }
        }

        return null;

    }

}
