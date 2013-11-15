package com.example.buscaminaslp;

import TableroBuscaminas.Casilla;

import android.os.Bundle;
import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

public class GameBuscamina extends Activity {

	private String nivelBuscaminas;
	int x; 
	int y;
	private boolean activo = true;
	int filasTablero = 10, columnasTablero = 10;
	private int numeroTotalMinas = 25;
	private Casilla casillas[][]; 
	private int dimensionCasillas = 50; 
	private int rellenoCasillas = 2;	
	
	private ImageButton botonIniciar;
	private TableLayout mineField;
	
	private int segundos = 0;
	private boolean perdida;
	private int encontrarMinas; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_buscamina);
		
		nivelBuscaminas = (String) getIntent().getSerializableExtra("nivel");
		TextView titulo = (TextView) findViewById(R.id.txtBuscamina);
		titulo.setText("Buscamina - " +nivelBuscaminas);
		
		if(nivelBuscaminas.equals("Facil")){
			filasTablero = 8;
			columnasTablero = 8;
		}else if(nivelBuscaminas.equals("Intermedio")){
			filasTablero = 8;
			columnasTablero = 10;
		}else if(nivelBuscaminas.equals("Dificil")){
			filasTablero = 10;
			columnasTablero = 12;
		}else{
			filasTablero = 9;
			columnasTablero = 5;
		}
		
		mineField = (TableLayout)findViewById(R.id.MineField);
		presionado();	
	}
	
	public void presionado(){
		createMineField();
		showMineField();
		encontrarMinas = numeroTotalMinas;
		perdida = false;
		segundos = 0;
	}
	
	private void showMineField(){
		for (int row = 1; row < filasTablero + 1; row++){
			TableRow tableRow = new TableRow(this);  
			tableRow.setLayoutParams(new LayoutParams((dimensionCasillas + 2 * rellenoCasillas) * columnasTablero, dimensionCasillas + 2 * rellenoCasillas));

			for (int column = 1; column < columnasTablero + 1; column++){
				casillas[row][column].setLayoutParams(new LayoutParams(dimensionCasillas + 2 * rellenoCasillas,dimensionCasillas + 2 * rellenoCasillas)); 
				casillas[row][column].setPadding(rellenoCasillas, rellenoCasillas, rellenoCasillas, rellenoCasillas);
				tableRow.addView(casillas[row][column]);
			}
			mineField.addView(tableRow,new TableLayout.LayoutParams((dimensionCasillas + 2 * rellenoCasillas) * columnasTablero, dimensionCasillas + 2 * rellenoCasillas));  
		}
	}
	
	private void createMineField(){
		casillas = new Casilla[filasTablero + 2][columnasTablero + 2];

		for (int row = 0; row < filasTablero + 2; row++){
			for (int column = 0; column < columnasTablero + 2; column++){	
				casillas[row][column] = new Casilla(this);
				casillas[row][column].set_Defecto();
			}
		}
	}


}