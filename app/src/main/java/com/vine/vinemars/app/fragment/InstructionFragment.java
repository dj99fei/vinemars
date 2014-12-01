package com.vine.vinemars.app.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.vine.vinemars.R;
import com.vine.vinemars.bus.LoginEvent;
import com.vine.vinemars.domain.DeviceInfo;
import com.vine.vinemars.utils.Constant;

import butterknife.InjectView;
import de.greenrobot.event.EventBus;

/**
 * Created by chengfei on 14-10-21.
 */
public class InstructionFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    @InjectView(R.id.pager)
    protected ViewPager pager;
    private int indicators[] = new int[]{R.drawable.indicator_point_green, R.drawable.indicator_point_orange, R.drawable.indicator_point_blue,
            R.drawable.indicator_point_grape};
    @InjectView(R.id.indicator1)
    protected ImageView indicatorImage1;
    @InjectView(R.id.indicator2)
    protected ImageView indicatorImage2;
    @InjectView(R.id.indicator3)
    protected ImageView indicatorImage3;
    @InjectView(R.id.indicator4)
    protected ImageView indicatorImage4;

    @InjectView(R.id.btn_signin)
    protected Button signinButton;
    @InjectView(R.id.btn_signup)
    protected Button signupButton;
    private ImageView[] indicatorImages = new ImageView[4];
    private int images[] = new int[]{R.drawable.instruction_1, R.drawable.instruction_2, R.drawable.instruction_3, R.drawable.instruction_4};

    private Bitmap[] bitmaps = new Bitmap[4];
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        for (int i = 0; i < bitmaps.length; i++) {
            bitmaps[i] = BitmapFactory.decodeResource(getResources(), images[i]);
        }
        return inflater.inflate(R.layout.fragment_instruction, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pager.setAdapter(new ScreenSlidePagerAdapter(getChildFragmentManager()));
        pager.setOnPageChangeListener(this);
        indicatorImages[0] = indicatorImage1;
        indicatorImages[1] = indicatorImage2;
        indicatorImages[2] = indicatorImage3;
        indicatorImages[3] = indicatorImage4;
        signinButton.setOnClickListener(this);
        signupButton.setOnClickListener(this);
        setIndicator(0);
        EventBus.getDefault().register(this);
    }
    private float scale = 0.4f;
    private float noScale = 1f;
    private void setIndicator(int position) {
        for (int i = 0; i < indicatorImages.length; i++) {
            if (i == position) {
                indicatorImages[i].setImageResource(indicators[i]);
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(indicatorImages[i], "scaleX", scale, noScale);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(indicatorImages[i], "scaleY", scale, noScale);
                AnimatorSet set = new AnimatorSet();
                set.playTogether(scaleX, scaleY);
                set.start();
            } else {
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(indicatorImages[i], "scaleX", noScale, scale);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(indicatorImages[i], "scaleY", noScale, scale);
                AnimatorSet set = new AnimatorSet();
                set.playTogether(scaleX, scaleY);
                indicatorImages[i].setImageResource(R.drawable.indicator_point_gray);
                set.start();
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float v, int positionOffsetPixels) {
        float nextCompent = 1.0f * Math.abs(positionOffsetPixels) / DeviceInfo.screenWidth;
        float currentCompent = 1.0f - nextCompent;
        Bitmap nextColorStr = null;
        if (position + 1 < pager.getAdapter().getCount()) {
            nextColorStr = bitmaps[position + 1];
        } else if (position - 1 > 0) {
            nextColorStr = bitmaps[position - 1];
        }
        Bitmap curColorStr = bitmaps[position];

        Drawable mixed = createDrawable(curColorStr, nextColorStr, currentCompent);
        pager.setBackgroundDrawable(mixed);
//        int mixedColor = ColorUtils.mixColor(Color.parseColor(curColorStr), Color.parseColor(nextColorStr), currentCompent);
//        pagerBackgroud.setBackgroundColor(mixedColor);
//        bar.setBackgroundDrawable(new ColorDrawable(mixedColor));
    }

    @Override
    public void onPageSelected(int i) {
        setIndicator(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        private int desIds[] = new int[]{R.string.instruction_1, R.string.instruction_2, R.string.instruction_3, R.string.instruction_4};

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ScreenSlidePageFragment.newInstance(desIds[position], position == images.length - 1);
        }

        @Override
        public int getCount() {
            return images.length;
        }


    }

    public static class ScreenSlidePageFragment extends BaseFragment {

        private boolean isLast;

        public static ScreenSlidePageFragment newInstance(int desId, boolean isLast) {
            ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
            Bundle arguments = new Bundle();
            arguments.putInt(Constant.INTENT_KEY.DES_ID, desId);
            arguments.putBoolean(Constant.INTENT_KEY.IS_LAST, isLast);
            fragment.setArguments(arguments);
            return fragment;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_screen_slide, container, false);
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            isLast = getArguments().getBoolean(Constant.INTENT_KEY.IS_LAST);
        }


    }

    private Drawable createDrawable(Bitmap bitmap1, Bitmap bitmap2, float componet) {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap1.getWidth(), bitmap1.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        Paint p1 = new Paint();
        p1.setAlpha((int) (componet * 255));

        Paint p2 = new Paint();
        p2.setAlpha((int) ((1 - componet) * 255));

        canvas.drawBitmap(bitmap1, 0, 0, p1);
        canvas.drawBitmap(bitmap2, 0, 0, p2);
        return new BitmapDrawable(newBitmap);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_signin) {
            SignInFragment.newInstance().show(getFragmentManager(), "signin");
//            SigninFragment.newInstance(bitmaps[pager.getCurrentItem()]).show(getFragmentManager(), "signin");
//                getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
//                getActivity().finish();
//                SharedPreferencesHelper.getInstance().withModule(R.string.module_default).withKey(R.string.key_first_open).setData(boolean.class, false).commit();
        } else if (v.getId() == R.id.btn_signup) {
            SignupFragment.newInstance().show(getFragmentManager(), "signup");
        }
    }

    public void onEvent(LoginEvent event) {
        if (event.isLogin()) {
            getActivity().finish();
        }
    }

}