package mars.all.activity.activitysource;

import mars.all.R;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
/**
 * activity�����������
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
	 * ��������Ӧ�ó����acticity   ����������
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
