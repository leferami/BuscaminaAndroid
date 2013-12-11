package TableroBuscaminas;
import com.example.buscaminaslp.R;
import android.content.Context;
import android.graphics.*;
import android.widget.Button;

public class Casilla extends Button{
	private boolean cubierto; 
	private boolean estadoMina; 
	private boolean bandera;
	private boolean presionado; 
	private int numeroMinasAlrededor; 
	public Casilla (Context context){
		super(context);
	}
	
	public void valoresIniciales(){
		cubierto = true;
		estadoMina = false;
		bandera = false;
		presionado = true;
		numeroMinasAlrededor = 0;
		this.setBackgroundResource(R.drawable.casilla_azul);
	}
	
	/** Marca Bloque con una mina debajo*/
	public void ponerMinas(){
		estadoMina = true;
	}
	
	/**
	 * @return cubierto*/
	public boolean cubierto(){
		return cubierto;
	}
	
	/**Verifica el estado de la casilla si esta ocupada**/
	public boolean existeMinas(){
		return estadoMina;
	}
	
	/**Descubrir la celda seleccionada**/
	public void abrirCasilla(){
		if (!cubierto)
			return;
		
		fijarCasillaDeshabilitada(false);
		cubierto = false;

		if (existeMinas())
			fijarIconoMinas(false);
		else
			cambiarNumeroMinasAlrededor(numeroMinasAlrededor);
	}
	
	/**
	 * @param habilitar boolean para obtener el estado de la casilla
	 * Fijar casillas desabilitada/abierta si es falso entonces habilitada/cerrada**/
	public void fijarCasillaDeshabilitada(boolean habilitada){
		if (!habilitada)
			this.setBackgroundResource(R.drawable.casilla_plomo);
		else
			this.setBackgroundResource(R.drawable.casilla_azul);
	}
	
	public void cambiarNumeroMinasAlrededor(int number){
		this.setBackgroundResource(R.drawable.casilla_plomo);
		actualizarNumero(number);
	}
	
	/**Poner el numero de minas que se encuentra alrededor*/
	public void actualizarNumero(int text){
		if (text != 0){
			if(text== 1){
				this.setBackgroundResource(R.drawable.casilla1);
			}
			else if(text == 2){
				this.setBackgroundResource(R.drawable.casilla2);
			}
			else if(text == 3){
				this.setBackgroundResource(R.drawable.casilla3);
			}
			else if(text == 4){
				this.setBackgroundResource(R.drawable.casilla4);
			}
			else if(text == 5){
				this.setBackgroundResource(R.drawable.casilla5);
			}
			else if(text == 6){
				this.setBackgroundResource(R.drawable.casilla6);
			}
			else if(text == 7){
				this.setBackgroundResource(R.drawable.casilla7);
			}
			else if(text == 8){
				this.setBackgroundResource(R.drawable.casilla8);
			}
		}
	}
	
	/**
	 * @return marcado */
	public boolean bandera(){
		return bandera;
	}
	
	/**
	 * @param marcado
	 * fijar si la casilla esta marcada*/
	public void fijarBandera(boolean marcado){
		this.bandera = marcado;
	}
	
	/**
	 * @param numero Es el numero de minas alrededor de una casilla
	 * Setea el numero de minas que se encuentra alrededor*/
	public void fijarNumeroMinasAlrededor(int numero){
		numeroMinasAlrededor = numero;
	}
	
	/**
	 * @return numeroMinasAlrededor*/
	public int obtenerMinasAlrededor(){
		return numeroMinasAlrededor;
	}
	
	/**
	 * Activar minas*/
	public void activarMinas(){
		fijarIconoMinas(true);
	}
	
	/**
	 * fijar mina como marcada*/
	public void marcarBandera(boolean hablitar){
		this.setBackgroundResource(R.drawable.casilla_bandera);
		if(! hablitar){
			this.setBackgroundResource(R.drawable.casilla_plomo);
		}
	}
	
	/**
	 * fijar Bandera*/
	public void fijarIconoMinas(boolean habilitar){
		if (!habilitar)
				this.setBackgroundResource(R.drawable.casilla_bomba);
		else
				this.setBackgroundResource(R.drawable.casilla_bomba_seleccionada);
	}
	
	public boolean presionado(){
		return presionado;
	}

	// disable block for receive click events
	public void fijarPresionado(boolean clickable){
		presionado = clickable;
	}

	public void limpiaTodo()
	{
		this.setText("");
	}
}

