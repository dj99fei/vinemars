package com.vine.vinemars.app.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ProgressBar;

import com.vine.vinemars.R;
import com.vine.vinemars.app.BaseActivityFeature;

import butterknife.InjectView;
import butterknife.Optional;

/**
 * Created by chengfei on 14/12/5.
 */
public class BaseDialogFragment extends DialogFragment implements BaseFragmentFeature {

    @Optional
    @InjectView(R.id.cross_fader_loading)
    protected ProgressBar loadingSpinner;
    @Optional
    @InjectView(R.id.cross_fader_content)
    protected View content;
    private BaseFragmentDelegate fragmentDelegate;

    @Override
    public Bundle getBundle() {
        return fragmentDelegate.getBundle();
    }

    @Override
    public BaseActivityFeature getActivityFeature() {
        return (BaseActivityFeature) getActivity();
    }

    @Override
    public View getContentView() {
        return content;
    }

    @Override
    public View getProgressView() {
        return loadingSpinner;
    }

    @Override
    public int getFragmentContainerId() {
        return fragmentDelegate.getFragmentContainerId();
    }

    @Override
    public boolean hasDestroyed() {
        return fragmentDelegate.hasDestroyed();
    }

    @Override
    public void setShowProgress(boolean show) {
        fragmentDelegate.setShowProgress(show);

    }

    @Override
    public void setShowProgress(View showView, View hideView) {
        fragmentDelegate.setShowProgress(showView, hideView);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        fragmentDelegate = new BaseFragmentDelegate(this);
        fragmentDelegate.onAttach(activity);
    }

}
