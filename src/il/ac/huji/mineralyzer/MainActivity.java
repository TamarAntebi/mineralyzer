package il.ac.huji.mineralyzer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Activity _activity=this;
	static Boolean init=false;
	SQLiteDatabase db;
	private Cursor cursor;
	private String hardSel;
	private String col;
	
//	@Override
//	protected void onDestroy()
//	{
//		
//		cursor.close();
//		db.close();
//		super.onDestroy();
//	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SqlHalper helper=new SqlHalper(this);
		db=helper.getWritableDatabase();

		//DEBUG
		//		cursor = db.query("mineral",
		//				new String[] { "_id", "name", "hard","color", "sf" },
		//				null, null, null, null, null);
		//
		//		cursor.moveToFirst();
		//		if(!cursor.isAfterLast())
		//		{   
		//			cursor.moveToFirst();
		//			do{  
		//				String entry1 = cursor.getInt(cursor.getColumnIndex("_id"))+cursor.getString(cursor.getColumnIndex("name"))+cursor.getString(cursor.getColumnIndex("hard"))+cursor.getString(cursor.getColumnIndex("color"))
		//						+cursor.getString(cursor.getColumnIndex("sf"));
		//
		//				Log.v("value:",entry1);
		//				cursor.moveToNext();
		//			}while(!cursor.isAfterLast());
		//
		//
		//
		//		}
		//DEBUG
		Button start=(Button)findViewById(R.id.start);
		start.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(_activity, Hardness.class);
				intent.putExtra("cursor", 2);
				startActivityForResult(intent, 1337);
			}
		});
		Button tank=(Button)findViewById(R.id.thanks);
		tank.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(_activity, Thanks.class);
				startActivityForResult(intent, 1341);
			}
		});
		Button about=(Button)findViewById(R.id.about);
		about.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(_activity, About.class);
				startActivityForResult(intent, 1342);
			}
		});
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//Activity- Hard- first
		if (requestCode == 1337 && resultCode == RESULT_OK) {
			hardSel=data.getStringExtra("hard");
			cursor=db.rawQuery("select DISTINCT "+"color"+" from mineral where hard = ? ", new String[] {hardSel});
			ArrayList<String> colors=new ArrayList<String>();
			cursor.moveToFirst();
			//get the color
			if(!cursor.isAfterLast())
			{   
				cursor.moveToFirst();
				do{  
					String entry2 =cursor.getString(cursor.getColumnIndex("color"));
					colors.add(entry2);
					cursor.moveToNext();
				}while(!cursor.isAfterLast());
			}
			//open 2 Activity color
			Intent intent = new Intent(_activity, Color.class);
			intent.putExtra("colors", colors);
			startActivityForResult(intent, 1338);
		}
		//Activity 2 COLOR
		else if (requestCode == 1338 && resultCode == RESULT_OK) {
			col=data.getStringExtra("color");
			cursor=db.rawQuery("select DISTINCT "+"sf"+" from mineral where hard = ? AND color = ? ", new String[] {hardSel,col });
			ArrayList<String> sf=new ArrayList<String>();
			cursor.moveToFirst();
			//get the color
			if(!cursor.isAfterLast())
			{   
				cursor.moveToFirst();
				do{  
					String entry2 =cursor.getString(cursor.getColumnIndex("sf"));
					sf.add(entry2);
					cursor.moveToNext();
				}while(!cursor.isAfterLast());
			}
			//open 3 activity Special Features(sf)
			Intent intent = new Intent(_activity, Special.class);
			intent.putExtra("sf", sf);
			startActivityForResult(intent, 1339);
		}
		//Activity 3- Special Features
		else if (requestCode == 1339 && resultCode == RESULT_OK) {
			ArrayList<String> sf=(ArrayList<String>) data.getSerializableExtra("sf");
			HashSet<String> minerals=new HashSet<String>();

			//query
			String select = "select * from mineral T0 ";
			String where = " where T0.hard=? and T0.color=? ";
			String[] specials=new String[sf.size()+2];
			specials[0]=hardSel;
			specials[1]=col;

			int counter = 1;
			for (Iterator<String> iterator = sf.iterator(); iterator.hasNext();){

				String special = (String) iterator.next();
				select += String.format(", mineral T%d", counter); 
				where += String.format(" and T%d.sf=? and T%d.name = T0.name ", counter,counter);
				specials[counter+1]=special;
				counter++;
			}
			for (int i = 0; i < specials.length; i++) {

			}
			
			cursor=db.rawQuery(select + where,specials);
			//get all minerals
			if(!cursor.isAfterLast())
			{   
				cursor.moveToFirst();
				do{  
					String entry2 =cursor.getString(cursor.getColumnIndex("name"));
					if(minerals.add(entry2)){
						}
					cursor.moveToNext();
				}while(!cursor.isAfterLast());
			}
			//open Activity minerals list
			Intent intent = new Intent(_activity, MineralList.class);
			intent.putExtra("minerals", minerals);
			startActivityForResult(intent, 1340);

		}
//		TODO close db

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
