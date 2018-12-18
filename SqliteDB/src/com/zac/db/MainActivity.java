package com.zac.db;

import java.util.List;

import com.zac.domain.Person;
import com.zac.service.DBOpenHelper;
import com.zac.service.PersonService;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String tag = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void onClickbutton(View v) {
		Log.i(tag, "onClickbutton");
		creatDb();
	}
	
	public  void creatDb() {
		DBOpenHelper dbOpenHelper = new DBOpenHelper(getApplicationContext());
		dbOpenHelper.getWritableDatabase();
	}
	
	public void saveqlit(View v) {
		Log.i(tag, "saveqlit");
		PersonService server = new PersonService(getApplicationContext());
		for(int i = 0; i < 20; i++) {
			Person person = new Person("zhangxx"+i,"1358454521"+i,100+i);
			server.save(person );
		}
	}
	
	@SuppressLint("UseValueOf")
	public void findSQlit(View v)
	{
		Log.i(tag, "findSQlit");
		PersonService server = new PersonService(getApplicationContext());
		Integer id = new Integer(1);
		Log.i(tag, "findSQlit id = "+id);
		Person person = server.find(id);
		if(person == null) {
			Log.i(tag, "null");
		}else {
			Log.i(tag, person.toString());
		}
	}
	
	@SuppressLint("UseValueOf")
	public void updateSqlit(View v) {
		Log.i(tag, "updateSqlit");
		PersonService server = new PersonService(getApplicationContext());
		Integer id = new Integer(1);
		Person person = server.find(id);
		if (person == null)
		{
			return;
		}else {
			Log.i(tag, person.toString());
			person.setName("zhangxiaoxiao");
			server.update(person);
			Log.i(tag, person.toString());
		}

	}
	
	public void countSqlit(View v) {
		PersonService server = new PersonService(getApplicationContext());
		long ret = server.getCount();
		Toast.makeText(getApplicationContext(), ret+"", Toast.LENGTH_SHORT).show();
	}
	
	public void onClickScrollDate(View v) {
		PersonService server = new PersonService(getApplicationContext());
		List<Person> persons = server.getScrollData(0, 5);
		for(Person person: persons)	{
			Log.i(tag, person.toString());
		}
	}
	
	@SuppressLint("UseValueOf")
	public void onClickDelect(View v) {
		PersonService server = new PersonService(getApplicationContext());
		Integer id = new Integer(1);
		server.delect(id);
	}
	
	public void onClickPayment(View v) {
		PersonService server = new PersonService(getApplicationContext());
		server.payment();
	}
}
