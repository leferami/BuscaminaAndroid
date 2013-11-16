package com.example.buscaminaslp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuPrincipal extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_principal);
		
		Button Jugar = (Button)findViewById(R.id.Jugar);
		Jugar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuPrincipal.this, JugarActivity.class);
				startActivity(intent);
			}
		});
		
		Button Score = (Button)findViewById(R.id.Score);
		Score.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Intent intent = new Intent(MenuPrincipal.this, JugarActivity.class);
				//startActivity(intent);
			}
		});
		
		Button Creditos = (Button)findViewById(R.id.Creditos);
		Creditos.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuPrincipal.this, CreditosActivity.class);
				startActivity(intent);
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_principal, menu);
		return true;
	}

}
