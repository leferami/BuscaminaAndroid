package TableroBuscaminas;



import com.example.buscaminaslp.R;

import android.content.Context;
import android.widget.Button;

public class Casilla extends Button{
	private boolean cubierto; 
	private boolean mina; 
	private boolean marcado;
	private boolean marcar; 
	private boolean presionado; 
	private int numeroMinasAlrededor; 
	
	
	
	public Casilla (Context context){
		super(context);
	}
	
	public void set_Defecto()
	{
		cubierto = true;
		mina = false;
		marcado = false;
		marcar = false;
		presionado = true;
		numeroMinasAlrededor = 0;

		this.setBackgroundResource(R.drawable.casilla_azul);
	}
	
}

