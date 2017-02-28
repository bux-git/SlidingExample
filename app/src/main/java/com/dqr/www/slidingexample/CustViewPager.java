package com.dqr.www.slidingexample;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-02-24 20:21
 */

public class CustViewPager extends ViewGroup {
    private Context mContext;
    private int leftLimit;//左边界
    private int rightLimit;//右边界
    private int mSlop;
    private float moveX;
    private float lastX;
    private Scroller mScroller;
    private int scrollX;
    private int width;

    private ViewConfiguration mViewConfiguration;

    public CustViewPager(Context context) {
        super(context);
        init(context);
    }

    public CustViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustViewPager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        mContext=context;
        mViewConfiguration=ViewConfiguration.get(context);
        mSlop=mViewConfiguration.getScaledTouchSlop();
        mScroller = new Scroller(context);
        width=getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);;
        }
        leftLimit=getChildAt(0).getLeft();
        rightLimit=getChildAt(childCount-1).getRight();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed) {
            int childCount = getChildCount();

            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                int top = 0;
                int left = 0;
                int right = 0;
                int bottom = child.getMeasuredHeight();

                left = i * child.getMeasuredWidth();
                right = (i + 1) * child.getMeasuredWidth();

                child.layout(left, top, right, bottom);
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX=ev.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                moveX=ev.getRawX();
                int distanceX = (int) Math.abs(moveX - lastX);
                if(distanceX>mSlop){
                    return true;
                }
                lastX=moveX;
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
       switch (event.getAction()){
           case MotionEvent.ACTION_DOWN:
               return true;
           case MotionEvent.ACTION_MOVE:
               moveX=event.getRawX();
               int moveDistance= (int) (lastX-moveX);
               scrollX=getScrollX();
                if(moveDistance+scrollX<leftLimit){//左边界
                    scrollTo(leftLimit,0);
                    return false;
                }
                if(moveDistance+scrollX>rightLimit-width){//右边界
                    scrollTo(rightLimit-width,0);
                    return false;
                }
                lastX=moveX;
                scrollBy(moveDistance,0);
               break;
           case MotionEvent.ACTION_UP:
               scrollX=getScrollX();
               int index=(scrollX+width/2)/width;//获取滑动目标页index，滑动超过一半进入下一index，否则返回上一index
               int distanceX=width*index-scrollX;
               mScroller.startScroll(scrollX,0,distanceX,0);
               invalidate();
               break;
       }
        return super.onTouchEvent(event);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(mContext, attrs);
    }



    public static class LayoutParams extends RelativeLayout.LayoutParams{

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int w, int h) {
            super(w, h);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public LayoutParams(RelativeLayout.LayoutParams source) {
            super(source);
        }
    }

}
