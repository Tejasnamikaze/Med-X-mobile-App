package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthcare.Register;

public class Login1 extends AppCompatActivity {
    EditText edUsername, edPassword;
    Button btn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        edPassword=findViewById(R.id.editLoginPassword);
        edUsername=findViewById(R.id.editLoginUser);
        btn=findViewById(R.id.buttonLogin);
        tv=findViewById(R.id.textNewUser);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=edUsername.getText().toString();
                String password= edPassword.getText().toString();
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                if(username.length()==0 || password.length()==0)
                {Toast.makeText(getApplicationContext(),"Please fill all details",Toast.LENGTH_SHORT).show();
                }
                else if(password.length()<6 || password.length()>16) {
                    Toast.makeText(getApplicationContext(),"Please enter correct password",Toast.LENGTH_SHORT).show();
                }
                else if((!TextUtils.isEmpty(username) && Patterns.EMAIL_ADDRESS.matcher(username).matches())==false)
                {
                    Toast.makeText(getApplicationContext(),"Please enter a valid email",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(db.login(username,password)==1){
                        Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_SHORT).show();
                        //SharedPreferences sharedpreferences = getSharedPreferences("shared_refs", Context.MODE_PRIVATE);
                        //SharedPreferences.Editor editor = sharedpreferences.edit();
                        //editor.apply();
                        startActivity(new Intent(Login1.this, HomeActivity.class));
                    }else{
                        Toast.makeText(getApplicationContext(),"Invalid Username and Password",Toast.LENGTH_SHORT).show();

                    }

                }}
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login1.this, Register.class));

            }
        });


    }
}