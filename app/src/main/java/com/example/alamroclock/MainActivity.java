package com.example.alamroclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button btnDat, btnDung;
    private TimePicker timePicker;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.txt_time);
        btnDat = findViewById(R.id.btn_datGio);
        btnDung = findViewById(R.id.btn_stop);
        timePicker = findViewById(R.id.time_picker);
        calendar = Calendar.getInstance();
         intent = new Intent(MainActivity.this, AlarmReceiver.class);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);// cho phép tư động truy cập hệ thống máy


        btnDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCalenda();
            }
        });

        btnDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("extra", "off");
                alarmManager.cancel(pendingIntent);
                textView.setText("Stop");
                sendBroadcast(intent);
            }
        });
    }


    private void getCalenda(){

        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
        calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
       int gio = timePicker.getCurrentHour();
       int phut = timePicker.getCurrentMinute();

       String string_gio = String.valueOf(gio);
       String string_phut = String.valueOf(phut);

       if (gio > 12){
           string_gio = String.valueOf(gio -12);
       }

       if (phut <10){
           string_phut = "0" + String.valueOf(phut);
       }
       intent.putExtra("extra","on");
       pendingIntent = PendingIntent.getBroadcast(MainActivity.this,
               0,intent, PendingIntent.FLAG_UPDATE_CURRENT);

       alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
       textView.setText(string_gio +" : "  + string_phut);
    }
}
