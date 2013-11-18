package com.example.buscaminaslp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class JugarActivity extends Activity {
	
	private RadioGroup radioGroupNivel;
	int seleccionaIdNivel;
	RadioButton radioButtonNivel;
	private String nivel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jugar);
		
		final TextView lblFilasP;
		final TextView lblColumnasP;
		final TextView lblNumeroMinasP;
		
		final EditText filasP;
		final EditText columnasP;
		final EditText numeroMinasP;
		
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
				// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub
				filasP.setVisibility(View.VISIBLE);
				lblFilasP.setVisibility(View.VISIBLE);
				columnasP.setVisibility(View.VISIBLE);
				lblColumnasP.setVisibility(View.VISIBLE);
				numeroMinasP.setVisibility(View.VISIBLE);
				lblNumeroMinasP.setVisibility(View.VISIBLE);
				
			}
		});
		
		Button rules = (Button)findViewById(R.id.btnNext);		
		rules.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText name = (EditText)findViewById(R.id.txtNombre);
				String nombre = name.getText().toString();
				
				EditText filas = (EditText) findViewById(R.id.txtFilasPersonalizados);
				EditText columnas = (EditText) findViewById(R.id.txtColumnasPersonalizados);
				EditText minas = (EditText) findViewById(R.id.txtNumeroMinas);
				
				
				int filasNumero = Integer.parseInt(filas.getText().toString());
				int columnasNumero = Integer.parseInt(filas.getText().toString());
				int minasNumero = Integer.parseInt(filas.getText().toString());
				
				if(nombre.matches("")){
					Toast.makeText(JugarActivity.this,"No Ingreso Nombre",Toast.LENGTH_LONG).show();
				}else  if (!(filasNumero >= 3 && filasNumero <= 12)){
					Toast.makeText(JugarActivity.this,"Numero de Filas fuera de Rango",Toast.LENGTH_LONG).show();
			        filas.setText("");
				}else  if (!(columnasNumero >= 3 && columnasNumero <= 12)){
					Toast.makeText(JugarActivity.this,"Numero de Columnas fuera de Rango",Toast.LENGTH_LONG).show();
			        columnas.setText("");
				}else  if (minasNumero > (filasNumero*columnasNumero)){
					Toast.makeText(JugarActivity.this,"Numero de Minas excede al numero de celdas",Toast.LENGTH_LONG).show();
			        minas.setText("");
				}else{
					radioGroupNivel = (RadioGroup) findViewById(R.id.radioGroup1);
					seleccionaIdNivel = radioGroupNivel.getCheckedRadioButtonId();
					radioButtonNivel = (RadioButton) findViewById(seleccionaIdNivel);
					
					nivel = radioButtonNivel.getText().toString();
					Intent intent = new Intent(JugarActivity.this,GameBuscamina.class);
					intent.putExtra("nivel", nivel);
					startActivity(intent);
				}
			}
		});//Boton NEXT
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.jugar, menu);
		return true;
	}	
}
