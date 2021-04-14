# Synchronous Delegate Task

This is an example of handling camunda synchronous/delegate tasks with an in-processes handler

There is one spring boot application:

- camunda-external: A spring boot application that contains the embedded camunda engine and 
  process definition.  It periodically creates a new instance of the process.  There is also a
  task handler bean that camunda calls when it needs to execute a task.
  
In this case the camunda engine is able to directly call our code because the spring boot integration
has made our beans available to the engine.

The task is considered complete if our handler bean's execute method completes without error.  The call
into our code is synchronous and blocks the engine's thread.  This approach should not be used for long-running
tasks.
