package com.example.buscaminaslp;

import TableroBuscaminas.Tablero;
import TableroBuscaminas.Casilla;

import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;

public class GameBuscamina extends Activity implements OnTouchListener {

	private Tablero fondo;
	int x; 
	int y;
	private boolean activo = true;
	int fila = 10, columna = 10;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_buscamina);
		
		
		LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
		fondo = new Tablero(this);
		fondo.setOnTouchListener(this);
		linearLayout1.addView(fondo);
		fondo.casillas = new Casilla[fila][columna];
		
		for (int f = 0; f < fila; f++) {
			for (int c = 0; c < columna; c++) {
				fondo.casillas[f][c] = new Casilla();			
			}
		}
	}
	
	public void presionado(View v){
		fondo.casillas = new Casilla[fila][columna];
		for (int f = 0; f < fila; f++) {
			for (int c = 0; c < columna; c++) {
				fondo.casillas[f][c] = new Casilla();				
			}
		}
		fondo.invalidate();
	}
	
	@Override
	public boolean onTouch(View arg0, MotionEvent event) {
		if(activo){
			
			for (int f = 0; f < fila; f++) {
				for (int c = 0; c < columna; c++) {
					if(fondo.casillas[f][c].dentro((int) event.getX(), (int) event.getY())){
						fondo.casillas[f][c].destapado = true;
						fondo.invalidate();
					}
				}
			}
		}
		return true;
	}
}
