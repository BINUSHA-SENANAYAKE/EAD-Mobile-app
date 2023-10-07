package com.example.sliit_travel_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class all_reservations extends AppCompatActivity {

    private ListView listview;
    private TextView count;
    Context context;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_reservations);


        context = this;
        listview = findViewById(R.id.doc_appoin_list);
        count = findViewById(R.id.appoin_count);
        //  appointments = new ArrayList<>();
        //   appointments = doc_dbHandler.getAllAppointments();

//        Doctor_Appointment_Adaptor adaptor = new Doctor_Appointment_Adaptor(context,R.layout.doc_single_appoi,appointments);
//        listview.setAdapter(adaptor);
//        //get Appointment count from the table
//        int countAppointment = doc_dbHandler.countAppointment();
//        count.setText("You have "+countAppointment+" Appointments");

//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                Doctor_Appointment_Model_Class doc_appointment_model_class = appointments.get(i);
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setTitle("What do you want to do to this Appointment ?");
//                builder.setMessage(doc_appointment_model_class.getUserName());
//                builder.show();
//                builder.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        startActivity(new Intent(context,doc_all_appointment.class));
//                    }
//                });
//                builder.setNeutralButton("DELETE APPOINTMENT", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        doc_dbHandler.deleteAppointment(doc_appointment_model_class.getId());
//                        myToast();
//                        startActivity(new Intent(context,doc_all_appointment.class));
//                    }
//                });
//
//                builder.setNegativeButton("EDIT APPOINTMENT", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Intent intent = new Intent(context,Doctor_Edit_Appontment.class);
//                        intent.putExtra("id",String.valueOf(doc_appointment_model_class.getId()));
//                        startActivity(intent);
//                    }
//                });
//                builder.show();
//            }
//        });
//
    }

}
