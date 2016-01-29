package com.example.tts;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TTSActivity extends Activity implements OnInitListener {
	// 引入OnInitListener的接口，用来初始化tts 
	String input = "默认";
	String match = null;
	EditText et = null;
	Button search = null;
	Button del = null;
	TextView display = null;

	TextToSpeech speak = null;// 定义tts
	Button speakbt = null;// tts发音按钮
	String speakstr = "你妹，日本芷江投降彩照公布,首相安倍、被打";// 发音文本

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tts);
		et = (EditText) findViewById(R.id.tts_edit);
		search = (Button) findViewById(R.id.speak_button);
		del = (Button) findViewById(R.id.del_button);
		display = (TextView) findViewById(R.id.tts_tv);
		speakbt = (Button) findViewById(R.id.sp_button);

		speak = new TextToSpeech(this, this);// new只是，系统会调用tts的初始化函数onInit()。所有要复写onInit()方法

		search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				input = et.getText().toString();
				// 现在工程的res目录下面新建一个xml文件夹，把cet4.xml拖进去
				XmlResourceParser xrp = getResources().getXml(R.xml.cet4);// 定义一个XML资源解析器

				try {
					StringBuilder strbuilder = new StringBuilder("");
					while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {// 当还没到XML文档结束时
						if (xrp.getEventType() == XmlResourceParser.START_TAG) {// 当遇到开始标签时,包括子标签和子标签里面的所有标签
							String tagname = xrp.getName();// 获取标签的名字

							if (tagname.equals("word")) {// 取出单词
								match = xrp.nextText();// 读取标签里面内容，也就是这个单词

								if (match.equals(input)) {// 如果取出来的这个单词和输入的单词相等
									xrp.next();// 读取这个下一个标签，也就是<trans>翻译
									strbuilder.append(xrp.nextText());// 取出翻译
									display.setText(strbuilder);// 显示翻译
									speakstr = match;// 赋值给发音文本
									break;

								}

							}

						}
						xrp.next();// 读取下一个标签

					}

				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});

		speakbt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				input = et.getText().toString();
				// TODO Auto-generated method stub
				speak.speak(input, TextToSpeech.QUEUE_ADD, null);

			}
		});
		del.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				display.setText("");
				et.setText("");
				speakstr = ""; 
			}
		}); 
	}

	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		speak.setLanguage(Locale.US);// 初始化TTS组件，设置语言为US英语

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (speak != null)
			speak.shutdown();// 关闭tts引擎
	}
	
	public void  voide2text(View view) {
		Toast.makeText(getApplicationContext(), R.string.voide2text, 1).show();
		startActivity(new Intent(getApplicationContext(), Google_STTActivity.class));
	}
}