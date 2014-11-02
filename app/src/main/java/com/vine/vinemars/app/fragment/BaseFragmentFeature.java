package com.vine.vinemars.app.fragment;

import android.os.Bundle;
import android.view.MotionEvent;

import com.vine.vinemars.app.BaseActivityFeature;


/**
 * The features that all the activities in this application should have.
 *
 * @author fei.cheng
 */
public interface BaseFragmentFeature extends BaseActivityFeature {

    public Bundle getBundle();

    public BaseActivityFeature getActivityFeature();

    boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY);
}
