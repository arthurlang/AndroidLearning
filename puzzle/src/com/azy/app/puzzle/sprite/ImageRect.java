package com.azy.app.puzzle.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class ImageRect {
	public static int rectW,rectH;//图片块的宽、高
	public Bitmap myRect;//图片对象
	public int xLine,yLine;//行、列位置
	
	public ImageRect(Bitmap currentImg,int level,int id){
		if(id==0){
			rectW=currentImg.getWidth()/level;
			rectH=currentImg.getHeight()/level;
		}		
		xLine=id/level;
		yLine=id%level;
		
		myRect=Bitmap.createBitmap(currentImg, yLine*rectW, xLine*rectH, rectW, rectH);//
		//最后一块变白块
	    if(id==level*level-1){
	    	Canvas c=new Canvas(myRect);
	    	c.drawColor(Color.WHITE);
	    }
	}
	
	public void paint(Canvas canvas,float drawX,float drawY,Paint paint){
		canvas.drawBitmap(myRect, drawX, drawY, paint);
	}
	
	
}
