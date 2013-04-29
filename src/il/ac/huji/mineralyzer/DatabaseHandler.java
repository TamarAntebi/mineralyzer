package il.ac.huji.mineralyzer;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper 
{
	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "DB";
 
    // Contacts table name
    private static final String TABLE_MINERAL = "MINERALDESC";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "_ID";
    private static final String KEY_NAME = "NAME";
    private static final String KEY_DESC = "DESCRIPTION";
    private static final String KEY_PIC = "PICTURE";

	private SQLiteDatabase _db;
    
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
	@Override
	public void onCreate(SQLiteDatabase db) {
		_db=db;
		String CREATE_MINERALS_TABLE = "CREATE TABLE " + TABLE_MINERAL + " (" + 
				KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				KEY_NAME + " TEXT, " +
				KEY_DESC + " TEXT, " +
                KEY_PIC + " TEXT" + ")";
        _db.execSQL(CREATE_MINERALS_TABLE);
        
        //insert values
        addItem("�����", "����� ������ �� ������ �������", "Ochre.jpg");
        addItem("�����", "������� ������, ������ �� ����� �� �� ����� ������ ������ ���� �� ��������  �������� ����� (�� �� �����). ���� ��� ����� ����. ������ ������� ����� ���� ����� �������(SiO2)  ��������(Al2O3)  ������� ����� ���.", "claystone.jpg");
        addItem("��������", "���� ��� ���� ����� �� ������� ��������������� ������� ��������� ����� ���� ����. ���� ���� �����: ���� ��� ����� ����������� �� ������. ����� ���� ����� ����� ����� �������� ������ ������ ����� �������� ������ ���� �� ����������� ������ ����� ���� ������� ��� ��� �� ������. ���� ����� ������ ������� ����� �� ����� ������ ��� ����� ������������ ������� ��������� ������� ������� �� ���� �� ������ ����������. (OH)  ", "kaolinite.jpg");
        addItem("���", "����� �� ���� ������ ����� ������� ������ ����� ��� CaSO4�2H2O. ���� ������ �� ���� ��� �-150 ����� ������ �������� 75% ����� ������� ����� ����� ���.", "geves.jpg");
        addItem("��������", "���� ����� ����, ����� ������� ����� ����. ��������� ����� ������ ������ �� ������ ������ �� ����� ������� ������. ������ �������� ������ ���� ��������� ��� ���� ���� �� ���� �������� ���� ��� ������ ��������, ��� �������� ��� ����� ���� �� ���� ���� ������ ����.", "Muscovite.jpg");
        addItem("������", "����� ������� ���� ������ ������, �������, ���� ����������. ����� ����� ���� ���� ����� ������ ������� �������� ����� �����. ����� ����� ������� �������. ����� ������ ������ ������ ��� ������ ������� ���� ��������. ������ ����� ����� ���� ������ ���� ���� ����� ���� �� ��� ����� ��� ������ ����� ����.", "Biotite.jpg");
	}
//����� �� ���� ������ ����� ������� ������ ����� ��� CaSO4�2H2O. ���� ������ �� ���� ��� �-150 ����� ������ �������� 75% ����� ������� ����� ����� ���.
	//
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MINERAL);
 
        // Create tables again
        onCreate(db);
	}
	
	// Adding new item
	boolean addItem(String name, String desc, String pic) 
	{
//		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, name);
		values.put(KEY_DESC, desc);
		values.put(KEY_PIC, pic);
		
	    // Inserting Row
	    long result = _db.insert(TABLE_MINERAL, null, values);
//	    db.close();
	    return (result > -1);
	}
}
