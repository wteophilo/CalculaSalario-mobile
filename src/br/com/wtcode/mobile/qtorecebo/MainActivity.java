package br.com.wtcode.mobile.qtorecebo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class MainActivity extends Activity implements Runnable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler handler = new Handler();
        handler.postDelayed(this,2500);
    }

    @Override
    public void run() {
        startActivity(new Intent(this, null));
        finish();
    }

}
