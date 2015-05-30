package mars.all.widget.niftydialogeffects;

import mars.all.R;
import mars.all.widget.niftydialogeffects.effects.BaseEffects;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Dialog   以后所有的dialog都必须在这个基础上修改 
 * @author Mars zhang 
 */
public class NiftyDialogBuilder extends Dialog implements DialogInterface {

    private final String defTextColor="#FFFFFFFF";

    private final String defDividerColor="#11000000";

    private final String defMsgColor="#FFFFFFFF";

    private final String defDialogColor="#FFE74C3C";



    private Effectstype type=null;

    private LinearLayout mLinearLayoutView;

    private RelativeLayout mRelativeLayoutView;

    private LinearLayout mLinearLayoutMsgView;

    private LinearLayout mLinearLayoutTopView;

    private FrameLayout mFrameLayoutCustomView;

    private View mDialogView;

    private View mDivider;

    private TextView mTitle;

    private TextView mMessage;

    private ImageView mIcon;

    private Button mButton1;

    private Button mButton2;

    private int mDuration = -1;

    private static  int mOrientation=1;

    private boolean isCancelable=true;

    private volatile static NiftyDialogBuilder instance;
    /**dialog1参构造函数*/
    public NiftyDialogBuilder(Context context) {
        super(context);
        init(context);

    }
    /**dialog2参构造函数*/
    public NiftyDialogBuilder(Context context,int theme) {
        super(context, theme);
        init(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.width  = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

    }
    /**
     * 获取dialog对象实例
     * @param context
     * @return
     */
    public static NiftyDialogBuilder getInstance(Context context) {

        int ort=context.getResources().getConfiguration().orientation;
        if (mOrientation!=ort){
            mOrientation=ort;
            instance=null;
        }

        if (instance == null) {
            synchronized (NiftyDialogBuilder.class) {
                if (instance == null) {
                    instance = new NiftyDialogBuilder(context,R.style.dialog_untran);
                }
            }
        }
        return instance;

    }

    private void init(Context context) {


        mDialogView = View.inflate(context, R.layout.dialog_layout, null);

        mLinearLayoutView=(LinearLayout)mDialogView.findViewById(R.id.parentPanel);
        mRelativeLayoutView=(RelativeLayout)mDialogView.findViewById(R.id.main);
        mLinearLayoutTopView=(LinearLayout)mDialogView.findViewById(R.id.topPanel);
        mLinearLayoutMsgView=(LinearLayout)mDialogView.findViewById(R.id.contentPanel);
        mFrameLayoutCustomView=(FrameLayout)mDialogView.findViewById(R.id.customPanel);

        mTitle = (TextView) mDialogView.findViewById(R.id.alertTitle);
        mMessage = (TextView) mDialogView.findViewById(R.id.message);
        mIcon = (ImageView) mDialogView.findViewById(R.id.icon);
        mDivider = mDialogView.findViewById(R.id.titleDivider);
        mButton1=(Button)mDialogView.findViewById(R.id.button1);
        mButton2=(Button)mDialogView.findViewById(R.id.button2);

        setContentView(mDialogView);

        this.setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {

                mLinearLayoutView.setVisibility(View.VISIBLE);
                if(type==null){
                    type=Effectstype.Slidetop;
                }
                start(type);


            }
        });
        mRelativeLayoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCancelable)dismiss();
            }
        });
    }
    /**默认配置标题等颜色*/
    public void toDefault(){
        mTitle.setTextColor(Color.parseColor(defTextColor));
        mDivider.setBackgroundColor(Color.parseColor(defDividerColor));
        mMessage.setTextColor(Color.parseColor(defMsgColor));
        mLinearLayoutView.setBackgroundColor(Color.parseColor(defDialogColor));
    }
    /**设置底部的背景颜色*/
    public NiftyDialogBuilder withparentPanelColor(String colorString) {
    	mLinearLayoutView.setBackgroundColor(Color.parseColor(colorString));
        return this;
    }
    /**设置顶部的背景颜色*/ 
    public NiftyDialogBuilder withtopPanelColor(String colorString) {
    	mLinearLayoutTopView.setBackgroundColor(Color.parseColor(colorString));
        return this;
    }
    /**设置分割线的背景颜色*/
    public NiftyDialogBuilder withDividerColor(String colorString) {
        mDivider.setBackgroundColor(Color.parseColor(colorString));
        return this;
    }

    /**设置标题文本*/
    public NiftyDialogBuilder withTitle(CharSequence title) {
        toggleView(mLinearLayoutTopView,title);
        mTitle.setText(title);
        return this;
    }
    /**设置标题颜色*/
    public NiftyDialogBuilder withTitleColor(String colorString) {
        mTitle.setTextColor(Color.parseColor(colorString));
        return this;
    }
    /**设置信息内容传String的id*/
    public NiftyDialogBuilder withMessage(int textResId) {
        toggleView(mLinearLayoutMsgView,textResId);
        mMessage.setText(textResId);
        return this;
    }
    /**设置信息内容传String*/
    public NiftyDialogBuilder withMessage(CharSequence msg) {
        toggleView(mLinearLayoutMsgView,msg);
        mMessage.setText(msg);
        return this;
    }
    /**设置内容文本颜色*/
    public NiftyDialogBuilder withMessageColor(String colorString) {
        mMessage.setTextColor(Color.parseColor(colorString));
        return this;
    }
    /**修改标题图片传id*/
    public NiftyDialogBuilder withIcon(int drawableResId) {
        mIcon.setImageResource(drawableResId);
        return this;
    }
    /**  修改标题图片传Drawable对象 */
    public NiftyDialogBuilder withIcon(Drawable icon) {
        mIcon.setImageDrawable(icon);
        return this;
    }
    /**设置动画持续时间*/
    public NiftyDialogBuilder withDuration(int duration) {
        this.mDuration=duration;
        return this;
    }
    /**动画的风格*/
    public NiftyDialogBuilder withEffect(Effectstype type) {
        this.type=type;
        return this;
    }
    /**按钮图片id  尽量拷下xml改下drawabled的颜色就好了(要有点击前后动画)   按钮1、2同时使用*/
    public NiftyDialogBuilder withButtonDrawable(int resid) {
        mButton1.setBackgroundResource(resid);
        mButton2.setBackgroundResource(resid);
        return this;
    }
    /**按钮1文本*/
    public NiftyDialogBuilder withButton1Text(CharSequence text) {
        mButton1.setVisibility(View.VISIBLE);
        mButton1.setText(text);

        return this;
    }
    /**按钮2文本*/
    public NiftyDialogBuilder withButton2Text(CharSequence text) {
        mButton2.setVisibility(View.VISIBLE);
        mButton2.setText(text);
        return this;
    }
    /**按钮1监听事件View.OnClickListener*/
    public NiftyDialogBuilder setButton1Click(View.OnClickListener click) {
        mButton1.setOnClickListener(click);
        return this;
    }
    /**按钮2监听事件View.OnClickListener*/
    public NiftyDialogBuilder setButton2Click(View.OnClickListener click) {
        mButton2.setOnClickListener(click);
        return this;
    }

    /**给内容区域添加View布局传layout的 */
    public NiftyDialogBuilder setCustomView(int resId, Context context) {
        View customView = View.inflate(context, resId, null);
        if (mFrameLayoutCustomView.getChildCount()>0){
            mFrameLayoutCustomView.removeAllViews();
        }
        mFrameLayoutCustomView.addView(customView);
        return this;
    }
    /**给文本区域添加View布局传View*/
    public NiftyDialogBuilder setCustomView(View view, Context context) {
        if (mFrameLayoutCustomView.getChildCount()>0){
            mFrameLayoutCustomView.removeAllViews();
        }
        mFrameLayoutCustomView.addView(view);

        return this;
    }
   /**在dialog外点击时dialog消失*/ 
    public NiftyDialogBuilder isCancelableOnTouchOutside(boolean cancelable) {
        this.isCancelable=cancelable;
        //在dialog外点击时window消失mWindow.setCloseOnTouchOutside(cancel);
        this.setCanceledOnTouchOutside(cancelable);
        return this;
    }
    /**是否可取消   参照isCancelableOnTouchOutside的dialog源码mWindow.setCloseOnTouchOutside(cancel);*/
    public NiftyDialogBuilder isCancelable(boolean cancelable) {
        this.isCancelable=cancelable;
        this.setCancelable(cancelable);
        return this;
    }
    /**obj对象存在时显示view，不然将其GONE*/
    private void toggleView(View view,Object obj){
        if (obj==null){
            view.setVisibility(View.GONE);
        }else {
            view.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void show() {

        if (mTitle.getText().equals("")) mDialogView.findViewById(R.id.topPanel).setVisibility(View.GONE);

        super.show();
    }
     /**还需慢慢研究该部分代码*/
    private void start(Effectstype type){
        BaseEffects animator = type.getAnimator();
        if(mDuration != -1){
            animator.setDuration(Math.abs(mDuration));
        }
        animator.start(mRelativeLayoutView);
    }
    /**
     * View.GONE销毁dialog
     */
    @Override
    public void dismiss() {
        super.dismiss();
        mButton1.setVisibility(View.GONE);
        mButton2.setVisibility(View.GONE);
    }
}
