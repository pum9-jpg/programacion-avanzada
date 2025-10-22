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
