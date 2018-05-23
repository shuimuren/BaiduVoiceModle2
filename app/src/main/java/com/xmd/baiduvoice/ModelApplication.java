package com.xmd.baiduvoice;

import android.app.Application;


import com.xmd.baidu_voice.BaiduVoiceManager;


public class ModelApplication extends Application {
    private String appId = "11276569";
    private String appKey = "T9ciFL5ITaSSy4SCi8GzrSCm";
    private String secretKey = "haGoRZ0kS4w3Z8f1v8tLOXNA2xpyl1rt";

    @Override
    public void onCreate() {
        super.onCreate();
        BaiduVoiceManager.getBaiduManagerInstance().initialTts(getApplicationContext(), appId, appKey, secretKey);
    }
}
