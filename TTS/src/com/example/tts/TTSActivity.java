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
	// ����OnInitListener�Ľӿڣ�������ʼ��tts 
	String input = "Ĭ��";
	String match = null;
	EditText et = null;
	Button search = null;
	Button del = null;
	TextView display = null;

	TextToSpeech speak = null;// ����tts
	Button speakbt = null;// tts������ť
	String speakstr = "���ã��ձ��ƽ�Ͷ�����չ���,���ల��������";// �����ı�

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

		speak = new TextToSpeech(this, this);// newֻ�ǣ�ϵͳ�����tts�ĳ�ʼ������onInit()������Ҫ��дonInit()����

		search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				input = et.getText().toString();
				// ���ڹ��̵�resĿ¼�����½�һ��xml�ļ��У���cet4.xml�Ͻ�ȥ
				XmlResourceParser xrp = getResources().getXml(R.xml.cet4);// ����һ��XML��Դ������

				try {
					StringBuilder strbuilder = new StringBuilder("");
					while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {// ����û��XML�ĵ�����ʱ
						if (xrp.getEventType() == XmlResourceParser.START_TAG) {// ��������ʼ��ǩʱ,�����ӱ�ǩ���ӱ�ǩ��������б�ǩ
							String tagname = xrp.getName();// ��ȡ��ǩ������

							if (tagname.equals("word")) {// ȡ������
								match = xrp.nextText();// ��ȡ��ǩ�������ݣ�Ҳ�����������

								if (match.equals(input)) {// ���ȡ������������ʺ�����ĵ������
									xrp.next();// ��ȡ�����һ����ǩ��Ҳ����<trans>����
									strbuilder.append(xrp.nextText());// ȡ������
									display.setText(strbuilder);// ��ʾ����
									speakstr = match;// ��ֵ�������ı�
									break;

								}

							}

						}
						xrp.next();// ��ȡ��һ����ǩ

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
		speak.setLanguage(Locale.US);// ��ʼ��TTS�������������ΪUSӢ��

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (speak != null)
			speak.shutdown();// �ر�tts����
	}
	
	public void  voide2text(View view) {
		Toast.makeText(getApplicationContext(), R.string.voide2text, 1).show();
		startActivity(new Intent(getApplicationContext(), Google_STTActivity.class));
	}
}