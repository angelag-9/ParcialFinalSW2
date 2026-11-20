
package co.edu.poli.Examen2.dominio.factory;

import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/**
 * Creador abstracto del patrón Factory Method.
 *
 * Define el contrato para crear exámenes de sangre
 * sin depender de un creador concreto.
 */
public abstract class ExamenFactory {

    /**
     * Método de fábrica para crear un examen.
     *
     * @param codigo código del examen
     * @param paciente nombre del paciente
     * @param costo costo del examen
     * @param tipo tipo sanguíneo
     * @param rh factor Rh
     * @return examen de sangre creado
     */
    public abstract ExamenSangre crearExamen(
            String codigo,
            String paciente,
            double costo,
            String tipo,
            String rh);
}
