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

![image](https://github.com/user-attachments/assets/c5a8c543-748b-4f32-b55f-aacc5e1c7d61)


Endpoint Consultar por usuario por ID 
El ID está en formato UUID.
http://localhost:8080/api/v1/users/111f1bcd-2efd-45ec-826e-cfcd3beaebf2
Respuesta del servicio:


![image](https://github.com/user-attachments/assets/0f285bfa-c2aa-41c2-b47b-1e8815fb1966)

Endpoint Actualizar Usuario.
http://localhost:8080/api/v1/users/c10b9c77-6490-44af-9d75-c0baf9cbc9ec

Body:

![image](https://github.com/user-attachments/assets/1b02561a-2db4-4c32-b878-7936a3a2739d)

Respuesta:
{
    "id": "c10b9c77-6490-44af-9d75-c0baf9cbc9ec",
    "name": "Juan",
    "email": "juan5@gmail.com",
    "password": "$2a$10$E2OLjQXFDk/2mVY5dqZJluvsZ6FE8.dofhWw61ofymY87pgzCSfOC",
    "phones": [
        {
            "id": 4,
            "number": "66666",
            "cityCode": "666",
            "countryCode": "7"
        }
    ]
}


Endpoint Eliminar Usuario:
http://localhost:8080/api/v1/users/c10b9c77-6490-44af-9d75-c0baf9cbc9ec

Respuesta: 
{
    "valid": true,
    "statusCode": 202,
    "message": "El usuario eliminado correctamente."
}


Para los dos últimos Endpoint Actualizar y Eliminar hago uso de Spring Security para restringir las dos rutas para que solo quienes se autentiquen mediante el envío del token puedas tener acceso a la actualización y eliminación. 
Como se muestra a continuación 


![image](https://github.com/user-attachments/assets/f6b57ede-0b34-4715-8783-7e5e756322a5)

Si el token ha expirado se puede autenticar nuevamente mediante el Endpoint de login
http://localhost:8080/api/v1/auth/login

Body

{
    "email":"juan@gmail.com",
    "password":"Test123="
}












