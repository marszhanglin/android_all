package mars.all.activity.at_staggeredgrid;

import mars.all.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * YoYo动画的界面
 * 2014-7-22下午4:13:38 类AfnailPictureActivity 
 * @author Mars zhang 
 */
public class AnimationsActivity extends Activity { 
	/**缩放的Image*/
    private ImageView imageView;
    
    private TextView time_tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.animations_activity); 
        // 启动activity时不自动弹出软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
       
        YoYo.with(Techniques.Tada).duration(700).playOn(findViewById(R.id.hello));
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
    	Toast.makeText(getApplicationContext(), "YoYo.with(Techniques.Tada).duration(700).playOn(findViewById(R.id.hello));", 0).show();
    }
    
    
    
    
    public void Tada(View view){
		YoYo.with(Techniques.Tada).duration(700).playOn(findViewById(R.id.hello));
	}
	public void FlipInX(View view){
		YoYo.with(Techniques.FlipInX).duration(700).playOn(view);
	} 
	public void Flash(View view){
		YoYo.with(Techniques.Flash).duration(700).playOn(view);
	}
	public void Pulse(View view){
		YoYo.with(Techniques.Pulse).duration(700).playOn(view);
	}
	public void RubberBand(View view){
		YoYo.with(Techniques.RubberBand).duration(700).playOn(view);
	}
	public void Shake(View view){
		YoYo.with(Techniques.Shake).duration(700).playOn(view);
	}
	public void Swing(View view){
		YoYo.with(Techniques.Swing).duration(700).playOn(view);
	}
	public void Wobble(View view){
		YoYo.with(Techniques.Wobble).duration(700).playOn(view);
	}
	public void Bounce(View view){
		YoYo.with(Techniques.Bounce).duration(700).playOn(view);
	}
	public void StandUp(View view){
		YoYo.with(Techniques.StandUp).duration(700).playOn(view);
	}
	public void Wave(View view){
		YoYo.with(Techniques.Wave).duration(700).playOn(view);
	}
}
