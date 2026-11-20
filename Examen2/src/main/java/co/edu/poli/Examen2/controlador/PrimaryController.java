package co.edu.poli.Examen2.controlador;
	
import java.util.List;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;
import co.edu.poli.Examen2.puertos.entrada.GestionExamenesUseCase;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PrimaryController {

    @FXML private TextField txtCodigoExamen;
    @FXML private TextField txtNombrePaciente;
    @FXML private TextField txtCosto;
    @FXML private ComboBox<String> cmbTipoSangre;
    @FXML private RadioButton rbPositivo;
    @FXML private RadioButton rbNegativo;
    @FXML private ToggleGroup tgFactorRH;
    @FXML private TextArea txtAreaMensajes;


private GestionExamenesUseCase servicio;

/**
 * Recibe el puerto de entrada mediante inyección
 * de dependencias.
 *
 * @param servicio casos de uso de gestión de exámenes
 */
public void setServicio(GestionExamenesUseCase servicio) {
    this.servicio = java.util.Objects.requireNonNull(servicio);
}

@FXML
public void initialize() {
    // Inicialización de componentes JavaFX.
}


    @FXML
    private void handleGuardar() {
        try {
            String codigo = txtCodigoExamen.getText().trim();
            String nombre = txtNombrePaciente.getText().trim();
            String tipo = cmbTipoSangre.getValue();

            if (codigo.isEmpty() || nombre.isEmpty() || tipo == null) {
                throw new IllegalArgumentException(
                    "Complete todos los campos.");
            }

            double costo = Double.parseDouble(
                txtCosto.getText().trim());

            if (!Double.isFinite(costo) || costo < 0) {
                throw new IllegalArgumentException(
                    "El costo debe ser un número válido no negativo.");
            }

            String rh = rbPositivo.isSelected()
                ? "Positivo" : "Negativo";

            servicio.registrarExamen(
                    codigo, nombre, costo, tipo, rh);
            txtAreaMensajes.setText(
                "Examen guardado correctamente: " + codigo);

        } catch (NumberFormatException e) {
            txtAreaMensajes.setText(
                "Error: el costo debe ser numérico.");
        } catch (IllegalArgumentException e) {
            txtAreaMensajes.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    private void handleLimpiar() {
        txtCodigoExamen.clear();
        txtNombrePaciente.clear();
        txtCosto.clear();
        cmbTipoSangre.getSelectionModel().clearSelection();
        rbPositivo.setSelected(true);
        txtAreaMensajes.clear();
    }

    @FXML
    private void handleListar() {
        List<ExamenSangre> examenes = servicio.listarExamenes();

        if (examenes.isEmpty()) {
            txtAreaMensajes.setText("No hay exámenes registrados.");
            return;
        }

        StringBuilder resultado = new StringBuilder();

        for (ExamenSangre e : examenes) {
            resultado.append("Código: ")
                .append(e.getCodigoExamen())
                .append("\nPaciente: ")
                .append(e.getNombrePaciente())
                .append("\nCosto: $")
                .append(e.getCosto())
                .append("\nTipo: ")
                .append(e.getTipoSangre())
                .append("\nRH: ")
                .append(e.getFactorRh())
                .append("\n----------------------\n");
        }

        txtAreaMensajes.setText(resultado.toString());
    }

    @FXML
    private void handleSerializar() {
        try {
            servicio.serializar();
            txtAreaMensajes.setText(
                "Exámenes exportados correctamente a CSV.");
        } catch (RuntimeException e) {
            txtAreaMensajes.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    private void handleDeserializar() {
        try {
            servicio.deserializar();
            txtAreaMensajes.setText(
                "Exámenes importados correctamente desde CSV.");
        } catch (RuntimeException e) {
            txtAreaMensajes.setText("Error: " + e.getMessage());
        }
    }
}

