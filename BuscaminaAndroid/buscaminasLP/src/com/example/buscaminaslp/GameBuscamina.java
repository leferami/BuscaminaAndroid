package com.example.buscaminaslp;

import TableroBuscaminas.Casilla;

import android.os.*;
import android.app.Activity;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.TableRow.LayoutParams;

public class GameBuscamina extends Activity implements OnClickListener{

        private String nivelBuscaminas;
        private Integer filasTablero = 10, columnasTablero = 10;
        private Integer minasTablero = 0;
        private Casilla casillas[][]; 
        private int dimensionCasillas = 50; 
        private int rellenoCasillas = 2;
        
        private Handler timer = new Handler();
        private boolean comenzarTiempo;
        private int segundos = 0;
        private TextView txtCronometro;
        private TextView txtMinas;
        private TableLayout campoMinas;
        
        private Button botonReiniciar;
    private Button botonSalir;
    private int minasEncontradas = 00;
    
    private boolean isTimerStarted;
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {

                requestWindowFeature(Window.FEATURE_NO_TITLE);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_game_buscamina);
                
                txtCronometro = (TextView) findViewById(R.id.Cronometro);
                txtMinas = (TextView) findViewById(R.id.Minas);
                nivelBuscaminas = (String) getIntent().getSerializableExtra("nivel");
                TextView titulo = (TextView) findViewById(R.id.txtNiveles);
                titulo.setText("BUSCAMINA - " +nivelBuscaminas.toUpperCase());        
                
                if(nivelBuscaminas.equals("Facil")){
                        filasTablero = 8;
                        columnasTablero = 8;
                        minasTablero = 10;
                }else if(nivelBuscaminas.equals("Intermedio")){
                        filasTablero = 8;
                        columnasTablero = 10;
                        minasTablero = 15;
                }else if(nivelBuscaminas.equals("Dificil")){
                        filasTablero = 10;
                        columnasTablero = 12;
                        minasTablero = 20;
                }else{
                        filasTablero = (Integer) getIntent().getSerializableExtra("filas");
                        columnasTablero  = (Integer) getIntent().getSerializableExtra("columnas");
                        minasTablero = (Integer) getIntent().getSerializableExtra("minas");
                }
                
                txtMinas.setText(minasEncontradas+"/"+minasTablero);
                
                
                botonReiniciar = (Button) findViewById(R.id.restart);
                botonReiniciar.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                salirJuego();
                                presionado();
                        }
                });
                
                campoMinas = (TableLayout)findViewById(R.id.MineField);
                
        }
        
        public void presionado(){//Comienza el juego
                crearCamposMinas();
                mostrarCamposMinas();
        }
        
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
                                casillas[filas][columnas].setOnClickListener(this);
                        }
                }
        }

        @Override
        public void onClick(View v) {
                int filasT = filasTablero + 2;
                int columnasT = columnasTablero + 2;
                for (int filas = 0; filas < filasT; filas++){
                        for (int columnas = 0; columnas < columnasT; columnas++){
                                if(v.equals(casillas[filas][columnas])){
                                        if (!comenzarTiempo){
                                                comenarTiempo();
                                                comenzarTiempo = true;
                                        }
                                }
                        }
                }
        }
        
        private void salirJuego(){
                detenenerTiempo(); 
                txtCronometro.setText("000"); 
                campoMinas.removeAllViews();
        }
        
        public void comenarTiempo(){
                if (segundos == 0){
                        timer.removeCallbacks(updateTimeElasped);
                        timer.postDelayed(updateTimeElasped, 1000);
                }
        }
        
        public void detenenerTiempo(){
                timer.removeCallbacks(updateTimeElasped);
        }
        
        private Runnable updateTimeElasped = new Runnable(){
                public void run(){
                        long currentMilisegundos = System.currentTimeMillis();
                        ++segundos;

                        if (segundos < 10){
                                txtCronometro.setText("00" + Integer.toString(segundos));
                        }else if (segundos < 100){
                                txtCronometro.setText("0" + Integer.toString(segundos));
                        }else{
                                txtCronometro.setText(Integer.toString(segundos));
                        }
                        timer.postAtTime(this, currentMilisegundos);
                        timer.postDelayed(updateTimeElasped, 1000);
                }
        };
}