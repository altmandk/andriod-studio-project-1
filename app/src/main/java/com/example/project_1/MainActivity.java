package com.example.project_1;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView receivedText = (TextView) findViewById(R.id.enteredText);
        final Button addButton = (Button) findViewById(R.id.button2);
        addButton.setEnabled(false);

        Bundle userName = getIntent().getExtras();
        if(userName == null) {return;}

        final String fullName = userName.getString("firstInput");
        final String result = userName.getString("result");

        if(result.contains("RESULT_OK")) {
            addButton.setEnabled(true);
            receivedText.setText("You Entered: " + fullName);
        }
        else {
            addButton.setEnabled(true);
            receivedText.setText("Entry Not Valid Please Try Again");
        }

        addButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        if(result.contains("RESULT_OK")) {
                            receivedText.setText("You Entered: " + fullName);
                            Intent contactIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
                            contactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                            contactIntent.putExtra(ContactsContract.Intents.Insert.NAME, fullName);
                            startActivity(contactIntent);
                        }
                        else {
                            receivedText.setText("Press Enter Contact Name");
                            Context context = getApplicationContext();
                            CharSequence text = "\"" + fullName + "\"" + " is not a valid entry";
                            int duration = Toast.LENGTH_LONG;
                            Toast.makeText(context, text, duration).show();
                        }
                    }
                }
        );
    }

    public void onClick(View view) {
        Intent nameIntent = new Intent(this, NameActivity.class);
        startActivity(nameIntent);
    }
}


