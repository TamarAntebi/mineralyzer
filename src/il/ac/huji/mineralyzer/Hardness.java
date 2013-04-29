package il.ac.huji.mineralyzer;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Hardness extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hardness);
		
		ListView hardList=(ListView)findViewById(R.id.hardnessList);
		
		List<String> courses = new ArrayList<String>();
        courses.add("���� �������");
        courses.add("���� �����");
        courses.add("���� ���� �����");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
        			this,
        			android.R.layout.simple_list_item_1,
        			courses
        			);
		hardList.setAdapter(adapter);
		
		hardList.setOnItemClickListener(new OnItemClickListener() {
		
			@Override
			public void onItemClick(AdapterView<?> parent, View arg1, int position,
					long arg3) {
				//open new intent...
				Intent resultIntent = new Intent();
				
				switch (position) {
				case 0:
					//������
					setResult(RESULT_OK, resultIntent);
					resultIntent.putExtra("hard", "���� �������");
					break;
				case 1:
					//����
					setResult(RESULT_OK, resultIntent);
					resultIntent.putExtra("hard", "���� �����");
					break;
				case 2:
					//���� ����
					setResult(RESULT_OK, resultIntent);
					resultIntent.putExtra("hard", "���� ���� �����");
					break;
				default:
					setResult(RESULT_CANCELED);
					break;
				}
				finish();
					
			}
			
		});
		
		

	}
}
