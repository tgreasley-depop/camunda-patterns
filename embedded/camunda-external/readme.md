# External Task In Process

This is an example of handling camunda external/async tasks with an in-processes handler

There is one spring boot application:

- camunda-external: A spring boot application that contains the embedded camunda engine and 
  process definition.  It periodically creates a new instance of the process.  There is also a
  task handler bean that polls the engine for work.
  
In this case the camunda engine knows nothing about our handler implementation.  
Our code is separated from the engine by a work topic managed by camunda.

The tasks are asynchronous and it's up to us to inform camunda that the process is complete by
executing a callback.
