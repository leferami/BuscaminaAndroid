package com.example.buscaminaslp;
import java.io.*;
import java.util.Random;

import TableroBuscaminas.Casilla;
import android.os.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.widget.TableRow.LayoutParams;

public class GameBuscamina extends Activity {
	//private MenuPrincipal Mp = new MenuPrincipal(); 
	private JugarActivity Ja = new JugarActivity();
	private PersonalizadoActivity Pa = new PersonalizadoActivity(); 


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
	private int contadorMinas;
	private String nombreJugador;
	
	private Button botonReiniciar;
    private Button botonSalir;
    private AlertDialog.Builder adb;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_buscamina);
		adb = new AlertDialog.Builder(this);
		
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
		}else if(nivelBuscaminas.equals("Personalizado")){
			filasTablero = ((Integer) getIntent().getSerializableExtra("filas"));
			columnasTablero  = (Integer) getIntent().getSerializableExtra("columnas");
			minasTablero = (Integer) getIntent().getSerializableExtra("minas");
			
			if(filasTablero <= 5 || columnasTablero <= 5){
				dimensionCasillas = 70; 
			}else if(filasTablero <= 8 || columnasTablero <= 8){
				dimensionCasillas = 60; 
			}else if(filasTablero <= 10 || columnasTablero <= 10){
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
				finish();
				Pa.Pa.finish();
				Ja.Ja.finish();	
			}
		});
		
		botonReiniciar = (Button) findViewById(R.id.reiniciar);
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
		contadorMinas = 0;
		segundos = 0;
	}
	
	/**
	 * Mostrar las minas en pantalla
	 * **/
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
						if (!casillas[filasActivasMinas][columnasActivasMinas].bandera()){
							cubierta(filasActivasMinas, columnasActivasMinas);
							if (casillas[filasActivasMinas][columnasActivasMinas].existeMinas())
								juegoFinalizado(filasActivasMinas,columnasActivasMinas);
							if (verificarJuegoGanado())
								juegoGanado();
						}
					}
				});			
				//Agregar bandera		
				casillas[filas][columnas].setOnLongClickListener(new  OnLongClickListener() {
					
					public boolean onLongClick(View v) {
						
						if (!casillas[filasActivasMinas][columnasActivasMinas].cubierto() && (casillas[filasActivasMinas][columnasActivasMinas].obtenerMinasAlrededor() > 0) && !perdistes)	{
							int casillasMinasCercas = 0;
							for (int filasPrevia = -1; filasPrevia < 2; filasPrevia++){
								for (int columnasPrevia = -1; columnasPrevia < 2; columnasPrevia++){
									if (casillas[filasActivasMinas + filasPrevia][columnasActivasMinas + columnasPrevia].bandera())
										casillasMinasCercas++;
								}
							}

							if (casillasMinasCercas == casillas[filasActivasMinas][columnasActivasMinas].obtenerMinasAlrededor()){
								for (int filasPrevias = -1; filasPrevias < 2; filasPrevias++){
									for (int ColumnasPrevias = -1; ColumnasPrevias < 2; ColumnasPrevias++){
										if (!casillas[filasActivasMinas + filasPrevias][columnasActivasMinas + ColumnasPrevias].bandera()){
											cubierta(filasActivasMinas + filasPrevias, columnasActivasMinas + ColumnasPrevias);
											if (casillas[filasActivasMinas + filasPrevias][columnasActivasMinas + ColumnasPrevias].existeMinas())
												juegoFinalizado(filasActivasMinas + filasPrevias, columnasActivasMinas + ColumnasPrevias);
											if (verificarJuegoGanado())
												juegoGanado();
										}
									}
								}
							}
							return true;
						}

						if (casillas[filasActivasMinas][columnasActivasMinas].isClickable() && (casillas[filasActivasMinas][columnasActivasMinas].isEnabled() || casillas[filasActivasMinas][columnasActivasMinas].bandera())){
							if (!casillas[filasActivasMinas][columnasActivasMinas].bandera() ){
								if(contadorMinas < minasTablero){
									casillas[filasActivasMinas][columnasActivasMinas].fijarCasillaDeshabilitada(false);
									casillas[filasActivasMinas][columnasActivasMinas].marcarBandera(true);
									casillas[filasActivasMinas][columnasActivasMinas].fijarBandera(true);
									contadorMinas++;
									txtMinas.setText(contadorMinas+"/"+minasTablero);
								}
							}else{
								casillas[filasActivasMinas][columnasActivasMinas].fijarCasillaDeshabilitada(true);
								casillas[filasActivasMinas][columnasActivasMinas].limpiaTodo();
								
								if (casillas[filasActivasMinas][columnasActivasMinas].bandera()){
									contadorMinas--; 
									txtMinas.setText(contadorMinas+"/"+minasTablero);
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
	
	/**
	 * Determina si ganastes el juego
	 * @return true si ganastes
	 */
	private boolean verificarJuegoGanado(){
		for (int filas = 1; filas < filasTablero + 1; filas++)
		{
			for (int columnas = 1; columnas < columnasTablero + 1; columnas++)
			{
				if (!casillas[filas][columnas].existeMinas() && casillas[filas][columnas].cubierto())
				{
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Ganas el Juego y te presenta una ventana para ingresar tu nombre
	 */
	private void juegoGanado(){
		detenerTiempo();
		comenzarTiempo = false;
		perdistes = true;

		for (int filas = 1; filas < filasTablero + 1; filas++)
		{
			for (int columnas = 1; columnas < columnasTablero + 1; columnas++)
			{
				casillas[filas][columnas].setClickable(false);
				if (casillas[filas][columnas].existeMinas())
				{
					casillas[filas][columnas].fijarBandera(false);
					casillas[filas][columnas].marcarBandera(true);
				}
			}
		}
		Toast.makeText(GameBuscamina.this,"GANASTES EL JUEGO",Toast.LENGTH_LONG).show();
		nombreJugador();
	}
	
	/**
	 * Presenta la interface de dialogo, que indica 
	 * que perdistes el juego.
	 */
	public void perdistesJuego(){
		final ImageView perdistes = new ImageView(this);
		perdistes.setBackgroundResource(R.drawable.triste);
		perdistes.setMaxHeight(20);
		adb.setTitle("PERDISTES"); 
		adb.setView(perdistes);
		adb.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Ja.Ja.finish();
				if(nivelBuscaminas.equals("Personalizado"))
					Pa.Pa.finish();
				finish();
			}
		}); 
		adb.show();	
	}
	
	/**
	 * Presenta la interface de dialogo para ingresar 
	 * el nombre del jugador que gano la partida.
	 */
	public void nombreJugador(){
		final EditText input = new EditText(this);
		adb.setTitle("Nombre del Jugador"); 
		adb.setView(input);
		adb.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				nombreJugador = input.getText().toString();
				Toast.makeText(GameBuscamina.this,"GANASTES EL JUEGO :",Toast.LENGTH_LONG).show();
				escribirArchivo(""+nivelBuscaminas.toLowerCase(), ""+nombreJugador, ""+segundos);
				Ja.Ja.finish();
				if(nivelBuscaminas.equals("Personalizado"))
					Pa.Pa.finish();
				finish();
			}
		}); 
		adb.show();	
	}

	/**
	 * Comienza el tiempo a transcurrir**/
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
			}else
				fila--;
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
	
	/**
	 * Finalizar el Juego*/	
	private void juegoFinalizado(int currentRow, int currentColumn){
		perdistes = true;
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
		perdistesJuego();
	}
	
	/**
	 * Detiene el tiempo**/
	public void detenerTiempo(){
		tiempo.removeCallbacks(updateTimeElasped);
	}
	
	/**
	 * Acabar Juego*/
	private void salirJuego(){
		detenerTiempo(); 
		txtCronometro.setText("000");
		txtMinas.setText("00/"+minasTablero);
		campoMinas.removeAllViews();
	
		comenzarTiempo = false;
		setearMinas = false;
		perdistes = false;
		minasEncontradas = 00;
	}
	
	private Runnable updateTimeElasped = new Runnable(){//Modificar al formato 00:00:00
		public void run(){
			long milisegundos = System.currentTimeMillis();
			++segundos;

			if (segundos < 10)
				txtCronometro.setText("00" + Integer.toString(segundos));
			else if (segundos < 100)
				txtCronometro.setText("0" + Integer.toString(segundos));
			else
				txtCronometro.setText(Integer.toString(segundos));
			tiempo.postAtTime(this, milisegundos);
			tiempo.postDelayed(updateTimeElasped, 1000);
		}
	};

	/**
	 * Escribie en el archivo cuando se gana una partida
	 * Y se lo guarda en el nivel que corresponda*/
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