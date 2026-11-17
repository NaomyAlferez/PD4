# Arquitectura y Diseño del Sistema

## 1. Patrón MVC + Capa de Base de Datos (JDBC)
El proyecto está estructurado aplicando separación de responsabilidades:
* **Modelo (`modelo`):** Clases que representan las entidades del dominio (`Stand`, `Editorial`, `Reservacion`, `EstadoStand`).
* **Acceso a Datos / DAO (`dao` y `config`):** `ConexionBD` administra la conexión mediante **JDBC** a la base de datos en memoria **H2**, mientras que los DAOs ejecutan las consultas `SELECT` de lectura.
* **Servicio (`servicio`):** `ServicioNotificaciones` procesa las reglas de negocio y plazos definidos.
* **Vista (`vista`):** `ConsolaVista` formatea e imprime en consola las tablas leídas y las alertas.
* **Controlador (`controlador`):** `SistemaControlador` comunica los DAOs y servicios con la Vista.

---

## 2. Diagrama de Clases (UML)

```mermaid
classDiagram
    class EstadoStand {
        <<enumeration>>
        DISPONIBLE
        RESERVADO
        OCUPADO
        BLOQUEADO
    }

    class Stand {
        -int id
        -double metrosCuadrados
        -double precio
        -EstadoStand estado
    }

    class Editorial {
        -int id
        -String nombre
        -String correo
        -String telefono
    }

    class Reservacion {
        -int id
        -int idStand
        -int idEditorial
        -LocalDate fechaReserva
        -LocalDate fechaVencimiento
        -double montoPagado
        -boolean aplicaProntoPago
    }

    class ConfiguracionSistema {
        -double costoPorMetroCuadrado
        -double porcentajeMinimoOcupado
        -double porcentajeDescuentoProntoPago
    }

    class ConexionBD {
        +obtenerConexion()$ Connection
    }

    class StandDAO {
        +obtenerTodos() List~Stand~
    }

    class EditorialDAO {
        +obtenerTodas() List~Editorial~
    }

    class ReservacionDAO {
        +obtenerTodas() List~Reservacion~
    }

    class ServicioNotificaciones {
        +generarAlertas(List~Reservacion~, List~Stand~, List~Editorial~) List~String~
    }

    class ConsolaVista {
        +mostrarStands(List~Stand~) void
        +mostrarNotificaciones(List~String~) void
    }

    class SistemaControlador {
        +iniciarDemostracionLectura() void
    }

    Stand --> EstadoStand
    StandDAO ..> ConexionBD
    EditorialDAO ..> ConexionBD
    ReservacionDAO ..> ConexionBD
    StandDAO ..> Stand
    EditorialDAO ..> Editorial
    ReservacionDAO ..> Reservacion
    ServicioNotificaciones --> ConfiguracionSistema
    SistemaControlador --> StandDAO
    SistemaControlador --> EditorialDAO
    SistemaControlador --> ReservacionDAO
    SistemaControlador --> ServicioNotificaciones
    SistemaControlador --> ConsolaVista
```

---

## 3. Diagrama Entidad-Relación

```mermaid
erDiagram
    STANDS ||--o{ RESERVACION : "es apartado en"
    EDITORIAL ||--o{ RESERVACION : "realiza"

    STANDS {
        int id PK
        double metros_cuadrados
        double precio
        varchar estado
    }

    EDITORIAL {
        int id PK
        varchar nombre
        varchar correo
        varchar telefono
    }

    RESERVACION {
        int id PK
        int id_stand FK
        int id_editorial FK
        date fecha_reserva
        date fecha_vencimiento
        double monto_pagado
        boolean aplica_pronto_pago
    }
```