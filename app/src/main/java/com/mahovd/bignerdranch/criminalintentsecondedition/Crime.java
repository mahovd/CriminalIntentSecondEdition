package com.mahovd.bignerdranch.criminalintentsecondedition;

import java.util.Date;
import java.util.UUID;

/**
 * Created by dmitriymakhov on 04/06/2017.
 */

public class Crime {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private boolean mRequiresPolicy;

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public boolean isRequiresPolicy() {
        return mRequiresPolicy;
    }

    public void setRequiresPolicy(boolean requiresPolicy) {
        mRequiresPolicy = requiresPolicy;
    }
}
