✔️ Guía de clonación del repositorio

Este repositorio contiene el proyecto base para el desarrollo de la actividad.

✔️ 1. Hacer Fork del repositorio
Haga clic en el botón Fork (parte superior derecha).
Seleccione su cuenta personal.
Espere a que se cree la copia del repositorio en su perfil.
# Sistema de Gestión de Exámenes de Sangre

## Descripción del proyecto

Aplicación de escritorio desarrollada en Java y JavaFX para gestionar información de exámenes de sangre. Permite registrar, consultar, serializar y deserializar exámenes, utilizando una arquitectura hexagonal y tres patrones de diseño GoF.

El proyecto corresponde al Parcial Final de Software II del Politécnico Grancolombiano.

## Tecnologías utilizadas

- Java 21
- JavaFX 21
- Maven
- Eclipse IDE
- JUnit 5 para pruebas unitarias
- JavaDoc para documentación
- SonarQube para análisis estático del código
- Git y GitHub para control de versiones

## Arquitectura del proyecto

El sistema utiliza arquitectura hexagonal, separando la lógica de negocio de la interfaz gráfica y de los mecanismos de almacenamiento.

### Organización de paquetes

| Paquete | Responsabilidad |
|---|---|
| `dominio.modelo` | Entidad `ExamenSangre` y sus validaciones |
| `dominio.factory` | Creación de exámenes mediante Factory Method |
| `dominio.observer` | Gestión de notificaciones mediante Observer |
| `puertos.entrada` | Contrato de los casos de uso |
| `puertos.salida` | Contratos de persistencia y manejo de archivos |
| `aplicacion.servicio` | Implementación de los casos de uso |
| `adaptadores.salida` | Repositorio en memoria y almacenamiento en CSV |
| `adaptadores.salida.decorator` | Auditoría de operaciones mediante Decorator |
| `controlador` | Comunicación entre la interfaz y los casos de uso |
| `vista` | Inicialización y presentación de la interfaz JavaFX |

## Funcionalidades

- Registrar exámenes de sangre.
- Validar los datos ingresados.
- Listar los exámenes registrados.
- Serializar los registros en un archivo CSV.
- Deserializar registros desde un archivo CSV.
- Registrar operaciones en un archivo de auditoría.
- Notificar eventos mediante el patrón Observer.

## Patrones de diseño implementados

### Factory Method — Creacional

Se implementa mediante `ExamenFactory` y `ExamenSangreFactory`.

Permite separar la creación de objetos `ExamenSangre` de la lógica de gestión de exámenes.

### Decorator — Estructural

Se implementa mediante `RepositorioAuditoriaDecorator`, que utiliza la interfaz `ExamenRepository`.

Permite añadir auditoría a las operaciones del repositorio sin modificar la implementación de `RepositorioEnMemoria`.

### Observer — Comportamiento

Se implementa mediante `ExamenObserver`, `ExamenNotificador` y `ConsolaExamenObserver`.

Permite informar sobre eventos relacionados con el registro y la importación de exámenes.

## Requisitos de instalación

Antes de ejecutar el proyecto, se necesita:

1. JDK 21.
2. Eclipse IDE con soporte para Maven.
3. Maven configurado en Eclipse.
4. Conexión a Internet para descargar las dependencias durante la primera compilación.

No se requiere instalar una base de datos, ya que el proyecto utiliza un repositorio en memoria y archivos CSV.

## Instalación y configuración

1. Clonar el repositorio:

   ```bash
   git clone https://github.com/angelag-9/ParcialFinalSW2.git
   ```

2. Abrir Eclipse.
3. Seleccionar `File > Import > Maven > Existing Maven Projects`.
4. Seleccionar la carpeta `ParcialFinalSW2/Examen2`.
5. Finalizar la importación.
6. Verificar que el proyecto utilice JDK 21.
7. Esperar a que Maven descargue las dependencias declaradas en `pom.xml`.

## Ejecución

Desde Eclipse, seleccionar el proyecto y ejecutar `Run As > Maven build...`.

En el campo `Goals`, ingresar:

```bash
javafx:run
```

También puede ejecutarse desde una terminal ubicada en la carpeta `Examen2`:

```bash
mvn javafx:run
```

La aplicación abrirá una interfaz gráfica para gestionar los exámenes.

## Almacenamiento de información

El sistema utiliza:

- `RepositorioEnMemoria`: mantiene temporalmente los registros durante la ejecución.
- `examenes.csv`: permite guardar y recuperar registros mediante serialización y deserialización.
- `auditoria.log`: registra las operaciones realizadas por el repositorio decorado.

Los registros guardados únicamente en memoria no permanecen después de cerrar la aplicación. Para conservarlos, debe utilizarse la opción de serialización.

## Pruebas y calidad del software

El proyecto contempla pruebas unitarias con JUnit 5, documentación JavaDoc y análisis estático mediante SonarQube.

Los resultados de las pruebas y el análisis se incorporarán como evidencias al repositorio y a su Wiki una vez ejecutados.

## Documentación

La documentación técnica se encuentra en los comentarios JavaDoc del código fuente.

Los diagramas UML y las evidencias de calidad del software se organizan en la Wiki del repositorio.

## Uso de inteligencia artificial

Durante el desarrollo se utilizó ChatGPT como herramienta de apoyo para comprender la arquitectura hexagonal, estudiar los patrones GoF, orientar la refactorización, revisar código y preparar documentación técnica.

El código y los resultados deben ser revisados y validados mediante la ejecución del proyecto y las herramientas de análisis correspondientes.

## Autora

**Ángela Gineth Estupiñán Sánchez**

Ingeniería de Sistemas  
Politécnico Grancolombiano  
Asignatura: Software II
