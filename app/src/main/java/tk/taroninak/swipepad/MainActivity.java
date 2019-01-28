package tk.taroninak.swipepad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import tk.taroninak.swipepad.services.GestureDetectorService;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    @Inject
    public GestureDetectorService gestureDetectorService;

    private GestureDetector gDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View view = findViewById(R.id.view);
        gDetector = new GestureDetector(this, gestureDetectorService);
        view.setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gDetector.onTouchEvent(event);
    }
}
