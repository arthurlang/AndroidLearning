package com.azy.app.puzzle;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.azy.app.puzzle.sound.Music;

public class OptionActivity extends Activity implements OnClickListener,OnSeekBarChangeListener{
	
	private ImageView audio_status;
	private ImageView audio_on;
	private ImageView audio_off;
	private SeekBar seekBar1;
	private ImageView volum_down;
	private ImageView volum_up;
	private TextView btn_clear;
	private TextView btn_back;
	private AudioManager am;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_setting);
		
		initWidget();
		
		setClickLiseners();
		
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.audio_status:
			break;
		case R.id.audio_on:
			if(!Music.isMusicOn){
				Music.isMusicOn = true;
				Music.play(this, R.raw.heros, true);
				audio_status.setImageResource(R.drawable.audio_on);
				Music.save();
			}
			break;
		case R.id.audio_off:
			if(Music.isMusicOn){
				Music.isMusicOn = false;
				Music.stop();
				audio_status.setImageResource(R.drawable.audio_off);
				Music.save();
			}
			break;
		case R.id.volum_up:
			am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, 0);
			Music.volume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
			seekBar1.setProgress(Music.volume);
			Music.save();
			break;
		case R.id.volum_down:
			am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, 0);
			Music.volume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
			seekBar1.setProgress(Music.volume);
			Music.save();
			break;
		case R.id.audio_clear:
			break;
		case R.id.goback:
			finish();
			break;
		}
		
	}

	private void setClickLiseners() {
		// TODO Auto-generated method stub
		audio_on.setOnClickListener(this);
		audio_off.setOnClickListener(this);
		volum_up.setOnClickListener(this);
		volum_down.setOnClickListener(this);

		btn_clear.setOnClickListener(this);
		btn_back.setOnClickListener(this);
		seekBar1.setOnSeekBarChangeListener(this);
		
	}

	private void initWidget() {
		
		// TODO Auto-generated method stub
		audio_status = (ImageView)findViewById(R.id.audio_status);
		audio_on = (ImageView)findViewById(R.id.audio_on);
		audio_off = (ImageView)findViewById(R.id.audio_off);
		volum_up = (ImageView)findViewById(R.id.volum_up);
		volum_down = (ImageView)findViewById(R.id.volum_down);
		seekBar1  =(SeekBar)findViewById(R.id.seekBar1);
		
		btn_clear = (TextView)findViewById(R.id.audio_clear);
		btn_back = (TextView)findViewById(R.id.goback);
		
		btn_clear.setBackgroundColor(Color.YELLOW);
		btn_back.setBackgroundColor(Color.YELLOW);
		btn_back.setTextColor(Color.BLACK);
		btn_clear.setTextColor(Color.BLACK);
		
		//³õÊ¼»¯¿Õ¼ä×´Ì¬
		if(Music.isMusicOn){
			audio_status.setImageResource(R.drawable.audio_on);
		}else{
			audio_status.setImageResource(R.drawable.audio_off);
		}
		
		seekBar1.setMax(20);
		seekBar1.setProgress(Music.volume);         
		
		am = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
		
	}


	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		seekBar.setProgress(progress);
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

}
 