package mars.all.activity.textclock.appwidgetprovider;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
 
    @Override
    public void onUpdate(Context context,
                         AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        this.context = context;
        toast(context, "all--onUpdate  length="+appWidgetIds.length);
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
	 */
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		toast(context,"all--onDeleted length="+appWidgetIds.length);
		super.onDeleted(context, appWidgetIds);
		
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
		super.onDisabled(context);
	}
    
    
	private void toast(Context context,String show_str) {

		Toast.makeText(context, show_str, 1).show();
	}
}