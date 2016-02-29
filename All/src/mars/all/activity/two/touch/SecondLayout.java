package mars.all.activity.two.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * 属性：程序员
 * 描述
 * @author Mars zhang
 * @created 2016-2-26 下午3:48:38
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
     * 描述   
     * @author Mars zhang
     * @created 2016-2-26 下午3:14:49
     * @param ev
     * @return  true 拦截并交给onTouchEvent(不向下传递)    false向下传递  
     * @see android.view.ViewGroup#onInterceptTouchEvent(android.view.MotionEvent)
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(TouchEventdispatchActivity.DEBUG==1)
        Log.i(TouchEventdispatchActivity.TAG, "SecondLayout---onInterceptTouchEvent 他不拦截false 交给下一级");
        return false;
    }
    
    
    /**
     * 
     * 描述
     * @author Mars zhang
     * @created 2016-2-26 下午3:16:38
     * @param event
     * @return true能够处理(不向上传递)     false不能处理交给领导
     * @see android.view.View#onTouchEvent(android.view.MotionEvent)
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(TouchEventdispatchActivity.DEBUG==1)
        Log.i(TouchEventdispatchActivity.TAG, "SecondLayout---onTouchEvent 他能处理true");
        return true;
    }
}
