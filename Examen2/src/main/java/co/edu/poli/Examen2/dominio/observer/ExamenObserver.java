
package co.edu.poli.Examen2.dominio.observer;

/**
 * Interfaz Observer.
 * Define el contrato para recibir notificaciones
 * relacionadas con los exámenes de sangre.
 */
public interface ExamenObserver {

    /**
     * Recibe una notificación del sistema.
     *
     * @param evento tipo de evento ocurrido
     * @param mensaje descripción del evento
     */
    void actualizar(String evento, String mensaje);
}
