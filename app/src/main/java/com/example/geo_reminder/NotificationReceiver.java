package com.example.geo_reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class NotificationReceiver extends BroadcastReceiver {

    private TextToSpeech ttp;
    @Override
    public void onReceive(Context context, Intent intent) {

        final String message = intent.getStringExtra("toastMessage");
        ttp = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    ttp.setLanguage(Locale.ENGLISH);
                    ttp.setLanguage(Locale.ENGLISH);
                    ttp.speak(message, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
    }
}
