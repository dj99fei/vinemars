package com.vine.vinemars.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.vine.vinemars.R;
import com.vine.vinemars.app.fragment.InstructionFragment;
import com.vine.vinemars.app.fragment.SplashFragment;
import com.vine.vinemars.utils.SharedPreferencesHelper;


/**
 * Created by chengfei on 14-10-20.
 */
public class EntryActivity extends BaseActivity {

    private SharedPreferencesHelper spHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_entry);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new SplashFragment()).disallowAddToBackStack().commit();
        spHelper = SharedPreferencesHelper.getInstance().withModule(R.string.module_default).withKey(R.string.key_first_open);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (spHelper.get(boolean.class, true)) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new InstructionFragment()).disallowAddToBackStack().commit();
                } else {
                    startActivity(new Intent(EntryActivity.this, MainActivity.class));
                    finish();
                }
            }
        }, 2000);
    }
}
