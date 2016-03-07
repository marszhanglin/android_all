package mars.all.activity.two.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * ���ԣ�ʵϰ��
 * ����
 * @author Mars zhang
 * @created 2016-2-26 ����3:28:34
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
     * ����
     * @author Mars zhang
     * @created 2016-2-26 ����3:16:38
     * @param event
     * @return true�ܹ�����(�����ϴ���)     false���ܴ������쵼
     * @see android.view.View#onTouchEvent(android.view.MotionEvent)
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //eventProcess();
        //getAllValue(event);
        logActionType(event);
        
        int pinterCount = event.getPointerCount();
        for (int i = 0; i < pinterCount; i++) {
            Log.i(TouchEventdispatchActivity.TAG, "       ��"+i+"��ָλ��:("+event.getX(i)+" , "+event.getY(i)+")");
        }
        
        
        return true;
    }
    
    
    /**
     * 
     * ����  ��ӡ��������
     * @author Mars zhang
     * @created 2016-2-29 ����7:33:06
     * @param event
     */
    private void logActionType(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TouchEventdispatchActivity.TAG, "ACTION_DOWN  ��ָ������"+event.getPointerCount()+"  ѹ����"+event.getPressure());
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TouchEventdispatchActivity.TAG, "ACTION_UP    ��ָ������"+event.getPointerCount()+"  ѹ����"+event.getPressure());
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TouchEventdispatchActivity.TAG, "ACTION_MOVE  ��ָ������"+event.getPointerCount()+"  ѹ����"+event.getPressure());
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i(TouchEventdispatchActivity.TAG, "ACTION_CANCEL��ָ������"+event.getPointerCount()+"  ѹ����"+event.getPressure());
                break;
            case MotionEvent.ACTION_OUTSIDE:
                Log.i(TouchEventdispatchActivity.TAG, "ACTION_OUTSIDE��ָ������"+event.getPointerCount()+"  ѹ����"+event.getPressure());
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.i(TouchEventdispatchActivity.TAG, "ACTION_POINTER_DOWN��ָ������"+event.getPointerCount()+"  ѹ����"+event.getPressure());
                break;
            case MotionEvent.ACTION_POINTER_UP:
                Log.i(TouchEventdispatchActivity.TAG, "ACTION_POINTER_UP��ָ������"+event.getPointerCount()+"  ѹ����"+event.getPressure());
                break;
            case MotionEvent.ACTION_HOVER_MOVE:
                Log.i(TouchEventdispatchActivity.TAG, "ACTION_HOVER_MOVE��ָ������"+event.getPointerCount()+"  ѹ����"+event.getPressure());
                break;
            case MotionEvent.ACTION_SCROLL:
                Log.i(TouchEventdispatchActivity.TAG, "ACTION_SCROLL��ָ������"+event.getPointerCount()+"  ѹ����"+event.getPressure());
                break;
            case MotionEvent.ACTION_HOVER_ENTER:
                Log.i(TouchEventdispatchActivity.TAG, "ACTION_HOVER_ENTER��ָ������"+event.getPointerCount()+"  ѹ����"+event.getPressure());
                break;
            case MotionEvent.ACTION_HOVER_EXIT:
                Log.i(TouchEventdispatchActivity.TAG, "ACTION_HOVER_EXIT��ָ������"+event.getPointerCount()+"  ѹ����"+event.getPressure());
                break;
            default:
                Log.i(TouchEventdispatchActivity.TAG, "UNKNOW_ACTION��ָ������"+event.getPointerCount()+"  ѹ����"+event.getPressure()+"     "+(event.getAction()& event.ACTION_MASK));
                break;
        }
    }
    
    /**
     * 
     * ����   onEventTouch�¼�����
     * @author Mars zhang
     * @created 2016-2-29 ����7:22:48
     */
    private void eventProcess() {
        if(TouchEventdispatchActivity.DEBUG==1)
        Log.i(TouchEventdispatchActivity.TAG, "MyTextView---onTouchEvent ���ܴ���true");
    }
    
    /**
     * 
     * ����  ��ѯ����λ������
     * @author Mars zhang
     * @created 2016-2-29 ����7:15:46
     * @param event
     */
    private void getAllValue(MotionEvent event) {
        final int historySize = event.getHistorySize();
        //��ǰ�ж��ٸ���ָ����    ����1s���֧��4��
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
