package com.example.serviceboundexcercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    MyService myService;
    boolean isBind = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);
        Intent intent = new Intent(this,MyService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    public void getFirstServiceMessage(View view) {

        String message = myService.getFirstMessage();
        textView.setText(message);
    }

    public void getSecondServiceMessage(View view) {
        String message = myService.getSecondMessage();
        textView.setText(message);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            MyService.LocalService localService = (MyService.LocalService) iBinder;
            myService = localService.getService();
            isBind = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

            isBind = false;

        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        if (isBind){
            unbindService(mConnection);
            isBind = false;
        }
    }
}