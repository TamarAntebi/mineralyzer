package il.ac.huji.mineralyzer;

import java.io.IOException;
import java.io.InputStream;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MineralDisplay extends Activity{
	SQLiteDatabase db;

	Activity _activity=this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mineral);
		//mineral DB
		DatabaseHandler helper = new DatabaseHandler(this);
		db = helper.getWritableDatabase();

		//views
		TextView minerName=(TextView)findViewById(R.id.name);
		ImageView pic=(ImageView)findViewById(R.id.pic);
		TextView minerdesc=(TextView)findViewById(R.id.desc);
		//get the mineral name
		Intent intent = getIntent();
		String name=intent.getStringExtra("mineral");
		//setting the views
		minerName.setText(name);
		//set image
		AssetManager ass = getAssets();
		String path="images/";
		Cursor cursor = db.rawQuery("select DISTINCT "+"PICTURE"+" from MINERALDESC where NAME = ?", new String[] {name});
		if(!cursor.isAfterLast()){
			cursor.moveToFirst();
			String entry2 =cursor.getString(cursor.getColumnIndex("PICTURE"));
			path+=entry2;
			try {
				InputStream img=ass.open(path);
				Bitmap bit = BitmapFactory.decodeStream(img);
				pic.setImageBitmap(bit);
				System.out.println("in pic");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//set description
		 cursor = db.rawQuery("select DISTINCT "+"DESCRIPTION"+" from MINERALDESC where NAME = ?", new String[] {name});
		 if(!cursor.isAfterLast()){
		 cursor.moveToFirst();
			String entry3 =cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
		minerdesc.setText(entry3);
		 }
		db.close();
		//TODO return
		//	    Intent resultIntent = new Intent();

		//		setResult(RESULT_OK, resultIntent);
		//					resultIntent.putExtra("mineral", minerl);
		//		finish();
	}




}
