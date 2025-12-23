# ✈️ API de Vuelos — Java & SpringBoot & Maven & Lombok/Validation

Este proyecto creado para la prueba técnica final del Bootcamp, implementa una API para gestionar vuelos sin usar una base de datos (Usando almacenamiento en memoria).
Incluye un CRUD completo, validaciones, filtros, ordenamiento y manejo de errores.

---
## Índice
- [Tecnologías](#tecnologías-usadas)
---

## 📌 Tecnologías usadas
- Java 21
- SpringBoot (Web + Validation)
- Maven
- Lombok
- Postman (Para comprobar los endpoints y guardar la collection)

---

## 📁 Estructura del proyecto.
- `controllers/` → Endpoints REST, validación de entrada y respuestas.
- `services/` → Logica de la APP (reglas, filtros, orden, duplicados).
- `repositories/` → Persistencia en memoria con `Map` (simula una DB).
- `dtos/` → DTOs de entrada/salida + Mapper (no se expone el models directamente).
- `models/` → Clases base como `Vuelo` y `ApiResponse`
- `utils/` → Utilidades de fecha y normalización de parametros (parseo/validaciones)
- `excepcions/` → Excepciones propias + handler para respuestas de errores generales.
- `postman/` → Aquí se guarda el **export** de la colección Postman (lo haces desde Postman)

---

## ✅ Requisitos

* **Java 21** (recomendado)
* **Maven**
* Instalar el plugin de **Lombok** y activar el `Annotation Processors`

---

## ⚙️ ¿Como arrancarlo?

Para ejecturar el programa, confirma que los siguientes puntos estan correctos.

1. **Java instalado**

   * JDK 21

2. **Dependencias usadas**

   * Dependencias Maven correctas en el `pom.xml`.
  
3. **En IntelliJ**

   * Ejecutar el `VuelosAppAplication.java`.
     - Al arrancar, la API queda disponible en `http://localhost:8080`

---

## 🦜 Persistencia en memoria y seed

- No hay base de datos
- Se usa una estructura en memoria `(Map<Integer, Vuelo>)` para guardar los vuelos
- Al iniciar, se cargan 10 vuelo.
- Si se reinicia la APP, se reinicia el estado y vuelve al seed

---

## 🧰 Endpoints

### GET /vuelos — Listar vuelos


