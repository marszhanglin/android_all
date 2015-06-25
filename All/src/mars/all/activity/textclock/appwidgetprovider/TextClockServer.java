package mars.all.activity.textclock.appwidgetprovider;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * 这里的服务用于更新怎么文本闹钟的时间  
 * @author EVECOM-PC
 *
 */
public class TextClockServer extends IntentService {
	/**我不知道为什么要构造函数中加这个：*/
	private static final String TAG="TEXTCLOCKSERVER";
	/** 这个用于判断intent是否是启动这个server的:  if(intent.getAction().equals(ACTION_UPDATE))
	 * <br>及创建intent的: new Intent(TextClockServer.ACTION_UPDATE)*/
	public static final String ACTION_UPDATE = "mars.all.activity.textclock.appwidgetprovider.TextClockServer";
	public TextClockServer() {
		super(TAG);
		Log.v("mars", "TextClockServer---TextClockServer()");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		if(intent.getAction().equals(ACTION_UPDATE)){
			//震动
			/*Vibrator mVibrator01
             =(Vibrator)getApplication().getSystemService(Service.VIBRATOR_SERVICE);
             long[] longs= new
             long[]{100l,2000l,1000l,100l,100l,100l,100l,100l,100l,100l,100l,100l}; //停开停开停开停开停开。。。
             mVibrator01.vibrate(longs, -1); //节奏   次数
			Log.v("mars", "TextClockServer---onHandleIntent()");*/
		}
	} 
}
