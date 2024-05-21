#This involves use of docker and minikube to deploy services including config server is deployed and being consumed by service-a.

This is demo for graphql service consumption, service a and b uses CircuitBreaker from resilence4j with retry for inter communication.

Service graphql is hosted to accept graphql query and its being consumed via graphql-consumer service through graphqlclient

server is eureka server and api is gateway.
