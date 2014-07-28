package com.azy.app.puzzle;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.azy.app.puzzle.sound.Music;

public class MusicActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music);
		
		Music.initMusic(MusicActivity.this);
		
		//Music.play(MusicActivity.this, R.raw.win, true);
		
		ImageButton startButton = (ImageButton)findViewById(R.id.ImageButton01);
		
		ImageButton stopButton = (ImageButton)findViewById(R.id.ImageButton02);
		
		startButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Music.play(MusicActivity.this, R.raw.win, true);
			}
		});
		
		stopButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Music.stop();
			}
		});
		
/*		private void gotoMusicActivity() {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MenuActivity.this,LisViewAdapterActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			finish();
		}*/
		
	}

}
 