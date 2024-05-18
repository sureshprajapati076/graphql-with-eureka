minikube start --driver=docker
docker context use default

mvn clean install
docker build -t servicea-example:1.0 .
minikube image load servicea-example:1.0
kubectl apply -f .\servicea-deployment.yaml

#below command is only required for apigateway since its exposed to outside
kubectl port-forward svc/servicea-svc 8081:8081

minikube dashboard
