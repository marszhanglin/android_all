package mars.all.activity.two.eventBus;

import mars.all.R;
import mars.all.activity.two.eventBus.event.EventMsgOne;
import mars.all.activity.two.eventBus.event.EventMsgTwo;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import de.greenrobot.event.EventBus;


/**
 * 
 * ÃèÊö  EventBus
 * @author Mars zhang
 * @created 2015-12-3 ÏÂÎç2:35:07
 */
public class EventSecondActivity extends Activity{ 
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_second_at);
        
    }
    
    
    public void event_second_ac_btn_1(View view){
        EventMsgOne eventMsgOne = new EventMsgOne(1L);
        eventMsgOne.getData().put("key", ((Button)view).getText().toString());
        EventBus.getDefault().post(eventMsgOne);
    } 
    public void event_second_ac_btn_2(View view){
        EventMsgTwo eventMsgTwo = new EventMsgTwo(1L);
        eventMsgTwo.getData().put("key", ((Button)view).getText().toString());
        EventBus.getDefault().post(eventMsgTwo);
    }
    public void event_second_ac_btn_3(View view){
        EventMsgOne eventMsgOne = new EventMsgOne(1L);
        eventMsgOne.getData().put("key", ((Button)view).getText().toString());
        EventBus.getDefault().post(eventMsgOne);
    }
    public void event_second_ac_btn_4(View view){
        EventMsgTwo eventMsgTwo = new EventMsgTwo(1L);
        eventMsgTwo.getData().put("key", ((Button)view).getText().toString());
        EventBus.getDefault().post(eventMsgTwo);
    }
    public void event_second_ac_btn_5(View view){
        EventMsgOne eventMsgOne = new EventMsgOne(1L);
        eventMsgOne.getData().put("key", ((Button)view).getText().toString());
        EventBus.getDefault().post(eventMsgOne);
    }
    public void event_second_ac_btn_6(View view){
        EventMsgTwo eventMsgTwo = new EventMsgTwo(1L);
        eventMsgTwo.getData().put("key", ((Button)view).getText().toString());
        EventBus.getDefault().post(eventMsgTwo);
    }
    public void event_second_ac_btn_7(View view){
        EventMsgOne eventMsgOne = new EventMsgOne(1L);
        eventMsgOne.getData().put("key", ((Button)view).getText().toString());
        EventBus.getDefault().post(eventMsgOne);
    }
    public void event_second_ac_btn_8(View view){
        EventMsgTwo eventMsgTwo = new EventMsgTwo(1L);
        eventMsgTwo.getData().put("key", ((Button)view).getText().toString());
        EventBus.getDefault().post(eventMsgTwo);
    }
    
    
    public void fh(View view){
        finish();
    }
}
