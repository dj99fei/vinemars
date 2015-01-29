package com.vine.vinemars.app.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import com.vine.vinemars.R;
import com.vine.vinemars.adapter.CommentsAdapter;

import butterknife.InjectView;

/**
 * Created by chengfei on 15/1/26.
 */
public class CommentsFragment extends BaseFragment {

    public static final String ARG_DRAWING_START_LOCATION = "arg_drawing_start_location";

    @InjectView(R.id.toolbar)
    LinearLayout contentRoot;
    @InjectView(R.id.rv_comments)
    RecyclerView rvComments;
    @InjectView(R.id.ll_add_Comment)
    LinearLayout llAddComment;

    private CommentsAdapter commentsAdapter;
    private int drawingStartLocation;

    public static CommentsFragment newInstance(Bundle args) {
        CommentsFragment fragment = new CommentsFragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_DRAWING_START_LOCATION, drawingStartLocation);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawingStartLocation = getArguments().getInt(ARG_DRAWING_START_LOCATION, 0);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_comments;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            contentRoot.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    contentRoot.getViewTreeObserver().removeOnPreDrawListener(this);
                    startIntroAnimation();
                    return true;
                }
            });
        }
    }

    private void startIntroAnimation() {
        contentRoot.setScaleY(0.1f);
        contentRoot.setPivotY(drawingStartLocation);
        llAddComment.setTranslationY(100);

        android.support.v4.view.ViewCompat.animate(contentRoot)
                .scaleY(1)
                .setDuration(200)
                .setInterpolator(new AccelerateInterpolator())
                .setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {

                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        animateContent();
                    }

                    @Override
                    public void onAnimationCancel(View view) {

                    }
                })
                .start();
    }

    private void animateContent() {
        commentsAdapter.updateItems();
        llAddComment.animate().translationY(0)
                .setInterpolator(new DecelerateInterpolator())
                .setDuration(200)
                .start();
    }
}
