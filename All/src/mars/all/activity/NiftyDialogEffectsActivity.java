package mars.all.activity;

import mars.all.R;
import mars.all.widget.niftydialogeffects.Effectstype;
import mars.all.widget.niftydialogeffects.NiftyDialogBuilder;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window; 
import android.widget.Toast;
/**
 * 动画Dialog
 * 2014-11-3上午11:53:36 类NiftyDialogEffectsActivity
 * @author Mars zhang
 *
 */
public class NiftyDialogEffectsActivity extends Activity {
	Effectstype effect;
	NiftyDialogBuilder dialogBuilder;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.nifty_dialog_effects);
		
	}
	
	
	public void button_onclick(View view){
		dialogBuilder=NiftyDialogBuilder.getInstance(this); 
		switch (view.getId()) { 
		case R.id.fadein:effect=Effectstype.Fadein;break;
        case R.id.slideright:effect=Effectstype.Slideright;break;
        case R.id.slideleft:effect=Effectstype.Slideleft;break;
        case R.id.slidetop:effect=Effectstype.Slidetop;break;
        case R.id.slideBottom:effect=Effectstype.SlideBottom;break;
        case R.id.newspager:effect=Effectstype.Newspager;break;
        case R.id.fall:effect=Effectstype.Fall;break;
        case R.id.sidefall:effect=Effectstype.Sidefill;break;
        case R.id.fliph:effect=Effectstype.Fliph;break;
        case R.id.flipv:effect=Effectstype.Flipv;break;
        case R.id.rotatebottom:effect=Effectstype.RotateBottom;break;
        case R.id.rotateleft:effect=Effectstype.RotateLeft;break;
        case R.id.slit:effect=Effectstype.Slit;break;
        case R.id.shake:effect=Effectstype.Shake;break; 
		default:
			break;
		}
		dialogBuilder
        .withTitle("标题")                                   
        .withTitleColor("#FFaaFF")                                  
        .withDividerColor("#00ff00")                               
        .withMessage("内容文本")                      
        .withMessageColor("#FFFFFF")                                 
        .withIcon(getResources().getDrawable(R.drawable.icon))
        .isCancelableOnTouchOutside(true)                            
        .withDuration(700)                                           
        .withEffect(effect)                                          
        .withButton1Text("确定")                                      
        .withButton2Text("取消")  
        .withparentPanelColor("#99aacc99")
        .withtopPanelColor("#ffaacc00")
        .setCustomView(R.layout.custom_view,view.getContext())          
        .setButton1Click(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "i'm btn1", Toast.LENGTH_SHORT).show();
                dialogBuilder.dismiss();
            }
        })
        .setButton2Click(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "i'm btn2", Toast.LENGTH_SHORT).show();
                dialogBuilder.dismiss();
            }
        })
        .show();
		
	}

}
