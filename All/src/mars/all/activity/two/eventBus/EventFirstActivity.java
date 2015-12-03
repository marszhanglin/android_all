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
 * 描述     经过测试得出结论： onEvent  onEventMainThread  onEventBackgroundThread  onEventAsync
 *                 谁会被执行取决于它们的参数  这个例子中8个方法就两种参数  说明每点一次就执行4次
 * @author Mars zhang
 * @created 2015-12-3 上午10:22:51
 */
public class EventFirstActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.event_fitrst_at);
        EventBus.getDefault().register(this);
        
    }
    //发布事件和接收事件线程在同一个线程
    public void onEvent(EventMsgOne event) { 
        System.out.println( "onEvent收到了消息EventMsgOne：" + event.getData().get("key"));  
    }  
    //发布事件和接收事件线程在同一个线程
    public void onEvent(EventMsgTwo event) {   
        System.out.println( "onEvent收到了消息EventMsgTwo：" + event.getData().get("key"));  
    }  
    //不论事件是在哪个线程中发布出来的，onEventMainThread都会在UI线程中执行
    public void onEventMainThread(EventMsgOne event) { 
        Toast.makeText(getApplicationContext(), "onEventMainThread收到了消息EventMsgOne：" + event.getData().get("key"), 1).show();
        System.out.println( "onEventMainThread收到了消息EventMsgOne：" + event.getData().get("key"));  
    }  
    //不论事件是在哪个线程中发布出来的，onEventMainThread都会在UI线程中执行
    public void onEventMainThread(EventMsgTwo event) {  
        Toast.makeText(getApplicationContext(), "onEventMainThread收到了消息EventMsgTwo：" + event.getData().get("key"), 1).show();
        System.out.println( "onEventMainThread收到了消息EventMsgTwo：" + event.getData().get("key"));  
    } 
    //不论事件是在哪个线程中发布出来的，onEventBackgroundThread都会在子线程中执行
    public void onEventBackgroundThread(EventMsgOne event) {
        System.out.println( "onEventBackgroundThread收到了消息EventMsgOne：" + event.getData().get("key"));  
    } 
    //不论事件是在哪个线程中发布出来的，onEventBackgroundThread都会在子线程中执行
    public void onEventBackgroundThread(EventMsgTwo event) {  
        System.out.println( "onEventBackgroundThread收到了消息EventMsgTwo：" + event.getData().get("key"));  
    } 
    //都会创建新的子线程在执行onEventAsync
    public void onEventAsync(EventMsgOne event) {  
        System.out.println( "onEventAsync收到了消息event：" + event.getData().get("key"));  
    } 
    //都会创建新的子线程在执行onEventAsync
    public void onEventAsync(EventMsgTwo event) {  
        System.out.println( "onEventAsync收到了消息EventMsgTwo：" + event.getData().get("key"));  
    } 
    
    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
    
    
    public void click_to_second_activity(View view){
        startActivity(new Intent(this, EventSecondActivity.class)); 
    }
/*    1、onEvent
    2、onEventMainThread
    3、onEventBackgroundThread
    4、onEventAsync*/
    
}
