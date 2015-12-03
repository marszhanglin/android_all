package mars.all.activity.two.eventBus.event;

import java.util.HashMap;

public class EventMsgTwo {

    private Long type;
    private HashMap<String, String> data = new HashMap<String, String>();;


    public EventMsgTwo(Long type) {
        super();
        this.type = type;
        data = new HashMap<String, String>();
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public HashMap<String, String> getData() {
        return data;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }
}
