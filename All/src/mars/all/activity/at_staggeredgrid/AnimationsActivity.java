package mars.all.activity.at_staggeredgrid;

import mars.all.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * YoYo动画的界面 2014-7-22下午4:13:38 类AfnailPictureActivity
 * 
 * @author Mars zhang
 */
public class AnimationsActivity extends Activity {
    /** 缩放的Image */
    private ImageView imageView;

    private TextView time_tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.animations_activity);
        // 启动activity时不自动弹出软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        YoYo.with(Techniques.Tada).duration(700).playOn(findViewById(R.id.hello));
    }

    /**
     * 返回按钮点击事件
     * 
     * @param v
     */
    public void fh(View v) {
        finish();
    }

    public void main_code(View v) {
        Toast.makeText(getApplicationContext(), "YoYo.with(Techniques.Tada).duration(700).playOn(view);", 0).show();
    }

    public void DropOut(View view) {
        YoYo.with(Techniques.DropOut).duration(700).playOn(view);
    }

    public void Landing(View view) {
        YoYo.with(Techniques.Landing).duration(700).playOn(view);
    }

    public void TakingOff(View view) {
        YoYo.with(Techniques.TakingOff).duration(700).playOn(view);
    }

    public void Flash(View view) {
        YoYo.with(Techniques.Flash).duration(700).playOn(view);
    }

    public void Pulse(View view) {
        YoYo.with(Techniques.Pulse).duration(700).playOn(view);
    }

    public void RubberBand(View view) {
        YoYo.with(Techniques.RubberBand).duration(700).playOn(view);
    }

    public void Shake(View view) {
        YoYo.with(Techniques.Shake).duration(700).playOn(view);
    }

    public void Swing(View view) {
        YoYo.with(Techniques.Swing).duration(700).playOn(view);
    }

    public void Wobble(View view) {
        YoYo.with(Techniques.Wobble).duration(700).playOn(view);
    }

    public void Bounce(View view) {
        YoYo.with(Techniques.Bounce).duration(700).playOn(view);
    }

    public void Tada(View view) {
        YoYo.with(Techniques.Tada).duration(700).playOn(view);
    }

    public void StandUp(View view) {
        YoYo.with(Techniques.StandUp).duration(700).playOn(view);
    }

    public void Wave(View view) {
        YoYo.with(Techniques.Wave).duration(700).playOn(view);
    }

    public void Hinge(View view) {
        YoYo.with(Techniques.Hinge).duration(700).playOn(view);
    }

    public void RollIn(View view) {
        YoYo.with(Techniques.RollIn).duration(700).playOn(view);
    }

    public void RollOut(View view) {
        YoYo.with(Techniques.RollOut).duration(700).playOn(view);
    }

    public void BounceIn(View view) {
        YoYo.with(Techniques.BounceIn).duration(700).playOn(view);
    }

    public void BounceInDown(View view) {
        YoYo.with(Techniques.BounceInDown).duration(700).playOn(view);
    }

    public void BounceInLeft(View view) {
        YoYo.with(Techniques.BounceInLeft).duration(700).playOn(view);
    }

    public void BounceInRight(View view) {
        YoYo.with(Techniques.BounceInRight).duration(700).playOn(view);
    }

    public void BounceInUp(View view) {
        YoYo.with(Techniques.BounceInUp).duration(700).playOn(view);
    }

    public void FadeIn(View view) {
        YoYo.with(Techniques.FadeIn).duration(700).playOn(view);
    }

    public void FadeInUp(View view) {
        YoYo.with(Techniques.FadeInUp).duration(700).playOn(view);
    }

    public void FadeInDown(View view) {
        YoYo.with(Techniques.FadeInDown).duration(700).playOn(view);
    }

    public void FadeInLeft(View view) {
        YoYo.with(Techniques.FadeInLeft).duration(700).playOn(view);
    }

    public void FadeInRight(View view) {
        YoYo.with(Techniques.FadeInRight).duration(700).playOn(view);
    }

    public void FadeOut(View view) {
        YoYo.with(Techniques.FadeOut).duration(700).playOn(view);
    }

    public void FadeOutDown(View view) {
        YoYo.with(Techniques.FadeOutDown).duration(700).playOn(view);
    }

    public void FadeOutLeft(View view) {
        YoYo.with(Techniques.FadeOutLeft).duration(700).playOn(view);
    }

    public void FadeOutRight(View view) {
        YoYo.with(Techniques.FadeOutRight).duration(700).playOn(view);
    }

    public void FadeOutUp(View view) {
        YoYo.with(Techniques.FadeOutUp).duration(700).playOn(view);
    }

    public void FlipInX(View view) {
        YoYo.with(Techniques.FlipInX).duration(700).playOn(view);
    }

    public void FlipOutX(View view) {
        YoYo.with(Techniques.FlipOutX).duration(700).playOn(view);
    }

    public void FlipOutY(View view) {
        YoYo.with(Techniques.FlipOutY).duration(700).playOn(view);
    }

    public void RotateIn(View view) {
        YoYo.with(Techniques.RotateIn).duration(700).playOn(view);
    }

    public void RotateInDownLeft(View view) {
        YoYo.with(Techniques.RotateInDownLeft).duration(700).playOn(view);
    }

    public void RotateInDownRight(View view) {
        YoYo.with(Techniques.RotateInDownRight).duration(700).playOn(view);
    }

    public void RotateInUpLeft(View view) {
        YoYo.with(Techniques.RotateInUpLeft).duration(700).playOn(view);
    }

    public void RotateInUpRight(View view) {
        YoYo.with(Techniques.RotateInUpRight).duration(700).playOn(view);
    }

    public void RotateOut(View view) {
        YoYo.with(Techniques.RotateOut).duration(700).playOn(view);
    }

    public void RotateOutDownLeft(View view) {
        YoYo.with(Techniques.RotateOutDownLeft).duration(700).playOn(view);
    }

    public void RotateOutDownRight(View view) {
        YoYo.with(Techniques.RotateOutDownRight).duration(700).playOn(view);
    }

    public void RotateOutUpLeft(View view) {
        YoYo.with(Techniques.RotateOutUpLeft).duration(700).playOn(view);
    }

    public void RotateOutUpRight(View view) {
        YoYo.with(Techniques.RotateOutUpRight).duration(700).playOn(view);
    }

    public void SlideInLeft(View view) {
        YoYo.with(Techniques.SlideInLeft).duration(700).playOn(view);
    }

    public void SlideInRight(View view) {
        YoYo.with(Techniques.SlideInRight).duration(700).playOn(view);
    }

    public void SlideInUp(View view) {
        YoYo.with(Techniques.SlideInUp).duration(700).playOn(view);
    }

    public void SlideInDown(View view) {
        YoYo.with(Techniques.SlideInDown).duration(700).playOn(view);
    }

    public void SlideOutLeft(View view) {
        YoYo.with(Techniques.SlideOutLeft).duration(700).playOn(view);
    }

    public void SlideOutRight(View view) {
        YoYo.with(Techniques.SlideOutRight).duration(700).playOn(view);
    }

    public void SlideOutUp(View view) {
        YoYo.with(Techniques.SlideOutUp).duration(700).playOn(view);
    }

    public void SlideOutDown(View view) {
        YoYo.with(Techniques.SlideOutDown).duration(700).playOn(view);
    }

    public void ZoomIn(View view) {
        YoYo.with(Techniques.ZoomIn).duration(700).playOn(view);
    }

    public void ZoomInDown(View view) {
        YoYo.with(Techniques.ZoomInDown).duration(700).playOn(view);
    }

    public void ZoomInLeft(View view) {
        YoYo.with(Techniques.ZoomInLeft).duration(700).playOn(view);
    }

    public void ZoomInRight(View view) {
        YoYo.with(Techniques.ZoomInRight).duration(700).playOn(view);
    }

    public void ZoomInUp(View view) {
        YoYo.with(Techniques.ZoomInUp).duration(700).playOn(view);
    }

    public void ZoomOut(View view) {
        YoYo.with(Techniques.ZoomOut).duration(700).playOn(view);
    }

    public void ZoomOutDown(View view) {
        YoYo.with(Techniques.ZoomOutDown).duration(700).playOn(view);
    }

    public void ZoomOutLeft(View view) {
        YoYo.with(Techniques.ZoomOutLeft).duration(700).playOn(view);
    }

    public void ZoomOutRight(View view) {
        YoYo.with(Techniques.ZoomOutRight).duration(700).playOn(view);
    }

    public void ZoomOutUp(View view) {
        YoYo.with(Techniques.ZoomOutUp).duration(700).playOn(view);
    }

}
