package mars.all.activity.three.vdhlayout;

import mars.all.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * 
 * ����  Android �Զ���View (��) ����
 * @author Mars zhang
 * @created 2016-2-29 ����8:44:33
 */
public class HXViewVDHActivity extends Activity {
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tree_vdh_activity);
    }
    
    
    public void aaa(View view){
        Toast.makeText(getApplicationContext(), "13131", 1).show();
    }
    
}
