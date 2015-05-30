package mars.all.activity.at_staggeredgrid;

import java.util.Date;

import mars.all.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 缩放的界面
 * 2014-7-22下午4:13:38 类AfnailPictureActivity
 * 
 * @author Mars zhang
 * 
 */
public class ZoomImageActivity extends Activity { 
	/**缩放的Image*/
    private ImageView imageView;
    
    private TextView time_tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.zoom_image_activity); 
        // 启动activity时不自动弹出软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        imageView = (ImageView) findViewById(R.id.test1_picture);
        time_tv =(TextView) findViewById(R.id.demo6_time);
        time_tv.setText(new Date(System.currentTimeMillis()).toLocaleString());
    }

    /**
     * 返回按钮点击事件
     * 
     * @param v
     */
    public void fh(View v) {  
        finish();
    }   
    
    public void main_code(View v){
    	Toast.makeText(getApplicationContext(), "zoom_image_activity.xml的mars.all.view.image.ZoomImageView", 1).show();
    }
}
