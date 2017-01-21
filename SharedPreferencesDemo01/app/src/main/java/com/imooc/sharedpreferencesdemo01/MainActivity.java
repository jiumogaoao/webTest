package com.imooc.sharedpreferencesdemo01;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText etUserName,etUserPass;
	CheckBox chk;
	SharedPreferences pref;
	Editor edtior;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
////		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
//		SharedPreferences pref =getSharedPreferences("myPref", MODE_PRIVATE);
//		Editor editor = pref.edit();
//		editor.putString("name","张三");
//		editor.putInt("age", 30);
//		editor.putLong("time", System.currentTimeMillis());
//		editor.putBoolean("default", true);
//		editor.commit();
//		editor.remove("default");
//		editor.commit();
//		System.out.println(pref.getString("name", ""));
//		System.out.println(pref.getInt("age", 0));
		etUserName = (EditText) findViewById(R.id.etuserName);
		etUserPass = (EditText) findViewById(R.id.etuserpass);
		chk = (CheckBox) findViewById(R.id.chkSaveName);
		pref =getSharedPreferences("UserInfo", MODE_PRIVATE);
		edtior = pref.edit();
		String name = pref.getString("userName", "");
		if (name==null) {
			chk.setChecked(false);
		}else {
			chk.setChecked(true);
			etUserName.setText(name);
		}
	}
	public void doClick(View v){
		switch (v.getId()) {
		case R.id.btnLogin:
			String name = etUserName.getText().toString().trim();
			String pass = etUserPass.getText().toString().trim();
			if ("admin".equals(name)&&"123456".equals(pass)) {
				if (chk.isChecked()) {
					edtior.putString("userName", name);
					edtior.commit();
					
				}else {
					edtior.remove("userName");
					edtior.commit();
				}
				Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_LONG).show();
			}else {
				Toast.makeText(MainActivity.this, "禁止登陆", Toast.LENGTH_LONG).show();
			}
			break;

		default:
			break;
		}
	}
	

}
