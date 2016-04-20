package mars.all.activity.three.vdhlayout;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class VdhLayout extends LinearLayout {
    
    private ViewDragHelper vdhelper;
    
    public VdhLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        vdhelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            
            @Override
            public boolean tryCaptureView(View arg0, int arg1) {
                return true;
            }
            //横向边界
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                
                int leftBound = getPaddingLeft();
                int rightBound = getPaddingRight();
                
                int newleft = Math.min(Math.min(left, leftBound), rightBound);
                return left;
            }
            
            //纵向边界
            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return top;
            }
            
            
            //如果子控件是clickable没有重写这个就不能拖动
            @Override
            public int getViewHorizontalDragRange(View child) {
                return getMeasuredWidth()-child.getMeasuredWidth();
            }
          //如果子控件是clickable没有重写这个就不能拖动
            @Override
            public int getViewVerticalDragRange(View child) {
                return getMeasuredHeight()-child.getMeasuredHeight();
            } 
            
        });
        
        
        
    }

    
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return vdhelper.shouldInterceptTouchEvent(ev);  //拦截当前事件
    }
    
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        vdhelper.processTouchEvent(event); //在这里处理了
        return true;  //true不再向下传递了    
    }
    
}
