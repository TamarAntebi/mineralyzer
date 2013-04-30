package il.ac.huji.mineralyzer;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Color extends Activity{
	Activity _activity=this;
	ListView cList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hardness);
		//using the same UI
		cList=(ListView)findViewById(R.id.hardnessList);
		Intent intent = getIntent();
		List<String> colors = (List<String>) intent.getSerializableExtra("colors");
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//				this,
//				android.R.layout.simple_list_item_1,
//				colors
//				);
		AdapterHelper adapter = new AdapterHelper(this, colors);
		cList.setAdapter(adapter);
		
		
		cList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View arg1, int position,
					long arg3) {
				//get the color that was clicked
				String color=(String)cList.getItemAtPosition(position);

				Intent resultIntent = new Intent();

				setResult(RESULT_OK, resultIntent);
				resultIntent.putExtra("color", color);
				finish();
			}

		});

	}
}
