package mars.all.activity.two.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * ���ԣ�����Ա
 * ����
 * @author Mars zhang
 * @created 2016-2-26 ����3:48:38
 */
public class SecondLayout extends LinearLayout {

    public SecondLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public SecondLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public SecondLayout(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
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
        Log.i(TouchEventdispatchActivity.TAG, "SecondLayout---onInterceptTouchEvent ��������false ������һ��");
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
        Log.i(TouchEventdispatchActivity.TAG, "SecondLayout---onTouchEvent ���ܴ���true");
        return true;
    }
}
