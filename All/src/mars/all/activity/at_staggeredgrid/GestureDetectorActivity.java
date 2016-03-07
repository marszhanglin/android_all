package mars.all.activity.at_staggeredgrid;

import mars.all.R;
import android.app.Activity; 
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 *   <br>1��ʵ��OnGestureListener�ӿ�
 *   <br>2��ֻ�ܼ��һ����ָ
 * @author Mars zhang
 * @created 2016-2-29 ����8:03:13
 */
public class GestureDetectorActivity extends Activity implements OnGestureListener {  

	public GestureDetector gestureDetector=null;
	
	private ScrollView scrollView=null;
	private TextView  textView=null;
	private StringBuilder sb=new StringBuilder();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.gesture_detector_activity); 
        // ����activityʱ���Զ����������
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ((TextView)findViewById(R.id.gesture_detector_top_tv)).setSelected(true);
        //2��ʵ����gestureDetector
        gestureDetector=new GestureDetector(this,  this);
        
      //4if1 �����������scrollView�������gestureDetector��������Ҫ��дdispatchTouchEvent����
        scrollView=(ScrollView) findViewById(R.id.gesture_detector_content_scrollview);
        textView=(TextView) findViewById(R.id.gesture_detector_content_tv);
    }

    /**
     * ���ذ�ť����¼� 
     * @param v
     */
    public void fh(View v) {  
        finish();
    }   
    
    public void main_code(View v){
    	Toast.makeText(getApplicationContext(), "zoom_image_activity.xml��mars.all.view.image.ZoomImageView", 1).show();
    }

    //�����¼�  
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	//3����activity�ϵĴ����¼�onTouchEvent����gestureDetector����
    	return gestureDetector.onTouchEvent(event);
    	
    }
    //4if2 ����scrollʱҪ���������
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
    	//ͬʱ��MotionEvent����gestureDetector��scroll
    	gestureDetector.onTouchEvent(ev);
        scrollView.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);

}
    //�ӿ�OnGestureListener�ķ���
	@Override
	public boolean onDown(MotionEvent e) {
		toast("onDown�����¼�����");
		return false;
	}
	//�ӿ�OnGestureListener�ķ���
	@Override
	public void onShowPress(MotionEvent e) {
		toast("onShowPress���¶��һ�δ�ƶ�");		
	}
	//�ӿ�OnGestureListener�ķ���
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		toast("onSingleTapUp����¼�");	
		return false;
	}
	//�ӿ�OnGestureListener�ķ���
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		toast("onSingleTapUp����");	
		return false;
	}
	//�ӿ�OnGestureListener�ķ���
	@Override
	public void onLongPress(MotionEvent e) {
		toast("onLongPress����");	 
		sb.delete(0, sb.length());
	}
	//�ӿ�OnGestureListener�ķ���
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		//x����Ϊ��     y����Ϊ��
		toast("onFling�϶��ٶ�X>"+velocityX+" Y��"+velocityY);	
		//�һ����˳�
		if(velocityX>6000l){ 
			finish();
		}
		return false;
	}

    
	
	private void toast(String string){
		sb.append(string+"\n");
		textView.setText(sb.toString());
		Toast.makeText(getApplicationContext(), string, 0).show();
	}
     
}
