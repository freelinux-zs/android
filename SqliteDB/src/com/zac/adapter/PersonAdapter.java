package com.zac.adapter;

import java.util.List;

import com.zac.db.R;
import com.zac.domain.Person;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PersonAdapter extends BaseAdapter {
	private List<Person> persons;
	private int resource; //�󶨽���
	private LayoutInflater inflater;
	
	public PersonAdapter(Context context,List<Person> persons, int resource) {
		this.persons = persons;
		this.resource = resource;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return persons.size(); //��������
	}

	@Override
	public Object getItem(int position) {
		return persons.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView nameView  = null;
		TextView phoneView = null;
		TextView amountView = null;
		if(convertView == null) {
			convertView = inflater.inflate(resource, null);  //���ɽ������
			
			nameView = (TextView) convertView.findViewById(R.id.name);
			phoneView = (TextView) convertView.findViewById(R.id.phone);
			amountView = (TextView) convertView.findViewById(R.id.amount);
			
			ViewCache vCache = new ViewCache();
			vCache.nameView = nameView;
			vCache.phoneView = phoneView;
			vCache.amountView = amountView;
			
			convertView.setTag(vCache);
		}else {
			ViewCache vCache = (ViewCache)convertView.getTag();
			nameView = vCache.nameView;
			phoneView = vCache.phoneView;
			amountView = vCache.amountView;
		}
		
		Person person = persons.get(position);
		
		//���ݰ�
		nameView.setText(person.getName());
		phoneView.setText(person.getPhone());
		amountView.setText(person.getAmount().toString());
		
		return convertView;
	}
	
	private final class ViewCache {
		public TextView nameView;
		public TextView phoneView;
		public TextView amountView;
		
	}

}
