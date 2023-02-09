package com.example.ticketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


//create 2 variables for logging in page
public class MainActivity extends AppCompatActivity {


    private EditText EnterName;
    private Button EnterButton;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connect variable to enter-name on xml
        EnterName = (EditText)findViewById(R.id.fldEnterName);

        //connect variable to button enter on xml
        EnterButton = (Button)findViewById(R.id.btnEnter);


        // when clicked button shows name typed
        EnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNextActivity(EnterName.getText().toString());

            }
        });

    }

    public void goToNextActivity(String EnteredName) {


        /*
        Toast.makeText(getApplicationContext(), EnterName.getText().toString(),
                Toast.LENGTH_LONG).show();

         */

        Intent myIntent = new Intent(getApplicationContext(), SecondActivity.class);
        myIntent.putExtra("theValue",EnteredName);
        startActivity(myIntent);

    }

}