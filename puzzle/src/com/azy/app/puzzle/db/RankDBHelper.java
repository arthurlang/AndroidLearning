package com.azy.app.puzzle.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class RankDBHelper extends SQLiteOpenHelper {
	
	
	// SQLite���ݿ�����mysql ���ݿ��sql�����ͬ��
	
	// limit ? offset ?   
    
	// null / integer / real? 
	
	// 
	
	/**
	 * TEXT	��ֵ�������ڱ�����֮ǰ����Ҫ�ȱ�ת��Ϊ�ı���ʽ��֮���ٲ��뵽Ŀ���ֶ��С�
	 * 	NUMERIC	���ı����ݱ����뵽��Ե��ΪNUMERIC���ֶ���ʱ�����ת���������ᵼ��������Ϣ��ʧ�Լ���ȫ���棬
	 * ��ôSQLite�ͻὫ���ı�����ת��ΪINTEGER��REAL���͵����ݣ����ת��ʧ�ܣ�SQLite�Ի���TEXT��ʽ�洢�����ݡ�
	 * ����NULL��BLOB���͵������ݣ�SQLite�������κ�ת����ֱ����NULL��BLOB�ķ�ʽ�洢�����ݡ���Ҫ����˵�����ǣ�
	 * ���ڸ����ʽ�ĳ����ı�����"30000.0"�������ֵ����ת��ΪINTEGERͬʱ�ֲ��ᶪʧ��ֵ��Ϣ����ôSQLite�ͻὫ��ת��ΪINTEGER�Ĵ洢��ʽ��
	 * INTEGER	������Ե����ΪINTEGER���ֶΣ�������ͬ��NUMERIC��Ψһ�������ִ��CAST���ʽʱ��
	 * REAL	����������ͬ��NUMERIC��Ψһ�Ĳ���ǲ��Ὣ"30000.0"�������ı�����ת��ΪINTEGER�洢��ʽ��
	 * NONE	�����κε�ת����ֱ���Ը������������������ͽ��д洢����
	 * 
	 */
	
/*    
	public RankDBHelper(Context context, String name, CursorFactory factory,
			int version) {
        
		ctx = context;
		// TODO Auto-generated constructor stub
	}
	
*/
/*	public void RankDBHelper(Context context, String name, CursorFactory factory,int version) {
		super(context, name, null, version);
		
	} */
    
	
	
    public RankDBHelper(Context context){
		super(context, DATABASE_NAME, null, 2);
		ctx = context;
    }

	private static final String DIF_TABLE_CREATE =
    		"create table Dif (dif_id integer primary key autoincrement, "
            + "difLevel text not null);";
    
    private static final String INFO_TABLE_CREATE =
    		"create table Info (info_id integer primary key autoincrement, "
    	            + "playerName text not null," + "rankNo integer not null,score integer not null,dif_id integer not null);";//forei key
    
    private static final String INFO_FK_CREATE = "ALTER TABLE Info ADD CONSTRAINT fk_dif_id"+"FOREIGN KEY (dif_id)"+"REFERENCES Dif(dif_id)";
    
	public static final String KEY_NAME = "playerName";

    public static final String KEY_RANK_ID = "info_id";
    
    private static final String DATABASE_NAME = "puzzle.db";
    
    private final Context ctx;
    
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL(DIF_TABLE_CREATE);
		
		db.execSQL(INFO_TABLE_CREATE);
		
		//db.execSQL(INFO_FK_CREATE);  //�������
		
        db.execSQL("insert into Dif values(1,'��')");   //  �����в������ݵ�ʱ�򣬲�����ð�ţ����ߣ����򱨴�����������������������������
        db.execSQL("insert into Dif values(2,'����')");
        db.execSQL("insert into Dif values(3,'����')");
        
        db.execSQL("insert into Info values(1,'zs',3,10,3)");   //  �����в������ݵ�ʱ�򣬲�����ð�ţ����ߣ����򱨴�����������������������������
        db.execSQL("insert into Info values(2,'ls',2,100,3)");
        db.execSQL("insert into Info values(3,'ww',1,10000,3)");
        
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
        db.execSQL("drop table if exists Dif");
        
        db.execSQL("drop table if exists Info");
        
        onCreate(db); 
	}
	
	public SQLiteDatabase openReadable(){
		return this.getReadableDatabase();
	}
	
	public SQLiteDatabase openWritable(){
		return this.getWritableDatabase();
	}
	
    public Cursor fetchAllScores(SQLiteDatabase db) {
    	return db.query("info", null, null, null, null, null, "score desc");
    }
	
	public void close(){
		
		this.close();//�ر����д򿪵����ݿ����
		
	}

}
