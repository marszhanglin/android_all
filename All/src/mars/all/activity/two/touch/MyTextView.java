package mars.all.activity.two.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * 属性：实习生
 * 描述
 * @author Mars zhang
 * @created 2016-2-26 下午3:28:34
 */
public class MyTextView extends TextView {

    public MyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public MyTextView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
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
        //eventProcess();
        //getAllValue(event);
        logActionType(event);
        
        int pinterCount = event.getPointerCount();
        for (int i = 0; i < pinterCount; i++) {
            Log.i(TouchEventdispatchActivity.TAG, "       第"+i+"手指位置:("+event.getX(i)+" , "+event.getY(i)+")");
        }
        
        
        return true;
    }
    
    
    /**
     * 
     * 描述  打印动作类型
     * @author Mars zhang
     * @created 2016-2-29 下午7:33:06
     * @param event
     */
    private void logActionType(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TouchEventdispatchActivity.TAG, "ACTION_DOWN  手指个数："+event.getPointerCount()+"  压力："+event.getPressure());
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TouchEventdispatchActivity.TAG, "ACTION_UP    手指个数："+event.getPointerCount()+"  压力："+event.getPressure());
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TouchEventdispatchActivity.TAG, "ACTION_MOVE  手指个数："+event.getPointerCount()+"  压力："+event.getPressure());
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i(TouchEventdispatchActivity.TAG, "ACTION_CANCEL手指个数："+event.getPointerCount()+"  压力："+event.getPressure());
                break;
            case MotionEvent.ACTION_OUTSIDE:
                Log.i(TouchEventdispatchActivity.TAG, "ACTION_OUTSIDE手指个数："+event.getPointerCount()+"  压力："+event.getPressure());
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.i(TouchEventdispatchActivity.TAG, "ACTION_POINTER_DOWN手指个数："+event.getPointerCount()+"  压力："+event.getPressure());
                break;
            case MotionEvent.ACTION_POINTER_UP:
                Log.i(TouchEventdispatchActivity.TAG, "ACTION_POINTER_UP手指个数："+event.getPointerCount()+"  压力："+event.getPressure());
                break;
            case MotionEvent.ACTION_HOVER_MOVE:
                Log.i(TouchEventdispatchActivity.TAG, "ACTION_HOVER_MOVE手指个数："+event.getPointerCount()+"  压力："+event.getPressure());
                break;
            case MotionEvent.ACTION_SCROLL:
                Log.i(TouchEventdispatchActivity.TAG, "ACTION_SCROLL手指个数："+event.getPointerCount()+"  压力："+event.getPressure());
                break;
            case MotionEvent.ACTION_HOVER_ENTER:
                Log.i(TouchEventdispatchActivity.TAG, "ACTION_HOVER_ENTER手指个数："+event.getPointerCount()+"  压力："+event.getPressure());
                break;
            case MotionEvent.ACTION_HOVER_EXIT:
                Log.i(TouchEventdispatchActivity.TAG, "ACTION_HOVER_EXIT手指个数："+event.getPointerCount()+"  压力："+event.getPressure());
                break;
            default:
                Log.i(TouchEventdispatchActivity.TAG, "UNKNOW_ACTION手指个数："+event.getPointerCount()+"  压力："+event.getPressure()+"     "+(event.getAction()& event.ACTION_MASK));
                break;
        }
    }
    
    /**
     * 
     * 描述   onEventTouch事件流程
     * @author Mars zhang
     * @created 2016-2-29 下午7:22:48
     */
    private void eventProcess() {
        if(TouchEventdispatchActivity.DEBUG==1)
        Log.i(TouchEventdispatchActivity.TAG, "MyTextView---onTouchEvent 他能处理true");
    }
    
    /**
     * 
     * 描述  查询所有位置数据
     * @author Mars zhang
     * @created 2016-2-29 下午7:15:46
     * @param event
     */
    private void getAllValue(MotionEvent event) {
        final int historySize = event.getHistorySize();
        //当前有多少个手指触摸    红米1s最多支持4个
        final int pointerCount = event.getPointerCount();
        for (int h = 0; h < historySize; h++) {
            System.out.printf("At time %d:", event.getHistoricalEventTime(h));
            for (int p = 0; p < pointerCount; p++) {
                if(TouchEventdispatchActivity.DEBUG==2)
                Log.i(TouchEventdispatchActivity.TAG, String.format("  pointer %d: (%f,%f)",
                        event.getPointerId(p), event.getHistoricalX(p, h), event.getHistoricalY(p, h)));
            }
        }
        if(TouchEventdispatchActivity.DEBUG==2)
            Log.i(TouchEventdispatchActivity.TAG, String.format("At time %d:", event.getEventTime()));
        for (int p = 0; p < pointerCount; p++) { 
            if(TouchEventdispatchActivity.DEBUG==2)
            Log.i(TouchEventdispatchActivity.TAG, String.format("  pointer %d: (%f,%f)",
                    event.getPointerId(p), event.getX(p), event.getY(p)));
            
        }
    }

}
