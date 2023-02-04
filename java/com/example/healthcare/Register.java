package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText edPhone,edEmail,edPassword,edConfirm;
    Button btn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edPhone=findViewById(R.id.editTextAppFulllName);
        edPassword=findViewById(R.id.editTextAppContactNumber);
        edEmail=findViewById(R.id.editTextAppAddress);
        edConfirm=findViewById(R.id.editTextAppfees);
        btn=findViewById(R.id.buttonBookAppointment);
        tv=findViewById(R.id.textOldUser);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,Login1.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone=edPhone.getText().toString();
                String email=edEmail.getText().toString();
                String password= edPassword.getText().toString();
                String confirm=edConfirm.getText().toString();
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                if(phone.length()==0 || password.length()==0 || email.length()==0 || confirm.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please fill all details",Toast.LENGTH_SHORT).show();
                }
                else if(phone.length()<10 || phone.length()>10)
                {
                    Toast.makeText(getApplicationContext(),"Please enter a valid Phone Number",Toast.LENGTH_SHORT).show();
                }

                else if((!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches())==false)
                {
                    Toast.makeText(getApplicationContext(),"Please enter a valid email",Toast.LENGTH_SHORT).show();
                }
                else if(password.length()<6 || password.length()>16)
                {
                    Toast.makeText(getApplicationContext(),"Please enter a password between 6 and 16 characters",Toast.LENGTH_SHORT).show();
                }
                else if(password.compareTo(confirm)==0)
                {
                        db.register(phone,email,password);
                        Toast.makeText(getApplicationContext(),"Record Inserted",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Register.this,Login1.class));
                }
                else{
                        Toast.makeText(getApplicationContext(),"Passwords do not match",Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }



}
