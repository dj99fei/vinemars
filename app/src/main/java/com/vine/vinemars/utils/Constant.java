package com.vine.vinemars.utils;

public class Constant {

    public static final String KEY_RESULT = "key_result";

    public static final class INTENT_KEY {

        public static final String IMAGE_ID = "intent_key_image_id";
        public static final String DES_ID = "intent_key_des_id";
        public static final String IS_LAST = "intent_key_is_last";
    }

    public static final String SCHEMA = "http://";
    public static final String HOST = "www.mobo123.com";

    public static String getBaseUrl() {
        return new StringBuilder(SCHEMA).append(HOST).append("/").toString();
    }


}
