package com.myexample.contractdatabase2;



import com.myexample.sqlite.DatabaseHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper database = new DatabaseHelper(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    public void searchActivity(View view) {
    	Intent searchIntent = new Intent(this, SearchActivity.class);
    	startActivity(searchIntent);
    }
    public void showAllActivity(View view) {
    	Intent showAllIntent = new Intent(this, ShowAll.class);
    	startActivity(showAllIntent);
    }
    public void addNewContact(View view) {
    	Intent addNewIntent = new Intent(this, AddNewContact.class);
    	startActivity(addNewIntent);
    }
}
