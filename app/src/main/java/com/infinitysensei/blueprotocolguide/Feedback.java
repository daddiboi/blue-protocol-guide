package com.infinitysensei.blueprotocolguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Feedback extends AppCompatActivity {
    ImageButton button1;
    EditText recipient, subject, body;
    String emailAddress, emailSubject, emailBody;
    Button sendEmailButton;

    Intent SendEmailIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        button1=(ImageButton)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recipient = (EditText) findViewById(R.id.act_main_et_email_address);
        subject = (EditText) findViewById(R.id.act_main_et_email_subject);
        body = (EditText) findViewById(R.id.act_main_et_email_body);
        sendEmailButton = (Button) findViewById(R.id.act_main_bt_send_email_button);


        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                emailAddress = recipient.getText().toString();
                emailSubject = subject.getText().toString();
                emailBody = body.getText().toString();

                SendEmailIntent = new Intent(Intent.ACTION_SEND);

                SendEmailIntent.setType("message/rfc822");
                SendEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAddress});
                SendEmailIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
                SendEmailIntent.putExtra(Intent.EXTRA_TEXT, emailBody);
                try {
                    startActivity(Intent.createChooser(SendEmailIntent, "Send email..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}