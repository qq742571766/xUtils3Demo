package com.example.administrator.xutils3demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

@ContentView(R.layout.activity_main)
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    @Event(value = {R.id.btn_note, R.id.btn_networking, R.id.btn_a_picture, R.id.btn_more_picture})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.btn_note:
                Intent note = new Intent(this,MyFragmentActivity.class);
                startActivity(note);
                break;
            case R.id.btn_networking:
                Intent networking = new Intent(this,NetActivity.class);
                startActivity(networking);
                break;
            case R.id.btn_a_picture:
                break;
            case R.id.btn_more_picture:
                break;
        }
    }
}