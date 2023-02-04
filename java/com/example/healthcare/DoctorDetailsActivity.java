package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;


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

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1=
            {
                    {"Doctor Name : Ajit Saste", "Hospital Address : JP Nagar", "Exp : 5yrs", "Mobile No : 7406666479","600"},
                    {"Doctor Name : Prasad Pawar", "Hospital Address : Jayanagar", "Exp : 15yrs", "Mobile No : 9845769323","900"},
                    {"Doctor Name : Swapnil Kale", "Hospital Address : Indranagar", "Exp : 8yrs", "Mobile No : 973100023","700"},
                    {"Doctor Name : Deepak D","Hospital Address : Whitefield", "Exp : 6yrs", "Mobile No : 9856432312","300"},
                    {"Doctor Name : Ashok Pandey", "Hospital Address : New Bel Road", "Exp : 7yrs", "Mobile No : 7689453200","800"},
            };
    private String[][] doctor_details2=
            {
                    {"Doctor Name : Sushma J", "Hospital Address : JP Nagar", "Exp : 5yrs", "Mobile No : 7406666479","600"},
                    {"Doctor Name : Sharanya S", "Hospital Address : Jayanagar", "Exp : 15yrs", "Mobile No : 9845769323","900"},
                    {"Doctor Name : Vani Krishna", "Hospital Address : Indranagar", "Exp : 8yrs", "Mobile No : 973100023","700"},
                    {"Doctor Name : Uma Trasi","Hospital Address : Whitefield", "Exp : 6yrs", "Mobile No : 9856432312","300"},
                    {"Doctor Name : Geetha B", "Hospital Address : New Bel Road", "Exp : 7yrs", "Mobile No : 7689453200","800"},
            };

    private String[][] doctor_details3=
            {
                    {"Doctor Name : Vivek Aithal", "Hospital Address : JP Nagar", "Exp : 5yrs", "Mobile No : 7406666479","600"},
                    {"Doctor Name : Jaswanth Reddy", "Hospital Address : Jayanagar", "Exp : 15yrs", "Mobile No : 9845769323","900"},
                    {"Doctor Name : Karthikeyan B.V", "Hospital Address : Indranagar", "Exp : 8yrs", "Mobile No : 973100023","700"},
                    {"Doctor Name : Sunila Sampath","Hospital Address : Whitefield", "Exp : 6yrs", "Mobile No : 9856432312","300"},
                    {"Doctor Name : P Rajesh", "Hospital Address : New Bel Road", "Exp : 7yrs", "Mobile No : 7689453200","800"},
            };
    private String[][] doctor_details4=
            {
                    {"Doctor Name : Umesh Gupta", "Hospital Address : JP Nagar", "Exp : 5yrs", "Mobile No : 7406666479","600"},
                    {"Doctor Name : Rakhee P", "Hospital Address : Jayanagar", "Exp : 15yrs", "Mobile No : 9845769323","900"},
                    {"Doctor Name : Ashok ML", "Hospital Address : Indranagar", "Exp : 8yrs", "Mobile No : 973100023","700"},
                    {"Doctor Name : Pradeep H","Hospital Address : Whitefield", "Exp : 6yrs", "Mobile No : 9856432312","300"},
                    {"Doctor Name : Pankaj Singhai", "Hospital Address : New Bel Road", "Exp : 7yrs", "Mobile No : 7689453200","800"},
            };
    private String[][] doctor_details5=
            {
                    {"Doctor Name : B.Ramesh", "Hospital Address : JP Nagar", "Exp : 5yrs", "Mobile No : 7406666479","600"},
                    {"Doctor Name : Sunil Kumar", "Hospital Address : Jayanagar", "Exp : 15yrs", "Mobile No : 9845769323","900"},
                    {"Doctor Name : Venkatesh S", "Hospital Address : Indranagar", "Exp : 8yrs", "Mobile No : 973100023","700"},
                    {"Doctor Name : Sanjay Bhat","Hospital Address : Whitefield", "Exp : 6yrs", "Mobile No : 9856432312","300"},
                    {"Doctor Name : Ashok Pandey", "Hospital Address : New Bel Road", "Exp : 7yrs", "Mobile No : 7689453200","800"},
            };
    TextView tv;
    Button btn;
    String [][] doctor_details = {};
    ArrayList list;
    SimpleAdapter sa;
    HashMap<String,String> item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textView_title);
        btn =findViewById(R.id.buttonDDback);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("General Physician")==0)
            doctor_details=doctor_details1;
        else
        if(title.compareTo("Dietitian")==0)
            doctor_details=doctor_details2;
        else
        if(title.compareTo("Dentist")==0)
            doctor_details=doctor_details3;
        else
        if(title.compareTo("Surgeon")==0)
            doctor_details=doctor_details4;
        else
            doctor_details=doctor_details5;


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });

        list = new ArrayList();
        for(int i=0;i<doctor_details.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","Cons Fees:"+doctor_details[i][4]+"/-");
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5",},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e,}
                );

        ListView lst = findViewById(R.id.listViewDD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it =  new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);


            }
        });


    }
}