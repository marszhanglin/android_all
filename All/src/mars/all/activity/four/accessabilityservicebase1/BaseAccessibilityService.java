package mars.all.activity.four.accessabilityservicebase1;

import de.greenrobot.event.EventBus;
import android.accessibilityservice.AccessibilityService;
import android.os.Looper;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

/**
 * 
 * ���� �����ԭ��Ļ�������
 * 
 * <br>
 * ���ã�1��mainfest.xml 2��@xml/accessibility_service_config <br>
 * http://krelve.com/android/21.html<br>
 * http://krelve.com/android/23.html
 * 
 * @author Mars zhang
 * @created 2016-3-7 ����9:35:49
 */
public class BaseAccessibilityService extends AccessibilityService {

    /**
     * 
     * ���� ���յ�ϵͳ��AccessibilityEvent�¼���Ϣʱ�ص�
     * 
     * @author Mars zhang
     * @created 2016-3-7 ����9:37:32
     * @param event
     * @see android.accessibilityservice.AccessibilityService#onAccessibilityEvent(android.view.accessibility.AccessibilityEvent)
     */
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        String eventText = "";
        switch (eventType) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                eventText = "TYPE_VIEW_CLICKED-���";
                break;
            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
                eventText = "TYPE_VIEW_FOCUSED-��ȡ����";
                break;
            case AccessibilityEvent.TYPE_VIEW_LONG_CLICKED:
                eventText = "TYPE_VIEW_LONG_CLICKED-����";
                break;
            case AccessibilityEvent.TYPE_VIEW_SELECTED:
                eventText = "TYPE_VIEW_SELECTED-ѡ��";
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                eventText = "TYPE_VIEW_TEXT_CHANGED-�ı����ݱ仯";
                break;
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                eventText = "TYPE_WINDOW_STATE_CHANGED-����״̬�ı�";
                break;
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                eventText = "TYPE_NOTIFICATION_STATE_CHANGED-������Ϣ״̬�ı�";
                break;
            case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_END:
                eventText = "TYPE_TOUCH_EXPLORATION_GESTURE_END-";
                break;
            case AccessibilityEvent.TYPE_ANNOUNCEMENT:
                eventText = "TYPE_ANNOUNCEMENT";
                break;
            case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_START:
                eventText = "TYPE_TOUCH_EXPLORATION_GESTURE_START";
                break;
            case AccessibilityEvent.TYPE_VIEW_HOVER_ENTER:
                eventText = "TYPE_VIEW_HOVER_ENTER";
                break;
            case AccessibilityEvent.TYPE_VIEW_HOVER_EXIT:
                eventText = "TYPE_VIEW_HOVER_EXIT";
                break;
            case AccessibilityEvent.TYPE_VIEW_SCROLLED:
                eventText = "TYPE_VIEW_SCROLLED";
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED:
                eventText = "TYPE_VIEW_TEXT_SELECTION_CHANGED";
                break;
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                eventText = "TYPE_WINDOW_CONTENT_CHANGED";
                break;
        }
        Log.v("weixin_accessability", eventText);
        EventBusAccessibilityMsg accessibilityMsg =new EventBusAccessibilityMsg(eventText);
        EventBus.getDefault().post(accessibilityMsg);
    }

    @Override
    public void onInterrupt() {

    }

}
