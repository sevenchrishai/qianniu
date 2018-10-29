package com.hqbb.duanzi.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * Created by ylh on 2018/1/8 0008.
 */

public class NoScrollListView extends ListView {

    /**
     * The parent scroll mView.
     */
    private ScrollView parentScrollView;

    /**
     * The max height.
     */
    private int maxHeight;

    public boolean isOnMeasure;

    /**
     * Gets the parent scroll mView.
     *
     * @return the parent scroll mView
     */
    public ScrollView getParentScrollView() {
        return parentScrollView;
    }

    /**
     * Sets the parent scroll mView.
     *
     * @param parentScrollView the new parent scroll mView
     */
    public void setParentScrollView(ScrollView parentScrollView) {
        this.parentScrollView = parentScrollView;
    }


    /**
     * Instantiates a new ab inner list mView.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public NoScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        isOnMeasure = true;
        if (maxHeight > -1) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                    MeasureSpec.AT_MOST);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        isOnMeasure = false;
        super.onLayout(changed, l, t, r, b);
    }

    /**
     *
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                setParentScrollAble(false);
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:

            case MotionEvent.ACTION_CANCEL:
                setParentScrollAble(true);
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * Sets the parent scroll able.
     *
     * @param flag the new parent scroll able
     */
    private void setParentScrollAble(boolean flag) {
        if (parentScrollView != null)
            parentScrollView.requestDisallowInterceptTouchEvent(!flag);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

}
