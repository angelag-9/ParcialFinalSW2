
package co.edu.poli.Examen2.dominio.modelo;

/**
 * Representa un examen de sangre registrado en el sistema.
 *
 * Contiene los datos del examen y las reglas de negocio
 * necesarias para garantizar que sean válidos.
 *
 * Esta entidad pertenece al dominio de la Arquitectura
 * Hexagonal y no depende de JavaFX ni de la persistencia.
 */
public class ExamenSangre {

    private String codigoExamen;
    private String nombrePaciente;
    private double costo;
    private String tipoSangre;
    private String factorRh;

    /**
     * Construye un examen de sangre validando sus datos.
     *
     * @param codigoExamen código único del examen
     * @param nombrePaciente nombre completo del paciente
     * @param costo costo del examen
     * @param tipoSangre grupo sanguíneo (A, B, AB u O)
     * @param factorRh factor Rh (Positivo o Negativo)
     * @throws IllegalArgumentException si algún dato es inválido
     */
    public ExamenSangre(String codigoExamen, String nombrePaciente,
                       double costo, String tipoSangre, String factorRh) {

        if (codigoExamen == null || codigoExamen.isBlank()) {
            throw new IllegalArgumentException(
                "El código del examen es obligatorio.");
        }

        if (nombrePaciente == null || nombrePaciente.isBlank()) {
            throw new IllegalArgumentException(
                "El nombre del paciente es obligatorio.");
        }

        if (!Double.isFinite(costo) || costo < 0) {
            throw new IllegalArgumentException(
                "El costo debe ser un número válido no negativo.");
        }

        if (tipoSangre == null ||
                !java.util.List.of("A", "B", "AB", "O")
                    .contains(tipoSangre)) {
            throw new IllegalArgumentException(
                "El tipo sanguíneo no es válido.");
        }

        if (!"Positivo".equals(factorRh) &&
                !"Negativo".equals(factorRh)) {
            throw new IllegalArgumentException(
                "El factor RH no es válido.");
        }

        this.codigoExamen = codigoExamen.trim();
        this.nombrePaciente = nombrePaciente.trim();
        this.costo = costo;
        this.tipoSangre = tipoSangre;
        this.factorRh = factorRh;
    }

    /**
     * Obtiene el código del examen.
     *
     * @return código del examen
     */
    public String getCodigoExamen() {
        return codigoExamen;
    }

    /**
     * Obtiene el nombre del paciente.
     *
     * @return nombre del paciente
     */
    public String getNombrePaciente() {
        return nombrePaciente;
    }

    /**
     * Obtiene el costo del examen.
     *
     * @return costo del examen
     */
    public double getCosto() {
        return costo;
    }

    /**
     * Obtiene el tipo sanguíneo.
     *
     * @return tipo sanguíneo
     */
    public String getTipoSangre() {
        return tipoSangre;
    }

    /**
     * Obtiene el factor Rh.
     *
     * @return factor Rh
     */
    public String getFactorRh() {
        return factorRh;
    }

    /**
     * Devuelve una representación textual del examen.
     *
     * @return información del examen
     */
    @Override
    public String toString() {
        return "ExamenSangre{" +
                "codigoExamen='" + codigoExamen + '\'' +
                ", nombrePaciente='" + nombrePaciente + '\'' +
                ", costo=" + costo +
                ", tipoSangre='" + tipoSangre + '\'' +
                ", factorRh='" + factorRh + '\'' +
                '}';
    }
}
