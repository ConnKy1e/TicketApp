package com.example.ticketapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;





public class SecondActivity extends AppCompatActivity {

    private ConstraintLayout ticketsButton;

    private Button aboutButton;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //connect ticket variable to button on activity second
        ticketsButton = (ConstraintLayout) findViewById(R.id.conGetTickets);

        //connect ticket variable to button on activity second
        aboutButton = (Button) findViewById(R.id.btnAbout);


        //when button clicked send to third activity, ticket purchase
        ticketsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, ThirdActivity.class));
            }
        });

        //when button clicked send to third activity, ticket purchase
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, FourthActivity.class));
            }
        });





        // theValue
        // fldResultText
        TextView nameview = (TextView)findViewById(R.id.fldResultText);

        // Display the string
        nameview.setText(getIntent().getExtras().getString("theValue"));

        createDatabase();



    }



    public void createDatabase() {

        try {

            StringBuilder mySB = new StringBuilder();

            SQLiteDatabase myDB = this.openOrCreateDatabase("TicketNames", MODE_PRIVATE, null);

            myDB.execSQL("CREATE TABLE IF NOT EXISTS tickets (ticket_id INT(5), " +
                    " ticket_name VARCHAR, ticket_type VARCHAR)");

            myDB.execSQL("INSERT INTO tickets (ticket_id, ticket_name, ticket_type) " +
                    "VALUES (1, 'User', 'Adult')");

            Cursor myCursor = myDB.rawQuery("SELECT * FROM tickets", null);

            int idIndex = myCursor.getColumnIndex("ticket_id");
            int nameIndex = myCursor.getColumnIndex("ticket_name");
            int typeIndex = myCursor.getColumnIndex("ticket_type");



            // move to first
            myCursor.moveToFirst();

            for (int i=0; i < myCursor.getCount(); i++) {
                //Log.i("id", Integer.toString(myCursor.getInt(idIndex)));
                //Log.i("name",myCursor.getString(nameIndex));
                //Log.i("type",myCursor.getString(typeIndex));

                mySB.append(Integer.toString(myCursor.getInt(idIndex)) + ",");
                mySB.append(myCursor.getString(nameIndex) + ",");
                mySB.append(myCursor.getString(typeIndex) + "\n");

                myCursor.moveToNext();


            }




            String SqlResult = mySB.toString();
            Toast.makeText(getApplicationContext(), SqlResult,
                    Toast.LENGTH_LONG).show();

            myCursor.close();

            myDB.execSQL("DROP TABLE IF EXISTS tickets");

            myDB.close();





        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}