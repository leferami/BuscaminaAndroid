package TableroBuscaminas;
import com.example.buscaminaslp.R;

import android.content.Context;
import android.graphics.*;
import android.widget.Button;

public class Casilla extends Button{
	private boolean cubierto; 
	private boolean estadoMina; 
	private boolean marcado;
	private boolean presionado; 
	private int numeroMinasAlrededor; 
	
	public Casilla (Context context){
		super(context);
	}
	
	public void valoresIniciales(){
		cubierto = true;
		estadoMina = false;
		marcado = false;
		presionado = true;
		numeroMinasAlrededor = 0;

		this.setBackgroundResource(R.drawable.casilla_a);
	}
	

	/**
	 * @param habilitar Estado del icono de la mina
	 * Fijar si se encuentra una mina**/
	public void fijarIconoMinas(boolean habilitar){
		this.setText("M");

		if (!habilitar)
		{
			this.setBackgroundResource(R.drawable.casilla_p);
			this.setTextColor(Color.RED);
		}
		else
			this.setTextColor(Color.BLACK);
	}
	
	
	
}

