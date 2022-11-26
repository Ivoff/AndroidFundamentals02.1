package com.example.twoactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static final String REPLY_MESSAGE = SecondActivity.class.getName() + ".extra.REPLY";
    private EditText replyInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        replyInput = findViewById(R.id.editTexTMain2);

        String receivedMessage = getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.text_message);
        textView.setText(receivedMessage);
    }

    public void returnReply(View view) {
        Intent replyIntent = new Intent();
        replyIntent.putExtra(REPLY_MESSAGE, replyInput.getText().toString());
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}