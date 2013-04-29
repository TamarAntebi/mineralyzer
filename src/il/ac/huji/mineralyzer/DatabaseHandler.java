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
        addItem("אוכרה", "הופעה אבקתית של המינרל לימוניט", "Ochre.jpg");
        addItem("חרסית", "מינרלים יציבים, תוצרים של בלייה או של פעולת תמיסות מימיות חמות על פלדספטים  ומינרלים אחרים (אך לא קוורץ). נפוץ מאד בסלעי משקע. המינרל מאופיין בגביש זעיר הבנוי מסיליקה(SiO2)  ואלומינה(Al2O3)  ובנטייה לספוח מים.", "claystone.jpg");
        addItem("קאוליניט", "נוצר עקב בליה כימית של מינרלים אלומו־סיליקטיים הכוללים אלומיניום דוגמת פצלת השדה. בנוי משתי שכבות: שכבה אחת בנויה מטטרהדרונים של סיליקה. שלושה מתוך ארבעת אטומי החמצן שבסיליקה נמצאים במישור השכבה ומתחברים לאטומי חמצן של הטטרהדרונים האחרים באותה שכבה ויוצרים בכך רשת של משושים. אטום החמצן הרביעי בסיליקה מתקשר עם השכבה השנייה אשר בנויה מאוקטהדרונים שבמרכזם אלומיניום המוקפים באטומים של חמצן או בקבוצת ההידרוקסיל. (OH)  ", "kaolinite.jpg");
        addItem("גבס", "מינרל רך מאוד המורכב מסידן וגופרית והרכבו הכימי הוא CaSO4•2H2O. כאשר מחממים את הגבס מעל ל-150 מעלות צלזיוס מתאיידים 75% מהמים האגורים במבנה הכימי שלו.", "geves.jpg");
        addItem("מוסקוביט", "נמצא בסלעי יסוד, סלעים מותמרים וסלעי משקע. למוסקוביט תפקיד משמעוי ביצירה של ההופעה הפצלית של סלעים מותמרים כצפחות. משקעים קלאסטיים שמקורם בסלע קריסטליני שלא עברו בליה או הסעה משמעותית בדרך כלל מכילים מוסקוביט, לכן מוסקוביט הוא מינרל נפוץ גם באבן החול ארקוזה ועוד.", "Muscovite.jpg");
        addItem("ביוטיט", "מינרל סיליקטי נפוץ המורכב מאשלגן, מגנזיום, ברזל ואלומיניום. מכונה לעתים נציץ ברזל ומצוי בגרניט ובסלעים המותמרים גנייס ושיסט. מתקלף בקלות לפתיתים אלסטיים. לעתים מוצאים יריעות גדולות שלו במיוחד בעורקים בתוך פגמטיטים. גבישים קטנים שעברו בליה מקבלים גוון זהוב מנצנץ ולכן הם אחד מסוגי זהב השוטים שהטעה רבים.", "Biotite.jpg");
	}
//מינרל רך מאוד המורכב מסידן וגופרית והרכבו הכימי הוא CaSO4•2H2O. כאשר מחממים את הגבס מעל ל-150 מעלות צלזיוס מתאיידים 75% מהמים האגורים במבנה הכימי שלו.
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
