# External Task Over Rest Example

This is an example of handling camunda external/async tasks in an external worker
using the REST api.

There are two spring boot applications:

- camunda: A spring boot application that contains the embedded camunda engine and 
  process definition.  It periodically creates a new instance of the process.
  
- worker: A spring boot application that uses the camunda external task client to 
  fetch tasks to execute from the engine using rest.
  
The camunda external client is just one way of building a worker that processes
tasks from the engine.  More information can be found here:

https://github.com/camunda/camunda-bpm-platform/tree/master/spring-boot-starter/starter-client

If you didn't want to use the client and wanted to build your own worker pattern, you
can craft REST requests using whichever tool suits your needs or you could generate an SDK
using camunada's OpenAPI support:

https://github.com/camunda-consulting/code/tree/master/snippets/camunda-openapi-client
