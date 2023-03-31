package com.example.passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button genButton, copyButton ;
    NumberPicker numberPicker;
    TextView textView;
    int value;
    String upper = "AZERTYUIOPQSDFGHJKLMWXCVBN";
    String lower = "azertyuiopqsdfghjklmwxxcvbn";
    String num = "0123456789";
    String special = ",;:!?/'.-_=+°@";
    String combination = upper + lower + num + special;
    Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberPicker = findViewById(R.id.numberpicker_ID);
        genButton = findViewById(R.id.buttongen_ID);
        copyButton = findViewById(R.id.buttoncopy_ID);
        textView = findViewById(R.id.textView);
        numberPicker.setMinValue(8);
        numberPicker.setMaxValue(40);

        genButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value = numberPicker.getValue();
                char[] password = new char[value];
                for(int i = 0 ;i<value;i++){
                    password[i] = combination.charAt(random.nextInt(combination.length()));
                }
                textView.setText(new String(password));
            }
        });
        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager =(ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE );
                ClipData clipData = ClipData.newPlainText("Copy",textView.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(MainActivity.this, "Copied" , Toast.LENGTH_SHORT).show();
            }
        });
    }
}