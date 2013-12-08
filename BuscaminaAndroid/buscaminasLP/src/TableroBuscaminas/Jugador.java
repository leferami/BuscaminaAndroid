package TableroBuscaminas;

import android.content.Context;

public class Jugador {
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

}
