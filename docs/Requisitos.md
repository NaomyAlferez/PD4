# Requisitos del Sistema

## 1. Casos de Uso

| ID | Nombre del Caso de Uso | Descripción |
| :--- | :--- | :--- |
| **CU-01** | **Consultar Catálogo de Stands** | El sistema permite visualizar todos los stands con su identificador, área en $m^2$, costo calculado y estado actual (`DISPONIBLE`, `RESERVADO`, `OCUPADO`, `BLOQUEADO`). |
| **CU-02** | **Gestionar Reservaciones y Pagos** | El sistema registra el vínculo entre una Editorial y un Stand, controlando la vigencia del apartado, el descuento por pronto pago y el porcentaje cubierto. |
| **CU-03** | **Emitir Notificaciones de Vencimiento** | El sistema evalúa las fechas configuradas y notifica a las editoriales cuando su reserva o el beneficio de pronto pago están por vencer, y cuando se acerca la fecha máxima de ocupación. |

## 2. Historias de Usuario

| ID | Rol | *Quiero...* | *Para...* |
| :--- | :--- | :--- | :--- |
| **HU-01** | Administrador | visualizar el listado completo de stands, editoriales y reservaciones almacenados en la base de datos | conocer la ocupación actual de la feria. |
| **HU-02** | Administrador | bloquear stands específicos antes del despliegue general del sistema | asegurar espacios en negociación interna y transferirlos posteriormente a la editorial correspondiente. |
| **HU-03** | Editorial | recibir notificaciones oportunas sobre el vencimiento de mi reserva y las fechas límite de pronto pago | liquidar mi stand a tiempo sin perder mi descuento ni mi espacio asignado. |