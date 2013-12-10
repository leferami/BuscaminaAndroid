package TableroBuscaminas;

import java.util.Comparator;

import android.content.Context;

public class Jugador implements Comparable {
	private Context context;
	private String nombre;
	private int tiempo;

	public Jugador(Context context) {
		this.context = context;
	}
	public Jugador() {
		
	}
	public Jugador(String nombre, int tiempo){
		this.nombre = nombre;
		this.tiempo = tiempo;
	}
	
	public String getNombreJugador(){
        return nombre;
    }
    
    public int getTiempoJugador(){
        return tiempo;
    }
    
    public void setNombreJugador(String nombreJ) {
        this.nombre = nombreJ;
    }
	
    public void setTiempoJugador(int tiempoJ) {
        this.tiempo = tiempoJ;
    }
    
    public static Comparator<Jugador> ValorComparatorDesc = new Comparator<Jugador>() {

		@Override
		public int compare(Jugador jugador1, Jugador jugador2) {
			// TODO Auto-generated method stub
			int tiempoJugador1 = jugador1.getTiempoJugador();
			int tiempoJugador2 = jugador2.getTiempoJugador();
			
			if(tiempoJugador2 > tiempoJugador1)
				return 1;
			else if(tiempoJugador2 < tiempoJugador1)
				return -1;
			else 
				return 0;
		}
	};

	@Override
	public int compareTo(Object another) {
		// TODO Auto-generated method stub
		return 0;
	}


}
