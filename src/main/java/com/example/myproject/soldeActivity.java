package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class soldeActivity  extends AppCompatActivity {
    EditText nbrSolde;
    Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solde);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        logout = findViewById(R.id.logout);

        nbrSolde = findViewById(R.id.nbrSolde);
        getSolde();

        //logout button
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(soldeActivity.this, loginActivity.class);
                startActivity(intent);
            }
        });
    };

    public void getSolde() {
        Intent intent = getIntent();
        nbrSolde.setText(intent.getStringExtra("solde"));
    }
}
