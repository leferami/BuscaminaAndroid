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
		
		EditText filasP, columnasP, numeroMinasP;
		filasP = (EditText) findViewById(R.id.txtFilasPersonalizados);
		columnasP = (EditText) findViewById(R.id.txtColumnasPersonalizados);
		numeroMinasP = (EditText) findViewById(R.id.txtNumeroMinas);
		
		radioGroupNivel = (RadioGroup) findViewById(R.id.radioGroup1);
		seleccionaIdNivel = radioGroupNivel.getCheckedRadioButtonId();
		radioButtonNivel = (RadioButton) findViewById(seleccionaIdNivel);
		
		nivel = radioButtonNivel.getText().toString();
				
		
		if(nivel.equals("Facil")){
			filasP.setEnabled(true);
			columnasP.setEnabled(true);
			numeroMinasP.setEnabled(true);
			
		}else if(nivel.equals("Intermedio")){
			filasP.setEnabled(false);
			columnasP.setEnabled(false);
			numeroMinasP.setEnabled(false);
		}
		
		Button rules = (Button)findViewById(R.id.btnNext);		
		rules.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText name = (EditText)findViewById(R.id.txtNombre);
				String nombre = name.getText().toString();
				if(nombre.matches("")){
					Toast.makeText(JugarActivity.this,"No Ingreso Nombre",Toast.LENGTH_LONG).show();
				}
				else{
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
