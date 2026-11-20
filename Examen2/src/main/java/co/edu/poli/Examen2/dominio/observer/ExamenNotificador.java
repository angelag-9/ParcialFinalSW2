
package co.edu.poli.Examen2.dominio.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Sujeto del patrón Observer.
 * Administra los observadores y distribuye eventos.
 */
public class ExamenNotificador {

    private final List<ExamenObserver> observadores = new ArrayList<>();

    /**
     * Registra un observador.
     *
     * @param observador observador que recibirá eventos
     */
    public void agregarObservador(ExamenObserver observador) {
        Objects.requireNonNull(observador);

        if (!observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    /**
     * Elimina un observador registrado.
     *
     * @param observador observador que dejará de recibir eventos
     */
    public void eliminarObservador(ExamenObserver observador) {
        observadores.remove(observador);
    }

    /**
     * Notifica un evento a todos los observadores.
     *
     * @param evento nombre del evento
     * @param mensaje información relacionada con el evento
     */
    public void notificar(String evento, String mensaje) {
        for (ExamenObserver observador :
                new ArrayList<>(observadores)) {

            observador.actualizar(evento, mensaje);
        }
    }
}
