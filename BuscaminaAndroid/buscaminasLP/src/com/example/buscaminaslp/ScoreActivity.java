package com.example.buscaminaslp;

import java.io.*;
import java.util.*;

import TableroBuscaminas.Jugador;
import android.os.Bundle;
import android.app.TabActivity;
import android.content.Context;
import android.util.Log;
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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);
		
		escribir("facil", "Scarleth", "120");
		escribir("intermedio", "Brenda", "180");
		escribir("dificil", "jose", "240");
		escribir("personalizado", "Brenda", "200");
		
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
				Collections.sort(listaJugador, Jugador.ValorComparatorDesc);
				escribirContenido(listaJugador, contenidoNombre,contenidoTiempo);
				
			}else if(tabId.equals("intermedio")){
				contenidoNombre.setText("");
				contenidoTiempo.setText("");
				listaJugador = leerArchivo(tabId);
				Collections.sort(listaJugador, Jugador.ValorComparatorDesc);
				escribirContenido(listaJugador, contenidoNombre,contenidoTiempo);
				
			}else if(tabId.equals("dificil")){
				listaJugador = leerArchivo(tabId);
				contenidoNombre.setText("");
				contenidoTiempo.setText("");
				Collections.sort(listaJugador, Jugador.ValorComparatorDesc);
				escribirContenido(listaJugador, contenidoNombre,contenidoTiempo);
			}else{
				listaJugador = leerArchivo(tabId);
				contenidoNombre.setText("");
				contenidoTiempo.setText("");
				Collections.sort(listaJugador, Jugador.ValorComparatorDesc);
				escribirContenido(listaJugador, contenidoNombre,contenidoTiempo);
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
	     }catch (FileNotFoundException ex) {
	    	 Toast.makeText(ScoreActivity.this,"No se abrio el archivo ",Toast.LENGTH_LONG).show();
	     }
         lector= new BufferedReader(flujo);
       
         int casilla=0;
         while(casilla < jugador.length){
	           String linea = null;
	           try {
	        	   linea = lector.readLine();
	           } catch (IOException ex) {}
	           
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

	public void escribir(String nivel,String nombre, String tiempo){
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
