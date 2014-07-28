package com.azy.app.puzzle.sprite;

import android.graphics.RectF;

public class Block {
	public int xLine,yLine;
	public int id;//画图片数组中的第几块图
	public float x,y;//块的左上角的坐标
	
	public RectF myRect;//矩形－－－碰撞区域
	
	public Block(int level,int id){
		xLine=id/level;  //获取格子的行数0~..
		yLine=id%level;  //获取格子的列数轴
		this.id=id;
		x=10+yLine*(ImageRect.rectW+1);//定位到x横坐标
		y=120+xLine*(ImageRect.rectH+1);//定位到y纵坐标
		
		myRect=new RectF(x, y, x+ImageRect.rectW, y+ImageRect.rectH);
	}
	
	public boolean onClick(float touchX,float touchY){
		return myRect.contains(touchX, touchY);
	}
	
}
