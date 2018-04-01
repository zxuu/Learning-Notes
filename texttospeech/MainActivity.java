package com.zxu.android.texttospeech;

import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity implements TextToSpeech.OnInitListener{

//    private TextToSpeech mSpeech;
    private Button btn;
    static final int TTs_CHECK_CODE = 0;
    private EditText mEditText;
    private TextToSpeech myTts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn  = (Button) findViewById(R.id.Button01);
        mEditText = (EditText) findViewById(R.id.EditText01);
        myTts = new TextToSpeech(this,ttsInitKistenner,"妈妈");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTts.setLanguage(Locale.CHINA);
                myTts.speak(mEditText.getText().toString(),0,null
                ,"滴滴");

                myTts.speak(mEditText.getText().toString(),
                        TextToSpeech.QUEUE_FLUSH,null,"滴滴");
            }
        });
    }

    private TextToSpeech.OnInitListener ttsInitKistenner = new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int i) {
            myTts.setLanguage(Locale.CHINA);
            myTts.speak("爸爸",0,null,"滴滴");
        }
    };

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = myTts.setLanguage(Locale.CHINA);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech
                    .LANG_NOT_SUPPORTED) {
                Log.d(TAG, "onInit: not use");
            } else {
                btn.setEnabled(true);
                myTts.speak("我喜欢你",TextToSpeech.QUEUE_FLUSH,null,"滴滴");
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (myTts != null) {
            myTts.stop();
            myTts.shutdown();
        }
        super.onDestroy();
    }
}
