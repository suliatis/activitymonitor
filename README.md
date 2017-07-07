# Activity Monitor

The program monitors the `insert` and `update` database operations and notifies it's clients via web socket.

## Build and Run the Application

It is a plain old Spring Boot application with Maven. So to build the app run `mvn install` command, it also runs the tests. To start it in development mode run `mvn spring-boot:run` command.

## Manual Testing

Insert a generated entity into the sample table with calling `http://localhost:8080/sample/save` in your browser. This will generate an entity and insert it to the database, also reports the result to the browser.

Update an entity with a given id and generated values call the `http://localhost:8080/sample/save/{id}`. Note that, if the entity is not presented by the given id, it will create a new entity to insert, but not uses the id.

To monitor `insert` and `update` database operations, open `http://localhost:8080/activity-monitor` in another browser window. While you calling the saving endpoints it will report them.