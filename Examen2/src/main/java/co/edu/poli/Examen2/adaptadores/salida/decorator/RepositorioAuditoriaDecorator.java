
package co.edu.poli.Examen2.adaptadores.salida.decorator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;
import co.edu.poli.Examen2.puertos.salida.ExamenRepository;

/**
 * Decorador que incorpora auditoría al repositorio
 * sin modificar su implementación original.
 *
 * Patrón GoF: Decorator (estructural).
 */
public class RepositorioAuditoriaDecorator implements ExamenRepository {

    private final ExamenRepository repositorio;
    private final Path rutaAuditoria;

    /**
     * Construye el decorador.
     *
     * @param repositorio repositorio que será decorado
     * @param rutaAuditoria archivo donde se registrarán los eventos
     */
    public RepositorioAuditoriaDecorator(
            ExamenRepository repositorio,
            Path rutaAuditoria) {

        this.repositorio = Objects.requireNonNull(repositorio);
        this.rutaAuditoria = Objects.requireNonNull(rutaAuditoria);
    }

    /**
     * Registra un evento de auditoría.
     */
    private void registrarEvento(String mensaje) {
        String registro = LocalDateTime.now()
                + " | " + mensaje + System.lineSeparator();

        try {
            Files.writeString(
                    rutaAuditoria,
                    registro,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);

        } catch (IOException e) {
            throw new IllegalStateException(
                    "No se pudo registrar la auditoría.", e);
        }
    }

    /**
     * Guarda un examen y registra la operación.
     */
    @Override
    public void guardar(ExamenSangre examen) {
        repositorio.guardar(examen);

        registrarEvento(
                "EXAMEN_GUARDADO | Código: "
                + examen.getCodigoExamen());
    }

    /**
     * Consulta los exámenes y registra la operación.
     */
    @Override
    public List<ExamenSangre> listar() {
        List<ExamenSangre> examenes = repositorio.listar();

        registrarEvento(
                "EXAMENES_LISTADOS | Cantidad: "
                + examenes.size());

        return examenes;
    }

    /**
     * Busca un examen por su código.
     */
    @Override
    public Optional<ExamenSangre> buscarPorCodigo(String codigo) {
        Optional<ExamenSangre> resultado =
                repositorio.buscarPorCodigo(codigo);

        registrarEvento(
                "BUSQUEDA_EXAMEN | Código: " + codigo);

        return resultado;
    }

    /**
     * Reemplaza los registros y documenta la operación.
     */
    @Override
    public void reemplazarTodos(List<ExamenSangre> examenes) {
        repositorio.reemplazarTodos(examenes);

        registrarEvento(
                "EXAMENES_IMPORTADOS | Cantidad: "
                + examenes.size());
    }
}
