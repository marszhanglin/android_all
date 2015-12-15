package mars.all.activity.two.circlemenu;

import mars.all.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * ����
 * 
 * @author Mars zhang
 * @created 2015-12-11 ����2:27:16
 */
public class CircleMenuLayout extends ViewGroup {

    /** �����˵������� */
    private String[] mItemTexts = {};
    /** �����˵���ͼƬ��Դ */
    private int[] mItemImageResourceIds = {};
    private CircleMenuInterface mCircleMenuInterface;
    /** ��Բֱ��  */
    private int mRadius = 0;
    /** ����ĸ��� */
    private int itemCount = 0;
    /** ���ûص� */
    public void setmCircleMenuInterface(CircleMenuInterface mCircleMenuInterface) {
        this.mCircleMenuInterface = mCircleMenuInterface;
    }

    /** �ص��ӿ� */
    public interface CircleMenuInterface {
        public void itemOnClick(View view, int pos);

        public void centerClick(View view);
    }

    public CircleMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);// xml��ʽ���û���������
        setPadding(0, 0, 0, 0);// ����padding
    }

    public CircleMenuLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CircleMenuLayout(Context context) {
        super(context);
    }

    /**
     * 
     * ���� �����ӿؼ�λ��
     * 
     * @author Mars zhang
     * @created 2015-12-11 ����2:28:07
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     * @see android.view.ViewGroup#onLayout(boolean, int, int, int, int)
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    /**
     * 
     * ���� �����������Լ��Ŀ�ߣ���ȷ���>�������>getWidth�� ��������item�Ŀ��
     * 
     * @author Mars zhang
     * @created 2015-12-11 ����2:43:41
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     * @see android.view.View#onMeasure(int, int)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // ������Դ�Ŀ��
        int resWidth = 0;
        int resHeight = 0;

        // ��ô�ViewGroup�ϼ�����Ϊ���Ƽ��Ŀ�͸ߣ��Լ�����ģʽ����wrap_contentʱ�Ŀ�ߣ�
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);// xml����Ķ���dp ʱ��
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);// xml����Ķ���dp ʱ��

        // ���߲��Ǿ�ȷģʽʱ��ͼƬ��Դ�Ŀ�� �Ǿ�ȷģʽʱ�þ�ȷģʽ�Ŀ��
        if (widthMode != MeasureSpec.EXACTLY || heightMode != MeasureSpec.EXACTLY) {
            // �������
            resWidth = getSuggestedMinimumWidth();
            resHeight = getSuggestedMinimumHeight();

            // getwith
            resWidth = resWidth == 0 ? getWidth() : resWidth;
            resHeight = resHeight == 0 ? getHeight() : resHeight;
            
        }else{
            //��ȷ
            resWidth = resHeight = Math.min(sizeWidth, sizeHeight);
        }
        //����ViewGroup�Ŀ��
        setMeasuredDimension(resWidth, resHeight);
        
        //�������ViewGroup��߿�����Ϊ�Ǵ�Բ�ε�ֱ��
        mRadius = Math.max(getMeasuredHeight(), getMeasuredWidth());
        
        itemCount = getChildCount();
        
    }

    /**
     * 
     * ���� ������Activity������item��ͼ�������ֵ�
     * 
     * @author Mars zhang
     * @created 2015-12-11 ����2:45:37
     */
    public void setMenuIconAndText(String[] texts, int[] resourceIds) {
        if (null == texts && resourceIds == null) {
            System.out.println("��ɵ������");
        } else {
            itemCount = texts == null ? resourceIds.length : texts.length;
            if (null != texts && resourceIds != null) {
                itemCount = Math.max(resourceIds.length, texts.length);
            }
        }

        mItemTexts = texts;
        mItemImageResourceIds = resourceIds;

        addItems();

    }

    /**
     * 
     * ���� ��ÿ��item��ӵ�ViewGroup������
     * 
     * @author Mars zhang
     * @created 2015-12-11 ����2:50:58
     */
    private void addItems() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        for (int i = 0; i < itemCount; i++) {
            final int tempi = i;
            View itemView = inflater.inflate(R.layout.circle_menu_item, null);
            ImageView itemImageView = (ImageView) itemView.findViewById(R.id.circle_menu_item_image);
            TextView itemTextView = (TextView) itemView.findViewById(R.id.circle_menu_item_text);
            itemImageView.setImageResource(mItemImageResourceIds[i]);
            itemTextView.setText(mItemTexts[i]);
            itemImageView.setVisibility(View.VISIBLE);
            itemTextView.setVisibility(View.VISIBLE);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mCircleMenuInterface) {
                        mCircleMenuInterface.itemOnClick(v, tempi);// �������������ʱʵ�ֽӿ�
                                                                   // �˴��ص�
                    }
                }
            });
            // ��ViewGroup������item ViewGroup�Ĺؼ�
            addView(itemView);
        }

    }
}
