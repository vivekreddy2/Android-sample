package com.example.rishikeshwar.loginapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AllUsers extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);

        final TableLayout userTable = (TableLayout) findViewById(R.id.userTable);
        final Context x = this;

        database.getReference().child("users").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot user : dataSnapshot.getChildren()) {
                    for (DataSnapshot booksSnapshot : user.getChildren()) {
                        String key = booksSnapshot.getKey();
                        Object value = booksSnapshot.getValue();

                        TableRow tr1 = new TableRow(x);
                        TextView textview = new TextView(x);
                        textview.setTextSize(15);
                        textview.setText("Username: " + value);
                        tr1.addView(textview);
                        userTable.addView(tr1);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
