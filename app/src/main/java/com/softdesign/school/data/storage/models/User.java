package com.softdesign.school.data.storage.models;

import android.graphics.drawable.Drawable;

/**
 * Created by CRN_soft on 19.01.2016.
 */
public class User {

    private String mFirstName;
    private String mLastName;
    private int mRating;
    private Drawable mImage;
    private String mVkLink;
    private String mGithubLink;
    private int mHometask;


    public User(Drawable mImage, String mFirstName, String mLastName) {
        this.mImage = mImage;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public int getmRating() {
        return mRating;
    }

    public Drawable getmImage() {
        return mImage;
    }

    public String getmVkLink() {
        return mVkLink;
    }

    public String getmGithubLink() {
        return mGithubLink;
    }

    public int getmHometask() {
        return mHometask;
    }
}