# API REST CREACIÓN DE USUARIO.

Se desarrolló la Api bajo arquitectura SOA Orientada a Servicios, mediante tres capas Cliente Rest, Servicio, Datos. El api cuenta con Spring Security e implantación de JWT, también se hace uso de varios patrones de diseños como Builder entre otros. También de hace uso de códigos de respuestas Http adecuados a cada solicitud, para ello se creó la clase ErrorCatalog en la cual se definen los mensajes para cada tipo de error de la Api.

![image](https://github.com/user-attachments/assets/c77ce4d2-0c70-441b-a8d4-5ce333fce30c)

Base de datos H2
Modelo Entidad Relación (MER)
Para la tabla Usuarios se define id en formato UUID de acuerdo con solicitud.

![image](https://github.com/user-attachments/assets/539adc24-2757-401b-bc15-9025f8afa3fc)


