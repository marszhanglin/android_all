package mars.all.activity.two.touch;

import mars.all.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
/**
 * 属性：开发经理   
 * 描述  Android Touch事件传递机制通俗讲解   
 * <br>注意touch不是onclick事件
 * http://krelve.com/android/2.html
 * <br>注意dispatchTouchEvent onTouch  onTouchEvent 的关系
 * http://blog.csdn.net/lmj623565791/article/details/38960443
 * @author Mars zhang
 * @created 2016-2-26 下午2:58:45
 */
public class TouchEventdispatchActivity extends Activity {
    public static final String TAG="touch";
    public static final int DEBUG =2;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_touch_activity);
        Log.i(TouchEventdispatchActivity.TAG, "TouchEventdispatchActivity---onCreate");
    }
    
    
    
    
    
    /**
     * 
     * 描述  最后只能自己上了  返回true表示成功处理了
     * @author Mars zhang
     * @created 2016-2-26 下午3:01:19
     * @param event
     * @return true能够处理(不向上传递)     false不能处理交给领导(呵呵已经是boss级了  处理不了就不了了之)
     * @see android.app.Activity#onTouchEvent(android.view.MotionEvent)
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(TouchEventdispatchActivity.DEBUG==1)
        Log.i(TouchEventdispatchActivity.TAG, "TouchEventdispatchActivity---onTouchEvent 他能处理true");
        return true;
    }
    
    
}
