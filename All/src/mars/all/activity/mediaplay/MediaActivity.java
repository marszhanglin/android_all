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
 * ��������
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
		mediaPlayer.start();
	}

	 

	

	
	@Override
	protected void onPause() {
		super.onPause();
		//����ת������ҳ��ʱ��ͣ��������  
		mediaPlayer.pause();
		//��ס��ǰλ��
		currentPosition=mediaPlayer.getCurrentPosition();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		if(null!=mediaPlayer){
			currentPosition=mediaPlayer.getCurrentPosition();
			if(currentPosition!=0){
				mediaPlayer.seekTo(currentPosition);
				mediaPlayer.start();
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onStart() {
		 if(null!=mediaPlayer){
			mediaPlayer.start();
			Log.v("mars", "start");
		} 
		super.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
		if(null!=mediaPlayer){
			mediaPlayer.stop();
		}
		
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(null!=mediaPlayer){
			mediaPlayer.release();
		}
		
	}
	
	public void btnonclick(View view){
		Intent intent=new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		Uri uri=Uri.parse("http://www.baidu.com");
		intent.setData(uri);
		startActivity(intent);
	} 
}
