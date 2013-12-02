package com.example.buscaminaslp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class JugarActivity extends Activity {
	public static Activity Ja; 
	private MenuPrincipal Mp = new MenuPrincipal(); 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Ja = this;
		Mp.Mp.finish();
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
		/*lblFilasP = (TextView) findViewById(R.id.filasPersonalizados);
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
		numeroMinasP.setVisibility(View.GONE);*/
		
		/*Button siguiente = (Button)findViewById(R.id.btnNext);		
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
				
				radioGroupNivel = (RadioGroup) findViewById(R.id.radioGroup1);
				seleccionaIdNivel = radioGroupNivel.getCheckedRadioButtonId();
				radioButtonNivel = (RadioButton) findViewById(seleccionaIdNivel);
				
				nivel = radioButtonNivel.getText().toString();
								
				if(nombre.matches("")){
					Toast.makeText(JugarActivity.this,"No Ingreso Nombre",Toast.LENGTH_LONG).show();
				}else if(nivel.matches("Personalizado")){
					
					if(filasNS.matches("")){
					Toast.makeText(JugarActivity.this,"Campo de Filas Vacio",Toast.LENGTH_LONG).show();
					}else if(columnasNS.matches("")){
					Toast.makeText(JugarActivity.this,"Campo de Columnas Vacio",Toast.LENGTH_LONG).show();
					}else if(minasNS.matches("")){
					Toast.makeText(JugarActivity.this,"Campo de Minas Vacio",Toast.LENGTH_LONG).show();
					}else{ 
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
							Intent intent = new Intent(JugarActivity.this,GameBuscamina.class);
							intent.putExtra("nivel", nivel);
							intent.putExtra("filas", filasNumero);
							intent.putExtra("columnas", columnasNumero);
							intent.putExtra("minas", minasNumero);
							startActivity(intent);
						}
					}
				}else{
					Intent intent = new Intent(JugarActivity.this,GameBuscamina.class);
					intent.putExtra("nivel", nivel);
					intent.putExtra("filas", filasNumero);
					intent.putExtra("columnas", columnasNumero);
					intent.putExtra("minas", minasNumero);
					startActivity(intent);
				}
			}
		});Boton SIGUIENTE*/
	}	
}
