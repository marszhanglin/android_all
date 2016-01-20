package mars.all.activity.two.androidannotations;

import mars.all.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 
 * ���� AndroidAnnotations����ע���� <br>
 * ע�⣺AndroidManifest.xmlҪ���»��� <br>
 * activity android:name=
 * "mars.all.activity.two.androidannotations.AndroidAnnotationsTest_"<br>
 * https://github.com/excilys/androidannotations/wiki/HowItWorks#starting-an-annotated-activity
 * 
 * @author Mars zhang
 * @created 2016-1-20 ����10:55:05
 */
@EActivity(R.layout.androidannotations_activity)
public class AndroidAnnotationsTest extends Activity {
    @ViewById(R.id.ed_id)
    EditText abcEt; 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
    }
    
    @AfterViews
    void afterview(){
        Toast.makeText(getApplicationContext(), abcEt.getText()+"1231", 1).show();
    }
    
    @Click(R.id.btn_id)
    void myBtnclick(){
        Toast.makeText(getApplicationContext(), abcEt.getText(), 1).show();
    }

}
