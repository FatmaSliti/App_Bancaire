package com.example.myproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class virementActivity extends AppCompatActivity {

    EditText montant, destinataire;
    Button confirmer, disc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virement);

        montant = findViewById(R.id.montant);
        destinataire = findViewById(R.id.destinataire);
        confirmer = findViewById(R.id.confirmer);
        disc = findViewById(R.id.disc);


    }

}
