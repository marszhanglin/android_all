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
 *   <br>1、实现OnGestureListener接口
 *   <br>2、只能监测一个手指
 * @author Mars zhang
 * @created 2016-2-29 下午8:03:13
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
        // 启动activity时不自动弹出软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ((TextView)findViewById(R.id.gesture_detector_top_tv)).setSelected(true);
        //2、实例化gestureDetector
        gestureDetector=new GestureDetector(this,  this);
        
      //4if1 这个对象在有scrollView的情况下gestureDetector不起作用要重写dispatchTouchEvent方法
        scrollView=(ScrollView) findViewById(R.id.gesture_detector_content_scrollview);
        textView=(TextView) findViewById(R.id.gesture_detector_content_tv);
    }

    /**
     * 返回按钮点击事件 
     * @param v
     */
    public void fh(View v) {  
        finish();
    }   
    
    public void main_code(View v){
    	Toast.makeText(getApplicationContext(), "zoom_image_activity.xml的mars.all.view.image.ZoomImageView", 1).show();
    }

    //触碰事件  
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	//3、将activity上的触碰事件onTouchEvent交给gestureDetector处理
    	return gestureDetector.onTouchEvent(event);
    	
    }
    //4if2 当有scroll时要调这个方法
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
    	//同时将MotionEvent传给gestureDetector与scroll
    	gestureDetector.onTouchEvent(ev);
        scrollView.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);

}
    //接口OnGestureListener的方法
	@Override
	public boolean onDown(MotionEvent e) {
		toast("onDown触碰事件按下");
		return false;
	}
	//接口OnGestureListener的方法
	@Override
	public void onShowPress(MotionEvent e) {
		toast("onShowPress按下而且还未移动");		
	}
	//接口OnGestureListener的方法
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		toast("onSingleTapUp轻击事件");	
		return false;
	}
	//接口OnGestureListener的方法
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		toast("onSingleTapUp滚动");	
		return false;
	}
	//接口OnGestureListener的方法
	@Override
	public void onLongPress(MotionEvent e) {
		toast("onLongPress长按");	 
		sb.delete(0, sb.length());
	}
	//接口OnGestureListener的方法
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		//x向右为正     y向下为正
		toast("onFling拖动速度X>"+velocityX+" Y下"+velocityY);	
		//右滑动退出
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
