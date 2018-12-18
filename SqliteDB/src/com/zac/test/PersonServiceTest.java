package com.zac.test;

import java.util.List;

import com.zac.domain.Person;
import com.zac.service.DBOpenHelper;
import com.zac.service.PersonService;

import android.annotation.SuppressLint;
import android.test.AndroidTestCase;
import android.util.Log;

public class PersonServiceTest extends AndroidTestCase {
	
	private static final String tag = "PersonServiceTest";
	/**
	 * 创建数据库
	 * @throws Exception
	 */
	public void testCreateDB() throws Exception{
		DBOpenHelper dbOpenHelper = new DBOpenHelper(getContext());
		dbOpenHelper.getWritableDatabase();
	}
	
	public void testSave() throws Exception {
		PersonService server = new PersonService(this.getContext());
		Person person = new Person("zhangxx","1358454521", 100);
		server.save(person );
	}
	
	@SuppressLint("UseValueOf")
	public void testDelete() throws Exception {
		PersonService server = new PersonService(this.getContext());
		Integer id = new Integer(1);
		server.delect(id);
	}
	
	@SuppressLint("UseValueOf")
	public void testUpdate() throws Exception {
		PersonService server = new PersonService(this.getContext());
		Integer id = new Integer(1);
		Person person = server.find(id);
		person.setName("zhangxiaoxiao");
		server.update(person);
	}
	
	@SuppressLint("UseValueOf")
	public void testFind() throws Exception {
		PersonService server = new PersonService(this.getContext());
		Integer id = new Integer(1);
		Person person = server.find(id);
		Log.i(tag, person.toString());
	}
	
	public void testScrollDate() throws Exception {
		PersonService server = new PersonService(this.getContext());
		List<Person> persons = server.getScrollData(0, 5);
		for(Person person: persons)	{
			Log.i(tag, person.toString());
		}
	}
	
	public void testCount() throws Exception {
		
	}
}
