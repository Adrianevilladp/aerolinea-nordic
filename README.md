# Proyecto de programación web - Aerolinea Nordic

## Descripción

Este es un proyecto de Java y Spring Boot para la gestión de un aeropuerto. La aplicación permite a los usuarios buscar vuelos, realizar reservas (booking) y autenticarse. 

## Características

- **Búsqueda de Vuelos**: Los usuarios pueden buscar vuelos disponibles según origen, destino y fecha.
- **Reserva de Vuelos**: Los usuarios pueden reservar vuelos disponibles.
- **Autenticación**: Implementación de un sistema de autenticación y autorización para usuarios registrados.

## Estructura del Proyecto

La estructura del proyecto es la siguiente:

- `/src/main/java` - Código fuente del proyecto.
  - `/controller` - Controladores REST.
  - `/service` - Lógica de negocio y servicios.
  - `/repository` - Repositorios para acceso a datos.
  - `/model` - Clases de entidad.
- `/src/main/resources` - Archivos de recursos.
  - `application.properties` - Configuración de la aplicación.
- `/src/test/java` - Pruebas unitarias e integradas.
- `README.md` - Este archivo.
- `pom.xml` - Archivo de configuración de Maven.
