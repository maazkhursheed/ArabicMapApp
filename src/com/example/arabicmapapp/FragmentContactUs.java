package com.example.arabicmapapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class FragmentContactUs extends DrawerScreen {
	
	
	 Button btnOK;
	 EditText txtTo;
	 EditText txtSubject;
	 EditText txtMessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_fragment_contact_us);
		
		ViewGroup content=(ViewGroup) findViewById( R.id.frame_container);       
        getLayoutInflater().inflate(R.layout.activity_fragment_contact_us,content,true);
        initializeDrawer();
        
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        
        android.support.v7.app.ActionBar ab =  this.getSupportActionBar() ;
        setActionBarStyling("اتصل بنا");
        ab.setTitle( "اتصل بنا");
        
//        btnOK = (Button) findViewById(R.id.btnOK);
//        txtTo = (EditText) findViewById(R.id.etTo);
//        txtTo.requestFocus();
//        txtSubject = (EditText) findViewById(R.id.etSubject);
//        txtMessage = (EditText) findViewById(R.id.etMessage);
//        btnOK.setOnClickListener(new OnClickListener() {
//            public void onClick(View v) {
//                String to = txtTo.getText().toString();
//                String subject = txtSubject.getText().toString();
//                String message = txtMessage.getText().toString();
//                Intent mail = new Intent(Intent.ACTION_SEND);
//                mail.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
//                mail.putExtra(Intent.EXTRA_SUBJECT, subject);
//                mail.putExtra(Intent.EXTRA_TEXT, message);
//                mail.setType("message/rfc822");
//                startActivity(Intent.createChooser(mail, "Send email via:"));
//            }
//        });
//        
        
	}

	
	@Override
	public void onBackPressed() {
		
		super.onBackPressed();
		
		overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
	}

	
}
