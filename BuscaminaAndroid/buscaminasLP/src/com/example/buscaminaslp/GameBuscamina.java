package com.example.buscaminaslp;
import java.io.*;
import java.util.Random;

import TableroBuscaminas.Casilla;
import android.os.*;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.widget.TableRow.LayoutParams;

public class GameBuscamina extends Activity {
	private JugarActivity Ja = new JugarActivity();

	private String nivelBuscaminas;
	private Integer filasTablero = 0;
	private Integer columnasTablero = 0;
	private Integer minasTablero = 0;
	
	private Casilla casillas[][]; 
	private int dimensionCasillas; 
	private int rellenoCasillas = 2;
	
	private Handler tiempo = new Handler();
	private int segundos = 0;
	
	private boolean comenzarTiempo;
	private boolean setearMinas;
	private boolean perdistes;
		
	private TextView tituloBuscamina;
	private TextView txtCronometro;
	private TextView txtMinas;
	private TableLayout campoMinas;
	private int minasEncontradas = 00;
	private int minasExistente;
	
	private Button botonReiniciar;
    private Button botonSalir;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_buscamina);
		
		nivelBuscaminas = (String) getIntent().getSerializableExtra("nivel");
		tituloBuscamina = (TextView) findViewById(R.id.txtNiveles);
		tituloBuscamina.setText("BUSCAMINA - " +nivelBuscaminas.toUpperCase());	
					
		if(nivelBuscaminas.equals("Facil")){
			filasTablero = 8;
			columnasTablero = 8;
			minasTablero = 10;
			dimensionCasillas = 60; 
		}else if(nivelBuscaminas.equals("Intermedio")){
			filasTablero = 10;
			columnasTablero = 10;
			minasTablero = 15;
			dimensionCasillas = 50; 
		}else if(nivelBuscaminas.equals("Dificil")){
			filasTablero = 12;
			columnasTablero = 12;
			minasTablero = 20;
			dimensionCasillas = 40; 
		}else{
			
			filasTablero = (Integer) getIntent().getSerializableExtra("filas");
			columnasTablero  = (Integer) getIntent().getSerializableExtra("columnas");
			minasTablero = (Integer) getIntent().getSerializableExtra("minas");
			
			if(filasTablero <= 5 || columnasTablero <= 5){
				dimensionCasillas = 70; 
			}else if(filasTablero <= 8 || columnasTablero <= 8){
				dimensionCasillas = 60; 
			}else if(filasTablero <= 10|| columnasTablero <= 10){
				dimensionCasillas = 50; 
			}else {
				dimensionCasillas = 40; 
			}
		}
		
		txtCronometro = (TextView) findViewById(R.id.Cronometro);		
		txtMinas = (TextView) findViewById(R.id.Minas);
		txtMinas.setText(minasEncontradas+"/"+minasTablero);//Seteas las minas encontradas
		
		botonSalir = (Button) findViewById(R.id.salir);
		botonSalir.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Ja.Ja.finish();
				finish();
			}
		});
		
		botonReiniciar = (Button) findViewById(R.id.restart);
		botonReiniciar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				salirJuego();
				presionado();
			}
		});//Boton Iniciar y Reiniciar el Juego
		
		campoMinas = (TableLayout)findViewById(R.id.MineField);
		presionado();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	}
	
	/**
	 * Comienza el juego**/
	public void presionado(){
		crearCamposMinas();
		mostrarCamposMinas();
		perdistes=false;
		minasExistente = minasTablero;
		segundos = 0;
	}
	
	/**
	 * Mostrar las minas en pantalla**/
	private void mostrarCamposMinas(){
		int filasT = filasTablero + 1;
		int columnasT = columnasTablero + 1;
		for (int filas = 1; filas < filasT; filas++){
			TableRow tRow = new TableRow(this);  
			tRow.setLayoutParams(new LayoutParams((dimensionCasillas + 2 * rellenoCasillas) * columnasTablero, dimensionCasillas + 2 * rellenoCasillas));

			for (int columnas = 1; columnas < columnasT; columnas++){
				casillas[filas][columnas].setLayoutParams(new LayoutParams(dimensionCasillas + 2 * rellenoCasillas,dimensionCasillas + 2 * rellenoCasillas)); 
				casillas[filas][columnas].setPadding(rellenoCasillas, rellenoCasillas, rellenoCasillas, rellenoCasillas);
				tRow.addView(casillas[filas][columnas]);
			}
			campoMinas.addView(tRow,new TableLayout.LayoutParams((dimensionCasillas + 2 * rellenoCasillas) * columnasTablero, dimensionCasillas + 2 * rellenoCasillas));  
		}
	}
	
	private void crearCamposMinas(){
		int filasT = filasTablero + 2;
		int columnasT = columnasTablero + 2;
		
		casillas = new Casilla[filasT][columnasT];
		for (int filas = 0; filas < filasT; filas++){
			for (int columnas = 0; columnas < columnasT; columnas++){	
				casillas[filas][columnas] = new Casilla(this);
				casillas[filas][columnas].valoresIniciales();
				
				
				final int filasActivasMinas = filas;
				final int columnasActivasMinas = columnas;
				
				casillas[filas][columnas].setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (!comenzarTiempo){
							comenzarTiempo();
							comenzarTiempo = true;
						}
						
						if (!setearMinas){
							setearMinas = true;
							fijarMinas(filasActivasMinas, columnasActivasMinas);
						}
						
						if (!casillas[filasActivasMinas][columnasActivasMinas].bandera())
						{
							cubierta(filasActivasMinas, columnasActivasMinas);
							
							if (casillas[filasActivasMinas][columnasActivasMinas].existeMinas()){
								juegoFinalizado(filasActivasMinas,columnasActivasMinas);
							}
						}
					}
				});
			
				//Agregar bandera		
				casillas[filas][columnas].setOnLongClickListener(new  OnLongClickListener() {
					
					public boolean onLongClick(View v) {
						
						if (!casillas[filasActivasMinas][columnasActivasMinas].cubierto() && (casillas[filasActivasMinas][columnasActivasMinas].obtenerMinasAlrededor() > 0) && !perdistes)
						{
							int casillasMinasCercas = 0;
							for (int filasPrevia = -1; filasPrevia < 2; filasPrevia++)
							{
								for (int columnasPrevia = -1; columnasPrevia < 2; columnasPrevia++)
								{
									if (casillas[filasActivasMinas + filasPrevia][columnasActivasMinas + columnasPrevia].bandera())
									{
										casillasMinasCercas++;
									}
								}
							}

							if (casillasMinasCercas == casillas[filasActivasMinas][columnasActivasMinas].obtenerMinasAlrededor())
							{
								for (int filasPrevias = -1; filasPrevias < 2; filasPrevias++)
								{
									for (int ColumnasPrevias = -1; ColumnasPrevias < 2; ColumnasPrevias++)
									{
										if (!casillas[filasActivasMinas + filasPrevias][columnasActivasMinas + ColumnasPrevias].bandera())
										{
											cubierta(filasActivasMinas + filasPrevias, columnasActivasMinas + ColumnasPrevias);
			
											if (casillas[filasActivasMinas + filasPrevias][columnasActivasMinas + ColumnasPrevias].existeMinas())
											{
												juegoFinalizado(filasActivasMinas + filasPrevias, columnasActivasMinas + ColumnasPrevias);
											}
										}
									}
								}
							}
							return true;
						}

						if (casillas[filasActivasMinas][columnasActivasMinas].isClickable() && 
								(casillas[filasActivasMinas][columnasActivasMinas].isEnabled() || casillas[filasActivasMinas][columnasActivasMinas].bandera()))
						{
							if (!casillas[filasActivasMinas][columnasActivasMinas].bandera() )
							{
								casillas[filasActivasMinas][columnasActivasMinas].fijarCasillaDeshabilitada(false);
								casillas[filasActivasMinas][columnasActivasMinas].marcarBandera(true);
								casillas[filasActivasMinas][columnasActivasMinas].fijarBandera(true);
								minasEncontradas--; 
							}
							
							else
							{
								casillas[filasActivasMinas][columnasActivasMinas].fijarCasillaDeshabilitada(true);
								casillas[filasActivasMinas][columnasActivasMinas].limpiaTodo();
								
								
								if (casillas[filasActivasMinas][columnasActivasMinas].bandera())
								{
									minasEncontradas++; 
								}
								casillas[filasActivasMinas][columnasActivasMinas].fijarBandera(false);
							}
						}

						return true;
					}
				});
			}
		}
	}
	
	
	/**Comienza el tiempo a transcurrir**/
	public void comenzarTiempo(){
		if (segundos == 0){
			tiempo.removeCallbacks(updateTimeElasped);
			tiempo.postDelayed(updateTimeElasped, 1000);
		}
	}

	/**
	 * @param filasActivas
	 * @param columnasActivas
	 * Poner aleatoriamente las minas en el tablero**/
	private void fijarMinas(int filasActivas, int columnasActivas){
		
		Random rand = new Random();
		int filaMina, columnaMina;

		for (int fila = 0; fila < minasTablero; fila++){
			filaMina = rand.nextInt(columnasTablero);
			columnaMina = rand.nextInt(filasTablero);
			
			if ((filaMina + 1 != filasActivas) || (columnaMina + 1 != columnasActivas)){
				
				if (casillas[filaMina + 1][columnaMina + 1].existeMinas())
					fila--;
				casillas[filaMina + 1][columnaMina + 1].ponerMinas();
			}
			
			else{
				fila--;
			}
		}
		
		

		int contadorMinasCercanas;

		for (int fila = 0; fila < filasTablero + 2; fila++){
			for (int columna = 0; columna < columnasTablero + 2; columna++){
				contadorMinasCercanas = 0;
				if ((fila != 0) && (fila != (filasTablero + 1)) && (columna != 0) && (columna != (columnasTablero + 1))){
					for (int filasPrevias = -1; filasPrevias < 2; filasPrevias++){
						for (int columnasPrevia = -1; columnasPrevia < 2; columnasPrevia++){
							if (casillas[fila + filasPrevias][columna + columnasPrevia].existeMinas())
								contadorMinasCercanas++;
						}
					}
					casillas[fila][columna].fijarNumeroMinasAlrededor(contadorMinasCercanas);
				}else{
					casillas[fila][columna].fijarNumeroMinasAlrededor(9);
					casillas[fila][columna].abrirCasilla();
				}
			}
		}
	}
	
	private void cubierta(int filaPresionada, int columnaPresionada){
		if (casillas[filaPresionada][columnaPresionada].existeMinas() || casillas[filaPresionada][columnaPresionada].bandera())
			return;
		
		casillas[filaPresionada][columnaPresionada].abrirCasilla();
		if (casillas[filaPresionada][columnaPresionada].obtenerMinasAlrededor() != 0 )
			return;

		for (int fila = 0; fila < 3; fila++){
			for (int columna = 0; columna < 3; columna++){
				if (casillas[filaPresionada + fila - 1][columnaPresionada + columna - 1].cubierto()	
						&& (filaPresionada + fila - 1 > 0) && (columnaPresionada + columna - 1 > 0)	
						&& (filaPresionada + fila - 1 < filasTablero + 1) 
						&& (columnaPresionada + columna - 1 < columnasTablero + 1))
					cubierta(filaPresionada + fila - 1, columnaPresionada + columna - 1 );
			}
		}
		return;
	}
	
	private void juegoFinalizado(int currentRow, int currentColumn){
		detenerTiempo(); 
		comenzarTiempo = false;

		for (int fila = 1; fila < filasTablero + 1; fila++){
			for (int columna = 1; columna < columnasTablero + 1; columna++){
				casillas[fila][columna].fijarCasillaDeshabilitada(false);
				
				if (casillas[fila][columna].existeMinas() && !casillas[fila][columna].bandera())
					casillas[fila][columna].fijarIconoMinas(false);
				
			   if (!casillas[fila][columna].existeMinas() && casillas[fila][columna].bandera())
					casillas[fila][columna].marcarBandera(false);;

				if (casillas[fila][columna].bandera())
					casillas[fila][columna].fijarPresionado(false);
			}
		}
		casillas[currentRow][currentColumn].activarMinas();
	}
	
	/**Detiene el tiempo**/
	public void detenerTiempo(){
		tiempo.removeCallbacks(updateTimeElasped);
	}
	
	private void salirJuego(){
		detenerTiempo(); 
		txtCronometro.setText("000");
		txtMinas.setText("00/"+minasTablero);
		campoMinas.removeAllViews();
		
		comenzarTiempo = false;
		setearMinas = false;
		minasEncontradas = 00;
	}
	
	private Runnable updateTimeElasped = new Runnable(){//Modificar al formato 00:00:00
		public void run(){
			long milisegundos = System.currentTimeMillis();
			++segundos;

			if (segundos < 10){
				txtCronometro.setText("00" + Integer.toString(segundos));
			}else if (segundos < 100){
				txtCronometro.setText("0" + Integer.toString(segundos));
			}else{
				txtCronometro.setText(Integer.toString(segundos));
			}
			tiempo.postAtTime(this, milisegundos);
			tiempo.postDelayed(updateTimeElasped, 1000);
		}
	};

	public void escribirArchivo(String nivel,String nombre, String tiempo){
		OutputStreamWriter escritor=null;
		try{
		    escritor=new OutputStreamWriter(openFileOutput(nivel+".txt", Context.MODE_APPEND));
		    escritor.write(nombre+";"+tiempo);
		    escritor.write("\n");		    
		}
		catch (Exception ex){
		    Log.e("Buscaminas", "Error al escribir el archivo");
		}
		finally{
			try {
				if(escritor!=null)
					escritor.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}