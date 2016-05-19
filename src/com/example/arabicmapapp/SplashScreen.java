package com.example.arabicmapapp;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

public class SplashScreen extends Activity {
	
	// private static int SPLASH_TIME_OUT = 1000;
	 final int welcomeScreenDisplay = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
//		 new Handler().postDelayed(new Runnable() {
//			 @Override
//	            public void run() {
//	                // This method will be executed once the timer is over
//	                // Start your app main activity
//	                Intent i = new Intent(SplashScreen.this, MainActivity.class);
//	                startActivity(i);
//	 
//	                // close this activity
//	                finish();
//	            }
//	        }, SPLASH_TIME_OUT);
		
		Thread welcomeThread = new Thread() {

			int wait = 0;

			@Override
			public void run() {
				try {
					super.run();
					/**
					 * use while to get the splash time. Use sleep() to increase
					 * the wait variable for every 100L.
					 */
					while (wait < welcomeScreenDisplay) {
						sleep(100);
						wait += 100;
					}
				} catch (Exception e) {
					System.out.println("EXc=" + e);
				} finally {
					/**
					 * Called after splash times up. Do some action after splash
					 * times up. Here we moved to another main activity class
					 */
					startActivity(new Intent(SplashScreen.this,
							                 MainActivity.class));
					finish();
				}
			}
		};
		welcomeThread.start();
		 }
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
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
}
