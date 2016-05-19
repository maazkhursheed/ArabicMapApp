package com.example.arabicmapapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebsiteView extends AppCompatActivity {
	
	public WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.website_view);
		
		getSupportActionBar().hide();
		
		 webView = (WebView) findViewById(R.id.webView1);
         
          Intent i = getIntent();
          String siteLinks =  i.getStringExtra("SitesLinks");
          startWebView(siteLinks);
          
		
	}


	private void startWebView(String siteLinks) {
		
		//Create new webview Client to show progress dialog
        //When opening a url or click on link
         
        webView.setWebViewClient(new WebViewClient() {      
            ProgressDialog progressDialog;
          
            //If you will not use this method url links are opeen in new brower not in webview
            public boolean shouldOverrideUrlLoading(WebView view, String siteLinks) {              
                view.loadUrl(siteLinks);
                return true;
            }
        
            //Show loader on url load
            public void onLoadResource (WebView view, String url) {

                String loadingMessage = "....";
                if (progressDialog == null) {
                    // in standard case YourActivity.this
                    progressDialog = new ProgressDialog(WebsiteView.this);

                    progressDialog.setMessage(getText(R.string.Loading_message).toString()+" "+loadingMessage);

                    progressDialog.show();
                }
            }
            public void onPageFinished(WebView view, String siteLinks) {
                try{
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }
             
        }); 
          
         // Javascript inabled on webview  
//        webView.getSettings().setJavaScriptEnabled(true); 
         
        //Load url in webview
        webView.loadUrl(siteLinks);
          
		
	}


//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.web_view, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
}
