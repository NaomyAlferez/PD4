# 🎪 Sistema Administrativo de Stands - FILEY

![Java](https://img.shields.io/badge/Java-17-blue)
![JDBC](https://img.shields.io/badge/JDBC-H2_Database-green)
![MVC](https://img.shields.io/badge/Patr%C3%B3n-MVC-orange)

Sistema en línea de comandos para la gestión de espacios (stands) de la **Feria Internacional de la Lectura Yucatán (FILEY)**, implementando el patrón de diseño **MVC** y base de datos **H2** en memoria.

## 🚀 Características Principales
* **Catálogo interactivo:** Consulta de stands por estados (`DISPONIBLE`, `RESERVADO`, `OCUPADO`, `BLOQUEADO`).
* **Gestión de pagos:** Control de reservaciones vinculadas a editoriales con cálculo de descuentos por pronto pago.
* **Motor de notificaciones:** Alertas automáticas por vencimiento de apartados y fechas límite de pago.

## 📂 Documentación Técnica
Para mantener el repositorio limpio, la documentación de diseño e ingeniería se encuentra en la carpeta `/docs`:
* 📝 **[Casos de Uso e Historias de Usuario](docs/REQUISITOS.md)**
* 🏗️ **[Arquitectura MVC, Diagrama de Clases y Entidad-Relación](docs/ARQUITECTURA.md)**

## ⚙️ Cómo ejecutar el proyecto
1. Abre el proyecto en tu IDE (ej. Visual Studio Code o IntelliJ).
2. Asegúrate de tener instalado Java 17 o superior.
3. Ejecuta el archivo principal ubicado en: `src/main/java/mx/uady/filey/Main.java`.
*(Nota: El proyecto usa Maven para resolver automáticamente la dependencia del driver H2).*

---
## 🎥 Evidencia (Prueba de Desempeño 4)
▶️ **[Clic aquí para ver el video explicativo](AQUI_PEGA_TU_LINK_DE_DRIVE_O_YOUTUBE)**