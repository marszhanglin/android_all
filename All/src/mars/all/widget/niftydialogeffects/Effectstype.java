package mars.all.widget.niftydialogeffects;

import mars.all.widget.niftydialogeffects.effects.BaseEffects;
import mars.all.widget.niftydialogeffects.effects.FadeIn;
import mars.all.widget.niftydialogeffects.effects.Fall;
import mars.all.widget.niftydialogeffects.effects.FlipH;
import mars.all.widget.niftydialogeffects.effects.FlipV;
import mars.all.widget.niftydialogeffects.effects.NewsPaper;
import mars.all.widget.niftydialogeffects.effects.RotateBottom;
import mars.all.widget.niftydialogeffects.effects.RotateLeft;
import mars.all.widget.niftydialogeffects.effects.Shake;
import mars.all.widget.niftydialogeffects.effects.SideFall;
import mars.all.widget.niftydialogeffects.effects.SlideBottom;
import mars.all.widget.niftydialogeffects.effects.SlideLeft;
import mars.all.widget.niftydialogeffects.effects.SlideRight;
import mars.all.widget.niftydialogeffects.effects.SlideTop;
import mars.all.widget.niftydialogeffects.effects.Slit;

/**
 * Dialog不同风格枚举对象
 * 2014-11-3下午2:49:14 类Effectstype
 * @author Mars zhang
 *
 */
public enum  Effectstype {

    Fadein(FadeIn.class),
    Slideleft(SlideLeft.class),
    Slidetop(SlideTop.class),
    SlideBottom(SlideBottom.class),
    Slideright(SlideRight.class),
    Fall(Fall.class),
    Newspager(NewsPaper.class),
    Fliph(FlipH.class),
    Flipv(FlipV.class),
    RotateBottom(RotateBottom.class),
    RotateLeft(RotateLeft.class),
    Slit(Slit.class),
    Shake(Shake.class),
    Sidefill(SideFall.class);
    private Class effectsClazz;

    private Effectstype(Class mclass) {
        effectsClazz = mclass;
    }

    public BaseEffects getAnimator() {
        try {
            return (BaseEffects) effectsClazz.newInstance();
        } catch (Exception e) {
            throw new Error("Can not init animatorClazz instance");
        }
    }
}
