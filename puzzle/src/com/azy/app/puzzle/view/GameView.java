package com.azy.app.puzzle.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.azy.app.puzzle.GameActivity;
import com.azy.app.puzzle.InputNameActivity;
import com.azy.app.puzzle.MenuActivity;
import com.azy.app.puzzle.R;
import com.azy.app.puzzle.SelectGameActivity;
import com.azy.app.puzzle.sprite.Block;
import com.azy.app.puzzle.sprite.BlockGroup;
import com.azy.app.puzzle.sprite.ImageRect;

public class GameView extends SurfaceView implements Runnable,SurfaceHolder.Callback{
	
	private final static int GAMESTATE_READY = 0, GAMESTATE_RUN = 1,GAMESTATE_WIN=2,GAMESTATE_SHOW=3,GAMESTATE_REPLAY=4;

	private Bitmap selectbig_bitmap;
	private Bitmap small_bitmap;
	private Bitmap time_bitmap;
	private Bitmap replay_bitmap;
	private Canvas canvas;
	private Paint  paint;
	private SurfaceHolder holder;
	private long startTime;
	private BlockGroup bg;
	private int myCount = 0;
	private int gameState;
	private int startMap[];
	private Typeface typeface;

	private RectF rect_small;

	private RectF rect_time;

	private RectF rect_repaly;

	private boolean flagrun = true;
	
	int count = 0;

	private int small_img_top;

	private int small_img_left;

	private int small_img_right;

	private int small_img_bottom;

	private int stepsize;

	private int replayNum;

	private int time;

	private float startRunText = 0;

	private float increment = GameActivity.ScreenW/3/9;
	
	private float viable = 0;
	
	public static long score;

	public GameView(Context context) {
		super(context);
		init();
	}
	
	private void init() {
		holder = getHolder();
		holder.addCallback(this); //监听 surfaceview
		selectbig_bitmap = BitmapFactory.decodeResource(getResources(), SelectGameActivity.imagesArray[SelectGameActivity.imageIndex]);     //?????SelectGameActivity.imageIndex
		small_bitmap = Bitmap.createScaledBitmap(selectbig_bitmap, 100, 100, true);
		small_img_right = small_img_left+small_bitmap.getWidth();
		small_img_bottom = small_img_top+small_bitmap.getHeight();
		rect_small=new RectF(small_img_left, small_img_top, small_img_right, small_img_bottom);
		time_bitmap =  BitmapFactory.decodeResource(getResources(), R.drawable.k);
		rect_time=new RectF(200, 10, 200+time_bitmap.getWidth(), 10+time_bitmap.getHeight());
		replay_bitmap =  BitmapFactory.decodeResource(getResources(), R.drawable.up);
		rect_repaly=new RectF(200, 60, 200+small_img_right, 10+small_img_bottom);
		
		canvas = null;
		paint = new Paint();
		gameState = GAMESTATE_READY;
		
		typeface = Typeface.createFromAsset(getResources().getAssets(), "font/comixheavy.ttf");
		
		bg = new BlockGroup(selectbig_bitmap,SelectGameActivity.levelIndex); //初始化地图         SelectGameActivity.levelIndex
	}
	


	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		new Thread(this).start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}

	@Override
	public void run() {
		while(flagrun){
			
			paint();//绘制图形       View
			
			try {
				updateState();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} 
			//状态机             Model    controller
		    
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

	private void paint() {
		// TODO Auto-generated method stub
		try {		
			canvas = holder.lockCanvas();
			if(canvas == null){
				return;
			}
			canvas.drawRGB(0, 0, 0);
			switch(gameState){
				case GAMESTATE_READY:
					readyInterupting();
				    break;
				case GAMESTATE_RUN:
					paintRun();
				    break;
				case GAMESTATE_SHOW:
					paintShow();
				    break;				    
				case GAMESTATE_WIN:
					
					paintWin();//显示成绩
					
				    break;
				    
				case GAMESTATE_REPLAY:
					paintReplay();
					bg.paint(canvas, paint);
				    break;				
		}
		} catch (Exception e) {
		}
		holder.unlockCanvasAndPost(canvas);
	}
	
	private void updateState() throws FileNotFoundException {
		switch(gameState){
			case GAMESTATE_READY:
				//gameState = GAMESTATE_RUN;
				int level = SelectGameActivity.levelIndex;
			    if(count<2){//10*level
			    	bg.disrupting(level);//打乱图片
			    	count++;
			    }else{
			    	bg.steps = new ArrayList<int[]>();
			    	startMap = bg.getMap();//保存刚打乱时候的顺序
			    	gameState = GAMESTATE_RUN;
			    	startTime = System.currentTimeMillis();       //记录开始游戏时间
			    }
			    break;
			case GAMESTATE_RUN:
			    break;
			case GAMESTATE_WIN:
					viable+=increment;
					startRunText = GameActivity.ScreenW - viable;  //GameActivity.ScreenW/3;
					
					if(startRunText<=0){
						
						flagrun = false;
						
						GameActivity.instance.gotoInputNameActivity();
						
						//关闭GameActivity
						
/*						boolean rtn = isCapableSaving();//判断是否符合存储记录的规则，如符合跳转到输入名称界面
						
						if(rtn){
							

						}*/
					}
			     break;
			case GAMESTATE_SHOW:
			     break;
			case GAMESTATE_REPLAY: //恢复绘图
			     break;
	    }
	}
	

/*	
	private Boolean isCapableSaving() throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		//判断一下积分是否进入前十
		byte[] readbuffer = new byte[1024];
		FileInputStream fileInputStream = InputNameActivity.context.openFileInput("hirank.and1");
		if(fileInputStream!=null){
			fileInputStream.read(readbuffer);
			fileInputStream.close();
			String ranks = readbuffer.toString();
			System.out.println("ranks=="+ranks);
			long seconds = (System.currentTimeMillis()-startTime)/1000;
			//是，弹出菜单让用户输入姓名保存新纪录
			Intent intent = new Intent(GameActivity.instance, InputNameActivity.class);
			GameActivity.instance.startActivity(intent);
		}
		return null;
	}*/

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		if(event.getAction()==MotionEvent.ACTION_UP){//控制点击屏幕是，只调用一次一下代码。
			float touchX = event.getX();
			float touchY = event.getY();
			
			switch(gameState){
			
				case GAMESTATE_READY:
				     break;
				case GAMESTATE_RUN:
					if(bg.onClick(touchX,touchY)){
						gameState = GAMESTATE_WIN;
						
						startRunText = GameActivity.ScreenW/3;//初始化文字的位置
						
						
					}
					if(rect_small.contains(touchX, touchY)){//判断点击区域
							gameState = GAMESTATE_SHOW;
					}else if(rect_repaly.contains(touchX, touchY)){
							stepsize = 0;
							replayNum = 0;
							time = 0;
							bg.setMap(startMap);
							gameState = GAMESTATE_REPLAY;
					}else if(rect_time.contains(touchX, touchY)){}
					break;
					
				case GAMESTATE_WIN:
				    break;
				case GAMESTATE_SHOW:
					gameState = GAMESTATE_RUN;
					break;
				case GAMESTATE_REPLAY://回放
				    break;
	    }
	}
		return true;
	}
	

	private void paintReplay() {
		// TODO Auto-generated method stub
		Log.e("HI", bg.steps.size()+"============================");
		if(bg.steps!=null && bg.steps.size()>0){

			stepsize = bg.steps.size();
			if(time<stepsize){
				replayNum++;
				if(replayNum<5)return;
				if(replayNum%2==0){
					int[] temp;
					temp = bg.steps.get(time);
					bg.replayoneStep(temp[0],temp[1]);
					time++;
				}
			}else{
				gameState = GAMESTATE_RUN;
			}
		}
	}

	private void paintShow() {
		canvas.drawBitmap(selectbig_bitmap, 10, 10, paint);
	}

	private void paintWin() {
		// TODO Auto-generated method stub
		//Paint paint = new Paint();
		
		paint.setColor(Color.YELLOW);
		
		paint.setTextSize(32);
		
		paint.setTypeface(typeface);
		
		String msg = "CONGRATULATIONS!YOU WIN!";
		
		canvas.drawBitmap(selectbig_bitmap,10,10,paint);
		
		canvas.drawText(msg , startRunText , 40+selectbig_bitmap.getHeight(), paint);
		
		//bg.paint(canvas, paint);    //开始绘制拼图的地图
		
	}

	private void readyInterupting() {
		// use the type face....
        //		canvas.drawBitmap(selectbig_bitmap, 0,100, paint);
		// -------------------------------------绘制文字----------------------
		StringBuffer sb = new StringBuffer();
		sb.append("disrupting ");
		switch(myCount%4){
			case 0: sb.append(". ");myCount++;break;
			case 1: sb.append(". . ");myCount++;break;
			case 2: sb.append(". . . ");myCount++;break;
			case 3: sb.append(". . . .");myCount++;break;
	    }
		
		//Paint paint = new Paint();
		
		paint.setColor(Color.YELLOW);
		
		paint.setTextSize(30);
		
		paint.setTypeface(typeface);
		
		canvas.drawText(sb.toString(),  50, 40, paint);
		
		//----------------------------------------绘制地图 -------------------------------------
		bg.paint(canvas, paint);   //开始绘制拼图的地图c
	}

	private void paintRun() {
		
//		canvas.drawBitmap(selectbig_bitmap, 0,100, paint);
		
		small_img_top = 10;
		
		small_img_left = 10;
		
		canvas.drawBitmap(small_bitmap,small_img_top, small_img_left, paint);
		
		canvas.drawBitmap(time_bitmap, 200, 10, paint);
		
		canvas.drawBitmap(replay_bitmap, 200, 60, paint);
		
		paint.setColor(Color.YELLOW);
		
		paint.setTextSize(40);
		
		canvas.drawText(getTime(), 270, 60, paint);
		
		Paint paint = new Paint();
		
		bg.paint(canvas, paint); //开始绘制拼图的地图
	}

	private String getTime() {
		// TODO Auto-generated method stub
		long seconds = (System.currentTimeMillis()-startTime)/1000;
		
		score = seconds;
		
		long hour = seconds/3600;
		long minutes = seconds/60%60;
		long leftseconds = seconds%60;
		StringBuffer timeBar = new StringBuffer();
		timeBar.append((hour>9?hour:("0"+hour))+":");
		timeBar.append((minutes>9?minutes:("0"+minutes))+":");
		timeBar.append((leftseconds>9?leftseconds:("0"+leftseconds))+"");
		System.out.println("time=="+timeBar.toString());
		return timeBar.toString();
	}
}
