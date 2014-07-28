package com.azy.app.puzzle.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class ImageRect {
	public static int rectW,rectH;//ͼƬ��Ŀ���
	public Bitmap myRect;//ͼƬ����
	public int xLine,yLine;//�С���λ��
	
	public ImageRect(Bitmap currentImg,int level,int id){
		if(id==0){
			rectW=currentImg.getWidth()/level;
			rectH=currentImg.getHeight()/level;
		}		
		xLine=id/level;
		yLine=id%level;
		
		myRect=Bitmap.createBitmap(currentImg, yLine*rectW, xLine*rectH, rectW, rectH);//
		//���һ���׿�
	    if(id==level*level-1){
	    	Canvas c=new Canvas(myRect);
	    	c.drawColor(Color.WHITE);
	    }
	}
	
	public void paint(Canvas canvas,float drawX,float drawY,Paint paint){
		canvas.drawBitmap(myRect, drawX, drawY, paint);
	}
	
	
}
