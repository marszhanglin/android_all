package mars.all.activity.mediaplay;

import mars.all.R;
import mars.all.activity.base.BaseActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
/**
 * ��������   ����������ÿ����ĵ��е�ͼ��
 * @author EVECOM-PC
 *
 */
public class MediaActivity extends BaseActivity {
	// ������
	private MediaPlayer mediaPlayer;
	// ��λ��
	private int currentPosition=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.media_activity);
		mediaPlayer=MediaPlayer.create(MediaActivity.this, R.raw.imissyou);
		
		//���ò�����ϼ����ӿ�  �������²���һ��
		mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer arg0) {
				Log.v("mars","mediaPlayer.getCurrentPosition:"+mediaPlayer.getCurrentPosition());
				//���ؿ�ʼλ�����²���
				mediaPlayer.seekTo(0);
				mediaPlayer.start();
			}
		});
	}

	 

	

	
	@Override
	protected void onPause() {
		super.onPause();
		//����ת������ҳ��ʱ��ͣ��������  
		mediaPlayer.pause();
		//��ס��ǰλ��
		currentPosition=mediaPlayer.getCurrentPosition();
		Log.v("mars", "onPause"+currentPosition);
	}

	@Override
	protected void onRestart() {
		Log.v("mars", "onRestart"+currentPosition);
		if(null!=mediaPlayer){
			if(currentPosition!=0){
				mediaPlayer.seekTo(currentPosition);
				mediaPlayer.start();
			}
		}
		super.onRestart();
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.v("mars", "onResume"+currentPosition);
	}

	@Override
	protected void onStart() {
		 if(null!=mediaPlayer){
			mediaPlayer.start();
			Log.v("mars", "start"+currentPosition);
		} 
		super.onStart();
		Log.v("mars", "onStart"+currentPosition);
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.v("mars", "onStop"+currentPosition);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(null!=mediaPlayer){
			mediaPlayer.stop();
			mediaPlayer.release();
		}
		Log.v("mars", "onDestroy"+currentPosition);
	}
	
	public void btnonclick(View view){
		Intent intent=new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		Uri uri=Uri.parse("http://www.baidu.com");
		intent.setData(uri);
		startActivity(intent);
	} 
}
