package mars.all.bean;

import java.util.Date;
/**
 * 瀑布流布局内部的数据item    gridview的item数据
 * 2014-11-6上午11:55:22 类ItemDataAtMain
 * @author Mars zhang
 *
 */
public class ItemDataAtMain {
	private String content;
	public ItemDataAtMain(String content, Date date) {
		super();
		this.content = content;
		this.date = date;
	}
	private Date date;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
