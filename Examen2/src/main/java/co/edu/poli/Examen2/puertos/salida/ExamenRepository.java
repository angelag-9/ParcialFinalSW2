
package co.edu.poli.Examen2.puertos.salida;

import java.util.List;
import java.util.Optional;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/**
 * Puerto de salida para gestionar la persistencia
 * de los exámenes de sangre.
 */
public interface ExamenRepository {

    void guardar(ExamenSangre examen);

    List<ExamenSangre> listar();

    Optional<ExamenSangre> buscarPorCodigo(String codigo);

    void reemplazarTodos(List<ExamenSangre> examenes);
}
