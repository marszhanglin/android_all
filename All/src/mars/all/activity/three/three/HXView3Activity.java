package mars.all.activity.three.three;

import mars.all.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * 
 * 描述  Android 自定义View (三) 进阶
 * @author Mars zhang
 * @created 2016-2-29 下午8:44:33
 */
public class HXView3Activity extends Activity {
    
    private int index=0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tree_hx3_activity);
    }
    
    
    public void btnclick(View view){
        index++;
        Toast.makeText(getApplicationContext(), "=="+index+"", 0).show();
    }
    
}
