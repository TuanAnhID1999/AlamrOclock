package com.example.alamroclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v("broad", "Hello");
        String chuoi_string = intent.getExtras().getString("extra");
        Log.e("key", chuoi_string);

         intent = new Intent(context, Music.class);
         // khi nhận dc sự kiện gì nó sẽ báo sang service class music
        intent.putExtra("extra",chuoi_string);
        context.startService(intent);
    }
}
