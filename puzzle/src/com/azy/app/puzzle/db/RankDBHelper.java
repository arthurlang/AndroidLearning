package com.azy.app.puzzle.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class RankDBHelper extends SQLiteOpenHelper {
	
	
	// SQLite数据库语句和mysql 数据库的sql语句相同。
	
	// limit ? offset ?   
    
	// null / integer / real? 
	
	// 
	
	/**
	 * TEXT	数值型数据在被插入之前，需要先被转换为文本格式，之后再插入到目标字段中。
	 * 	NUMERIC	当文本数据被插入到亲缘性为NUMERIC的字段中时，如果转换操作不会导致数据信息丢失以及完全可逆，
	 * 那么SQLite就会将该文本数据转换为INTEGER或REAL类型的数据，如果转换失败，SQLite仍会以TEXT方式存储该数据。
	 * 对于NULL或BLOB类型的新数据，SQLite将不做任何转换，直接以NULL或BLOB的方式存储该数据。需要额外说明的是，
	 * 对于浮点格式的常量文本，如"30000.0"，如果该值可以转换为INTEGER同时又不会丢失数值信息，那么SQLite就会将其转换为INTEGER的存储方式。
	 * INTEGER	对于亲缘类型为INTEGER的字段，其规则等同于NUMERIC，唯一差别是在执行CAST表达式时。
	 * REAL	其规则基本等同于NUMERIC，唯一的差别是不会将"30000.0"这样的文本数据转换为INTEGER存储方式。
	 * NONE	不做任何的转换，直接以该数据所属的数据类型进行存储。　
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
		
		//db.execSQL(INFO_FK_CREATE);  //建立外键
		
        db.execSQL("insert into Dif values(1,'简单')");   //  程序中插入数据的时候，不适用冒号，否者，程序报错！！！！！！！！！！！！！！！
        db.execSQL("insert into Dif values(2,'复杂')");
        db.execSQL("insert into Dif values(3,'困难')");
        
        db.execSQL("insert into Info values(1,'zs',3,10,3)");   //  程序中插入数据的时候，不适用冒号，否者，程序报错！！！！！！！！！！！！！！！
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
		
		this.close();//关闭所有打开的数据库对象
		
	}

}
