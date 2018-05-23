package com.xmd.baidu_voice;

import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;

import java.util.Map;

public class InitConfig {

    /**
     * appId appKey 和 secretKey。注意如果需要离线合成功能,请在您申请的应用中填写包名。
     * 本demo的包名是com.baidu.tts.sample，定义在build.gradle中。
     */
    private String appId;
    private String appKey;
    private String secretKey;

    private TtsMode ttsMode;

    private Map<String, String> params;

    private SpeechSynthesizerListener listener;

//    private InitConfig(String appId, String appKey, String secretKey, String ttsMode, Map<String, String> params, SpeechSynthesizerListener listener) {
//
//    }

    public InitConfig(String appId, String appKey, String secretKey,
                      Map<String, String> params,TtsMode mode ,SpeechSynthesizerListener listener) {
        this.appId = appId;
        this.appKey = appKey;
        this.secretKey = secretKey;
        this.ttsMode = mode;
        this.params = params;
        this.listener = listener;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public TtsMode getTtsMode() {
        return ttsMode;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public SpeechSynthesizerListener getListener() {
        return listener;
    }

}
