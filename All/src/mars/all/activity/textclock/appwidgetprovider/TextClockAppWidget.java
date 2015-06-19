package mars.all.activity.textclock.appwidgetprovider;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
 
    @Override
    public void onUpdate(Context context,
                         AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        this.context = context;
        toast(context, "all--onUpdate  length="+appWidgetIds.length);
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
	 */
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		toast(context,"all--onDeleted length="+appWidgetIds.length);
		super.onDeleted(context, appWidgetIds);
		
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
		super.onDisabled(context);
	}
    
    
	private void toast(Context context,String show_str) {

		Toast.makeText(context, show_str, 1).show();
	}
}