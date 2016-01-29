package com.example.tts;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Google_STTActivity extends Activity {
    protected static final int RESULT_SPEECH = 1;
    private Button btnSpeak;
    private TextView txtText;
  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stt);
  
        txtText = (TextView) findViewById(R.id.stt_tv);
  
        btnSpeak = (Button) findViewById(R.id.stt_button);
  
        btnSpeak.setOnClickListener(new View.OnClickListener() {
  
            @Override
            public void onClick(View v) {
            	if(isSpeechRecognitionActivityPresented(Google_STTActivity.this)==true){
            		Toast.makeText(getApplicationContext(), "是否安装语音识别应用true", 0).show();
            		startstt();
            	}else{
            		Toast.makeText(getApplicationContext(), "是否安装语音识别应用false", 0).show();
            		installGoogleVoiceSearch(Google_STTActivity.this);
            	}
                
            }
        });
  
    } 
    
    
    
  
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
  
        switch (requestCode) {
        case RESULT_SPEECH: {
            if (resultCode == RESULT_OK && null != data) {
  
                ArrayList<String> text = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
  
                txtText.setText(text.get(0));
            }
            break;
        }
  
        }
    }
    
    
    private void  startstt(){
    	Intent intent = new Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.CHINA.toString());
        try {
            startActivityForResult(intent, RESULT_SPEECH);
            txtText.setText(""+RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        } catch (ActivityNotFoundException a) {
            Toast t = Toast.makeText(getApplicationContext(),
                    "Opps! Your device doesn't support Speech to Text",
                    Toast.LENGTH_SHORT);
            t.show();
        }
    }
    
    
    /**
     * Checks availability of speech recognizing Activity   查看设备是否支持语音识别应用
     *
     * @param callerActivity – Activity that called the checking
     * @return true – if Activity there available, false – if Activity is absent
     */
    private static boolean isSpeechRecognitionActivityPresented(Activity callerActivity) {
        try {
            // getting an instance of package manager
            PackageManager pm = callerActivity.getPackageManager();
            // a list of activities, which can process speech recognition Intent
            List activities = pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);

            if (activities.size() != 0) {    // if list not empty
                return true;                // then we can recognize the speech
            }
        } catch (Exception e) {

        }

        return false; // we have no activities to recognize the speech
    }
    
    
    /**
     * 安装语音识别应用
     * Asking the permission for installing Google Voice Search. 
     * If permission granted – sent user to Google Play
     * @param callerActivity – Activity, that initialized installing
     */
    private static void installGoogleVoiceSearch(final Activity ownerActivity) {

        // creating a dialog asking user if he want
        // to install the Voice Search
        Dialog dialog = new AlertDialog.Builder(ownerActivity)
            .setMessage("For recognition it’s necessary to install  Google Voice Search ")    // dialog message
            .setTitle("Install Voice Search from Google Play?")    // dialog header
            .setPositiveButton("Install", new DialogInterface.OnClickListener() {    // confirm button

                // Install Button click handler
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        // creating an Intent for opening applications page in Google Play
                        // Voice Search package name: com.google.android.voicesearch
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.voicesearch"));
                        // setting flags to avoid going in application history (Activity call stack)
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                        // sending an Intent
                        ownerActivity.startActivity(intent);
                     } catch (Exception ex) {
                         // if something going wrong
                         // doing nothing
                     }
                }})

            .setNegativeButton("Cancel", null)    // cancel button
            .create();

        dialog.show();    // showing dialog
    }
}