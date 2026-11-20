
package co.edu.poli.Examen2.dominio.observer;

/**
 * Observador concreto que muestra los eventos
 * del sistema en la consola.
 */
public class ConsolaExamenObserver implements ExamenObserver {

    /**
     * Recibe y muestra una notificación.
     *
     * @param evento tipo de evento
     * @param mensaje descripción del evento
     */
    @Override
    public void actualizar(String evento, String mensaje) {

        System.out.println(
                "[OBSERVER] " + evento + " | " + mensaje);
    }
}
