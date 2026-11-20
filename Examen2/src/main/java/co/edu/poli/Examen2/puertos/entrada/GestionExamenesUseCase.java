
package co.edu.poli.Examen2.puertos.entrada;

import java.util.List;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/**
 * Puerto de entrada para gestionar los exámenes de sangre.
 */
public interface GestionExamenesUseCase {

    /**
     * Registra un examen a partir de sus datos.
     */
    void registrarExamen(
            String codigo,
            String paciente,
            double costo,
            String tipo,
            String rh);

    /**
     * Guarda una entidad de examen existente.
     */
    void guardarExamen(ExamenSangre examen);

    /**
     * Obtiene los exámenes registrados.
     */
    List<ExamenSangre> listarExamenes();

    /**
     * Exporta los exámenes al archivo CSV.
     */
    void serializar();

    /**
     * Recupera los exámenes del archivo CSV.
     */
    void deserializar();
}
