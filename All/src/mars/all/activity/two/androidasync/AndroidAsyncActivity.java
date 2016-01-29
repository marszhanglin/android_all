package mars.all.activity.two.androidasync;

import mars.all.R;

import org.androidannotations.annotations.EActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


/**
 * 
 * 描述  网络请求库AndroidAsync的使用<br>
 * 基于NIO，一个线程、Callback驱动、高性能。<br>
        所有操作都返回一个Future对象，用于撤销。<br>
        Socket client + Socket Server<br>
        HTTP Client + Server<br>
        WebSocket client + server<br>
        Socket.IO Client<br>
 * https://github.com/koush/AndroidAsync<br>
 *  还不会玩干。。。
 * @author Mars zhang
 * @created 2016-1-25 下午4:09:47
 */
@EActivity(R.layout.androidasync_activity)
public class AndroidAsyncActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    public void download_a_url_to_a_string(View view){
        Toast.makeText(getApplicationContext(), "1", 0).show();
    }
    
    public void download_json_from_a_url(View view){
        Toast.makeText(getApplicationContext(), "2", 0).show();
    }
    
    public void supports_socket_io(View view){
        Toast.makeText(getApplicationContext(), "3", 0).show();
    }

}
