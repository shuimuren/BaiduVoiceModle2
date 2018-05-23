package com.xmd.baidu_voice;

import android.content.Context;
import com.baidu.tts.client.SpeechSynthesizer;

import com.baidu.tts.client.TtsMode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BaiduVoiceManager {
    private Context context;
    private ModelSyntherizer synthesizer;

    private BaiduVoiceManager() {

    }

    private static final class BaiduVoiceManagerHolder {
        public static final BaiduVoiceManager voiceManager = new BaiduVoiceManager();
    }

    public static BaiduVoiceManager getBaiduManagerInstance() {
        return BaiduVoiceManagerHolder.voiceManager;
    }

    public void initialTts(Context context, String appId, String appKey, String secretKey) {
        this.context = context;
        Map<String, String> params = getParams();
        InitConfig initConfig = new InitConfig(appId, appKey, secretKey, params, TtsMode.MIX, null);
        synthesizer = new NonBlockSyntherizer(context, initConfig);
    }

    private Map<String, String> getParams() {
        Map<String, String> params = new HashMap<String, String>();
        // 以下参数均为选填
        // 设置在线发声音人： 0 普通女声（默认） 1 普通男声 2 特别男声 3 情感男声<度逍遥> 4 情感儿童声<度丫丫>
        params.put(SpeechSynthesizer.PARAM_SPEAKER, "0");
        // 设置合成的音量，0-9 ，默认 5
        params.put(SpeechSynthesizer.PARAM_VOLUME, "9");
        // 设置合成的语速，0-9 ，默认 5
        params.put(SpeechSynthesizer.PARAM_SPEED, "5");
        // 设置合成的语调，0-9 ，默认 5
        params.put(SpeechSynthesizer.PARAM_PITCH, "5");
        params.put(SpeechSynthesizer.PARAM_MIX_MODE, SpeechSynthesizer.MIX_MODE_DEFAULT);
        // 该参数设置为TtsMode.MIX生效。即纯在线模式不生效。
        // MIX_MODE_DEFAULT 默认 ，wifi状态下使用在线，非wifi离线。在线状态下，请求超时6s自动转离线
        // MIX_MODE_HIGH_SPEED_SYNTHESIZE_WIFI wifi状态下使用在线，非wifi离线。在线状态下， 请求超时1.2s自动转离线
        // MIX_MODE_HIGH_SPEED_NETWORK ， 3G 4G wifi状态下使用在线，其它状态离线。在线状态下，请求超时1.2s自动转离线
        // MIX_MODE_HIGH_SPEED_SYNTHESIZE, 2G 3G 4G wifi状态下使用在线，其它状态离线。在线状态下，请求超时1.2s自动转离线
        // 离线资源文件， 从assets目录中复制到临时目录，需要在initTTs方法前完成
        OfflineResource offlineResource = createOfflineResource(OfflineResource.VOICE_MALE);
        // 声学模型文件路径 (离线引擎使用), 请确认下面两个文件存在
        params.put(SpeechSynthesizer.PARAM_TTS_TEXT_MODEL_FILE, offlineResource.getTextFilename());
        params.put(SpeechSynthesizer.PARAM_TTS_SPEECH_MODEL_FILE,
                offlineResource.getModelFilename());
        return params;
    }

    protected OfflineResource createOfflineResource(String voiceType) {
        OfflineResource offlineResource = null;
        try {
            offlineResource = new OfflineResource(context, voiceType);
        } catch (IOException e) {
            // IO 错误自行处理
            e.printStackTrace();
        }
        return offlineResource;
    }

    public void speak(String text) {
        // 需要合成的文本text的长度不能超过1024个GBK字节。
        int result = synthesizer.speak(text);
    }

    public void stopSpeak(){
        if(synthesizer != null){
            synthesizer.stop();
        }
    }


}
