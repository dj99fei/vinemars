package com.vine.vinemars.view;

import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by chengfei on 14/11/17.
 */
public class HorizontalScrollDetctor extends GestureDetector.SimpleOnGestureListener {
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return Math.abs(distanceY) < Math.abs(distanceX);
    }
}
