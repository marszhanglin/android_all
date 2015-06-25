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
 * 1、桌面控件AppWidgetProvider 继承自 BroadcastReceiver(广播接收者) 所以我们要配置Manifest 
 *<br> 2、响应ACTION_APPWIDGET_UPDATE的广播
 * 然而，因为 AppWidgetProvider 是一种特殊的 BroadcastReceiver，
 * 所以还需要为 AppWidgetProvider 在XML文件中额外添加一些元数据。
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
        //在这里启动定时器 每隔一分钟就paddingIntent一次执行一次Server
        pendingIntentTimer();
    }
    
    
    private void pendingIntentTimer(){
    	//获取闹钟服务
    	AlarmManager alarmManager=(AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    	pendingIntent=PendingIntent.getService(context,
    			1, 
    			updateIntent, 
    			PendingIntent.FLAG_NO_CREATE); // 会检查是否有一个带有同样参数的PendingIntent已经存在于此设备上。如果存在返回它的实例，否则返回null	
    	if(null==pendingIntent){
    		pendingIntent=PendingIntent.getService(context,
        			1, 
        			updateIntent, 
        			PendingIntent.FLAG_CANCEL_CURRENT);
    		/*alarmManager.setRepeating(//重复闹钟
        			AlarmManager.RTC_WAKEUP, //闹钟唤醒类型5种    RTC:不唤醒设备 RTC_WAKEUP:能唤醒设备  4:关机都唤醒(不管用)
        			System.currentTimeMillis(), //何时开始    业务：这样不能确保是整分钟时更新 有可能到了35秒才更新
        			60*1000, //每隔多久
        			pendingIntent);//广播
    		Log.v("mars", "alarmManager成功创建");*/
    		Calendar calendar=Calendar.getInstance();
    		calendar.set(Calendar.SECOND, 0);
    		calendar.set(Calendar.MILLISECOND, 0);
    		calendar.add(Calendar.MINUTE, 1);
    		alarmManager.setRepeating(//重复闹钟    致命问题：由于4.4之后的重复闹钟不准确所以不是一分钟就执行一次server
        			4, //闹钟唤醒类型5种
        			calendar.getTimeInMillis(), //何时开始    业务：这样不能确保是整分钟时更新 有可能到了35秒才更新
        			20*1000, //每隔多久
        			pendingIntent);//广播
    		Log.v("mars", "alarmManager成功创建");
    	} 
    }
    
    
    
    /**
	 * 每接收一次广播消息就调用一次，使用频繁
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		toast(context,"all--onReceive ");
		super.onReceive(context, intent);
		
	}
	/**
	 * 桌面控件被放到桌面时调用
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
	 * 当桌面控件从桌面删除时调用 没删除一个就调用一次   
	 * 当没有widgetids时取消定时器
	 */
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		toast(context,"all--onDeleted "+appWidgetIds.length);
	}

	/**
	 * 当该Widget第一次添加到桌面是调用该方法，可添加多次但只第一次调用
	 */
	@Override
	public void onEnabled(Context context) {
		toast(context,"all--onEnabled ");
		super.onEnabled(context); 
	}
	
	/**
	 * 当最后一个该Widget删除是调用该方法，注意是最后一个
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
			alarmManager.cancel(pendingIntent);//取消该闹钟
			pendingIntent.cancel();
		} 
	}
    
    
	private void toast(Context context,String show_str) {

		Toast.makeText(context, show_str, 1).show();
	}
}