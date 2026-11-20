
package co.edu.poli.Examen2.adaptadores.salida;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;
import co.edu.poli.Examen2.puertos.salida.ExamenRepository;

/**
 * Adaptador que almacena exámenes temporalmente en memoria.
 */
public class RepositorioEnMemoria implements ExamenRepository {

    private final List<ExamenSangre> examenes = new ArrayList<>();

    @Override
    public void guardar(ExamenSangre examen) {
        if (buscarPorCodigo(examen.getCodigoExamen()).isPresent()) {
            throw new IllegalArgumentException(
                "Ya existe un examen con ese código.");
        }
        examenes.add(examen);
    }

    @Override
    public List<ExamenSangre> listar() {
        return new ArrayList<>(examenes);
    }

    @Override
    public Optional<ExamenSangre> buscarPorCodigo(String codigo) {
        return examenes.stream()
            .filter(e -> e.getCodigoExamen().equals(codigo))
            .findFirst();
    }

    @Override
    public void reemplazarTodos(List<ExamenSangre> nuevos) {
        examenes.clear();
        examenes.addAll(nuevos);
    }
}

