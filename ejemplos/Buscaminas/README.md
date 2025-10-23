# Diseno

``` mermaid

classDiagram
    class Principal {
        +main(String[] args) void
    }

    class VentanaUI {
        -Celda[][] botones
        -int DIMENSION
        -Terreno terreno
        +VentanaUI()
        -makeButton(int valor, Font font) Celda
        -handleButtonClick(Celda boton) void
        -getColorPorNumero(int n) Color
        -mostrarTodosLosBotones() void
        -crearPanelBotones() JPanel
    }

    class Celda {
        -int valor
        -boolean esVisible
        +Celda(int v)
        +getValor() int
        +setValor(int v) void
        +getEsVisible() boolean
        +setEsVisible(boolean ev) void
    }

    class Terreno {
        -int dimension
        -int[][] celdas
        +Terreno(int dimension)
        +getDimension() int
        +getValor(int fila, int columna) int
        -generarMinas() void
        -generarContadores() void
    }

    %% Relaciones de herencia
    VentanaUI --|> JFrame
    Celda --|> JButton

    %% Relaciones de composición/agregación
    VentanaUI --> Terreno : contiene
    VentanaUI --> Celda : contiene (matriz)
    Terreno --> "2D" int : compone

    %% Relaciones de uso/dependencia
    Principal --> VentanaUI : crea instancia
    VentanaUI ..> Font : usa
    VentanaUI ..> Color : usa
    VentanaUI ..> JPanel : usa
    VentanaUI ..> GridBagLayout : usa
    Terreno ..> Random : usa

```

Explicación del diagrama:

1. Principal: Clase principal que inicia la aplicación
2. VentanaUI: Interfaz gráfica principal que extiende JFrame
3. Celda: Representa cada celda del tablero, extiende JButton
4. Terreno: Maneja la lógica del tablero y distribución de minas

Relaciones principales:

1. Herencia: VentanaUI hereda de JFrame, Celda hereda de JButton
2. Composición: VentanaUI contiene una matriz de Celdas y un objeto Terreno
3. Agregación: Terreno contiene una matriz bidimensional de enteros
4. Dependencia: Las clases usan varias clases de Swing y AWT

Características clave:

1. VentanaUI maneja toda la interfaz gráfica y eventos
2. Celda encapsula el estado de cada posición (valor y visibilidad)
3. Terreno se encarga de la generación del tablero y cálculo de minas adyacentes
4. Principal coordina el inicio de la aplicación
