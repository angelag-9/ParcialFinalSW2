
package co.edu.poli.Examen2.adaptadores.salida;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;
import co.edu.poli.Examen2.puertos.salida.ArchivoExamenesPort;

/**
 * Adaptador de persistencia de exámenes mediante archivo CSV.
 */
public class ArchivoExamenesAdapter implements ArchivoExamenesPort {

    private final Path ruta;

    public ArchivoExamenesAdapter(Path ruta) {
        this.ruta = ruta;
    }

    @Override
    public void escribir(List<ExamenSangre> examenes) {
        List<String> lineas = new ArrayList<>();

        for (ExamenSangre e : examenes) {
            lineas.add(
                e.getCodigoExamen() + ";" +
                e.getNombrePaciente() + ";" +
                e.getCosto() + ";" +
                e.getTipoSangre() + ";" +
                e.getFactorRh()
            );
        }

        try {
            Files.write(ruta, lineas, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            throw new IllegalStateException(
                "No se pudieron guardar los exámenes.", ex);
        }
    }

    @Override
    public List<ExamenSangre> leer() {
        List<ExamenSangre> examenes = new ArrayList<>();

        try {
            if (!Files.exists(ruta)) {
                throw new IllegalStateException(
                    "No existe un archivo de exámenes guardado.");
            }

            for (String linea : Files.readAllLines(
                    ruta, StandardCharsets.UTF_8)) {

                String[] datos = linea.split(";", -1);

                if (datos.length != 5) {
                    throw new IllegalArgumentException(
                        "El archivo contiene un registro inválido.");
                }

                ExamenSangre examen = new ExamenSangre(
                    datos[0],
                    datos[1],
                    Double.parseDouble(datos[2]),
                    datos[3],
                    datos[4]
                );

                examenes.add(examen);
            }

            return examenes;

        } catch (IOException ex) {
            throw new IllegalStateException(
                "No se pudieron leer los exámenes.", ex);
        }
    }
}
