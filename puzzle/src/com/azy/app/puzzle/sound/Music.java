package com.azy.app.puzzle.sound;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.AudioManager;
import android.media.MediaPlayer;

public class Music {
	public static boolean isMusicOn;//音乐开关的标志
	private static MediaPlayer player;
	private static int resid;
	private static Context context;
	public static int volume;
	
	//初始化
	public static void initMusic(Context context){
		Music.context=context;
		//load
		SharedPreferences preferences=context.getSharedPreferences("gamesetting", Context.MODE_PRIVATE);
		isMusicOn=preferences.getBoolean("music", true);
		volume=preferences.getInt("volume", 20);
		//设置音量
		AudioManager audioManager=(AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
	}
	
	//播放
	public static void play(Context context,int resid,boolean isLoop){
		if(isMusicOn==false){
			return;
		}
		if(player!=null && player.isPlaying()){
			if(Music.resid==resid){
				return;
			}else{

				player.stop();
				player.reset();
				player=null;
			}
		}
		Music.resid=resid;
		player=MediaPlayer.create(context, Music.resid); //Music.resid
		player.setLooping(isLoop);
		player.start();		
	}
	
	//停止
	public static void stop(){
		if(player!=null && player.isPlaying()){
			player.stop();
			player.release();
			player=null;
		}
	}
	
	//保存
	public static void save(){
		//偏好设置
		SharedPreferences preferences=context.getSharedPreferences("gamesetting", Context.MODE_PRIVATE);
		Editor editor=preferences.edit();
		editor.putBoolean("music", isMusicOn);
		editor.putInt("volume", volume);
		editor.commit();
	}	
}
