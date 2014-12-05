package com.vine.vinemars.app.fragment;

import android.os.Bundle;

import com.vine.vinemars.app.BaseActivityFeature;


/**
 * The features that all the activities in this application should have.
 *
 * @author fei.cheng
 */
public interface BaseFragmentFeature extends BaseActivityFeature {

    public Bundle getBundle();

    public BaseActivityFeature getActivityFeature();

}
