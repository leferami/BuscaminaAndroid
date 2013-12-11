package com.example.buscaminaslp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class JugarActivity extends Activity {
	public static Activity Ja; 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Ja = this;
		//Mp.Mp.finish();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jugar);
		
		Button btnFacil = (Button)findViewById(R.id.Facil);
		Button btnIntermedio = (Button)findViewById(R.id.Intermedio);
		Button btnDificil = (Button)findViewById(R.id.Dificil);
		Button btnPersonalizado = (Button)findViewById(R.id.Personalizado);
		
		btnFacil.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(JugarActivity.this,GameBuscamina.class);
				intent.putExtra("nivel", "Facil");
				startActivity(intent);
			}
		});
		
		btnIntermedio.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(JugarActivity.this,GameBuscamina.class);
				intent.putExtra("nivel", "Intermedio");
				startActivity(intent);
			}
		});
		
		btnDificil.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(JugarActivity.this,GameBuscamina.class);
				intent.putExtra("nivel", "Dificil");
				startActivity(intent);
			}
		});
		
		btnPersonalizado.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(JugarActivity.this,PersonalizadoActivity.class);
				intent.putExtra("nivel", "Personalizado");
				startActivity(intent);
			}
		});
		
	}	
}
