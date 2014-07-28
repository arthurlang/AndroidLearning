package com.azy.app.puzzle.sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class BlockGroup {
	public ImageRect[] images;//ͼƬԪ��
	public Block[][] blocks;
	
	public List<int[]>steps = new ArrayList<int[]>();
	int []array = new int[2];
	public int level;
	public int blockNums;
	private int whiteBox;
	private int rand;
	private Random r;
	
	public BlockGroup(Bitmap currentImg,int level){
		this.level=level;
		blockNums=level*level;
		images=new ImageRect[blockNums];
		for(int i=0;i<blockNums;i++){
			images[i]=new ImageRect(currentImg, level, i);//��ԭʼ��ͼ�и��ΪblockNums��Сͼ
		}
		
		blocks=new Block[level][level];
		for(int i=0;i<blockNums;i++){
			blocks[i/level][i%level]=new Block(level, i);//�и�ΪblockNums��С��
		}
		
		r = new Random();
	}
	
	//����
	public void disrupting(int number){
		for(int j=0;j<number;j++){//1)ÿ֡����num��autoChange()������
			chaos1time();
		}
	}
	
	public void chaos1time() {
		whiteBox = blockNums-1;
		for(int i=0;i<blockNums;i++){
			Block bl = blocks[i/level][i%level];
			if(bl.id == whiteBox){
				while(true){
					rand = r.nextInt(4);
					switch(rand){
					    case 0:
                            if(bl.yLine>0){
    					    	int temp = bl.id;
    					    	bl.id = blocks[bl.xLine][bl.yLine-1].id;
    					    	blocks[bl.xLine][bl.yLine-1].id = temp;
    					    	return ;
                            }
                            break;
					    case 1:
					    	if(bl.yLine<level-1){
	   					    	int temp = bl.id;
    					    	bl.id = blocks[bl.xLine][bl.yLine+1].id;
    					    	blocks[bl.xLine][bl.yLine+1].id = temp;
    					    	return ;
					    	}
					    	break;
					    case 2:
					    	if(bl.xLine>0){
	   					    	int temp = bl.id;
    					    	bl.id = blocks[bl.xLine-1][bl.yLine].id;
    					    	blocks[bl.xLine-1][bl.yLine].id = temp;
    					    	return ;
					    	}					    	
					    	break;
					    case 3:
					    	
					    	if(bl.xLine<level-1){
	   					    	int temp = bl.id;
    					    	bl.id = blocks[bl.xLine+1][bl.yLine].id;
    					    	blocks[bl.xLine+1][bl.yLine].id = temp;
    					    	return ;
					    	}	
                            break;
					}
				}
			}
		}
	}
	
	
	
	//����
	public void paint(Canvas canvas,Paint paint){
		for(int i=0;i<blockNums;i++){
			Block b=blocks[i/level][i%level];//��ȡ�������еĿ�
			images[b.id].paint(canvas, b.x, b.y, paint);//��ÿһ��С���У����Ƴ�ÿһ��СͼƬ
		}
	}
	
	//�������¼�
	public boolean onClick(float touchX, float touchY) {
		// TODO Auto-generated method stub
		for(int i=0;i<blockNums;i++){
			Block b=blocks[(i/level)][(i%level)];
			if(checkBlock(b,touchX,touchY)){//ѭ��Ѱ�ҵ������ͼƬ��
				int re = checkBlockMovable(b);

				if(re>0){//------------------------��֤�ƶ������Ƿ�ʵ��
					
					switch (re) {
					case 1:
						int yline2 = b.yLine-1;
						swap(blocks,b.xLine,b.yLine,b.xLine,yline2);
						break;
						
					case 2:
						yline2 = b.yLine+1;
						swap(blocks,b.xLine,b.yLine,b.xLine,yline2);
						break;
						
					case 3:
						int xline2 = b.xLine-1;
						swap(blocks,b.xLine,b.yLine,xline2,b.yLine);
						break;
						
					case 4:
						xline2 = b.xLine+1;
						swap(blocks,b.xLine,b.yLine,xline2,b.yLine);
						break;

					default:
						break;
					}

					return checkWin();
				}				
				return false;
			}
		}
		return false;
	}

	private boolean checkBlock(Block b, float touchX, float touchY) {
		return b.onClick(touchX, touchY)?true:false;
	}

	private int checkBlockMovable(Block b) {
		// TODO Auto-generated method stub
		if(b.yLine>0 && (whiteBox==blocks[b.xLine][b.yLine-1].id)){//���
			addStep(b,blocks[b.xLine][b.yLine-1]);
            return 1;
		}
		
		if(b.yLine<level-1 && (whiteBox==blocks[b.xLine][b.yLine+1].id)){//�ұ�
			addStep(b,blocks[b.xLine][b.yLine+1]);
	        return 2;
		}
		
		if(b.xLine>0 && (whiteBox==blocks[b.xLine-1][b.yLine].id)){//�ϱ�
			addStep(b,blocks[b.xLine-1][b.yLine]);
	        return 3;
		}
		
		if(b.xLine<level-1 && (whiteBox==blocks[b.xLine+1][b.yLine].id)){//�±�
			addStep(b,blocks[b.xLine+1][b.yLine]);
	        return 4;
		}
		return -1;
	}

	private void swap(Block[][] blocks2, int xLine, int yLine, int xLine2, int yLine2) {
		// TODO Auto-generated method stub
		int temp = blocks2[xLine][yLine].id;
		blocks2[xLine][yLine].id = blocks2[xLine2][yLine2].id;
		blocks2[xLine2][yLine2].id = temp;
	}

	private boolean checkWin() {
		// TODO Auto-generated method stub
		boolean win = true;
	    for(int id=0;id<blockNums;id++)
	    {
	    	Block block = blocks[id/level][id%level];
	    	if(id != block.id){
	    		win = false;
	    		break;
	    	}
	    }
		return win;
	}

	
	public int[] getMap() {
		// TODO Auto-generated method stub
		int temp[] = new int[blockNums];
		for(int j=0;j<blockNums;j++){
			temp[j] = blocks[j/level][j%level].id;
		}
		return temp;
	}

	public void setMap(int[] startMap2) {
		for(int j=0;j<blockNums;j++){
			blocks[j/level][j%level].id = startMap2[j];
		}
	}

	public void replayoneStep(int i, int j) {
		// TODO Auto-generated method stub
		int temp = blocks[i/level][i%level].id ;
		blocks[i/level][i%level].id = blocks[j/level][j%level].id;
		blocks[j/level][j%level].id = temp;
	}
	
	public void addStep(Block b1,Block b2){
		int temp[] = new int[2];
		temp[0]=b1.xLine*level+b1.yLine;
		temp[1]=b2.xLine*level+b2.yLine;
		steps.add(temp);
	}

	//getmap
	
	
	//setmap
	
	
	//addstep(){}
	
	
	//replayoneStep(){}
	
	
	
	
	
	
	
	
	
	
}
