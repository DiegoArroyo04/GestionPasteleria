# Pastelería Dulce De Siempre - Aplicación Web

## Descripción General
Esta aplicación web ha sido desarrollada para una de las pastelerías más antiguas de Manzanares, permitiendo a los clientes visualizar los productos disponibles y realizar encargos en línea. Además, facilita la gestión interna del negocio, permitiendo al propietario supervisar todos los aspectos de la operación desde cualquier lugar.

## Características Principales

### 1. Gestión de Productos
- Registrar productos con:
  - Nombre
  - Descripción
  - Precio
  - Stock
  - Categoría
  - Imagen
- Editar y eliminar productos existentes.
- Controlar el stock y generar alertas cuando los productos tengan menos de 2 unidades.
- Asociar ingredientes a los productos.

### 2. Gestión de Ingredientes
- Registrar ingredientes con:
  - Nombre
  - Precio
  - Stock
  - Fabricante
  - Imagen
- Asociar ingredientes a productos.
- Actualizar automáticamente el stock de ingredientes según la producción.

### 3. Gestión de Pedidos
- Registrar pedidos de clientes con:
  - Nombre del cliente
  - Fecha del pedido
  - Productos incluidos
  - Precio total
  - Observaciones (ej. alergias)
  - Estado (pendiente, entregado)
- Validar disponibilidad de ingredientes antes de confirmar un pedido.
- Actualizar stock de productos e ingredientes tras la elaboración del pedido.

### 4. Gestión de Empleados
- Registrar empleados con:
  - Nombre
  - Apellidos
  - Teléfono
  - Fecha de contratación
- Registro de hora de entrada y salida de los trabajadores.
- Control de permisos:
  - Trabajadores pueden gestionar productos, ingredientes y pedidos.
  - Solo el administrador puede gestionar empleados y clientes.
- El administrador proporciona credenciales a los empleados.

### 5. Gestión de Clientes
- Registro de clientes con:
  - Nombre
  - Apellidos
  - Dirección
  - Teléfono
  - Email
- Los clientes pueden realizar pedidos desde la web.
- Los pedidos quedan registrados en la gestión de pedidos.

### 6. Inicio de sesión y control de accesos
- Inicio de sesión para:
  - Administrador
  - Trabajadores
  - Clientes
- Cada perfil tiene acceso y permisos personalizados según su rol.

## Funcionalidades Adicionales
- **Notificación por correo**: Se envía un email de confirmación cada vez que un cliente realiza un pedido.
- **Almacenamiento de imágenes en la base de datos**: Para optimizar el uso de espacio.
- **Generación de estadísticas de ventas**:
  - Estadísticas semanales y mensuales sobre productos vendidos.
- **Generación de PDF**:
  - Facturas detalladas con los productos adquiridos y el monto total.

## Tecnologías Utilizadas
- **Backend**:
  - Java con Spring Boot
  - Spring Data JPA
  - EmailJS para envío de correos
  - MySQL como base de datos
- **Frontend**:
  - Thymeleaf para renderizado de vistas
- **Otros**:
  - Hibernate para la gestión de la base de datos
  - JasperReports para la generación de PDFs

## Instalación y Configuración
### Requisitos Previos
- Java 17 o superior
- Maven
- MySQL

### Pasos para la Instalación
1. Clonar el repositorio:
   ```sh
   git clone https://github.com/DiegoArroyo04/GestionPasteleria.git
   ```
2. Configurar la base de datos en `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/pasteleria
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   ```

## Uso de la Aplicación
- Acceder a la aplicación desde el navegador:
  ```sh
  http://localhost:9091
  ```
- Iniciar sesión con el rol correspondiente.
- Gestionar productos, pedidos, clientes y empleados según los permisos asignados.

## Mantenimiento y Desarrollo Futuro
- Implementación de métodos de pago en línea.



## Contacto
Para soporte o sugerencias, contactar con el equipo de desarrollo diegoarroyogonzalez04@gmail.com .

