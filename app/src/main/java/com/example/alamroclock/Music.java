package com.example.alamroclock;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class Music extends Service {
    MediaPlayer mediaPlayer;
    int id;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String key = intent.getExtras().getString("extra");
        Log.e("key", key);

        if (key.equals("on")){
            id =1;
        }else if (key.equals("off")){
            id =0;
        }

        if (id == 1){
            mediaPlayer = MediaPlayer.create(this, R.raw.vocung);
            mediaPlayer.start();
            id =0;
        } else if (id == 0) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }

        Log.v("server", "Service");
        return START_NOT_STICKY;
    }
}
