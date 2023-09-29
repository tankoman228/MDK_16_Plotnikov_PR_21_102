package com.example.mdk_16_plotnikov_pr_21_102;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etText;
    private SharedPreferences sPref; //For saving options or other small data of this app
    private static final String SAVED_TEXT = "saved_text"; //key of text value in prefs

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_save = findViewById(R.id.btn_save);
        Button btn_load = findViewById(R.id.btn_load);
        etText = findViewById(R.id.tb);

        loadText(); //After starting app load prefs from prev. session

        btn_save.setOnClickListener(v -> saveText());

        btn_load.setOnClickListener(v -> loadText());
    }

    //Saving text to prefs
    private void saveText() {
        //Open prefs
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();

        //Saving
        ed.putString(SAVED_TEXT, etText.getText().toString());
        ed.apply();

        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
    }

    //Loading text from prefs + output
    private void loadText() {
        //Open prefs
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");

        //Loading
        etText.setText(savedText);
        Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
    }

    //Save in case of closing
    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }
}