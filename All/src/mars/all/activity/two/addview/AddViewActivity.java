package mars.all.activity.two.addview;

import mars.all.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
/**
 * 
 * 描述  动态添加控件
 * @author Mars zhang
 * @created 2015-12-18 上午10:39:42
 */
public class AddViewActivity extends Activity {
    private Context mContext;
    private LayoutInflater mInflater;
    private LinearLayout mLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_view_activity);
        this.mContext=this;
        mInflater = LayoutInflater.from(mContext);
        mLayout = (LinearLayout) findViewById(R.id.add_view_linearlayout_id);
    }
    
    
    public  void  addview(View view){
        View child=mInflater.inflate(R.layout.add_view_itemview, null);
        mLayout.addView(child);
        mLayout.invalidate();  
    }
    
    public  void  removeview(View view){
        mLayout.removeAllViews();
    }
    
    
}
