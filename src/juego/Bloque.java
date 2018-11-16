package juego;

import javafx.scene.image.ImageView;

public class Bloque {

	
	private ImageView bloque;
	private int filaBloque;
	private int columnaBloque;
	
	
	public Bloque() {
		
          bloque = new ImageView("imagenes/bloque.png");
          bloque.setFitHeight(40);
		  bloque.setFitWidth(40);

	}


	public ImageView getBloque() {
		return bloque;
	}


	public void setBloque(ImageView bloque) {
		this.bloque = bloque;
	}


	public int getFilaBloque() {
		return filaBloque;
	}


	public void setFilaBloque(int filaBloque) {
		this.filaBloque = filaBloque;
	}


	public int getColumnaBloque() {
		return columnaBloque;
	}


	public void setColumnaBloque(int columnaBloque) {
		this.columnaBloque = columnaBloque;
	}
	
	
	
}
