package com.example.twoactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = MainActivity.class.getName() + ".extra.MESSAGE";

    private final int REPLY_ACTIVITY_INTENT = 1;

    private EditText messageInput;
    private TextView replyHeader;
    private TextView replyMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "----------");
        Log.d(LOG_TAG, "onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageInput = findViewById(R.id.editTextMain);
        replyHeader = findViewById(R.id.reply_header);
        replyMessage = findViewById(R.id.reply_message);

        if (savedInstanceState != null && savedInstanceState.getBoolean("reply_visible")) {
            replyHeader.setVisibility(View.VISIBLE);
            replyMessage.setText(savedInstanceState.getString("reply_text"));
            replyMessage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause()");
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy()");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(LOG_TAG, "onSaveInstanceState()");
        if (replyHeader.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible", true);
            outState.putString("reply_text", replyMessage.getText().toString());
        }
    }

    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "Button Clicked!");
        Intent secondActivity = new Intent(this, SecondActivity.class);
        secondActivity.putExtra(EXTRA_MESSAGE, messageInput.getText().toString());
        startActivityForResult(secondActivity, REPLY_ACTIVITY_INTENT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REPLY_ACTIVITY_INTENT) {
                replyHeader.setVisibility(View.VISIBLE);
                replyMessage.setText(data.getStringExtra(SecondActivity.REPLY_MESSAGE));
                replyMessage.setVisibility(View.VISIBLE);
            }
        }
        else {
            String result;
            switch (resultCode) {
                case RESULT_CANCELED:
                    result = "RESULT_CANCELED";
                    break;
                case RESULT_FIRST_USER:
                    result = "RESULT_FIRST_USER";
                    break;
                default:
                    result = "NOT_RECOGNIZED_CODE";
            }
            Log.d(LOG_TAG, result);
        }
    }
}