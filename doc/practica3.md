# Práctica 3 – Aplicación XP en un Proyecto Completo

**Alumno:** Álvaro Zumbana  
**Proyecto:** TodoList XP – Gestión de tareas  
**Versión entregada:** v1.1.0  
**Repositorio:** [https://github.com/greyhatbat/zumbana-TodoListSpringBoot](https://github.com/greyhatbat/zumbana-TodoListSpringBoot)  
**Trello:** [https://trello.com/b/uEVwJX9S/todolist-epn]  
**GitHub Project:** [https://github.com/users/greyhatbat/projects/2](https://github.com/users/greyhatbat/projects/2)

---

##  Historias de Usuario Implementadas

| HU  | Descripción                        | Rama                        | PR                             | Estado     |
|-----|------------------------------------|-----------------------------|--------------------------------|------------|
| HU1 | Barra de navegación                | `hu1-navbar`                | #1                             | ✅ Mergeado |
| HU2 | Listado de usuarios                | `hu2-listado-usuarios`      | #2                             | ✅ Mergeado |
| HU3 | Descripción de usuario             | `hu3-detalle-usuario`       | #3                             | ✅ Mergeado |
| HU4 | Admin y protección de rutas        | `hu4-admin-proteccion`      | #4                             | ✅ Mergeado |
| TESTS | Tests automatizados (HU1–HU4)     | `tests-hu1-a-hu4`           | #5                             | ✅ Mergeado |

---

##  Nuevas clases y métodos implementados

Durante el desarrollo de esta práctica se incorporaron métodos importantes para extender la funcionalidad base del proyecto. Entre ellos, se incluyó la posibilidad de obtener un usuario por su identificador, verificar si un usuario ya registrado es administrador, y proteger ciertas rutas sensibles del sistema.

También se amplió el DTO de usuarios para permitir transportar información relacionada al nuevo rol administrativo, y se ajustaron los servicios para reflejar esa información correctamente durante el registro.

En cuanto al controlador, se agregaron rutas nuevas que permiten acceder al listado de usuarios y a su información detallada. Dichas rutas son accesibles únicamente por el administrador, para lo cual se introdujo lógica condicional que verifica la sesión activa del usuario.

---

##  Plantillas Thymeleaf añadidas

Como parte del desarrollo de funcionalidades nuevas, se agregaron plantillas visuales para mejorar la navegación y experiencia del usuario. Se diseñó una barra de navegación adaptable según el rol, vistas para mostrar tablas con los usuarios registrados, páginas de detalles individuales, y una página de error que informa al usuario cuando intenta acceder a recursos restringidos.

La estructura de las plantillas sigue las recomendaciones de buenas prácticas para proyectos Thymeleaf, utilizando fragmentos reutilizables para el encabezado y los scripts.

---

##  Tests automatizados

Se implementó una batería de pruebas utilizando JUnit y MockMvc, orientadas a validar los puntos más relevantes de cada historia de usuario. Esto incluye pruebas de login exitoso, login con error de contraseña, login con usuario inexistente, y validaciones de acceso a rutas protegidas.

Para la parte administrativa, se comprobó que solo un usuario con rol adecuado puede acceder a la vista de usuarios registrados o a sus detalles, mientras que cualquier otro usuario recibe un mensaje de acceso denegado. Estos tests fueron fundamentales para garantizar que las funcionalidades implementadas no solo funcionen en desarrollo, sino también bajo condiciones controladas de prueba.

Cada historia de usuario está respaldada por al menos una prueba automatizada, lo cual mejora significativamente la confiabilidad del sistema y permite escalar la aplicación con mayor confianza.

---

##  Explicación de funcionalidades clave

Una de las funcionalidades más relevantes fue la incorporación del rol de administrador. Esto permitió introducir una lógica más robusta de control de acceso. La solución considera que solo puede haber un único administrador registrado, y que la opción para registrarse como administrador solo aparece la primera vez.

Otra funcionalidad importante fue el enlace entre usuarios listados y su perfil, lo que mejora la navegabilidad y da al sistema una apariencia más profesional.

Además, se cuidó la experiencia del usuario con retroalimentación clara, como páginas de acceso denegado o mensajes adecuados en casos de error durante el login.

---

##  Conclusión

La práctica se desarrolló siguiendo el enfoque de XP (Extreme Programming), utilizando Git y GitHub como herramientas centrales. Cada historia fue implementada en su propia rama, con control de versiones, issues, pull requests y pruebas automatizadas.

El flujo de trabajo permitió iterar rápidamente y mantener un orden lógico en cada entrega. La documentación, el testing y la modularización fueron clave para alcanzar una solución completa y profesional. Esta práctica no solo permitió aplicar los conceptos teóricos de metodologías ágiles, sino también mejorar la calidad técnica y estructural del proyecto.
