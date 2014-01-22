package com.radiostar.android.util;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.content.Context;
import android.widget.Toast;
import java.util.Locale;

/**
 * Created by Adnaan Velji on 12/25/13.
 */
public class TTSEngine implements TextToSpeech.OnInitListener {

    private TextToSpeech _tts;
    private Context _context;
    //private int MY_DATA_CHECK_CODE = 0;

    public TTSEngine(Context context) {
        _context = context;

        //TODO Check for TTS Data
        /*
        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);
        */
    }

    public boolean start() {
        if (_tts !=null) {
            _tts = new TextToSpeech(_context, this);
        }
        return true;
    }

    @Override
    public void onInit(int initStatus) {
        if (initStatus == TextToSpeech.SUCCESS) {
            if(_tts.isLanguageAvailable(Locale.US)==TextToSpeech.LANG_AVAILABLE) {
                _tts.setLanguage(Locale.US);
            }
        }
        else if (initStatus == TextToSpeech.ERROR) {
            Toast.makeText(_context, "Text To Speech failed :(", Toast.LENGTH_LONG).show();
        }
    }

    public void speak(String speech, boolean flush) {
        //TODO implement
        if (_tts != null) {
            if (flush) {
                _tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
            }
            else {
                _tts.speak(speech, TextToSpeech.QUEUE_ADD, null);
            }
        }
    }

    public void stop() {
        if (_tts != null) {
            _tts.stop();
        }
    }

    public void shutdown() {
        if (_tts != null) {
            _tts.stop();
            _tts.shutdown();
        }
    }
}