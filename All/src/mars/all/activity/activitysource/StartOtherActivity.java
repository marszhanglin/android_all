package mars.all.activity.activitysource;

import mars.all.R;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
/**
 * activity启动其他组件
 * @author EVECOM-PC
 *
 */
public class StartOtherActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startother_activity);
	}

	/**
	 * 启动其他应用程序的acticity   启动传感器
	 * @param view
	 */
	public void btnonclick(View view){ 
//		Intent intent=new Intent("com.example.marssensor.MainActivity");
//		startActivity(intent);
		
		Intent intent = new Intent(); 
		ComponentName cn = new ComponentName("com.example.marssensor", "com.example.marssensor.MainActivity");          
		intent.setComponent(cn);
		startActivity(intent);
	}
	
}
