package com.example.serviceboundexcercise;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MyService extends Service {
    private final IBinder mBinder = new LocalService();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class LocalService extends Binder{

        MyService getService(){


            return MyService.this;
        }

    }

    public String getFirstMessage(){

        return "Hello Welcome All";
    }

    public String getSecondMessage(){

        return "This is Bound Service Example";
    }
}
