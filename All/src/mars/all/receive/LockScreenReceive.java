package mars.all.receive;

import mars.all.activity.at_staggeredgrid.lockscrren.QQLockScreenActivity;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class LockScreenReceive extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		
		KeyguardManager km=(KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
		System.out.println("ËøÆÁ"+km.inKeyguardRestrictedInputMode());
		Toast.makeText(context, "123131"+km.inKeyguardRestrictedInputMode(), 1).show();
		if(km.inKeyguardRestrictedInputMode()){
			Intent intent2=new Intent(context, QQLockScreenActivity.class);
			intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent2);
		}
		
		
	}

}
