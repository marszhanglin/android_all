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
 * ���ŵĽ���
 * 2014-7-22����4:13:38 ��AfnailPictureActivity
 * 
 * @author Mars zhang
 * 
 */
public class ZoomImageActivity extends Activity { 
	/**���ŵ�Image*/
    private ImageView imageView;
    
    private TextView time_tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.zoom_image_activity); 
        // ����activityʱ���Զ����������
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        imageView = (ImageView) findViewById(R.id.test1_picture);
        time_tv =(TextView) findViewById(R.id.demo6_time);
        time_tv.setText(new Date(System.currentTimeMillis()).toLocaleString());
    }

    /**
     * ���ذ�ť����¼�
     * 
     * @param v
     */
    public void fh(View v) {  
        finish();
    }   
    
    public void main_code(View v){
    	Toast.makeText(getApplicationContext(), "zoom_image_activity.xml��mars.all.view.image.ZoomImageView", 1).show();
    }
}
