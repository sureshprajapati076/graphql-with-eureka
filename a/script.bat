docker build -t servicea-example:1.0 .
minikube image load servicea-example:1.0
kubectl apply -f .\deployment.yaml