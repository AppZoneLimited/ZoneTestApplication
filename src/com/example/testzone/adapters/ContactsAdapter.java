package com.example.testzone.adapters;

import java.util.ArrayList;

import com.appzone.zone.orchestra.engine.datatypes.Contacts;
import com.example.testzone.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * @author Akapo Damilola F. [ helios66, fdamilola ]
 *
 * 
 */

public class ContactsAdapter extends ArrayAdapter<Contacts> {

	Context ctx;
	int res;
	ArrayList<Contacts> data;

	public ContactsAdapter(Context context, int resource,
			ArrayList<Contacts> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.ctx = context;
		this.res = resource;
		this.data = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;

		if (v == null) {
			LayoutInflater li = (LayoutInflater) this.ctx
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = li.inflate(res, parent, false);
		}

		Contacts cts = getItem(position);

		TextView name = (TextView) v.findViewById(R.id.name);
		TextView number = (TextView) v.findViewById(R.id.number);

		name.setText(cts.getContactName());

		number.setText(cts.getContactNumber());

		return v;
	}

	@Override
	public Contacts getItem(int position) {
		// TODO Auto-generated method stub
		return this.data.get(position);
	}

}
