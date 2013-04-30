package il.ac.huji.mineralyzer;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Special extends Activity{
	Activity _activity=this;
	ListView sList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.special);

		sList=(ListView)findViewById(R.id.specialList);
		Button getChoice = (Button)findViewById(R.id.getchoice);
		//make the list multiple choice
		sList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		Intent intent = getIntent();
		List<String> sf =  (List<String>) intent.getSerializableExtra("sf");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this,
				android.R.layout.simple_list_item_multiple_choice,
				sf
				);
//		AdapterHelper adapter = new AdapterHelper(this, sf);

		sList.setAdapter(adapter);

		getChoice.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ArrayList<String> selected = new ArrayList<String>();
				int cntChoice = sList.getCount();
				// get selected items
				SparseBooleanArray sparseBooleanArray = sList.getCheckedItemPositions();
				for(int i = 0; i < cntChoice; i++){
					if(sparseBooleanArray.get(i)) {
						selected.add(sList.getItemAtPosition(i).toString());
					}
				}
				//open new intent...
				Intent resultIntent = new Intent();
				resultIntent.putExtra("sf", selected);
				setResult(RESULT_OK, resultIntent);
				finish();
			}

		});

	}

}
