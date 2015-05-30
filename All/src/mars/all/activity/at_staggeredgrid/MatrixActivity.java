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
 * Matrix��chose���ִ���   
 * �ؼ���ImageView��scaleType������Matrix  
 * ������src�Ĳ�������Ч    ����ֻ����Imageview���ڲ��ƶ�����
 * 2014-7-22����4:13:38 ��AfnailPictureActivity
 * 
 * @author Mars zhang
 * 
 */
public class MatrixActivity extends Activity implements OnLongClickListener {  

	/**1*/
	private Matrix matrix=null;
	/**��������ͼƬ*/
	private ImageView imageView=null;
	/**ͼƬ��Դ*/
	private Bitmap bitmap=null;
	private TextView  textView=null;
	private StringBuilder sb=new StringBuilder();
	/**����*/
	private float a=1f,b=0f,c=0f,d=0f,e=1f,f=0f;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.matrix_activity); 
        // ����activityʱ���Զ����������
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ((TextView)findViewById(R.id.gesture_detector_top_tv)).setSelected(true);
        textView=(TextView) findViewById(R.id.gesture_detector_content_tv);
        imageView=(ImageView) findViewById(R.id.matrix_image);
        imageView.setScaleType(ScaleType.MATRIX);
        /**2*/
        matrix=new Matrix();
        /**��ȡͼƬԴ*/
//        bitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.fzaj_xxgg);
        
    }

    /**
     * ���ذ�ť����¼� 
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
		String[] strs={"ƽ��x30 y60","ƽ��x-30 y-60","����0.2f, 0.25f","����5f, 4f","����0.5f, 0.5f, 200f, 200f","����","{a,b,c,d,e,f,0f,0f,1}��ɵ�˰�"};
    	Dialog schDia = new AlertDialog.Builder(
                MatrixActivity.this)
                .setIcon(R.drawable.login_error_icon)
                .setTitle("��ѡ��")
                .setItems(strs, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dia, final int which_p) {
                    	//Ϊ�˷�ֹ�ڴ��������imageView��bitmapDrawable
//                    	BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView
//                    			.getDrawable();
//                    	if(bitmapDrawable.getBitmap().isRecycled()){
//                    		bitmapDrawable.getBitmap().recycle();
//                    	} 
                    	
                    	
                    	//3��matrix���ݲ���
                    	switch (which_p) {
						case 0://ƽ��
							matrix.postTranslate(30f, 60f);
							break;
						case 1://ƽ��
							matrix.postTranslate(-30f, -60f);
							break;
						case 2://����
							a=0.2f*a;
							e=0.25f*e;
							matrix.setScale(a, e);
							break;
						case 3://����
							a=5f*a;
							e=4f*e;
							matrix.setScale(a, e);
							break;
						case 4://����      ��������ʲô��Ҫ��ס��ʷ
							a=0.5f*a;
							e=0.5f*e;
							matrix.setScale(a, e, 200f, 200f);
							break;
						case 5://����
							matrix.reset();
							break;
						case 6://����   
							a=2f*a;
							e=2f*e;
							matrix.setValues(new float[]{a,b,c,d,e,f,0f,0f,1});
							break;
						default:
							break;
						}
                    	/**4����*/
                        imageView.setImageMatrix(matrix);
                    }
                })
                .setNegativeButton("ȡ��",
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
