package mars.all.view.image;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;

@SuppressLint("FloatMath")
public class ZoomImageView extends ImageView {

    private Matrix matrix=new Matrix();
    private Matrix savedMatrix=new Matrix();
    
    static final int NONE = 0;  
    static final int DRAG = 1;  
    static final int ZOOM = 2;  
    int mode = NONE;  

    //�ְ���ʱ�ĵ�
    PointF startPonit = new PointF();  
    PointF midPonit = new PointF();  
    float oldDist = 1f; 
    
    private int screenw=320;
    private int screenh=480;
    private boolean isstop=false;
	
	public ZoomImageView(Context context) {  
		super(context);
		WindowManager wm=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics displayMetrics= new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(displayMetrics);
		screenw=displayMetrics.widthPixels;
		screenh=displayMetrics.heightPixels;
	}

	public ZoomImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		WindowManager wm=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics displayMetrics= new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(displayMetrics);
		screenw=displayMetrics.widthPixels;
		screenh=displayMetrics.heightPixels;
	}

	public ZoomImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		WindowManager wm=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics displayMetrics= new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(displayMetrics);
		screenw=displayMetrics.widthPixels;
		screenh=displayMetrics.heightPixels;
	}
	//xƽ����yƽ��������
	private float spacing(MotionEvent event) {  
        float x = event.getX(0) - event.getX(1);  
        float y = event.getY(0) - event.getY(1);  
        return FloatMath.sqrt(x * x + y * y);  
     }  

	/**���ĵ�point???*/
      private void midPoint(PointF point, MotionEvent event) {  
        float x = event.getX(0) + event.getX(1);  
        float y = event.getY(0) + event.getY(1);  
        point.set(x / 2, y / 2);  
     } 

      private boolean isStop(PointF point, MotionEvent event){
    	   float px=point.x;
    	   float py=point.y;
    	   float x = event.getX(0) - event.getX(1);  
           float y = event.getY(0) - event.getY(1);
//           float sh=screenw/2;
//           float hh=screenh/2;
//           System.out.println("point.x"+point.x+"point.y"+point.y
//	    			+"event.getX(0)"+event.getX(0)+"event.getX(1)"+event.getX(1)
//	    			+"event.getY(0)"+event.getY(0)+"event.getY(1)"+event.getY(1)
//	    			+"event.getY()"+event.getY()+"event.getY()"+event.getY()
//	    			+"screenh"+screenh+"screenw"+screenw); 
//           if(x<screenw){
//        	   return true;
//           }else if(y<screenh){
//        	   return true;
//           }
           
    	  return false;
      }
	@Override
	public boolean onTouchEvent(MotionEvent event) { 
	    if(event.getActionMasked()==MotionEvent.ACTION_POINTER_UP)
	    	Log.d("Infor", "������");
	    switch(event.getActionMasked()){
	    case MotionEvent.ACTION_DOWN:  //����
	    	  matrix.set(getImageMatrix());//��ȡͼƬ��matrix
	    	  savedMatrix.set(matrix);
	    	  startPonit.set(event.getX(),event.getY());
	    	  Log.d("Infor", "������...");
	    	  mode=DRAG;
	          break;
	    case MotionEvent.ACTION_POINTER_DOWN:  //��㴥��
	    	 oldDist=this.spacing(event);
	        if(oldDist>10f){
	    	 Log.d("Infor", "oldDist"+oldDist);
	         savedMatrix.set(matrix);
	    	 midPoint(midPonit,event);
	    	 mode=ZOOM;
	        }
	        break;
	    case MotionEvent.ACTION_POINTER_UP://̧��
	    	mode=NONE;
	        break;
	    case MotionEvent.ACTION_MOVE:  //�϶�
	    	if(mode==DRAG){         //��ʵ��ͼƬ���϶�����...
	    		matrix.set(savedMatrix);
	    	    matrix.postTranslate(event.getX()-startPonit.x, event.getY()-startPonit.y);
	    	}
	    	else if(mode==ZOOM){// ��ʵ��ͼƬ�����Ź���...   ��Ϊ��ʱ�Ƕ�㴥��
	    	 float newDist=spacing(event);
	    	 if(newDist>10){
	    		 matrix.set(savedMatrix);
	    		 float scale=newDist/oldDist;
//	    		 if(scale>1){//ֻ�Ŵ�   ����С
//	    			 matrix.postScale(scale, scale, mid.x, mid.y);
//	    		 }
//	    			if(isStop(mid, event)){
//	    			}else {
//	    				matrix.postScale(scale, scale, mid.x, mid.y);
//	    			}
	    			matrix.postScale(scale, scale, midPonit.x, midPonit.y);  	
	    		 }
	    	    }
	    	break;
	    }
	    //�ı�ͼƬ�ľ���
	    setImageMatrix(matrix);
		return true;
	}
}