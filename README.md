# 📚 BookDex - API de Consulta de Libros

BookDex es una aplicación construida con **Spring** que consume la API de [Gutendex](https://gutendex.com/) para buscar libros, mostrarlos por título, idioma y otros criterios, y almacenarlos en una base de datos local usando **JPA/Hibernate**. Este proyecto sirve como práctica para el consumo de APIs, el manejo de datos JSON, el uso de JPA y la persistencia con Spring Data.

---

## 🚀 Características

- 🔎 Buscar libros por título desde la API de Gutendex.
- 📄 Persistir libros y autores en una base de datos relacional.
- 🌍 Consultar libros por idioma.
- 👨‍💻 Consultar autores registrados.
- 📆 Filtrar autores vivos en un año determinado.
- 🗂 Listar autores por edad o por rango de edad.

---

## 🧱 Tecnologías Usadas

- Java 17
- Spring Boot 3
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Gutendex API
- Jackson (para deserialización de JSON)
- H2 (opcional para pruebas locales)

---

## 📦 Estructura del Proyecto

```bash
BookDex/
├── model/            # Entidades JPA: Libro, Autor
├── repository/       # Interfaces JpaRepository
├── service/          # Lógica para consumir API y convertir datos
├── Principal.java    # Menú principal de la app en consola
```

---

## 🔧 Configuración

### 1. Clonar el proyecto

```bash
git clone https://github.com/alonso1298/BookDex-Spring.git
cd BookDex-Spring
```

### 2. Configurar la base de datos

Asegúrate de tener una base de datos PostgreSQL corriendo y configura tu application.properties:

``` properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bookdex
spring.datasource.username=postgres
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```

Cambia los valores según tu configuración local.

### Ejecuta el proyecto

---

## 💡 Uso

Una vez ejecutado, se mostrará un menú en consola con las siguientes opciones:
1.	Buscar libro por título
2.	Mostrar libros por idioma
3.	Mostrar autores registrados
4.	Buscar autores vivos en un año específico
5.	Salir

---

## 🛠 Ejemplo de uso

```
Bienvenido a BookDex
1. Buscar libro por título
Ingrese el nombre del libro: Sherlock Holmes

Libro guardado con éxito.
```

---

## ✍️ Autor

Desarrollado por Alonso Sagrero como parte de su aprendizaje en Java y Spring Boot.
