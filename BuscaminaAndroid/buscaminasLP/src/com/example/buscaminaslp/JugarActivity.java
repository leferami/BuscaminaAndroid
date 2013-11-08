package com.example.buscaminaslp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JugarActivity extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jugar);
		
		Button rules = (Button)findViewById(R.id.btnNext);
		rules.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText name = (EditText)findViewById(R.id.txtName);
				String nombre = name.getText().toString();
				if(nombre.matches("")){
					Toast.makeText(JugarActivity.this,"No Ingreso Nombre",Toast.LENGTH_LONG).show();
				}
				else{
					Intent intent = new Intent(JugarActivity.this,RulesActivity.class);
					startActivity(intent);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.jugar, menu);
		return true;
	}

}
