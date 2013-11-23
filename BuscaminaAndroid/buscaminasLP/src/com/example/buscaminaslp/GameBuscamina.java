package com.example.buscaminaslp;

import TableroBuscaminas.Casilla;
import java.util.Random;
import android.os.*;
import android.app.Activity;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.TableRow.LayoutParams;

public class GameBuscamina extends Activity{

        private String nivelBuscaminas;
        private Integer filasTablero = 0;
    	private Integer columnasTablero = 0;
    	private Integer minasTablero = 0;
        private Casilla casillas[][]; 
        private int dimensionCasillas = 50; 
        private int rellenoCasillas = 2;
        
        private Handler timer = new Handler();
        private int segundos = 0;
        
        private boolean comenzarTiempo;
    	private boolean setearMinas;
    	private TextView tituloBuscamina;
        private TextView txtCronometro;
        private TextView txtMinas;
        private TableLayout campoMinas;
        
        private int minasEncontradas = 00;
    	private Button botonReiniciar;
        
        private long segundostiempo = 0;
        
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
                }else if(nivelBuscaminas.equals("Intermedio")){
                        filasTablero = 10;
                        columnasTablero = 10;
                        minasTablero = 15;
                }else if(nivelBuscaminas.equals("Dificil")){
                        filasTablero = 12;
                        columnasTablero = 12;
                        minasTablero = 20;
                }else{
                        filasTablero = (Integer) getIntent().getSerializableExtra("filas");
                        columnasTablero  = (Integer) getIntent().getSerializableExtra("columnas");
                        minasTablero = (Integer) getIntent().getSerializableExtra("minas");
                }
                
                txtCronometro = (TextView) findViewById(R.id.Cronometro);
                txtMinas = (TextView) findViewById(R.id.Minas);
                txtMinas.setText(minasEncontradas+"/"+minasTablero);
                
               
                botonReiniciar = (Button) findViewById(R.id.restart);
                botonReiniciar.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                salirJuego();
                                presionado();
                        }
                });//Boton Iniciar y Reiniciar el Juego
                
                campoMinas = (TableLayout)findViewById(R.id.MineField);
                presionado();// Dibujar el TABLERO al inicio del JUEGO
                
        }
        
        /**
    	 * Comienza el juego**/
        public void presionado(){
                crearCamposMinas();
                mostrarCamposMinas();
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
                						// Comienza el tiempo del juego
                						if (!comenzarTiempo){
                							comenzarTiempo();
                							comenzarTiempo = true;
                						}
                						//Fija las minas aleatoriamente despues que se da un clic en cualquier posicion
                						if (!setearMinas){
                							setearMinas = true;
                							fijarMinas(filasActivasMinas, columnasActivasMinas);
                						}
                						
                						if (!casillas[filasActivasMinas][columnasActivasMinas].marcado())
                						{
                							cubierta(filasActivasMinas, columnasActivasMinas);
                							
                							if (casillas[filasActivasMinas][columnasActivasMinas].existeMinas()){
                								juegoFinalizado(filasActivasMinas,columnasActivasMinas);
                							}
                							//if (checkGameWin())
                							//	winGame();
                						}
                						
                					}
                				});
                        }
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
    			filaMina = rand.nextInt(filasTablero);
    			columnaMina = rand.nextInt(columnasTablero);
    			
    			if ((filaMina + 1 != filasActivas) || (columnaMina + 1 != columnasActivas)){
    				if (casillas[filaMina + 1][columnaMina + 1].existeMinas())
    					fila--;
    				casillas[filaMina + 1][columnaMina + 1].ponerMinas();
    			}else{
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
    		if (casillas[filaPresionada][columnaPresionada].existeMinas() || casillas[filaPresionada][columnaPresionada].marcado())
    			return;
    		
    		casillas[filaPresionada][columnaPresionada].abrirCasilla();
    		if (casillas[filaPresionada][columnaPresionada].obtenerMinasAlrededor() != 0 )
    			return;

    		for (int fila = 0; fila < 3; fila++){
    			for (int columna = 0; columna < 3; columna++){
    				if (casillas[filaPresionada + fila - 1][columnaPresionada + columna - 1].cubierto()	&& (filaPresionada + fila - 1 > 0) && (columnaPresionada + columna - 1 > 0)	&& (filaPresionada + fila - 1 < filasTablero + 1) && (columnaPresionada + columna - 1 < columnasTablero + 1))
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
    				
    				if (casillas[fila][columna].existeMinas() && !casillas[fila][columna].cubierto())
    					casillas[fila][columna].fijarIconoMinas(false);

    				if (casillas[fila][columna].cubierto())
    					casillas[fila][columna].fijarPresionado(false);
    			}
    		}
    		casillas[currentRow][currentColumn].activarMinas();
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
        
    	/**Comienza el tiempo a transcurrir**/      
        public void comenzarTiempo(){
                if (segundos == 0){
                        timer.removeCallbacks(updateTimeElasped);
                        timer.postDelayed(updateTimeElasped, 1000);
                }
        }
        
        /**Detiene el tiempo**/
        public void detenerTiempo(){
                timer.removeCallbacks(updateTimeElasped);
        }
        
        /**Controla el hilo del tiempo*/
        private Runnable updateTimeElasped = new Runnable(){
                public void run(){
                        segundostiempo = System.currentTimeMillis();
                        ++segundos;
                        txtCronometro.setText(Integer.toString(segundos));
                        
                        timer.postAtTime(this, segundostiempo);
                        timer.postDelayed(updateTimeElasped, 1000);
                }
        };
}