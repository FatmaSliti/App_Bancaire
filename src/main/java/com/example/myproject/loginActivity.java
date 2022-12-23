package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class loginActivity extends AppCompatActivity {


    EditText username, password;
    Button signIn, signup;
    public String resTest = "";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);


        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        signIn=findViewById(R.id.signIn);
        signup = findViewById(R.id.account);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }});

                signIn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String user = username.getText().toString();
                        String pass = password.getText().toString();


                        if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass))
                            Toast.makeText(loginActivity.this, "All fields required", Toast.LENGTH_SHORT).show();
                        else {
                            Client_Test test = new Client_Test(loginActivity.this);
//                    do{
//                    }while(resTest == null);
                            test.execute(user, pass);
                            if (resTest.length() == 3) {
                                Intent intent = new Intent(loginActivity.this, soldeActivity.class);
                                intent.putExtra("solde", resTest);
                                startActivity(intent);
                            } else
                                Toast.makeText(loginActivity.this, "" + resTest, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            public class Client_Test extends AsyncTask<String, Void, String> {

                private loginActivity sign = null;

                public Client_Test(Context context) {
                    sign = (loginActivity) context;
                }

                @Override
                protected void onPostExecute(String string) {
                    sign.resTest = string;
                }

                @Override
                protected String doInBackground(String... strings) {

                    try {
                        Socket socket = new Socket("192.168.1.14", 402);

                        OutputStream os = socket.getOutputStream();
                        OutputStreamWriter osr = new OutputStreamWriter(os);
                        PrintWriter pw = new PrintWriter(osr, true);

                        pw.println(strings[0] + "," + strings[1]);

                        InputStream sr = socket.getInputStream();
                        InputStreamReader r = new InputStreamReader(sr);
                        BufferedReader br = new BufferedReader(r);

                        String s = br.readLine();

                        socket.close();
                        return s;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return null;
                }
            }
        }
