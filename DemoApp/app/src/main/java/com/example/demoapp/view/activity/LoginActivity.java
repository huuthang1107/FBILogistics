package com.example.demoapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.demoapp.services.PutData;
import com.example.demoapp.R;
import com.example.demoapp.view.activity.pro.ProActivity;
import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity  {
    EditText EditText_username,EditText_password;
    MaterialButton btnLogin;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText_username = findViewById(R.id.username);
        EditText_password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.loginbtn);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String username, password;
                username = String.valueOf(EditText_username.getText());
                password = String.valueOf(EditText_password.getText());


                if(!username.equals("") && !password.equals("")){
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "password";
                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = username;
                            data[1] = password;
                            PutData putData = new PutData("http://192.168.1.199/login/login.php", "POST", field, data);
                            if(putData.startPut()){
                                if(putData.onComplete()){
                                    String result = putData.getResult();
                                    if(result.equals("Professional")){

                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), ProActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else if ( result.equals("Sale")) {
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        //Intent intent = new Intent(getApplicationContext(), SaleActivity.class);
                                        //startActivity(intent);
                                        //finish();
                                    } else{
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(),"All the field required",Toast.LENGTH_SHORT).show();
                }
            }
        });
  }


}