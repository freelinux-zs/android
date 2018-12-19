package com.zac.db;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zac.adapter.PersonAdapter;
import com.zac.domain.Person;
import com.zac.service.PersonService;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


public class MainActivity extends Activity {
	private ListView listView;
	private PersonService personService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		personService = new PersonService(getApplicationContext());
		
		setContentView(R.layout.activity_main);
		listView = (ListView)this.findViewById(R.id.listView);
		listView.setOnItemClickListener(new ItemClickListener());
		show2();
	}
	
	private final class ItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			ListView lview =(ListView) parent;
			
			//方法2
			Cursor cursor = (Cursor)lview.getItemAtPosition(position);
			int personid  = cursor.getInt(cursor.getColumnIndex("_id"));
			int amount = cursor.getInt(cursor.getColumnIndex("amount"));
			Toast.makeText(getApplicationContext(), personid+"", Toast.LENGTH_SHORT).show();;
			Toast.makeText(getApplicationContext(), amount+"", Toast.LENGTH_SHORT).show();;
			/*方法 1/3*/
			//Person person = (Person)lview.getItemAtPosition(position);	
			//Toast.makeText(getApplicationContext(), person.getAmount().toString(), Toast.LENGTH_SHORT).show();
		}
		
	}
//	
//	public void onClickbutton(View v) {
//		Log.i(tag, "onClickbutton");
//		creatDb();
//	}
//	
//	public  void creatDb() {
//		DBOpenHelper dbOpenHelper = new DBOpenHelper(getApplicationContext());
//		dbOpenHelper.getWritableDatabase();
//	}
//	
//	public void saveqlit(View v) {
//		Log.i(tag, "saveqlit");
//		PersonService server = new PersonService(getApplicationContext());
//		for(int i = 0; i < 20; i++) {
//			Person person = new Person("zhangxx"+i,"1358454521"+i,100+i);
//			server.save(person );
//		}
//	}
//	
//	@SuppressLint("UseValueOf")
//	public void findSQlit(View v)
//	{
//		Log.i(tag, "findSQlit");
//		PersonService server = new PersonService(getApplicationContext());
//		Integer id = new Integer(1);
//		Log.i(tag, "findSQlit id = "+id);
//		Person person = server.find(id);
//		if(person == null) {
//			Log.i(tag, "null");
//		}else {
//			Log.i(tag, person.toString());
//		}
//	}
//	
//	@SuppressLint("UseValueOf")
//	public void updateSqlit(View v) {
//		Log.i(tag, "updateSqlit");
//		PersonService server = new PersonService(getApplicationContext());
//		Integer id = new Integer(1);
//		Person person = server.find(id);
//		if (person == null)
//		{
//			return;
//		}else {
//			Log.i(tag, person.toString());
//			person.setName("zhangxiaoxiao");
//			server.update(person);
//			Log.i(tag, person.toString());
//		}
//
//	}
//	
//	public void countSqlit(View v) {
//		PersonService server = new PersonService(getApplicationContext());
//		long ret = server.getCount();
//		Toast.makeText(getApplicationContext(), ret+"", Toast.LENGTH_SHORT).show();
//	}
//	
//	public void onClickScrollDate(View v) {
//		PersonService server = new PersonService(getApplicationContext());
//		List<Person> persons = server.getScrollData(0, 5);
//		for(Person person: persons)	{
//			Log.i(tag, person.toString());
//		}
//	}
//	
//	@SuppressLint("UseValueOf")
//	public void onClickDelect(View v) {
//		PersonService server = new PersonService(getApplicationContext());
//		Integer id = new Integer(1);
//		server.delect(id);
//	}
//	
//	public void onClickPayment(View v) {
//		PersonService server = new PersonService(getApplicationContext());
//		server.payment();
//	}
	/*自定义适配器*/
	private void show3() {
		List<Person> persons = personService.getScrollData(0, 20);
		PersonAdapter adapter = new PersonAdapter(getApplicationContext(), persons, R.layout.item);
		listView.setAdapter(adapter);
	}

	private void show2() {
		Cursor cursor = personService.getCursorScrollData(0, 20);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),R.layout.item, cursor,  new String[]{"name","phone","amount"}, new int[] {R.id.name, R.id.phone, R.id.amount},
										CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		listView.setAdapter(adapter);
	}

	private void show() {
		List<Person> persons = personService.getScrollData(0, 20);
		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		for(Person person :persons) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("name", person.getName());
			item.put("phone", person.getPhone());
			item.put("id", person.getId());
			item.put("amount", person.getAmount());
			data.add(item);
		}
		SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), data, R.layout.item, new String[]{"name","phone","amount"}, 
				new int[] {R.id.name, R.id.phone, R.id.amount});
		listView.setAdapter(adapter);
	}
}
