package mars.all.activity.two.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * ���ԣ������鳤
 * ����
 * @author Mars zhang
 * @created 2016-2-26 ����3:12:20
 */
public class FirstLayout extends LinearLayout {
    
    public FirstLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public FirstLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FirstLayout(Context context) {
        super(context);
    }
    
    
    /**
     * 
     * ����   
     * @author Mars zhang
     * @created 2016-2-26 ����3:14:49
     * @param ev
     * @return  true ���ز�����onTouchEvent(�����´���)    false���´���  
     * @see android.view.ViewGroup#onInterceptTouchEvent(android.view.MotionEvent)
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(TouchEventdispatchActivity.DEBUG==1)
        Log.i(TouchEventdispatchActivity.TAG, "FirstLayout---onInterceptTouchEvent ��������false ������һ��");
        return false;
    }
    
    
    /**
     * 
     * ����
     * @author Mars zhang
     * @created 2016-2-26 ����3:16:38
     * @param event
     * @return true�ܹ�����(�����ϴ���)     false���ܴ������쵼
     * @see android.view.View#onTouchEvent(android.view.MotionEvent)
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(TouchEventdispatchActivity.DEBUG==1)
        Log.i(TouchEventdispatchActivity.TAG, "FirstLayout---onTouchEvent ���ܴ���true");
        return true;
    }

}
