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
 * ��QQ��������
 * 2014-7-22����4:13:38 ��QQLockScreenActivity
 * 
 * @author Mars zhang
 * 1��oncreate   2��manifest��xml    3��layout͸��      4��д���㲥������������ʱ�ŵ�    5��һЩȨ��
 * 6����Դ��ʲô�ı�֤����
 */
public class QQLockScreenActivity extends Activity {  

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Window win=getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.zoom_image_activity);
        
        
      //1��oncreate�����Window��4����־addflag
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED  //������ʱ��ʾ
        		|WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD  //��Ҫ����
        		|WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON    //������Ļ����
        		|WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);   //��ʹ�㰴�˵�Դ����Ҳ���ᱻ����
        
        
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
