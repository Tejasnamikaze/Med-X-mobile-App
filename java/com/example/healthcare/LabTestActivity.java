package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.lights.LightState;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {
    private String[][] lab_details=
            {
                    {"Full Body Checkup", "2000", "Heart rate","Blood test","Urine test"},
                    {"Blood Glucose Fasting", "3000","Blood test", "Vitamin profile", "Lipid profile"},
                    {"COVID-19 Antibody IgG", "1000","Platelet count", "Blood Test", "Urine Test"},
                    {"Thyroid Check","3000","Blood test","Thyroid profile","Protein profile"},
                    {"Immunity Check","4000","Heart rate","Blood test","Urine test"},
            };



    TextView tv;
    Button btn;

    ArrayList list;
    SimpleAdapter sa;
    HashMap<String,String> item;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);


        btn =findViewById(R.id.buttonLabBack);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this,HomeActivity.class));
            }
        });

        list = new ArrayList();
        for(int i=0;i<lab_details.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",lab_details[i][0]);
            item.put("line2",lab_details[i][2]);
            item.put("line3",lab_details[i][3]);
            item.put("line4",lab_details[i][4]);
            item.put("line2","Fees:"+lab_details[i][1]+"/-");

            list.add(item);
        }
        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5",},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e,}
        );

        ListView lst = findViewById(R.id.listViewLT);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it =  new Intent(LabTestActivity.this,BookLabActivity.class);
                it.putExtra("text1",lab_details[i][0]);
                it.putExtra("text2",lab_details[i][1]);
                startActivity(it);


            }
        });


    }
}