# Tarea2Patrones
Patrón Factory Method
Descripción: El Factory Method permite la creación de objetos sin exponer la lógica de creación al cliente. En lugar de instanciar clases concretas directamente, se utiliza una fábrica que se encarga de crear los objetos de acuerdo con la necesidad del cliente. Este patrón ayuda a que el código cliente esté desacoplado de las clases concretas de los objetos.

Justificación: En el sistema, este patrón se utiliza para crear diferentes tipos de vehículos y pagos sin que el cliente tenga que conocer las clases concretas. Esto facilita la extensión del sistema al agregar nuevos tipos de vehículos o métodos de pago, sin necesidad de modificar el código cliente.

2. Patrón Observer
Descripción: El Observer permite que un objeto (el sujeto) mantenga una lista de sus dependientes (los observadores) y les notifique automáticamente cuando su estado cambie, sin necesidad de que los observadores estén directamente acoplados al sujeto.

Justificación: En el sistema, el patrón Observer se aplica en el contexto de notificaciones. Los observadores (como los servicios que envían notificaciones) reaccionan a cambios en el estado de objetos como reservas o pagos. Esto permite un sistema más flexible y desacoplado, donde las acciones o notificaciones se pueden agregar o modificar sin cambiar la lógica del sujeto (por ejemplo, la reserva o el pago).

3. Patrón Decorator
Descripción: El Decorator permite añadir funcionalidades a un objeto de manera dinámica sin alterar su estructura. Este patrón envuelve un objeto con una clase que agrega nuevas responsabilidades de forma flexible.

Justificación: En el sistema, el patrón Decorator se utiliza para agregar funcionalidades adicionales a los servicios, como el servicio de notificación. Esto permite extender el comportamiento de los servicios sin modificar las clases originales, facilitando la adición de nuevas características, como notificaciones adicionales, sin complicar el código base.

4. Patrón Builder
Descripción: El Builder se utiliza para construir objetos complejos paso a paso. Este patrón permite crear un objeto en varias etapas, separando la construcción de un objeto de su representación final.

Justificación: El patrón Builder se aplica a la construcción de objetos de tipo Reserva, permitiendo que la creación de una reserva con muchos atributos (como el vehículo, vuelo, pago, etc.) se realice de manera estructurada y flexible. Esto facilita la adición de nuevos campos o atributos a las reservas sin afectar el código que ya utiliza el objeto Reserva.
