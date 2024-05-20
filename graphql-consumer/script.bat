docker build -t graphql-consumer-example:1.0 .
minikube image load graphql-consumer-example:1.0
kubectl apply -f .\deployment.yaml