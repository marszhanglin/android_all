package mars.all.activity.textclock.appwidgetprovider;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

/**
 * ����ķ������ڸ�����ô�ı����ӵ�ʱ��  
 * @author EVECOM-PC
 *
 */
public class TextClockServer extends IntentService {
	/**�Ҳ�֪��ΪʲôҪ���캯���м������*/
	private static final String TAG="TEXTCLOCKSERVER";
	/** ��������ж�intent�Ƿ����������server��:  if(intent.getAction().equals(ACTION_UPDATE))
	 * <br>������intent��: new Intent(TextClockServer.ACTION_UPDATE)*/
	public static final String ACTION_UPDATE = "mars.all.activity.textclock.appwidgetprovider.TextClockServer";
	public TextClockServer() {
		super(TAG);
		Toast.makeText(getApplicationContext(), "", 1).show();
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		if(intent.getAction().equals(ACTION_UPDATE)){
			Toast.makeText(getApplicationContext(), "onHandleIntent", 1).show();
		}
	}

	
}
