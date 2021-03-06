package com.example.buscaminaslp;

import java.io.*;
import java.util.*;

import TableroBuscaminas.Jugador;
import android.R.integer;
import android.os.Bundle;
import android.app.TabActivity;
import android.view.Menu;
import android.widget.*;
import android.widget.TabHost.*;

public class ScoreActivity extends TabActivity {

	private TextView contenidoNombre;
	private TextView contenidoTiempo;
	private Object[] fila;
	private Jugador[] jugador = new Jugador[5];
	
	private TabHost contenedorPestana;
	private TabSpec pestana;
	private ArrayList<Jugador> listaJugador;
	ArrayList<Jugador> jugadorNivel = new ArrayList<Jugador>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);
		
		contenedorPestana =(TabHost) findViewById(android.R.id.tabhost);
		contenedorPestana.setup();
	
		pestana = contenedorPestana.newTabSpec("facil")
				.setIndicator("Facil")
				.setContent(R.id.tabLayout);
		contenedorPestana.addTab(pestana);
		
		pestana = contenedorPestana.newTabSpec("intermedio")
				.setIndicator("Intermedio")
				.setContent(R.id.tabLayout);
		contenedorPestana.addTab(pestana);
		
		pestana = contenedorPestana.newTabSpec("dificil")
				.setIndicator("Dificil")
				.setContent(R.id.tabLayout);
		contenedorPestana.addTab(pestana);
		
		pestana = contenedorPestana.newTabSpec("personalizado")
				.setIndicator("Personalizado")
				.setContent(R.id.tabLayout);
		contenedorPestana.addTab(pestana);
				
		contenedorPestana.setCurrentTab(0);
		contenedorPestana.setOnTabChangedListener(new cambia());	
	}
	
	/**
	 * Clase que cambia la pestaña**/
	class cambia implements OnTabChangeListener{
		@Override
		public void onTabChanged(String tabId) {
			
			contenidoNombre = (TextView) findViewById(R.id.contenidoNombre);
			contenidoTiempo = (TextView) findViewById(R.id.contenidoTiempo);
			
			if(tabId.equals("facil")){
				listaJugador = leerArchivo(tabId);
				contenidoNombre.setText("");
				contenidoTiempo.setText("");
				try{
					Collections.sort(listaJugador, Jugador.ValorComparatorAsc);
					escribirContenido(listaJugador, contenidoNombre,contenidoTiempo);
				}catch(IOError io){}
				
			}else if(tabId.equals("intermedio")){
					contenidoNombre.setText("");
					contenidoTiempo.setText("");
					listaJugador = leerArchivo(tabId);
					try{
						Collections.sort(listaJugador, Jugador.ValorComparatorAsc);
						escribirContenido(listaJugador, contenidoNombre,contenidoTiempo);
					}catch(IOError io){}
				
			}else if(tabId.equals("dificil")){
				listaJugador = leerArchivo(tabId);
				contenidoNombre.setText("");
				contenidoTiempo.setText("");
				try{
					Collections.sort(listaJugador, Jugador.ValorComparatorAsc);
					escribirContenido(listaJugador, contenidoNombre,contenidoTiempo);
				}catch(IOError io){}
			}else{
				listaJugador = leerArchivo(tabId);
				contenidoNombre.setText("");
				contenidoTiempo.setText("");
				try{
					Collections.sort(listaJugador, Jugador.ValorComparatorAsc);
					escribirContenido(listaJugador, contenidoNombre,contenidoTiempo);
				}catch(IOError io){}
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.score, menu);
		return true;
	}
	
	/**
	 * Lee el archivo y guarada en una lista de Jugadores los nombre y los tiempos**/
	public ArrayList<Jugador> leerArchivo(String niveles){
		ArrayList<Jugador> jugadorNivel = new ArrayList<Jugador>();
		 
		 InputStreamReader flujo=null;
	     BufferedReader lector=null;
		 try {
			 	flujo= new InputStreamReader(openFileInput(niveles+".txt"));
			 	lector= new BufferedReader(flujo);
	     }catch (FileNotFoundException ex) {
	    	 Toast.makeText(ScoreActivity.this,"No existe el archivo ",Toast.LENGTH_LONG).show();
	     }
         
       
         int casilla=0;
         while(casilla < jugador.length){
	           String linea = null;
	           try {
	        	   linea = lector.readLine();
	           } catch (IOException ex) {}
	           catch (NullPointerException e) {}
	           catch (ArrayIndexOutOfBoundsException e) {}          
	           if(linea == null){break;}
	           else if(linea.equals("")){break;}
	           
	           String[] valores;
	           valores = linea.split(";");
	           int puntaje = Integer.parseInt(valores[1]);
	           jugador[casilla] = new Jugador();
	           jugador[casilla].setNombreJugador(valores[0]);
	           jugador[casilla].setTiempoJugador(puntaje);
	           jugadorNivel.add(jugador[casilla]);
	           casilla++;
         }
         return jugadorNivel;	
	}

	/**
	 * Escribe el contenido del archivo en la actividad Puntajes**/
	public void escribirContenido(ArrayList<Jugador> jugador, TextView contenidoNombre, TextView contenidoTiempo){
	
		if(jugador.isEmpty()){
			Toast.makeText(ScoreActivity.this,"No existes puntjes disponibles ",Toast.LENGTH_LONG).show();
			contenidoNombre.setText("------\n------\n------\n------\n------\n------\n------\n------\n------\n------\n");
			contenidoTiempo.setText("------\n------\n------\n------\n------\n------\n------\n------\n------\n------\n");
		}else{
			ListIterator it = jugador.listIterator();
	       while(it.hasNext()){
	           Jugador nivelJ = (Jugador)it.next();
	               fila = new Object[2];
	               fila[0] = nivelJ.getNombreJugador();
	               fila[1] = nivelJ.getTiempoJugador();
	               contenidoNombre.append(""+fila[0]+"\n");
	               contenidoTiempo.append(""+fila[1]+"\n");
	       }
	       contenidoNombre.append("------\n------\n------\n------\n------\n------\n------\n------\n------\n------\n");
			contenidoTiempo.append("------\n------\n------\n------\n------\n------\n------\n------\n------\n------\n");
			
		}
	}
}
