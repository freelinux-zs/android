package com.example.test;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

	private static final String tag = "MainActivity";
	private EditText telNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		telNumber = (EditText)findViewById(R.id.editText1);
	}
	
//	public void onClickCall(View v) {
//		//Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+"5556"));
//		//String number = "5554";
//		//Intent intent = new Intent();
//		//intent.setAction("android.intent.action.DIAL");
//		//intent.addCategory("android.intent.category.DEFAULT");
//		//Intent intent = new Intent(Intent.ACTION_DIAL);
//		//intent.setData(Uri.parse("tel:"+number));
//		//startActivity(intent);
//		Log.i(tag, "log.i");
//		String number = "5554";
//		Intent intent = new Intent(Intent.ACTION_CALL);
//		intent.setData(Uri.parse("tel:"+number));
//		startActivity(intent);
//	}
	
//	private void callDirectly(String mobile) {
//		Intent intent = new Intent(Intent.ACTION_CALL);
//		intent.setData(Uri.parse("tel:"+mobile));
//		startActivity(intent);
//	}

	public void onClickCall(View v) {
		String number = telNumber.getText().toString();
		if(number == null)
			Toast.makeText(this, "输入号码为空", Toast.LENGTH_LONG).show();
		else
			call(number);
	}
	
//	final public static int REQUEST_CODE_ASK_CALL_PHONE = 123;
//	public void onCall(String mobile) {
//		if(Build.VERSION.SDK_INT >= 23) {
//			int checkCallPhonePermisson = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
//			if(checkCallPhonePermisson != PackageManager.PERMISSION_GRANTED) {
//				ActivityCompat.requestPermissions(this, new String[]{
//					Manifest.permission.CALL_PHONE
//				},REQUEST_CODE_ASK_CALL_PHONE);
//				return;
//			}else {
//				callDirectly("5554");
//			}
//		}else {
//			callDirectly("5554");
//		}
//	}
//	
//
//	public void onRequestPermissonsResult(int requestCode, String[] permissions, int[] grantResults) {
//		switch (requestCode) {
//		case REQUEST_CODE_ASK_CALL_PHONE:
//			if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//				
//			}else {
//				
//			}
//			break;
//
//		default:super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//		}
//	}
//	
	public static final int REQUEST_CODE_PERMISSON = 10111; //拨号请求码
	
	/**
	 * 判断是否有某项权限值
	 * @param string_permisson 权限
	 * @param request_code 请求码
	 * @return
	 */
	public boolean checkReadPermisson(String string_permisson, int request_code)
	{
		boolean flag = false;
		if (ContextCompat.checkSelfPermission(this, string_permisson) == PackageManager.PERMISSION_GRANTED) {
			flag = true;
		} else { //申请权限
			ActivityCompat.requestPermissions(this, new String[]{string_permisson}, request_code);
		}
		Log.i(tag, "checkReadPermisson="+flag);
		return flag;
	}
	
	
	/**
	 * 检查权限后回调
	 * @param requestCode
	 * @param permissons
	 * @param grantResults
	 */
	public void onRequestPermissionsResult(int requestCode, String[] permissons, int[] grantResults) {
		Log.i(tag, "requestCode ="+requestCode);
		switch (requestCode){
		case REQUEST_CODE_PERMISSON:
			Log.i(tag, "onRequsetPermissonResult..if 01");
			if((permissons.length != 0) && (grantResults[0] != PackageManager.PERMISSION_GRANTED)) { //失败
				Toast.makeText(this, "请允许拨号权限后再尝试！", Toast.LENGTH_LONG).show();	
				Log.i(tag, "onRequsetPermissonResult..if 02");
			} else {
				Log.i(tag, "onRequsetPermissonResult..else");
				call("5554");
			}
			break;
		}
	}
	
	/**
	 * 直接拨打电话
	 * @param telphone 电话号码
	 */
	public void call(String telphone) {
		Log.i(tag, "call");
		if(checkReadPermisson(Manifest.permission.CALL_PHONE, REQUEST_CODE_PERMISSON)) {		
			Intent intent = new Intent(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:"+telphone));
			startActivity(intent);
		}
	}
}
