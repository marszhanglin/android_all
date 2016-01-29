package mars.all.activity.two.androidasync;

import mars.all.R;

import org.androidannotations.annotations.EActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


/**
 * 
 * ����  ���������AndroidAsync��ʹ��<br>
 * ����NIO��һ���̡߳�Callback�����������ܡ�<br>
        ���в���������һ��Future�������ڳ�����<br>
        Socket client + Socket Server<br>
        HTTP Client + Server<br>
        WebSocket client + server<br>
        Socket.IO Client<br>
 * https://github.com/koush/AndroidAsync<br>
 *  ��������ɡ�����
 * @author Mars zhang
 * @created 2016-1-25 ����4:09:47
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
