package com.xmd.baiduvoice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xmd.baidu_voice.BaiduVoiceManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editText;
    private Button btn;
    private TextView kb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kb = findViewById(R.id.kb);
        editText = findViewById(R.id.editText);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);
        kb.setText(String.valueOf(GetFlowUtil.getTotalTraffic(GetFlowUtil.getUid(MainActivity.this))/1024)+" kb");
    }

    @Override
    public void onClick(View v) {
        String text = editText.getText().toString();
        for (int i = 0; i < 100; i++) {
            BaiduVoiceManager.getBaiduManagerInstance().speak("您收到一个最新的订单");
        }

    }
}
