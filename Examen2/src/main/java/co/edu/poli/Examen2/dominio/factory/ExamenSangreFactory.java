
package co.edu.poli.Examen2.dominio.factory;

import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/**
 * Creador concreto del patrón Factory Method.
 *
 * Se encarga de construir los exámenes de sangre.
 */
public class ExamenSangreFactory extends ExamenFactory {

    /**
     * Crea un examen de sangre utilizando las
     * validaciones de la entidad de dominio.
     *
     * @return examen de sangre válido
     */
    @Override
    public ExamenSangre crearExamen(
            String codigo,
            String paciente,
            double costo,
            String tipo,
            String rh) {

        return new ExamenSangre(
                codigo,
                paciente,
                costo,
                tipo,
                rh);
    }
}
