package com.example.sliit_travel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class TrainReservePage extends AppCompatActivity {

    private Button dateButton;
    private String date;
    private DatePickerDialog datePickerDialog;

    private scheduleServiceList selectedSchedule  ;

    String[] From = {"Galle1", "Galle2"};
    String[] to = {"Matara 1", "Matara 2"};

    AutoCompleteTextView autocompletetextview , autocompletetextview2;

    ArrayAdapter<String> adapterItems;

    Intent intent = getIntent();
   // scheduleServiceList selectedSchedule = (scheduleServiceList) intent.getSerializableExtra("selected_schedule");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_reserve_page);
        initDatePicker();

        autocompletetextview = findViewById(R.id.fromDropdown);
        autocompletetextview2 = findViewById(R.id.toDropdown);
        dateButton = findViewById(R.id.dateButton);


        // Create an ArrayAdapter to populate the AutoCompleteTextView with values from String[] From
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, From);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, to);

        // Set the adapter for the AutoCompleteTextView
        autocompletetextview.setAdapter(adapter);
        autocompletetextview2.setAdapter(adapter2);


        Button button_d1 = findViewById(R.id.date_time_save_button);
        Intent intent = getIntent();
        selectedSchedule = (scheduleServiceList)
                intent.getSerializableExtra("selected_schedule");
        autocompletetextview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
               // Toast.makeText(getApplicationContext(), "From:" + From, Toast.LENGTH_SHORT).show();
            }
        });

        button_d1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // Retrieve user inputs from the EditText and AutoCompleteTextView
                EditText passengerCountEditText = findViewById(R.id.doc_dog_counta1);
                String passengerCountStr = passengerCountEditText.getText().toString();

                AutoCompleteTextView fromAutoCompleteTextView = findViewById(R.id.fromDropdown);
                String from = fromAutoCompleteTextView.getText().toString();

                AutoCompleteTextView toAutoCompleteTextView = findViewById(R.id.toDropdown);
                String to = toAutoCompleteTextView.getText().toString();

                // Assuming you also have selectedDate from the DatePicker
                String selectedDate = dateButton.getText().toString(); // Retrieve from DatePicker

                // Convert passengerCountStr to an integer
                int passengerCount = 0;
                if (!passengerCountStr.isEmpty()) {
                    passengerCount = Integer.parseInt(passengerCountStr);
                }

                // Create an intent and pass the values to the next activity
                Intent intent = new Intent(TrainReservePage.this, selected_reservation_details.class);
                intent.putExtra("passengerCount", passengerCount);
                intent.putExtra("selectedDate", selectedDate);
                intent.putExtra("from", from);
                intent.putExtra("to", to);
                startActivity(intent);
            }
        });

    }



    private Bundle initDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                date = makeDateString(day, month, year);
                dateButton.setText(date);
                //*********************************************************

            }

        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        return null;
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";
        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view) {
        datePickerDialog.setTitle("Select Date");
        datePickerDialog.show();
    }

}