docker build -t apigateway-example:1.0 .
minikube image load apigateway-example:1.0
kubectl apply -f .\deployment.yaml