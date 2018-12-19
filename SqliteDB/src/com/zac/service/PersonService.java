package com.zac.service;

import java.util.ArrayList;
import java.util.List;
import com.zac.domain.Person;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PersonService {
	private DBOpenHelper dbOpenHelper;
	
	

	public PersonService(Context context) {
		this.dbOpenHelper = new DBOpenHelper(context);
	}

	/**
	 * 添加记录，
	 * @param context
	 */
	public void save(Person person) {
		SQLiteDatabase database= dbOpenHelper.getWritableDatabase();
//		database.execSQL("insert into person(name, phone) values(?,?)",
//				new Object[]{person.getName(),person.getPhone()});
		ContentValues values = new ContentValues();
		values.put("name", person.getName());
		values.put("phone", person.getPhone());
		values.put("amount", person.getAmount());
		database.insert("person", null, values);
		//null 值字段 第三个参数是一个null或者空集合。
	}
	
	
	/**
	 * 删除记录
	 * @param id
	 */
	public void delect(Integer id) {
		SQLiteDatabase database= dbOpenHelper.getWritableDatabase();
		database.delete("person", "personid=?", new String[]{id.toString()});
		//		database.execSQL("delete from person where personid=?",
//				new Object[]{id});
	}
	/**
	 * 更新记录
	 * @param person
	 */
	public void update(Person person) {
		SQLiteDatabase database= dbOpenHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", person.getName());
		values.put("phone", person.getPhone());
		values.put("amount", person.getAmount());
		database.update("person", values, "personid=?", new String[] {person.getId().toString()});
//		database.execSQL("update person set name=?,phone=? where personid=?",
//				new Object[]{person.getName(),person.getPhone(),person.getId()});
	}
	
	/**
	 * 查找记录
	 * @param id
	 * @return
	 */
	public Person find(Integer id) {
		SQLiteDatabase database= dbOpenHelper.getReadableDatabase();
		Cursor cursor = database.query("person", null, "personid=?", new String[] {id.toString()}, null,null, null);
		if(cursor.moveToFirst()) {
			int personid = cursor.getInt(cursor.getColumnIndex("personid"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			int amount = cursor.getInt(cursor.getColumnIndex("amount"));
			return new Person(personid,name, phone,amount);
		}
		cursor.close();
		
//		Cursor cursor = database.rawQuery("select * from person where personid=?", new String[] {id.toString()});
//		if(cursor.moveToFirst()) {
//			int peronid = cursor.getInt(cursor.getColumnIndex("personid"));
//			String name = cursor.getString(cursor.getColumnIndex("name"));
//			String phone = cursor.getString(cursor.getColumnIndex("phone"));
//			return new Person(peronid,name, phone);
//		}
//		cursor.close();
			
		return null;
	}
	
	/**
	 * 分页获取记录
	 * @param offset 跳过前面多少条记录
	 * @param maxResult 每页获取多少条记录
	 * @return
	 */
	public List<Person> getScrollData(int offset, int maxResult) {
		List<Person> persons = new ArrayList<Person>();
		SQLiteDatabase database= dbOpenHelper.getReadableDatabase();
		Cursor cursor =database.query( "person", null, null, null, null, null, "personid asc", offset+","+maxResult);
		
		
		//Cursor cursor = database.rawQuery("select * from person order by personid asc limit ?,?",
			//		new String[]{String.valueOf(offset), String.valueOf(maxResult)});
		while(cursor.moveToNext()) {
			int personid = cursor.getInt(cursor.getColumnIndex("personid"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			int amount = cursor.getInt(cursor.getColumnIndex("amount"));
			persons.add(new Person(personid, name, phone, amount));
		}
		cursor.close();
		return persons;
	}
	
	/**
	 * 获取多少条记录
	 * @return
	 */
	public long getCount() {
		SQLiteDatabase database= dbOpenHelper.getReadableDatabase();
		Cursor cursor = database.query("person",new String[] {"count(*)"}, null, null, null, null, null);
		//Cursor cursor = database.rawQuery("select count(*) from person", null);
		cursor.moveToFirst();
		long result = cursor.getLong(0);
		cursor.close();
		return result;
	}
	
	public void payment() {
		SQLiteDatabase database= dbOpenHelper.getReadableDatabase();
		database.beginTransaction();
		try {
			database.execSQL("update person set amount=amount-10 where personid=2");
			database.execSQL("update person set amount=amount+10 where personid=3");
			database.setTransactionSuccessful();
		}finally {
			database.endTransaction();
		}
	}

	/**
	 * 分页获取记录
	 * @param offset 跳过前面多少条记录
	 * @param maxResult 每页获取多少条记录
	 * @return
	 */
	public Cursor getCursorScrollData(int offset, int maxResult) {
		SQLiteDatabase database= dbOpenHelper.getReadableDatabase();
		//Cursor cursor =database.query( "person", null, null, null, null, null, "personid asc", offset+","+maxResult);
		Cursor cursor = database.rawQuery("select personid as _id,name,phone,amount from person order by personid asc limit ?,?",
				new String[]{String.valueOf(offset), String.valueOf(maxResult)});
		return cursor;
	}

}
