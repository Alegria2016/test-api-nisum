# API REST CREACIÓN DE USUARIO.

Se desarrolló la Api bajo arquitectura SOA Orientada a Servicios, mediante tres capas Cliente Rest, Servicio, Datos. El api cuenta con Spring Security e implantación de JWT, también se hace uso de varios patrones de diseños como Builder entre otros. También de hace uso de códigos de respuestas Http adecuados a cada solicitud, para ello se creó la clase ErrorCatalog en la cual se definen los mensajes para cada tipo de error de la Api.

![image](https://github.com/user-attachments/assets/c77ce4d2-0c70-441b-a8d4-5ce333fce30c)

Base de datos H2,
Modelo Entidad Relación (MER)
Para la tabla Usuarios se define id en formato UUID de acuerdo con solicitud.

![image](https://github.com/user-attachments/assets/539adc24-2757-401b-bc15-9025f8afa3fc)

Endpoints 

Endpoint de creación del Usuario:
Al crear el usuario se realizan las validaciones del correo y la contraseña con las expresiones regulares según solicitud.
El correo debe cumplir este formato: juan@gmail.com al igual que la contraseña el siguiente: Test123=
Las dos expresiones se encuentran en la en el paquete utils en la clase Constant se hace uso de ellas mediante un servicio que permite hacer las validaciones correspondientes desde cualquier otra clase mediante el uso de Composición, sólo inyectando el servicio.
http://localhost:8080/api/v1/auth/register

![image](https://github.com/user-attachments/assets/2fb307a4-2be4-4c1a-8d81-a23ae04d957c)

Respuesta:
![image](https://github.com/user-attachments/assets/0fcc52e2-1fcf-424c-9cc4-0019e39ef080)





