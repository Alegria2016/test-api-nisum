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


Si se intenta crear un nuevo usuario con el mismo correo se realiza validación de acuerdo con la solicitud, la cual indica que existe un usuario con el mismo correo, ver respuesta.

![image](https://github.com/user-attachments/assets/83ac361f-391a-4a78-beb1-03dc7d2c3517)

Endpoint Consultar Usuarios:
Retorna todos los usuarios creados con paginación.
http://localhost:8080/api/v1/users

{
    "content": [
        {
            "id": "d31e4973-2804-4d78-b5dc-c086d12eb8e8",
            "name": "Juan",
            "email": "juan@gmail.com",
            "password": "$2a$10$oiGCWtn.EoKpbh6.gZvs.uberTOawsnfocX83UlERTHH0xY2543lW",
            "phones": [
                {
                    "id": 1,
                    "number": "312442334",
                    "cityCode": null,
                    "countryCode": null
                }
            ]
        },
        {
            "id": "ddabf504-7c4c-4a12-b2d7-0ac200d776ae",
            "name": "Juan",
            "email": "juan2@gmail.com",
            "password": "$2a$10$tU85mjm86xTgX9MDbH2tUeLvlXiLL.l9fuKcCF2dhhTTgC6Y1VHii",
            "phones": [
                {
                    "id": 2,
                    "number": "312442334",
                    "cityCode": null,
                    "countryCode": null
                }
            ]
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalElements": 2,
    "totalPages": 1,
    "size": 10,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": true,
    "numberOfElements": 2,
    "empty": false
}






