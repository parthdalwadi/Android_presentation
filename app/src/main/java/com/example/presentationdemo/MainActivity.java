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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener,
       GestureDetector.OnGestureListener, View.OnDragListener,
    GestureDetector.OnDoubleTapListener {


    private static final String TAG = "touchDemo";


    ImageView topImage, shareImage, delImage, likeImage;
    TextView textView;


    GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.details);
        topImage = findViewById(R.id.image1);

        shareImage = findViewById(R.id.share_i);
        delImage = findViewById(R.id.del_i);
        likeImage = findViewById(R.id.like);



        topImage.setOnTouchListener(this);
        likeImage.setOnTouchListener(this);
        delImage.setOnTouchListener(this);
        shareImage.setOnTouchListener(this);



        gestureDetector = new GestureDetector(this, this);


    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // Touch detection

//        Log.d(TAG, "onTouch: called");
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

        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.i(TAG, "onShowPress: ");
        textView.setText("Image Details : icon01_01.png");


    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.i(TAG, "onSingleTapUp: ");
        shareImage.setVisibility(View.INVISIBLE);
        delImage.setVisibility(View.INVISIBLE);

        likeImage.setImageResource(R.drawable.hearte);
        topImage.setImageResource(R.drawable.icon01_01);



        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.i(TAG, "onScroll: ");

        topImage.setY(e2.getY() + topImage.getY());
        topImage.setX(e2.getX() + topImage.getX());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.i(TAG, "onLongPress: ");
        shareImage.setVisibility(View.VISIBLE);
        delImage.setVisibility(View.VISIBLE);

        View.DragShadowBuilder builder = new View.DragShadowBuilder(topImage);

        topImage.startDrag(null, builder, null,0);

        builder.getView().setOnDragListener(this);



    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {


        boolean result = false;

        float diffX = e2.getX() - e1.getX();
        float diffY = e2.getY() - e1.getY();

        if (Math.abs(diffX) > Math.abs(diffY)){
            // fliong right-left

            if (Math.abs(diffX) > 200 && Math.abs(velocityX) > 100){
                if (diffX > 0){
                    //right fling
                    result = true;
                    flingDirection("right");
                } else{
                    //left fling
                    result = true;
                    flingDirection("left");
                }
            }

        }

        else{
            //fling top-bottom
            if (Math.abs(diffY) > 200 && Math.abs(velocityY) > 100){
                if (diffY > 0){
                    //bottom fling
                    result = true;
                    flingDirection("Bottom");
                } else{
                    //up fling
                    result = true;
                    flingDirection("Up");
                }
            }
        }

        return result;
    }

    public void flingDirection(String direction){
        Toast.makeText(this, direction + " fling", Toast.LENGTH_SHORT).show();
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
                //iv_touch.setImageResource(0);
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
        likeImage.setImageResource(R.drawable.heartf);
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.i(TAG, "onDoubleTapEvent: ");
        return true;
    }
}
