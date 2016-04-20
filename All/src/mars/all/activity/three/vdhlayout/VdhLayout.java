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
            //����߽�
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                
                int leftBound = getPaddingLeft();
                int rightBound = getPaddingRight();
                
                int newleft = Math.min(Math.min(left, leftBound), rightBound);
                return left;
            }
            
            //����߽�
            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return top;
            }
            
            
            //����ӿؼ���clickableû����д����Ͳ����϶�
            @Override
            public int getViewHorizontalDragRange(View child) {
                return getMeasuredWidth()-child.getMeasuredWidth();
            }
          //����ӿؼ���clickableû����д����Ͳ����϶�
            @Override
            public int getViewVerticalDragRange(View child) {
                return getMeasuredHeight()-child.getMeasuredHeight();
            } 
            
        });
        
        
        
    }

    
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return vdhelper.shouldInterceptTouchEvent(ev);  //���ص�ǰ�¼�
    }
    
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        vdhelper.processTouchEvent(event); //�����ﴦ����
        return true;  //true�������´�����    
    }
    
}
