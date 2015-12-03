package mars.all.activity.two.eventBus;

import mars.all.R;
import mars.all.activity.two.eventBus.event.EventMsgOne;
import mars.all.activity.two.eventBus.event.EventMsgTwo;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import de.greenrobot.event.EventBus;
/**
 * 
 * ����     �������Եó����ۣ� onEvent  onEventMainThread  onEventBackgroundThread  onEventAsync
 *                 ˭�ᱻִ��ȡ�������ǵĲ���  ���������8�����������ֲ���  ˵��ÿ��һ�ξ�ִ��4��
 * @author Mars zhang
 * @created 2015-12-3 ����10:22:51
 */
public class EventFirstActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.event_fitrst_at);
        EventBus.getDefault().register(this);
        
    }
    //�����¼��ͽ����¼��߳���ͬһ���߳�
    public void onEvent(EventMsgOne event) { 
        System.out.println( "onEvent�յ�����ϢEventMsgOne��" + event.getData().get("key"));  
    }  
    //�����¼��ͽ����¼��߳���ͬһ���߳�
    public void onEvent(EventMsgTwo event) {   
        System.out.println( "onEvent�յ�����ϢEventMsgTwo��" + event.getData().get("key"));  
    }  
    //�����¼������ĸ��߳��з��������ģ�onEventMainThread������UI�߳���ִ��
    public void onEventMainThread(EventMsgOne event) { 
        Toast.makeText(getApplicationContext(), "onEventMainThread�յ�����ϢEventMsgOne��" + event.getData().get("key"), 1).show();
        System.out.println( "onEventMainThread�յ�����ϢEventMsgOne��" + event.getData().get("key"));  
    }  
    //�����¼������ĸ��߳��з��������ģ�onEventMainThread������UI�߳���ִ��
    public void onEventMainThread(EventMsgTwo event) {  
        Toast.makeText(getApplicationContext(), "onEventMainThread�յ�����ϢEventMsgTwo��" + event.getData().get("key"), 1).show();
        System.out.println( "onEventMainThread�յ�����ϢEventMsgTwo��" + event.getData().get("key"));  
    } 
    //�����¼������ĸ��߳��з��������ģ�onEventBackgroundThread���������߳���ִ��
    public void onEventBackgroundThread(EventMsgOne event) {
        System.out.println( "onEventBackgroundThread�յ�����ϢEventMsgOne��" + event.getData().get("key"));  
    } 
    //�����¼������ĸ��߳��з��������ģ�onEventBackgroundThread���������߳���ִ��
    public void onEventBackgroundThread(EventMsgTwo event) {  
        System.out.println( "onEventBackgroundThread�յ�����ϢEventMsgTwo��" + event.getData().get("key"));  
    } 
    //���ᴴ���µ����߳���ִ��onEventAsync
    public void onEventAsync(EventMsgOne event) {  
        System.out.println( "onEventAsync�յ�����Ϣevent��" + event.getData().get("key"));  
    } 
    //���ᴴ���µ����߳���ִ��onEventAsync
    public void onEventAsync(EventMsgTwo event) {  
        System.out.println( "onEventAsync�յ�����ϢEventMsgTwo��" + event.getData().get("key"));  
    } 
    
    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
    
    
    public void click_to_second_activity(View view){
        startActivity(new Intent(this, EventSecondActivity.class)); 
    }
/*    1��onEvent
    2��onEventMainThread
    3��onEventBackgroundThread
    4��onEventAsync*/
    
}
