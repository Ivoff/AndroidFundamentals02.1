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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageInput = findViewById(R.id.editTextMain);
        replyHeader = findViewById(R.id.reply_header);
        replyMessage = findViewById(R.id.reply_message);
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
            throw new RuntimeException();
        }
    }
}