
package il.ac.huji.mineralyzer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MineralList extends Activity{
	ListView mList;
	Activity _activity=this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mineralist);
		mList=(ListView)findViewById(R.id.minerals);
		Intent intent = getIntent();


		HashSet<String> Miner = ((HashSet<String>)intent.getSerializableExtra("minerals"));
		if(Miner.isEmpty()){
			Miner.add("אין מינרלים מתאימים, אנא בדוק את התכונות שהוכנסו");
		}
		ArrayList<String> t = new ArrayList<String>();
		for (Iterator<String> iterator = Miner.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			t.add(string);
			
		}
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//				this,
//				android.R.layout.simple_list_item_1,
//				t
//				);
		AdapterHelper adapter = new AdapterHelper(this, t);
		mList.setAdapter(adapter);

		mList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View arg1, int position,
					long arg3) {
				//get selested mineral
				String minerl=(String)mList.getItemAtPosition(position);
				//open intent to display mineral
				Intent intent = new Intent(_activity, MineralDisplay.class);
				intent.putExtra("mineral", minerl);
				startActivityForResult(intent, 1333);
			}
		});
		Button tank=(Button)findViewById(R.id.button1);
		tank.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				Intent resultIntent = new Intent();
				setResult(RESULT_OK, resultIntent);
				finish();
			}
		});
	

	}
}
