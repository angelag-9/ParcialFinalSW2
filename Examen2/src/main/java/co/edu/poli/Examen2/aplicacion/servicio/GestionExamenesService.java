
package co.edu.poli.Examen2.aplicacion.servicio;

import java.util.List;
import java.util.Objects;

import co.edu.poli.Examen2.dominio.factory.ExamenFactory;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;
import co.edu.poli.Examen2.dominio.observer.ExamenNotificador;
import co.edu.poli.Examen2.puertos.entrada.GestionExamenesUseCase;
import co.edu.poli.Examen2.puertos.salida.ExamenRepository;
import co.edu.poli.Examen2.puertos.salida.ArchivoExamenesPort;

/**
 * Servicio de aplicación para gestionar exámenes de sangre.
 *
 * Implementa los casos de uso y utiliza Factory Method
 * y Observer para la creación y notificación de eventos.
 */
public class GestionExamenesService implements GestionExamenesUseCase {

    private final ExamenRepository repositorio;
    private final ArchivoExamenesPort archivo;
    private final ExamenFactory fabrica;
    private final ExamenNotificador notificador;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param repositorio puerto de persistencia
     * @param archivo puerto de archivos
     * @param fabrica creador de exámenes
     * @param notificador sujeto del patrón Observer
     */
    public GestionExamenesService(
            ExamenRepository repositorio,
            ArchivoExamenesPort archivo,
            ExamenFactory fabrica,
            ExamenNotificador notificador) {

        this.repositorio = Objects.requireNonNull(repositorio);
        this.archivo = Objects.requireNonNull(archivo);
        this.fabrica = Objects.requireNonNull(fabrica);
        this.notificador = Objects.requireNonNull(notificador);
    }

    /**
     * Registra un examen utilizando Factory Method.
     */
    @Override
    public void registrarExamen(
            String codigo,
            String paciente,
            double costo,
            String tipo,
            String rh) {

        ExamenSangre examen = fabrica.crearExamen(
                codigo, paciente, costo, tipo, rh);

        guardarExamen(examen);
    }

    /**
     * Guarda un examen y notifica a los observadores.
     */
    @Override
    public void guardarExamen(ExamenSangre examen) {

        if (examen == null) {
            throw new IllegalArgumentException(
                    "El examen no puede ser nulo.");
        }

        repositorio.guardar(examen);

        notificador.notificar(
                "EXAMEN_REGISTRADO",
                "Código: " + examen.getCodigoExamen());
    }

    /**
     * Consulta los exámenes registrados.
     */
    @Override
    public List<ExamenSangre> listarExamenes() {
        return repositorio.listar();
    }

    /**
     * Exporta los exámenes al archivo CSV.
     */
    @Override
    public void serializar() {
        archivo.escribir(repositorio.listar());
    }

    /**
     * Recupera los exámenes desde el archivo CSV
     * y notifica a los observadores.
     */
    @Override
    public void deserializar() {

        List<ExamenSangre> examenes = archivo.leer();

        repositorio.reemplazarTodos(examenes);

        notificador.notificar(
                "EXAMENES_IMPORTADOS",
                "Cantidad: " + examenes.size());
    }
}
