package com.vine.vinemars.net;

import com.android.volley.VolleyError;
import com.vine.vinemars.MyApplication;
import com.vine.vinemars.R;

/**
 * Created by chengfei on 14-10-26.
 */
public class ParseError extends VolleyError {

    private static ParseError parseError;


    public ParseError() {
        super(MyApplication.get().getResources().getString(R.string.error_parse));
    }


}
