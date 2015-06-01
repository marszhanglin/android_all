package mars.all.activity.base;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import mars.all.R;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
/**
 *  ������Activity��
 * 2015-2-15����7:21:37 ��BaseActivity
 * @author Mars zhang
 *
 */
public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//������������
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		//��Ҫ����
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		
	}
	
	
	
	
	
	/**
     * �������ʾ��Ϣ
     * 
     * @param errorMsg
     */
    protected void DialogToast(String errorMsg,final ICallback callback) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("��ʾ��Ϣ");
        builder1.setIcon(R.drawable.qq_dialog_default_icon);// ͼ��
        builder1.setMessage("" + errorMsg);
        builder1.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
            // @Override
            public void onClick(DialogInterface dialog, int which) {
            	if(null!=callback){
            		callback.execute();
            	}
            }
        });
        builder1.show();
    }
    /*DialogToast("aasdasdasd",new ICallback() { 
		@Override
		public Object execute() {
			toast("�ص���", 1);
			return null;
		}
	});*/
    
    
    /** ��˾ */
    protected void toast(String strMsg, int L1S0) {
        Toast.makeText(getApplicationContext(), strMsg, L1S0).show();
    }
    
    
    
    
    /**
     * 
     * @param http  get����
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     */ 
    private String connServerForResult(String strUrl) throws Exception {
        // HttpGet����
        HttpGet httpRequest = new HttpGet(strUrl);
        String strResult = "";
        // HttpClient����
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 5000);// ���ó�ʱʱ��
        HttpClient httpClient = new DefaultHttpClient(httpParams);
        HttpResponse httpResponse = httpClient.execute(httpRequest);
        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            // ȡ�÷��ص�����
            strResult = EntityUtils.toString(httpResponse.getEntity());
        }

        return strResult;
    }
    
    /**
     * 
     * @param http  post����
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     */ 
    /**
     * 
     * @param strUrl   http://localhost:8080/ecssp/jfs/mobile/androidIndex/login
     * @param entity_str   username=sysadmin&password=D860103725C09C63BFDFB0D6962EC1AB&imei=null
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String connServerForResultPost(String strUrl, String entity_str) throws ClientProtocolException,
            IOException {
        String strResult = "";
        URL url = new URL(strUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        byte[] entity = entity_str.getBytes();
        conn.setConnectTimeout(5000);
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
        conn.getOutputStream().write(entity);
        if (conn.getResponseCode() == 200) {
            InputStream inputstream = conn.getInputStream();
            StringBuffer buffer = new StringBuffer();
            byte[] b = new byte[4096];
            for (int n; (n = inputstream.read(b)) != -1;) {
                buffer.append(new String(b, 0, n));
            }
            strResult = buffer.toString();
        }
        return strResult;
    }
}
