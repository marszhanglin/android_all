package mars.all.activity.at_staggeredgrid.lockscrren;

import mars.all.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Window;
import android.view.WindowManager;

/**
 * 仿QQ锁屏界面
 * 2014-7-22下午4:13:38 类QQLockScreenActivity
 * 
 * @author Mars zhang
 * 1、oncreate   2、manifest。xml    3、layout透明      4、写个广播接收器在锁屏时才弹    5、一些权限
 * 6、电源锁什么的保证、、
 */
public class QQLockScreenActivity extends Activity {  

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Window win=getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.zoom_image_activity);
        
        
      //1在oncreate里面给Window加4个标志addflag
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED  //当锁屏时显示
        		|WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD  //不要键盘
        		|WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON    //保持屏幕常亮
        		|WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);   //即使你按了电源键他也不会被回收
        
        
    }
    
    
    //6
    @Override
    protected void onNewIntent(Intent intent) {
    	super.onNewIntent(intent);
    	
    	PowerManager pw=(PowerManager) this.getSystemService(Context.POWER_SERVICE);
    	if(!pw.isScreenOn()){
    		PowerManager.WakeLock wl=pw.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP |
    				PowerManager.SCREEN_BRIGHT_WAKE_LOCK,"bight");
    		wl.acquire();
    		wl.release();
    	}
    }

    
}
