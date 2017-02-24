package com.dqr.www.slidingexample;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-02-24 17:15
 */

public class PullDownRelativeLayout extends RelativeLayout {
    private static final String TAG = "PullDownRelativeLayout";

    private Scroller mScroller;
    private GestureDetector mGesture;


    public PullDownRelativeLayout(Context context) {
        super(context);
        init(context);
    }

    public PullDownRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PullDownRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PullDownRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        mScroller = new Scroller(context);
        mGesture = new GestureDetector(context,new PullDownGesture());
    }


    public void startScroll(int dx,int dy){
        mScroller.startScroll(mScroller.getFinalX(),mScroller.getFinalY(),dx,dy);
        invalidate();
    }

    private void reset(int x,int y){
        Log.d(TAG, "reset:x:"+mScroller.getFinalX()+"    y:"+mScroller.getFinalY());
        startScroll(x-mScroller.getFinalX(),y-mScroller.getFinalY());

    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: "+event.getAction());
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                reset(0,0);
                break;
            default :
                return mGesture.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }

    private  class PullDownGesture implements GestureDetector.OnGestureListener{

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d(TAG, "onScroll: distanceX:"+distanceX+"   distanceY:"+distanceY);
            startScroll(0,(int)distanceY/2);
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    }
}
