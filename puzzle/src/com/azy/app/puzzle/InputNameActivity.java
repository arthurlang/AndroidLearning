package com.azy.app.puzzle;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.azy.app.puzzle.db.RankDBHelper;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InputNameActivity extends Activity {

	private EditText editText;
	private TextView scoreText;
	private TextView levelText;
	public static Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		context = this;

		super.onCreate(savedInstanceState);
		
		long score = getIntent().getLongExtra("score", 0);
		
		long gamelevel = SelectGameActivity.levelIndex;

		iniWegit(score,gamelevel);
		
		
	}

	private void iniWegit(long score, long gamelevel) {
		// TODO Auto-generated method stub
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		LayoutInflater layoutFactory = LayoutInflater.from(this);
		
		View inputView = layoutFactory.inflate(R.layout.activity_save_input, null);
		
		setContentView(inputView);
		
		scoreText = (TextView) inputView.findViewById(R.id.scoreText);
		levelText = (TextView) inputView.findViewById(R.id.levelText);
		
		scoreText.setText("总分："+score);
		levelText.setText("难度："+gamelevel);
		
		// insert databases
		RankDBHelper sqliteHelper = null;
		SQLiteDatabase db = null;
		
		try {
			sqliteHelper = new RankDBHelper(this);
			db = sqliteHelper.openWritable();
			db.execSQL("insert into Info values(4,?,?,?,?)",new Object []{"swf",2,2,1});
			db.execSQL("insert into Info values(5,?,?,?,?)",new Object []{"lj",1,1,1});
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally{
			if(db.isOpen()){
				db.close();//关闭打开的db数据库对象
			}
		}
		
		// query databaseis
		Cursor cursor = null;
        try {
			sqliteHelper = new RankDBHelper(this);
			db = sqliteHelper.openReadable();
			cursor = sqliteHelper.fetchAllScores(db);
			if(cursor.moveToFirst()){
				System.out.println("=========== startManagingCursor(notesCursor)=="+cursor.getCount());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally{
			if(!cursor.isClosed()){
				cursor.close();
			}
			if(db.isOpen()){
				db.close();
			}
		}
        
        
		
		editText = (EditText)findViewById(R.id.playerName);
		Button button1 = (Button)findViewById(R.id.button1);
		
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				double score = 100.00;
				String nameText = editText.getText().toString();
				if(nameText==null || "".equals(nameText)){
					nameText = "无名氏";
				}
				byte[] buffer = new byte[1024];
				buffer = nameText.getBytes();
				
				//存放文件中。。。。。
				//方式一
/*				File fileDirectory = android.os.Environment.getExternalStorageDirectory();
				if(!fileDirectory.isDirectory()){
					fileDirectory.mkdir();
				}
				File file = new File(fileDirectory,"hirank.and1");
				if(!file.exists()){
					try {
						file.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				*/
				
				//方式二
				try {
					FileOutputStream fileOutputStream = context.openFileOutput("hirank.and1", 0);
					BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
					/*int i = 0;
					while(buffer[i]!='0'){
						System.out.println("--------"+buffer[i]);
						bos.write(buffer[i]);
					}*/
					bos.write(buffer);
					bos.flush();
					fileOutputStream.close();
					bos.close();
					System.out.println("-----------------------------write ends");
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
