package il.ac.huji.mineralyzer;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class About extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		//using the same UI
		TextView text=(TextView)findViewById(R.id.tank);
		//		Intent intent = getIntent();
//		Button tank=(Button)findViewById(R.id.button1);
//		tank.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//
//				Intent resultIntent = new Intent();
//				setResult(RESULT_OK, resultIntent);
//				finish();
//			}
//		});
	}
}
