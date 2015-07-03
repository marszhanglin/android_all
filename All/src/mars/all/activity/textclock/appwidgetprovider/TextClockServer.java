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
			 Vibrator mVibrator01
             =(Vibrator)getApplication().getSystemService(Service.VIBRATOR_SERVICE);
             long[] longs= new
             long[]{100l,2000l,1000l,100l,100l,100l,100l,100l,100l,100l,100l,100l}; //ͣ��ͣ��ͣ��ͣ��ͣ��������
             mVibrator01.vibrate(longs, -1); //����   ����
			Log.v("mars", "TextClockServer---onHandleIntent()"); 
			Toast.makeText(this, "TextClockServer---onHandleIntent()", 1).show();
			//����widget����
			Calendar nowCalendar=Calendar.getInstance();
			
			updateTimeView(nowCalendar);
		}
	} 
	
	/**
	 * ����widgetʱ�����
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
RemoteViews ��һ�����������ṩ����Щ��ͼ�����Ƶķ��ʡ����ԣ����������ǵ�Ӧ��С������ʹ��������ЩС�����������ǣ�
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
ͬ�������ƣ����ǵ�С����ֻ��ʹ�����в��֣�
FrameLayout
LinearLayout
RelativeLayout
GridLayout
 * */

