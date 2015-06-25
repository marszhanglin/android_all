package mars.all.activity.textclock.appwidgetprovider;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
/**
 * 1������ؼ�AppWidgetProvider �̳��� BroadcastReceiver(�㲥������) ��������Ҫ����Manifest 
 *<br> 2����ӦACTION_APPWIDGET_UPDATE�Ĺ㲥
 * Ȼ������Ϊ AppWidgetProvider ��һ������� BroadcastReceiver��
 * ���Ի���ҪΪ AppWidgetProvider ��XML�ļ��ж������һЩԪ���ݡ�
 * <receiver android:name=".TextClockAppWidget">
            <intent-filter>
                <action android:name="android.appwidget.action.APPWIDGET_UPDATE"/>
            </intent-filter>
            <meta-data
                    android:name="android.appwidget.provider"
                    android:resource="@xml/appwidget_info"/>
        </receiver>
 * @author EVECOM-PC
 *
 */
public class TextClockAppWidget extends AppWidgetProvider {
    private Context context = null;
    private Intent updateIntent=new Intent(TextClockServer.ACTION_UPDATE);
    private PendingIntent pendingIntent=null;
    @Override
    public void onUpdate(Context context,
                         AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
    	toast(context, "all--onUpdate  length="+appWidgetIds.length);
        this.context = context;
//        this.context.startService(updateIntent);
        //������������ʱ�� ÿ��һ���Ӿ�paddingIntentһ��ִ��һ��Server
        pendingIntentTimer();
    }
    
    
    private void pendingIntentTimer(){
    	//��ȡ���ӷ���
    	AlarmManager alarmManager=(AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    	pendingIntent=PendingIntent.getService(context,
    			1, 
    			updateIntent, 
    			PendingIntent.FLAG_NO_CREATE); // �����Ƿ���һ������ͬ��������PendingIntent�Ѿ������ڴ��豸�ϡ�������ڷ�������ʵ�������򷵻�null	
    	if(null==pendingIntent){
    		pendingIntent=PendingIntent.getService(context,
        			1, 
        			updateIntent, 
        			PendingIntent.FLAG_CANCEL_CURRENT);
    		/*alarmManager.setRepeating(//�ظ�����
        			AlarmManager.RTC_WAKEUP, //���ӻ�������5��    RTC:�������豸 RTC_WAKEUP:�ܻ����豸  4:�ػ�������(������)
        			System.currentTimeMillis(), //��ʱ��ʼ    ҵ����������ȷ����������ʱ���� �п��ܵ���35��Ÿ���
        			60*1000, //ÿ�����
        			pendingIntent);//�㲥
    		Log.v("mars", "alarmManager�ɹ�����");*/
    		Calendar calendar=Calendar.getInstance();
    		calendar.set(Calendar.SECOND, 0);
    		calendar.set(Calendar.MILLISECOND, 0);
    		calendar.add(Calendar.MINUTE, 1);
    		alarmManager.setRepeating(//�ظ�����    �������⣺����4.4֮����ظ����Ӳ�׼ȷ���Բ���һ���Ӿ�ִ��һ��server
        			4, //���ӻ�������5��
        			calendar.getTimeInMillis(), //��ʱ��ʼ    ҵ����������ȷ����������ʱ���� �п��ܵ���35��Ÿ���
        			20*1000, //ÿ�����
        			pendingIntent);//�㲥
    		Log.v("mars", "alarmManager�ɹ�����");
    	} 
    }
    
    
    
    /**
	 * ÿ����һ�ι㲥��Ϣ�͵���һ�Σ�ʹ��Ƶ��
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		toast(context,"all--onReceive ");
		super.onReceive(context, intent);
		
	}
	/**
	 * ����ؼ����ŵ�����ʱ����
	 */
	@Override
	public void onAppWidgetOptionsChanged(Context context,
			AppWidgetManager appWidgetManager, int appWidgetId,
			Bundle newOptions) {
		toast(context,"all--onAppWidgetOptionsChanged appWidgetId="+appWidgetId);
		super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId,
				newOptions);
		
	}
	
	/**
	 * ������ؼ�������ɾ��ʱ���� ûɾ��һ���͵���һ��   
	 * ��û��widgetidsʱȡ����ʱ��
	 */
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		toast(context,"all--onDeleted "+appWidgetIds.length);
	}

	/**
	 * ����Widget��һ����ӵ������ǵ��ø÷���������Ӷ�ε�ֻ��һ�ε���
	 */
	@Override
	public void onEnabled(Context context) {
		toast(context,"all--onEnabled ");
		super.onEnabled(context); 
	}
	
	/**
	 * �����һ����Widgetɾ���ǵ��ø÷�����ע�������һ��
	 */
	@Override
	public void onDisabled(Context context) {
		toast(context,"all--onDisabled ");
		AlarmManager alarmManager=(AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		pendingIntent=PendingIntent.getService(context,
    			1, 
    			updateIntent, 
    			PendingIntent.FLAG_NO_CREATE); 
		if(null!=pendingIntent){
			alarmManager.cancel(pendingIntent);//ȡ��������
			pendingIntent.cancel();
		} 
	}
    
    
	private void toast(Context context,String show_str) {

		Toast.makeText(context, show_str, 1).show();
	}
}