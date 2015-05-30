package mars.all.activity.at_staggeredgrid;

import mars.all.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Matrix看chose部分代码   
 * 关键是ImageView的scaleType必须是Matrix  
 * 必须是src的操作才有效    而且只能在Imageview的内部移动缩放
 * 2014-7-22下午4:13:38 类AfnailPictureActivity
 * 
 * @author Mars zhang
 * 
 */
public class MatrixActivity extends Activity implements OnLongClickListener {  

	/**1*/
	private Matrix matrix=null;
	/**被操作的图片*/
	private ImageView imageView=null;
	/**图片资源*/
	private Bitmap bitmap=null;
	private TextView  textView=null;
	private StringBuilder sb=new StringBuilder();
	/**矩阵*/
	private float a=1f,b=0f,c=0f,d=0f,e=1f,f=0f;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.matrix_activity); 
        // 启动activity时不自动弹出软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ((TextView)findViewById(R.id.gesture_detector_top_tv)).setSelected(true);
        textView=(TextView) findViewById(R.id.gesture_detector_content_tv);
        imageView=(ImageView) findViewById(R.id.matrix_image);
        imageView.setScaleType(ScaleType.MATRIX);
        /**2*/
        matrix=new Matrix();
        /**获取图片源*/
//        bitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.fzaj_xxgg);
        
    }

    /**
     * 返回按钮点击事件 
     * @param v
     */
    public void fh(View v) {  
        finish();
    }   
    
    public void main_code(View v){
    	chose();
    }

    @Override
	public boolean onLongClick(View v) {
    	toast("onLongClick");
		return false;
	}
    



    
	
	private void toast(String string){
		sb.append(string+"\n");
		textView.setText(sb.toString());
		Toast.makeText(getApplicationContext(), string, 0).show();
	}
	
	
	private void chose() {
		String[] strs={"平移x30 y60","平移x-30 y-60","缩放0.2f, 0.25f","缩放5f, 4f","缩放0.5f, 0.5f, 200f, 200f","重置","{a,b,c,d,e,f,0f,0f,1}！傻了吧"};
    	Dialog schDia = new AlertDialog.Builder(
                MatrixActivity.this)
                .setIcon(R.drawable.login_error_icon)
                .setTitle("请选择")
                .setItems(strs, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dia, final int which_p) {
                    	//为了防止内存溢出回收imageView的bitmapDrawable
//                    	BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView
//                    			.getDrawable();
//                    	if(bitmapDrawable.getBitmap().isRecycled()){
//                    		bitmapDrawable.getBitmap().recycle();
//                    	} 
                    	
                    	
                    	//3给matrix传递参数
                    	switch (which_p) {
						case 0://平移
							matrix.postTranslate(30f, 60f);
							break;
						case 1://平移
							matrix.postTranslate(-30f, -60f);
							break;
						case 2://缩放
							a=0.2f*a;
							e=0.25f*e;
							matrix.setScale(a, e);
							break;
						case 3://缩放
							a=5f*a;
							e=4f*e;
							matrix.setScale(a, e);
							break;
						case 4://缩放      继续缩放什么的要记住历史
							a=0.5f*a;
							e=0.5f*e;
							matrix.setScale(a, e, 200f, 200f);
							break;
						case 5://重置
							matrix.reset();
							break;
						case 6://重置   
							a=2f*a;
							e=2f*e;
							matrix.setValues(new float[]{a,b,c,d,e,f,0f,0f,1});
							break;
						default:
							break;
						}
                    	/**4根据*/
                        imageView.setImageMatrix(matrix);
                    }
                })
                .setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dia,
                                    int which) {
                                dia.dismiss();
                            }
                        }).create();
        schDia.show();
	}

	

     
}
