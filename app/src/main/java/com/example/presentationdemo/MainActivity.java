package com.example.presentationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;//
import android.widget.TextView; // added

public class MainActivity extends AppCompatActivity implements View.OnTouchListener,
        GestureDetector.OnGestureListener, View.OnDragListener, GestureDetector.OnDoubleTapListener {


    private static final String TAG = "touchDemo";
    TextView tv_touch;
    ImageView iv_touch;
    GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_touch = findViewById(R.id.touch_result);
        iv_touch = findViewById(R.id.touch);

        iv_touch.setOnTouchListener(this);
        gestureDetector = new GestureDetector(this, this);


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
       // Log.d(TAG, "onTouch: called");
//        int action = event.getAction();
//
//        switch (action){
//            case MotionEvent.ACTION_DOWN:
//                tv_touch.setText("Action Down");
//                return true;
//            case MotionEvent.ACTION_UP:
//                tv_touch.setText("Action Up");
//                return true;
//            case MotionEvent.ACTION_MOVE:
//                tv_touch.setText("Action Move at: " + event.getX() + " , " +event.getY());
//                return true;
//            default: return true;
//        }

        gestureDetector.onTouchEvent(event);

        return true;

    }


    /* Gesture detector methods */


    @Override
    public boolean onDown(MotionEvent e) {
        Log.i(TAG, "onDown: ");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.i(TAG, "onShowPress: ");


    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.i(TAG, "onSingleTapUp: ");
        iv_touch.setImageResource(R.drawable.touch);

        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.i(TAG, "onScroll: ");
        iv_touch.setImageResource(R.drawable.scroll);

        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

        Log.i(TAG, "onLongPress: ");
        iv_touch.setImageResource(R.drawable.broken);

        // build shadow builder - 3 line code

        //View.DragShadowBuilder shadow_builder = new View.DragShadowBuilder(iv_touch);
       // iv_touch.startDrag(null, shadow_builder, null, 0);
        //shadow_builder.getView().setOnDragListener(this);

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        Log.i(TAG, "onFling: ");
        return true;
    }

    // OnDrag Method

    @Override
    public boolean onDrag(View v, DragEvent event) {

        switch(event.getAction()){

            case DragEvent.ACTION_DRAG_STARTED:
                Log.i(TAG, "onDrag: started");
                return true;
            case DragEvent.ACTION_DRAG_ENDED:
                Log.i(TAG, "onDrag: ended");
                iv_touch.setImageResource(0);
                return true;
            default: return true;


        }




    }

    /* double tap methods */
    
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.i(TAG, "onSingleTapConfirmed: ");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.i(TAG, "onDoubleTap: ");
        Intent i = new Intent(MainActivity.this, Second.class);
        startActivity(i);
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.i(TAG, "onDoubleTapEvent: ");
        return true;
    }
}
