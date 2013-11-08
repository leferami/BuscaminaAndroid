package TableroBuscaminas;

import TableroBuscaminas.Casilla;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;

public class Tablero extends View {
	public Casilla[][] casillas;
	public int fila = 10, columna = 10;
	public Tablero(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawRGB(0, 0, 0);
		int ancho = 0;
		
		if(canvas.getWidth() < canvas.getHeight()){
			ancho = this.getWidth();
		}else{
			ancho = this.getHeight();
		}
		int anchocua = ancho/fila;
		
		Paint pincel = new Paint();
		pincel.setTextSize(20);
		
		Paint pincel2 = new Paint();
		pincel2.setTextSize(20);
		pincel2.setTypeface(Typeface.DEFAULT_BOLD);
		pincel2.setARGB(255, 255, 255, 255);
		
		Paint pincelLineal = new Paint();
		pincelLineal.setARGB(255, 255, 255, 255);
		
		int filaact = 0;
		
		
		//Pintar el tablero
		for (int f=0; f<fila; f++){
			for (int c = 0; c < columna; c++) {
				casillas[f][c].fijarxy(c*anchocua, filaact, anchocua);
				if(casillas[f][c].destapado == false){
					pincel.setARGB(153, 153, 204, 204);
				}else{
					pincel.setARGB(255, 153, 153, 153);//destapado
				}
				canvas.drawRect(c*anchocua, filaact, c*anchocua+anchocua-2, filaact+anchocua-2, pincel);
				canvas.drawLine(c*anchocua, filaact, c*anchocua+anchocua, filaact, pincelLineal);
				canvas.drawLine(c*anchocua+anchocua-1, filaact, c*anchocua+anchocua-1, filaact+anchocua, pincelLineal);
						
			}
			filaact = filaact + anchocua;
		}
	}
}
