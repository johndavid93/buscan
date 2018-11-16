package juego;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Tablero extends GridPane {

	private int [][]buscamina;
	private Image numeroMinas,destapado,perdio;
	private ImageView caritaFeliz;
	private int posicion=0;
	private int contMinas=0;
	private ArrayList<Bloque> bloques;
	private Label cantidadMinas;
	private Label txMinas;
	private Label tiempo;
	private Label contTiempo;
	private int cronometro=0;
	GridPane tablero;
	HBox datos = new HBox();
	
	
	public Tablero(ArrayList<Bloque> bloques) {
		tablero=new GridPane();
		this.buscamina=new int[10][10];
		this.bloques = bloques;
		cantidadMinas = new Label("Minas: ");
		txMinas = new Label("0");
		tiempo = new Label("Tiempo: ");
		caritaFeliz = new ImageView("imagenes/feliz.jpg");
		caritaFeliz.setFitHeight(40);
		caritaFeliz.setFitWidth(40);
		Tiempo();
		contTiempo = new Label(cronometro+"");
		tamano();
		leerArchivo("boards/nivel1.txt");
		llenarMatriz();
		txMinas.setText(contarMinas()+"");
		datos.setTranslateY(410);
		datos.setSpacing(20);
		datos.getChildren().add(cantidadMinas);
		datos.getChildren().add(txMinas);
		caritaFeliz.setTranslateX(200);
		datos.getChildren().add(caritaFeliz);
		datos.getChildren().add(tiempo);
		datos.getChildren().add(contTiempo);
	}
	public void tamano() {
		tiempo.setTextFill(Color.BLUE);
		contTiempo.setTextFill(Color.WHITE);
		cantidadMinas.setTextFill(Color.BLUE);
		txMinas.setTextFill(Color.WHITE);
		tiempo.setFont(new Font("Arial",20));
		contTiempo.setFont(new Font("Arial",20));
		cantidadMinas.setFont(new Font("Arial",20));
		txMinas.setFont(new Font("Arial",20));
	}
	public void destaparCasillas(int fila,int columna) {
		/**
		 *  cambiamos en el tablero la casilla que no tenga mina
		 *   10 -> destapar casilla
		 */
		for (int i = 0; i < bloques.size(); i++) {
			if((fila > 0 ) &&( buscamina[fila-1][columna]!=9)) {
				buscamina[fila-1][columna]=10;
				if((columna > 0 ) &&( buscamina[fila][columna-1]!=9)) {
					buscamina[fila][columna-1]=10;
					if((fila < buscamina.length-1 ) &&( buscamina[fila-1][columna]!=9)) {
						buscamina[fila+1][columna]=10;
						if(buscamina[bloques.get(i).getFilaBloque()][bloques.get(i).getColumnaBloque()]== 10) {
							destapado = new Image("imagenes/destapado.png");
							bloques.get(i).getBloque().setImage(destapado);
						}
					}
				}
			}
		}
	}
	public void Tiempo() {
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
			contTiempo.setText(cronometro+++"");	
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		timeline.setRate(1);
	}
	public void cantidadBombasCercanas(int fila, int columna) {
		/**
		 * buscar minas cercanas distancia : 8 casillas
		 */
		int contadorBombas=0;
		if(buscamina[fila][columna]!= 9) {
			for (int i = 0 ; i < 9; i++) {
				if(buscamina[fila][i]== 9) {//buscar en filas
					contadorBombas++;
				}
				if(buscamina[i][columna]== 9){//buscar en columas
					contadorBombas++;
				}	
			}
			buscamina[fila][columna]=contadorBombas;
		}
		ubicarNumero();
		destaparCasillas(fila, columna);
	}
	/**
	 * segun la cantidad de minas cercanas cambiar la casilla con el numero
	 */
	public void ubicarNumero(){

		for (int i = 0; i < bloques.size(); i++) {
			if(buscamina[bloques.get(i).getFilaBloque()][bloques.get(i).getColumnaBloque()]==1) {
				numeroMinas = new Image("numerosImagen/1.png");
				bloques.get(i).getBloque().setImage(numeroMinas);

			}if(buscamina[bloques.get(i).getFilaBloque()][bloques.get(i).getColumnaBloque()]==2) {
				numeroMinas = new Image("numerosImagen/2.png");
				bloques.get(i).getBloque().setImage(numeroMinas);

			}if(buscamina[bloques.get(i).getFilaBloque()][bloques.get(i).getColumnaBloque()]==3) {
				numeroMinas = new Image("numerosImagen/3.png");
				bloques.get(i).getBloque().setImage(numeroMinas);

			}if(buscamina[bloques.get(i).getFilaBloque()][bloques.get(i).getColumnaBloque()]==4) {
				numeroMinas = new Image("numerosImagen/4.png");
				bloques.get(i).getBloque().setImage(numeroMinas);

			}if(buscamina[bloques.get(i).getFilaBloque()][bloques.get(i).getColumnaBloque()]==5) {
				numeroMinas = new Image("numerosImagen/5.png");
				bloques.get(i).getBloque().setImage(numeroMinas);

			}if(buscamina[bloques.get(i).getFilaBloque()][bloques.get(i).getColumnaBloque()]==6) {
				numeroMinas = new Image("numerosImagen/6.png");
				bloques.get(i).getBloque().setImage(numeroMinas);

			}if(buscamina[bloques.get(i).getFilaBloque()][bloques.get(i).getColumnaBloque()]==7) {
				numeroMinas = new Image("numerosImagen/7.png");
				bloques.get(i).getBloque().setImage(numeroMinas);

			}if(buscamina[bloques.get(i).getFilaBloque()][bloques.get(i).getColumnaBloque()]==8) {
				numeroMinas = new Image("numerosImagen/8.png");
				bloques.get(i).getBloque().setImage(numeroMinas);

			}
		}
	}
	/**
	 * validar si el jugador encontro bomba
	 */
	public void	casillaBomba(int fila, int columna) {	
		if(buscamina[fila][columna]== 9) {
			perdio = new Image ("imagenes/perdio.jpg");
			caritaFeliz.setImage(perdio);
			ImageView mina = new ImageView("imagenes/mina.png");
			mina.setFitHeight(40);
			mina.setFitWidth(40);
			tablero.add(mina, columna, fila);
			mostrarBombas();
		}
	}
	/**
	 *mostrar todas las bombas en el tablero 
	 */
	public void mostrarBombas() {
		for (int i = 0; i < buscamina.length; i++) {
			for (int j = 0; j < buscamina.length; j++) {
				if(buscamina[i][j]== 9 ) {
					ImageView mina = new ImageView("imagenes/mina.png");
					mina.setFitHeight(40);
					mina.setFitWidth(40);
					tablero.add(mina, j,i);
				}
			}
		}
	}
	public void llenarMatriz(){
		/**
		 * 0 -> sin mina
		 * 9 -> mina
		 */
		for (int i = 0; i < buscamina.length; i++) {
			for (int j = 0; j < buscamina.length; j++) {
				if (buscamina[i][j]== 0 || buscamina[i][j]== 9) {
					bloques.get(posicion).setFilaBloque(i);
					bloques.get(posicion).setColumnaBloque(j);
					tablero.add(bloques.get(posicion).getBloque(),j,i);
					posicion++;  				
				}
			}
		}
	}
	public int contarMinas() {
		for (int i = 0; i < buscamina.length; i++) {
			for (int j = 0; j < buscamina.length; j++) {
				if (buscamina[i][j]== 9) {
					contMinas++;
				}
			}
		}
		return contMinas;
	}
	public void leerArchivo(String rutaArchivo) {
		FileReader lectorArchivo = null;
		try {
			lectorArchivo = new FileReader(rutaArchivo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader buffer = new BufferedReader(lectorArchivo);
		try {
			String readLine = buffer.readLine();
			int contador=0;
			while (readLine != null) {
				String []values =readLine.split("");
				for (int i = 0; i < values.length; i++) {
					buscamina[contador][i]=Integer.parseInt(values[i]); 
				}
				contador++;
				readLine = buffer.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int[][] getBuscamina() {
		return buscamina;
	}
}

