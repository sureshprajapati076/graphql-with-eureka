docker build -t graphql-example:1.0 .
minikube image load graphql-example:1.0
kubectl apply -f .\deployment.yaml