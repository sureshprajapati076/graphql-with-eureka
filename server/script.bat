docker build -t eureka-example:1.0 .
minikube image load eureka-example:1.0
kubectl apply -f .\deployment.yaml