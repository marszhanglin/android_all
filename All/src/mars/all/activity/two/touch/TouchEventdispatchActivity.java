package mars.all.activity.two.touch;

import mars.all.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
/**
 * ���ԣ���������   
 * ����  Android Touch�¼����ݻ���ͨ�׽���   
 * <br>ע��touch����onclick�¼�
 * http://krelve.com/android/2.html
 * <br>ע��dispatchTouchEvent onTouch  onTouchEvent �Ĺ�ϵ
 * http://blog.csdn.net/lmj623565791/article/details/38960443
 * @author Mars zhang
 * @created 2016-2-26 ����2:58:45
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
     * ����  ���ֻ���Լ�����  ����true��ʾ�ɹ�������
     * @author Mars zhang
     * @created 2016-2-26 ����3:01:19
     * @param event
     * @return true�ܹ�����(�����ϴ���)     false���ܴ������쵼(�Ǻ��Ѿ���boss����  �����˾Ͳ�����֮)
     * @see android.app.Activity#onTouchEvent(android.view.MotionEvent)
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(TouchEventdispatchActivity.DEBUG==1)
        Log.i(TouchEventdispatchActivity.TAG, "TouchEventdispatchActivity---onTouchEvent ���ܴ���true");
        return true;
    }
    
    
}
