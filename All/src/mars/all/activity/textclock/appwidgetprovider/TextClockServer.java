package mars.all.activity.textclock.appwidgetprovider;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

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
		Log.v("mars", "TextClockServer---TextClockServer()");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		if(intent.getAction().equals(ACTION_UPDATE)){
			//��
			/*Vibrator mVibrator01
             =(Vibrator)getApplication().getSystemService(Service.VIBRATOR_SERVICE);
             long[] longs= new
             long[]{100l,2000l,1000l,100l,100l,100l,100l,100l,100l,100l,100l,100l}; //ͣ��ͣ��ͣ��ͣ��ͣ��������
             mVibrator01.vibrate(longs, -1); //����   ����
			Log.v("mars", "TextClockServer---onHandleIntent()");*/
		}
	} 
}
