
package co.edu.poli.Examen2.vista;

import java.io.IOException;
import java.nio.file.Path;

import co.edu.poli.Examen2.adaptadores.salida.ArchivoExamenesAdapter;
import co.edu.poli.Examen2.adaptadores.salida.RepositorioEnMemoria;
import co.edu.poli.Examen2.aplicacion.servicio.GestionExamenesService;
import co.edu.poli.Examen2.controlador.PrimaryController;
import co.edu.poli.Examen2.dominio.factory.ExamenFactory;
import co.edu.poli.Examen2.dominio.factory.ExamenSangreFactory;
import co.edu.poli.Examen2.puertos.entrada.GestionExamenesUseCase;
import co.edu.poli.Examen2.adaptadores.salida.decorator.RepositorioAuditoriaDecorator;
import co.edu.poli.Examen2.puertos.salida.ExamenRepository;
import co.edu.poli.Examen2.dominio.observer.ExamenNotificador;
import co.edu.poli.Examen2.dominio.observer.ConsolaExamenObserver;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal de la aplicación JavaFX.
 *
 * Configura e inyecta las dependencias de la
 * Arquitectura Hexagonal y el patrón Factory Method.
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        // Adaptadores de salida
    	ExamenRepository repositorio =
    	        new RepositorioAuditoriaDecorator(
    	                new RepositorioEnMemoria(),
    	                Path.of("auditoria.log"));

        ArchivoExamenesAdapter archivo =
                new ArchivoExamenesAdapter(
                        Path.of("examenes.csv"));

        // Patrón Factory Method
        ExamenFactory fabrica =
                new ExamenSangreFactory();

        // Servicio de aplicación
     // Patrón Observer
        ExamenNotificador notificador = new ExamenNotificador();

        notificador.agregarObservador(
                new ConsolaExamenObserver());

        // Servicio con Factory Method y Observer
        GestionExamenesUseCase servicio =
                new GestionExamenesService(
                        repositorio, archivo, fabrica, notificador);

        // Cargar la interfaz gráfica
        FXMLLoader loader = new FXMLLoader(
                App.class.getResource(
                        "/co/edu/poli/Examen2/primary.fxml"));

        Parent root = loader.load();

        // Inyectar los casos de uso
        PrimaryController controlador =
                loader.getController();

        controlador.setServicio(servicio);

        Scene scene = new Scene(root, 640, 580);

        stage.setTitle("Exámenes de Sangre");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Inicia la aplicación.
     *
     * @param args argumentos de ejecución
     */
    public static void main(String[] args) {
        launch(args);
    }
}
