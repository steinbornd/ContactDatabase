package com.myexample.sqlite;

import com.mexample.model.Contact;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{
	// Logcat tag
		private static final String LOG = "DatabaseHelper";

		// Database Version
		private static final int DATABASE_VERSION = 1;

		// Database Name
		private static final String DATABASE_NAME = "contactsManager";
		
		private static final String TABLE_CONTACT = "contacts";
		
		// Common column names
		private static final String KEY_CREATED_AT = "created_at";
		
		// Contacts Table Columns names
		private static final String KEY_CONTACT_ID = "contact_id";
		static final String KEY_NAME = "name";
		static final String KEY_PHONE = "phone_number";
		static final String KEY_EMAIL = "email_address";
		static final String KEY_STREET = "street";
		static final String KEY_CITY = "city";
		static final String KEY_STATE = "state";
		static final String KEY_ZIP = "zip";
		
		private static final String CREATE_TABLE_CONTACT = "CREATE TABLE " + TABLE_CONTACT + "(" + KEY_CONTACT_ID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT,"  + KEY_PHONE + " TEXT," + KEY_EMAIL + " TEXT," + KEY_STREET + " TEXT," + KEY_CITY + " TEXT," + KEY_STATE + " TEXT," + KEY_ZIP + " INTEGER,"+ KEY_CREATED_AT + " DATETIME" + ")"; 
		
		public DatabaseHelper(Context context){
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_TABLE_CONTACT);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);
			
			onCreate(db);
		}
		public void createContact(Contact contact) {
			SQLiteDatabase db = this.getWritableDatabase();

			ContentValues values = new ContentValues();
			values.put(KEY_NAME, contact.getName()); // Contact Name
			values.put(KEY_PHONE, contact.getPhone());
			values.put(KEY_EMAIL, contact.getEmail());
			values.put(KEY_STREET, contact.getStreet());
			values.put(KEY_STATE, contact.getState());
			values.put(KEY_CITY, contact.getCity());
			values.put(KEY_ZIP, contact.getZip());
			values.put(KEY_CREATED_AT, getDateTime());
			// Inserting Row
			db.insert(TABLE_CONTACT, null, values);
		}

		// Getting single contact
		Contact getContact(int id) {
			SQLiteDatabase db = this.getReadableDatabase();

			Cursor cursor = db.query(TABLE_CONTACT, new String[] { KEY_CONTACT_ID,
					KEY_NAME, KEY_PHONE, KEY_EMAIL, KEY_STREET, KEY_CITY, KEY_STATE, KEY_ZIP}, KEY_CONTACT_ID + "=?",
					new String[] { String.valueOf(id) }, null, null, null, null);
			if (cursor != null)
				cursor.moveToFirst();

			Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
					cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
			// return contact
			return contact;
		}
		public Cursor getData(int id){
		      SQLiteDatabase db = this.getReadableDatabase();
		      Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
		      return res;
		   }
		public ArrayList<Contact> getAllContacts() {
			ArrayList<Contact> contactList = new ArrayList<Contact>();
			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_CONTACT;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Contact contact = new Contact();
					contact.setId(Integer.parseInt(cursor.getString(0)));
					contact.setName(cursor.getString(1));
					contact.setPhone(cursor.getString(2));
					contact.setEmail(cursor.getString(3));
					contact.setStreet(cursor.getString(4));
					contact.setCity(cursor.getString(5));
					contact.setState(cursor.getString(6));
					contact.setZip(cursor.getString(7));
					// Adding contact to list
					contactList.add(contact);
				} while (cursor.moveToNext());
			}

			// return contact list
			return contactList;
		}
		public Cursor allContacts(){
			SQLiteDatabase db = this.getWritableDatabase();
		    return db.rawQuery("select * from " + TABLE_CONTACT, null);
		}

		// Updating single contact
		public int updateContact(Contact contact) {
			SQLiteDatabase db = this.getWritableDatabase();

			ContentValues values = new ContentValues();
			values.put(KEY_NAME, contact.getName());
			values.put(KEY_PHONE, contact.getPhone());
			values.put(KEY_EMAIL, contact.getEmail());
			values.put(KEY_STREET, contact.getStreet());
			values.put(KEY_STATE, contact.getState());
			values.put(KEY_CITY, contact.getCity());
			values.put(KEY_ZIP, contact.getZip());

			// updating row
			return db.update(TABLE_CONTACT, values, KEY_CONTACT_ID + " = ?",
					new String[] { String.valueOf(contact.getId()) });
		}

		// Deleting single contact
		public void deleteContact(Contact contact) {
			SQLiteDatabase db = this.getWritableDatabase();
			db.delete(TABLE_CONTACT, KEY_CONTACT_ID + " = ?",
					new String[] { String.valueOf(contact.getId()) });
			db.close();
		}


		// Getting contacts Count
		public int getContactsCount() {
			String countQuery = "SELECT  * FROM " + TABLE_CONTACT;
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(countQuery, null);
			cursor.close();

			// return count
			return cursor.getCount();
		}
		private String getDateTime() {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss", Locale.getDefault());
			Date date = new Date();
			return dateFormat.format(date);
		}
}
