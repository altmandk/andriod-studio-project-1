package com.example.project_1;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
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
    TextView receivedText;
    Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        receivedText = (TextView) findViewById(R.id.enteredText);
        addButton = (Button) findViewById(R.id.button2);
        addButton.setEnabled(false);
    }

    public void onClick(View view) {
        Intent nameIntent = new Intent(this, NameActivity.class);
        startActivityForResult(nameIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK) {
            addButton.setEnabled(true);
            final String receivedMessage = data.getStringExtra("firstInput");
            receivedText.setText("You Entered: " + receivedMessage);

            addButton.setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            Intent contactIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
                            contactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                            contactIntent.putExtra(ContactsContract.Intents.Insert.NAME, receivedMessage);
                            startActivity(contactIntent);
                        }
                    }
            );
        }
        else {
            addButton.setEnabled(true);
            final String receivedMessage = data.getStringExtra("firstInput");
            receivedText.setText("Entry Not Valid Please Try Again");
            addButton.setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            receivedText.setText("Press Enter Contact Name");
                            Context context = getApplicationContext();
                            CharSequence text = "\"" + receivedMessage + "\"" + " is not a valid entry";
                            int duration = Toast.LENGTH_LONG;
                            Toast.makeText(context, text, duration).show();
                        }
                    }
            );
        }
    }
}


