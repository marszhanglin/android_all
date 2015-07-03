package mars.all.activity.textclock.appwidgetprovider;

import java.util.Calendar;

import mars.all.R;
import android.app.IntentService;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

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
			 Vibrator mVibrator01
             =(Vibrator)getApplication().getSystemService(Service.VIBRATOR_SERVICE);
             long[] longs= new
             long[]{100l,2000l,1000l,100l,100l,100l,100l,100l,100l,100l,100l,100l}; //停开停开停开停开停开。。。
             mVibrator01.vibrate(longs, -1); //节奏   次数
			Log.v("mars", "TextClockServer---onHandleIntent()"); 
			Toast.makeText(this, "TextClockServer---onHandleIntent()", 1).show();
			//更新widget界面
			Calendar nowCalendar=Calendar.getInstance();
			
			updateTimeView(nowCalendar);
		}
	} 
	
	/**
	 * 更新widget时间界面
	 */
	private void updateTimeView(Calendar nowCalendar){
		RemoteViews remoteViews=new RemoteViews(getPackageName(), R.layout.textclockappwidget);
		remoteViews.setTextViewText(R.id.hours, nowCalendar.get(Calendar.HOUR_OF_DAY)+"");
		remoteViews.setTextViewText(R.id.tens, "-----");
		remoteViews.setTextViewText(R.id.minutes, nowCalendar.get(Calendar.MINUTE)+"");
		AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(this);
		appWidgetManager.updateAppWidget(new ComponentName(this, TextClockAppWidget.class), remoteViews);
	}
}



/** 
RemoteViews 是一个代理，它会提供对这些视图带限制的访问。所以，可以在我们的应用小部件中使用下面这些小部件。它们是：
AnalogClock
Button
Chronometer
ImageButton
ImageView
ProgressBar
TextView
ViewFlipper
ListView
GridView
StackView
AdapterViewFlipper
同样的限制，我们的小部件只能使用下列布局：
FrameLayout
LinearLayout
RelativeLayout
GridLayout
 * */

