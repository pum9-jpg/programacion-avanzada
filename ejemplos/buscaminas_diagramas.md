## Diagrama de Clases

```mermaid
classDiagram
    direction LR
    
    class Principal {
        +main()
    }
    
    class VentanaUI {
        -int DIMENSION
        -int[][] terreno
        -boolean juegoActivo
        -int celdasReveladas
        -final int CELDAS_SEGURAS
        
        +VentanaUI()
        -iniciarJuego()
        -initializeTerreno()
        -handleButtonClick(Celda)
        -terminarJuego(haGanado: boolean)
    }
    
    class Celda {
        -int valor
        -boolean esVisible
        
        +Celda(v)
        +getValor() int
        +getEsVisible() boolean
        +setEsVisible(ev)
    }

    Principal --> VentanaUI : inicia
    VentanaUI o-- Celda : contiene [10x10]
    graph TD
    Actor(Jugador) --> A(Jugar)
    
    A --> B(Reiniciar Juego)
    A --> C(Revelar Celda)

    C --> D(Terminar Juego (Perder))
    C --> E(Terminar Juego (Ganar))
    
    B -- incluye --> F(Generar Tablero)
    ```markdown
## Diagrama de Actividades

```mermaid
flowchart TD
    start(Inicio del Juego) --> A{Click en Celda};

    A --> B{¿Juego activo y Celda invisible?};
    
    B -- No --> end(Fin del Turno);
    B -- Sí --> C(Obtener valor de Celda);
    
    C --> D{Valor = -1 (Mina)?};
    
    D -- Sí --> E(Revelar Celda con "💥");
    E --> F(Terminar Juego (Perder));
    F --> H(Finalizar Sistema);

    D -- No --> I(Deshabilitar Celda y celdasReveladas++);
    
    I --> J{¿celdasReveladas == CELDAS_SEGURAS?};
    
    J -- Sí --> K(Terminar Juego (Ganar));
    K --> H;
    
    J -- No --> end;