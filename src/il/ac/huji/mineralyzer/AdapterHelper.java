package il.ac.huji.mineralyzer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AdapterHelper  extends ArrayAdapter<String> {
	public AdapterHelper(
			Special activity, List<String> courses) {
		super(activity, android.R.layout.simple_list_item_multiple_choice, courses);
	}

	public AdapterHelper(Context activity, List<String> colors) {
		// TODO Auto-generated constructor stub

		super(activity, android.R.layout.simple_list_item_1, colors);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		String course = getItem(position);
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View view = inflater.inflate(R.layout.adpt, null);
		
		TextView txtName = (TextView)view.findViewById(R.id.hardnessList);
		txtName.setText(course);
		txtName.setTextColor(Color.WHITE);
//		txtName.setBackgroundColor(Color.WHITE);
		if(position%2==0){
			int red;
			int green;
			int blue;
			float[] hsv;
			txtName.setBackgroundColor(Color.parseColor("#004A7F"));
		}
		else{
			txtName.setBackgroundColor(Color.parseColor("#002046"));
		}
		return view;
	}
}