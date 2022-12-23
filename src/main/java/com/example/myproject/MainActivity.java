package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    EditText username,email, password, repassword,txtsolde;
    Button signup, signin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String email1 = email.getText().toString();
                String pass = password.getText().toString();
                String solde = txtsolde.getText().toString();



                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(solde))
                    Toast.makeText(MainActivity.this, "all fields required", Toast.LENGTH_SHORT).show();
                else {
                    Client_Post post = new Client_Post();
                    post.execute(user,email1,pass,solde);
                    Toast.makeText(MainActivity.this, "Data Sent", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),loginActivity.class);
                startActivity(intent);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),loginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void init() {
        username = findViewById(R.id.username);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        signup=findViewById(R.id.signup);
        signin=findViewById(R.id.signin);
        txtsolde=findViewById(R.id.txtsolde);
    }

    public class Client_Post extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            try {

                Socket socket = new Socket("192.168.1.14", 401);

                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osr = new OutputStreamWriter(os);
                PrintWriter pw =new PrintWriter(osr,true);

                pw.println(strings[0]+","+strings[1]+","+strings[2]+","+strings[3]);

                socket.close();

            }catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}


