docker build -t serviceb-example:1.0 .
minikube image load serviceb-example:1.0
kubectl apply -f .\deployment.yaml