package com.myexample.contractdatabase2;

import com.myexample.sqlite.DatabaseHelper;
import com.mexample.model.Contact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class AddNewContact extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_contact);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_new_contact, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void addContact(View view) {
		EditText name = (EditText)findViewById(R.id.editTextName);
		EditText phone = (EditText)findViewById(R.id.editTextPhone);
		EditText email = (EditText)findViewById(R.id.EditTextEmail);
		EditText street = (EditText)findViewById(R.id.EditTextStreet);
		EditText city = (EditText)findViewById(R.id.EditTextCity);
		EditText state = (EditText)findViewById(R.id.EditTextState);
		EditText zip = (EditText)findViewById(R.id.EditTextZip);
		
		String nameString = name.getText().toString();
		String phoneString = phone.getText().toString();
		String emailString = email.getText().toString();
		String streetString = street.getText().toString();
		String zipString = zip.getText().toString();
		String cityString = city.getText().toString();
		String stateString = state.getText().toString();
		
		DatabaseHelper database = new DatabaseHelper(this);
    	database.createContact(new Contact(nameString, phoneString, emailString, streetString, cityString, stateString, zipString));
    	
    	Intent addContactIntent = new Intent(this, ShowAll.class);
    	startActivity(addContactIntent);
    }
    public void cancelAddContact(View view) {
    	Intent showAllIntent = new Intent(this, ShowAll.class);
    	startActivity(showAllIntent);
    }
}
