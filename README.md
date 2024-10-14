# API REST CREACIÓN DE USUARIO.

Se desarrolló la Api bajo arquitectura SOA Orientada a Servicios, mediante tres capas Cliente Rest, Servicio, Datos. La api cuenta con Spring Security e implantación de JWT, también se hace uso de varios patrones de diseños como Builder entre otros. Se hace uso de códigos de respuestas Http adecuados a cada solicitud, para ello se creó la clase ErrorCatalog en la cual se definen los mensajes para cada tipo de error de la Api.

![image](https://github.com/user-attachments/assets/c77ce4d2-0c70-441b-a8d4-5ce333fce30c)

Base de datos H2,
Modelo Entidad Relación (MER)
Para la tabla Usuarios se define id en formato UUID de acuerdo con solicitud.

![image](https://github.com/user-attachments/assets/539adc24-2757-401b-bc15-9025f8afa3fc)

Para Iniciar el proyecto.
1) Clone repositorio.
2) De preferencia usar Intellij IDEA
3) Limpiar proyecto e Iniciar.

Una vez inicia el proyecto ir na navegador para ver la documentacion. 
http://localhost:8080/swagger-ui/index.html
Especificar el puerto en el cual haya iniciado el proyecto en me caso es http://localhost:8080 seguido de la ruta de la documentacion como aparece anteriormente.

Endpoints 

Endpoint de creación del Usuario:
Al crear el usuario se realizan las validaciones del correo y la contraseña con las expresiones regulares según solicitud.
El correo debe cumplir este formato: juan@gmail.com al igual que la contraseña el siguiente: Test123=
Las dos expresiones se encuentran en la en el paquete utils en la clase Constant se hace uso de ellas mediante un servicio que permite hacer las validaciones correspondientes desde cualquier otra clase mediante el uso de Composición, sólo inyectando el servicio.
http://localhost:8080/api/v1/auth/register

![image](https://github.com/user-attachments/assets/e4a099ee-e9cd-4239-be10-540438b373ac)



Respuesta:

![image](https://github.com/user-attachments/assets/b1b22444-e78b-47d8-b2bc-ea34aecbb220)



Si se intenta crear un nuevo usuario con el mismo correo se realiza validación de acuerdo con la solicitud, la cual indica que existe un usuario con el mismo correo, ver respuesta.

![image](https://github.com/user-attachments/assets/9ebf36a1-2e38-49f7-ac70-25db9a69c4bc)


Endpoint Consultar Usuarios:
Retorna todos los usuarios creados con paginación.
http://localhost:8080/api/v1/users

![image](https://github.com/user-attachments/assets/bd4893c2-9df5-4b3c-81b4-f12140db5e90)



Endpoint Consultar por usuario por ID 
El ID está en formato UUID.
http://localhost:8080/api/v1/users/7948bf1d-ec34-4c96-9ad2-2b41935edddf
Respuesta del servicio:


![image](https://github.com/user-attachments/assets/c1f33ea4-4407-4981-966a-3fc8ab9d529a)


Endpoint Actualizar Usuario.
http://localhost:8080/api/v1/users/c10b9c77-6490-44af-9d75-c0baf9cbc9ec

Body:

![image](https://github.com/user-attachments/assets/c1b069b6-2dd7-477b-9c0e-0853c6d76abd)


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












