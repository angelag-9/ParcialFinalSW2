
package co.edu.poli.Examen2.puertos.salida;

import java.util.List;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/**
 * Puerto de salida para exportar e importar
 * exámenes mediante archivos.
 */
public interface ArchivoExamenesPort {

    void escribir(List<ExamenSangre> examenes);

    List<ExamenSangre> leer();
}

