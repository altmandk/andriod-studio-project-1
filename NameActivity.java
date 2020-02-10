package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class NameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        EditText EnterName = findViewById(R.id.enterName);
        EnterName.setOnEditorActionListener(editorListener);
    }

    private TextView.OnEditorActionListener editorListener = new TextView.OnEditorActionListener() {

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                EditText EnterName = (EditText) findViewById(R.id.enterName);
                String userInput = EnterName.getText().toString().trim();

                Intent mainIntent = new Intent(NameActivity.this, MainActivity.class);

                if (userInput.contains(" ") && !userInput.matches(".*\\d.*")) {
                    mainIntent.putExtra("firstInput", userInput);
                    setResult(RESULT_OK, mainIntent);
                    finish();
                }
                else {
                    mainIntent.putExtra("firstInput", userInput);
                    setResult(RESULT_CANCELED, mainIntent);
                    finish();
                }
            }
            return false;
        }
    };
}
