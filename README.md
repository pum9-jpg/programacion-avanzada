graph TD
    A[Jugador] --> B[Iniciar Juego]
    A --> C[Descubrir Celda]
    A --> D[Marcar Celda con Bandera]
    A --> E[Reiniciar Juego]
    A --> F[Consultar Tiempo]
    A --> G[Consultar Minas Restantes]
    
    B --> H{Configurar Dificultad}
    H --> I[10x10 - 15 minas]
    
    C --> J[Validar Movimiento]
    J --> K{Es Mina?}
    K -->|Sí| L[Fin del Juego - Derrota]
    K -->|No| M[Mostrar Número]
    
    M --> N{Área Vacía?}
    N -->|Sí| O[Expandir Área]
    N -->|No| P[Actualizar Vista]
    
    O --> Q{Todas Celdas<br>Descubiertas?}
    Q -->|Sí| R[Fin del Juego - Victoria]
    
    E --> S[Reiniciar Tablero]
    
    style A fill:#e1f5fe
    style L fill:#ffebee
    style R fill:#e8f5e8
    style B fill:#f3e5f5
