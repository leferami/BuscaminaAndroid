package com.example.buscaminaslp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class PersonalizadoActivity extends Activity{
	public static Activity Pa; 
	
	private NumberPicker np1 = null;
	private NumberPicker np2 = null;
	private int numeroFilas;
	private int numeroColumnas;
	private int numeroMinas;
	private String nivelBuscaminas;
	private Button siguiente;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Pa = this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personalizado);
		
		nivelBuscaminas = (String) getIntent().getSerializableExtra("nivel");
		
		np1 = (NumberPicker) findViewById(R.id.npFilas);
		np1.setMinValue(3);
		np1.setMaxValue(12);
				
		np2 = (NumberPicker) findViewById(R.id.npMinas);
		np2.setMinValue(1);
		np2.setMaxValue(144);
				
		siguiente = (Button) findViewById(R.id.btnSiguiente);
		siguiente.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				numeroFilas = np1.getValue();
				numeroColumnas = np1.getValue();
				numeroMinas = np2.getValue();
				
				if((numeroFilas*numeroColumnas) <= numeroMinas){
					Toast.makeText(PersonalizadoActivity.this,"Numero de Minas Excede ",Toast.LENGTH_LONG).show();
				}else{
					Intent intent = new Intent(PersonalizadoActivity.this,GameBuscamina.class);
					intent.putExtra("nivel", nivelBuscaminas);
					intent.putExtra("filas", numeroFilas);
					intent.putExtra("columnas", numeroColumnas);
					intent.putExtra("minas", numeroMinas);
					startActivity(intent);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.personalizado, menu);
		return true;
	}
}
