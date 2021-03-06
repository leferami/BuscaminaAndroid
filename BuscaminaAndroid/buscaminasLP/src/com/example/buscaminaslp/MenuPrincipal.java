package com.example.buscaminaslp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuPrincipal extends Activity {
	
	public static Activity Mp; 
	
	private Button jugar, puntajes, creditos, reglas;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Mp = this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_principal);
		
		jugar = (Button)findViewById(R.id.Facil);//BOTON JUGAR
		jugar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuPrincipal.this, JugarActivity.class);
				startActivity(intent);
			}
		});
		
		puntajes = (Button)findViewById(R.id.Dificil);//BOTON PUNTAJES
		puntajes.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuPrincipal.this, ScoreActivity.class);
				startActivity(intent);
			}
		});
		
		creditos = (Button)findViewById(R.id.Personalizado);//BOTON CREDITOS
		creditos.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuPrincipal.this, CreditosActivity.class);
				startActivity(intent);
			}
		});
		
		reglas = (Button)findViewById(R.id.Intermedio);//BOTON REGLAS
		reglas.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuPrincipal.this, RulesActivity.class);
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
