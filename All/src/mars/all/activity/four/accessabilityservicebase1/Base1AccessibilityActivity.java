package mars.all.activity.four.accessabilityservicebase1;

import mars.all.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import de.greenrobot.event.EventBus;
/**
 * 
 * 描述   抢红包辅助  base1
 * @author Mars zhang
 * @created 2016-3-7 上午9:55:54
 */
public class Base1AccessibilityActivity extends Activity {
    public TextView four_base1_at_tv=null; 
    StringBuilder mstringBuilder =new StringBuilder();
    private SharedPreferences sp=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_base1_activity);
        four_base1_at_tv= (TextView) findViewById(R.id.four_base1_at_tv);
        EventBus.getDefault().register(this);
        sp = getSharedPreferences("Base1AccessibilityActivity", Context.MODE_PRIVATE);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        freshTV(sp.getString("log", "无"));
    }
    
    /**
     * 
     * 描述  刷新textView
     * @author Mars zhang
     * @created 2016-3-7 上午11:04:34
     * @param msg
     */
    void freshTV(String msg){
        four_base1_at_tv.setText(msg);
    }
    
    //不论事件是在哪个线程中发布出来的，onEventMainThread都会在UI线程中执行
    public void onEventMainThread(EventBusAccessibilityMsg eventmsg) {  
        Toast.makeText(getApplication(), eventmsg.getMsg(), 0).show();
        mstringBuilder.append("\n"+eventmsg.getMsg()); 
        Editor editor=sp.edit();
        editor.putString("log", mstringBuilder.toString());
        editor.commit();
    }  
    
    public void onsettingclick(View view ){
        Intent intent =new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivity(intent);
    }
}
