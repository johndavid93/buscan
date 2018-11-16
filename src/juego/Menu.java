package juego;
/**
 * @author Michael Rodriguez lozano
 *          David santiago Bohorquez
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Menu extends Application{
	    ArrayList<Bloque> bloques = new ArrayList<Bloque>();
 	    Pane pane = new Pane();
	@Override
	public void start(Stage primaryStage) throws Exception  {
		pane.setStyle("-fx-background-color: gray");
		primaryStage.setTitle("BUSCAMINAS");
	      /**
	       * crear bloques 
	       */
	     for (int i = 0; i < 100; i++) {
			bloques.add(new Bloque());
	     } 
		Tablero mapa = new Tablero(bloques);
        pane.getChildren().add(mapa.datos);
        pane.getChildren().add(mapa.tablero);
		Scene scene=new Scene(pane,400,450);
		primaryStage.setScene(scene);
		primaryStage.show();
		mapa.tablero.getChildren().forEach(casilla -> {
			casilla.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if (event.getButton() == MouseButton.PRIMARY) {
						javafx.scene.Node source = (javafx.scene.Node)event.getSource() ;
						Integer fila = GridPane.getRowIndex(source);
						Integer columna = GridPane.getColumnIndex(source);
                           mapa.casillaBomba(fila, columna);
                           mapa.cantidadBombasCercanas(fila, columna);
					}
				}
			});
		});
	}
	public static void main(String[] args) {
	launch(args);
	}
}