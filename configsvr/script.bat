docker build -t config-svr-example:1.0 .
minikube image load config-svr-example:1.0
kubectl apply -f .\deployment.yaml