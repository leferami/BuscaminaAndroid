package com.example.buscaminaslp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class JugarActivity extends Activity {
	
	private RadioGroup radioGroupNivel;
	private int seleccionaIdNivel;
	private RadioButton radioButtonNivel;
	private String nivel;
	
	TextView lblFilasP;
	TextView lblColumnasP;
	TextView lblNumeroMinasP;
	
	EditText filasP;
	EditText columnasP;
	EditText numeroMinasP;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jugar);
		
		lblFilasP = (TextView) findViewById(R.id.filasPersonalizados);
		lblColumnasP = (TextView) findViewById(R.id.columnasPersonalizados);
		lblNumeroMinasP = (TextView) findViewById(R.id.numeroMinas);
		
		filasP = (EditText) findViewById(R.id.txtFilasPersonalizados);
		columnasP = (EditText) findViewById(R.id.txtColumnasPersonalizados);
		numeroMinasP = (EditText) findViewById(R.id.txtNumeroMinas);
		
		filasP.setText("");
		lblFilasP.setVisibility(View.GONE);
		filasP.setVisibility(View.GONE);
		columnasP.setText("");
		lblColumnasP.setVisibility(View.GONE);
		columnasP.setVisibility(View.GONE);
		numeroMinasP.setText("");
		lblNumeroMinasP.setVisibility(View.GONE);
		numeroMinasP.setVisibility(View.GONE);
		
		RadioButton rdbFacil = (RadioButton)findViewById(R.id.rbtFacil);
		rdbFacil.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				filasP.setText("");
				lblFilasP.setVisibility(View.GONE);
				filasP.setVisibility(View.GONE);
				columnasP.setText("");
				lblColumnasP.setVisibility(View.GONE);
				columnasP.setVisibility(View.GONE);
				numeroMinasP.setText("");
				lblNumeroMinasP.setVisibility(View.GONE);
				numeroMinasP.setVisibility(View.GONE);
			}
		});
		
		RadioButton rdbIntermedio = (RadioButton)findViewById(R.id.rbtIntermedio);
		rdbIntermedio.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				filasP.setText("");
				lblFilasP.setVisibility(View.GONE);
				filasP.setVisibility(View.GONE);
				columnasP.setText("");
				lblColumnasP.setVisibility(View.GONE);
				columnasP.setVisibility(View.GONE);
				numeroMinasP.setText("");
				lblNumeroMinasP.setVisibility(View.GONE);
				numeroMinasP.setVisibility(View.GONE);
			}
		});
		RadioButton rdbDificil = (RadioButton)findViewById(R.id.rbtDificil);
		rdbDificil.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				filasP.setText("");
				lblFilasP.setVisibility(View.GONE);
				filasP.setVisibility(View.GONE);
				columnasP.setText("");
				lblColumnasP.setVisibility(View.GONE);
				columnasP.setVisibility(View.GONE);
				numeroMinasP.setText("");
				lblNumeroMinasP.setVisibility(View.GONE);
				numeroMinasP.setVisibility(View.GONE);
			}
		});
		RadioButton rdbPersonalizado = (RadioButton)findViewById(R.id.rbtPersonalizado);
		rdbPersonalizado.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				filasP.setVisibility(View.VISIBLE);
				lblFilasP.setVisibility(View.VISIBLE);
				columnasP.setVisibility(View.VISIBLE);
				lblColumnasP.setVisibility(View.VISIBLE);
				numeroMinasP.setVisibility(View.VISIBLE);
				lblNumeroMinasP.setVisibility(View.VISIBLE);
			}
		});
		
		Button siguiente = (Button)findViewById(R.id.btnNext);		
		siguiente.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText name = (EditText)findViewById(R.id.txtNombre);				
				String nombre = name.getText().toString();
				int filasNumero = 0;
				int columnasNumero = 0;
				int minasNumero = 0;
				
				EditText filas = (EditText) findViewById(R.id.txtFilasPersonalizados);
				EditText columnas = (EditText) findViewById(R.id.txtColumnasPersonalizados);
				EditText minas = (EditText) findViewById(R.id.txtNumeroMinas);
				
				String filasNS =filas.getText().toString();
				String columnasNS =columnas.getText().toString();
				String minasNS =minas.getText().toString();
				
				if(nombre.matches("")){
					Toast.makeText(JugarActivity.this,"No Ingreso Nombre",Toast.LENGTH_LONG).show();
				}else  if(!filasNS.matches("") && !columnasNS.matches("") && !minasNS.matches("")){
					filasNumero= Integer.parseInt(filasNS);
					columnasNumero = Integer.parseInt(columnasNS);
					minasNumero = Integer.parseInt(minasNS);
					
					if (!(filasNumero >= 3 && filasNumero <= 12)){
						Toast.makeText(JugarActivity.this,"Numero de Filas fuera de Rango",Toast.LENGTH_LONG).show();
				        filas.setText("");
					}else  if (!(columnasNumero >= 3 && columnasNumero <= 12)){
						Toast.makeText(JugarActivity.this,"Numero de Columnas fuera de Rango",Toast.LENGTH_LONG).show();
				        columnas.setText("");
					}else  if (minasNumero >=(filasNumero*columnasNumero)){
						Toast.makeText(JugarActivity.this,"Numero de Minas excede al numero de celdas",Toast.LENGTH_LONG).show();
				        minas.setText("");
					}else{
						radioGroupNivel = (RadioGroup) findViewById(R.id.radioGroup1);
						seleccionaIdNivel = radioGroupNivel.getCheckedRadioButtonId();
						radioButtonNivel = (RadioButton) findViewById(seleccionaIdNivel);
						
						nivel = radioButtonNivel.getText().toString();
						Intent intent = new Intent(JugarActivity.this,GameBuscamina.class);
						intent.putExtra("nivel", nivel);
						intent.putExtra("filas", filasNumero);
						intent.putExtra("columnas", columnasNumero);
						intent.putExtra("minas", minasNumero);
						startActivity(intent);
					}
				}else{
					Toast.makeText(JugarActivity.this,"Existesn Campos Vacios",Toast.LENGTH_LONG).show();
				}
			}
		});//Boton SIGUIENTE
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.jugar, menu);
		return true;
	}	
}
