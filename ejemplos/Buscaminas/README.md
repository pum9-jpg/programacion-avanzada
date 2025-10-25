# Buscaminas en Java

Un juego clásico de Buscaminas implementado en Java con interfaz gráfica Swing.

## Características

1. Tablero 10x10 con 15 minas
2. Primer movimiento seguro
3. Expansión automática de áreas vacías
4. Sistema de banderas con click derecho
5. Temporizador integrado
6. Detección automática de victoria/derrota
7. Reinicio completo del juego

## Estructura del Proyecto

```
buscaminas/
├── Principal.java          # Punto de entrada
├── VentanaUI.java          # Interfaz gráfica
├── Terreno.java            # Lógica del juego
└── Celda.java              # Representación de celdas
```

## Cómo Ejecutar

```bash
# Compilar todos los archivos
javac *.java

# Ejecutar el juego
java Principal
```

## Diagramas UML

### 1. Diagrama de Casos de Uso

```mermaid
graph TD
    A[Jugador] --> B[Iniciar Juego]
    A --> C[Descubrir Celda]
      
    C --> J[Validar Movimiento]
    J --> K{Es Mina?}
    K -->|Sí| L[Fin del Juego - Derrota]
    K -->|No| M[Mostrar Número]
    
    M --> N{Área Vacía?}
    N -->|Sí| O[Expandir Área]
    N -->|No| P[Actualizar Vista]
    
    O --> Q{Todas Celdas<br>Descubiertas?}
    Q -->|Sí| R[Fin del Juego - Victoria]
    
```

EXPLICACIÓN DEL DIAGRAMA:

Casos de Uso Principales:

INICIAR JUEGO

    Propósito: Configurar y comenzar nueva partida
    
    Flujo: Crea tablero 10x10 con 15 minas (15% densidad)
    
    Precondición: Aplicación ejecutándose

DESCUBRIR CELDA

    Propósito: Revelar contenido de celda con click izquierdo
    
    Flujo Complejo:
    
    Si es primer movimiento → genera minas excluyendo área segura
    
    Si es mina → fin del juego (derrota)
    
    Si es número → muestra número con color específico
    
    Si es vacía (0) → expande área automáticamente

MARCAR CELDA CON BANDERA

    Propósito: Marcar/desmarcar minas sospechosas con click derecho
    
    Precondición: Celda no descubierta
    
    Efecto: Actualiza contador de minas restantes
    
    RELACIONES PRINCIPALES:
    Inclusión: Descubrir Celda incluye Validar Movimiento
    
    Extensión: Descubrir Celda puede extenderse a Expandir Área
    
    Generalización: Dificultad fija en 10x10

CARACTERÍSTICAS CLAVE:

    
    1.-Expansión automática: Áreas vacías se descubren automáticamente
    
    2.-Feedback inmediato: Colores diferentes para cada número
    


### 2. Diagrama de Clases

```mermaid
classDiagram
    class Principal {
        +main(String[] args)
    }

    class VentanaUI {
        -Celda[][] botones
        -Terreno terreno
        -int DIMENSION = 10
        -Timer timer
        -boolean juegoActivo
        +VentanaUI()
        -crearPanelBotones() JPanel
        -manejarClickIzquierdo(Celda)
        -manejarClickDerecho(Celda)
        -actualizarVistaCompleta()
    }

    class Terreno {
        -int dimension
        -Celda[][] celdas
        -int totalMinas
        -int celdasDescubiertas
        -boolean primerMovimiento
        +Terreno(int)
        +descubrirCelda(int, int) boolean
        +marcarCelda(int, int)
        +generarMinas(int, int)
        +verificarVictoria() boolean
        +revelarTodasLasMinas()
        +getMinasRestantes() int
        +getCelda(int, int) Celda
    }

    class Celda {
        -int fila
        -int columna
        -int valor
        -boolean esMina
        -boolean descubierta
        -boolean marcada
        -int minasAlrededor
        +Celda(int, int)
        +descubrir()
        +marcar()
        +convertirEnMina()
        +esMina() boolean
        +estaDescubierta() boolean
        +estaMarcada() boolean
        +estaVacia() boolean
        -actualizarApariencia()
    }

    Principal --> VentanaUI
    VentanaUI "1" *-- "1" Terreno
    VentanaUI "1" *-- "*" Celda
    Terreno "1" *-- "*" Celda
    Celda --|> JButton
```

EXPLICACIÓN DEL DIAGRAMA:

Arquitectura Modelo-Vista:

Modelo (Terreno): Lógica del juego y estado del tablero

Vista (VentanaUI): Interfaz gráfica y presentación

Controlador: Integrado en VentanaUI (manejo de eventos)

Clases Principales:

VENTANAUi - Capa de Presentación

Gestiona toda la interfaz gráfica

Controla eventos de mouse y teclado

Coordina actualizaciones visuales

TERRENO - Capa de Lógica

Administra el estado del juego

Contiene la matriz de celdas

Implementa reglas del Buscaminas

CELDA - Componente Base

Representa cada posición del tablero

Hereda de JButton para integración Swing

Auto-gestiona su apariencia visual

RELACIONES PRINCIPALES:

1.-Composición Fuerte: VentanaUI contiene Terreno y Celdas (si se destruye la ventana, se destruye todo)
2.-Agregación: Terreno contiene Celdas pero pueden existir independientemente
3.-Herencia: Celda extiende JButton para funcionalidad de botón

CARACTERÍSTICAS CLAVE:

1.-Encapsulamiento: Cada clase tiene responsabilidades bien definidas
2.-Acoplamiento bajo: Las clases se comunican through interfaces claras
3.-Cohesión alta: Cada clase tiene un propósito específico
4.-Extensibilidad: Fácil agregar nuevas características



### 3. Diagrama de Actividades

```mermaid
graph TD
    A[Inicio Programa] --> B[Configurar Look & Feel]
    B --> C[Crear Ventana Principal]
    C --> D[Inicializar Terreno 10x10]
    D --> E[Crear Botones Celda]
    E --> F[Mostrar Interfaz Gráfica]
    F --> G{Esperar Acción Usuario}
    

    G --> I[Click Derecho Celda]
 
    
    H --> K{Primer Movimiento?}
    K -->|Sí| L[Generar Minas<br>excluyendo área segura]
    K -->|No| M
    
    L --> M{La celda es mina?}
    M -->|Sí| N[Revelar Todas las Minas]
    N --> O[Mostrar Mensaje Derrota]
    O --> P[Desactivar Juego]
    
    M -->|No| Q[Descubrir Celda]
    Q --> R{Es celda vacía?}
    R -->|Sí| S[Expandir Área Automáticamente]
    R -->|No| T[Mostrar Número]
    
    S --> U[Actualizar Vista Completa]
    T --> U
    
    U --> V{Verificar Victoria?}
    V -->|Sí| W[Mostrar Mensaje Victoria]
    W --> P
    V -->|No| G
    
    style A fill:#e3f2fd
    style P fill:#f5f5f5
    style O fill:#ffebee
    style W fill:#e8f5e8
```

EXPLICACIÓN DEL DIAGRAMA:
Flujo Principal del Juego:

INICIALIZACIÓN 

Configuración del entorno gráfico
Creación del modelo de datos (Terreno)
Construcción de la interfaz

BUCLE PRINCIPAL

Estado de espera constante por input del usuario
Tres tipos de acciones posibles

GESTIÓN DE EVENTOS 

Procesos Clave:

Generación Inteligente de Minas :

Excluye celda del primer click y sus 8 vecinos
Garantiza juego justo desde el inicio

Expansión Recursiva:

Algoritmo que descubre automáticamente áreas contiguas vacías
Mejora experiencia de usuario

Verificación de Victoria:

Fórmula: celdasDescubiertas == (totalCeldas - totalMinas)
Detección automática sin intervención del usuario

TRANSICIONES CRÍTICAS:

Primer Movimiento: Activa generación segura de minas
Descubrimiento de Mina: Transición a estado de derrota
Expansión Completa: Posible transición a victoria
Reinicio: Ciclo completo de reinicialización

CARACTERÍSTICAS CLAVE:

Flujo Continuo: Bucle infinito hasta victoria/derrota
Manejo de Estados: Transiciones claras entre estados del juego
Recursividad: Expansión de áreas usa llamadas recursivas
