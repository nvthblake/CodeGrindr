package com.example.codegrindr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();

        Map<String, String> values = new HashMap<>();
        values.put("name", "Blake");

        dbref.push().setValue(values, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                if (databaseError == null) {
                    Log.i("Info", "Save Successful");
                }
                else {
                    Log.i("Info", "Save Failed");
                }
            }
        });

        final Button loginButton = findViewById(R.id.loginButton);
        final TextView signUpTextView = findViewById(R.id.signUpTextView);
        final EditText emailEditText = findViewById(R.id.emailEditText);
        final EditText passEditText = findViewById(R.id.passEditText);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty(emailEditText)) {
                    emailEditText.setError("You cannot leave this empty");
                }
                else if (isEmpty(passEditText)) {
                    passEditText.setError("You cannot leave this empty");
                }
                else if (checkPasswordLeng(passEditText)) {
                    passEditText.setError("Password length should be at least 10 characters");
                    passEditText.setText("");
                } else {
            //final TextView lblUserName = findViewById(R.id.lblUserName);
            //lblUserName.setText("Works!!");
                    Intent FinishLogin = new Intent(getBaseContext(), RegisteredHackActivity.class);
                    startActivity(FinishLogin);
                }
            }

            private boolean checkPasswordLeng(EditText passText) {
                if (passText.getText().toString().trim().length() >= 10) return false;
                return true;
            }

            private boolean isEmpty(EditText etText) {
                if (etText.getText().toString().trim().length() > 0) return false;
                return true;
            }
        });

        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent goToSignUp = new Intent(getBaseContext(), SignUpActivity.class);
                startActivity(goToSignUp);
            }
        });
    }
}

