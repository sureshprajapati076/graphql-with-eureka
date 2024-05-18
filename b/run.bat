minikube start --driver=docker
docker context use default

mvn clean install
docker build -t serviceb-example:1.0 .
minikube image load serviceb-example:1.0
kubectl apply -f .\serviceb-deployment.yaml

#below command is only required for apigateway since its exposed to outside
kubectl port-forward svc/serviceb-svc 9999:9999

minikube dashboard
