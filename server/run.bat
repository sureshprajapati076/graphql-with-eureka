minikube start --driver=docker
docker context use default

mvn clean install
docker build -t eureka-example:1.0 .
minikube image load eureka-example:1.0
kubectl apply -f .\eureka-deployment.yaml

#below command is only required for apigateway and eureka since its exposed to outside
kubectl port-forward svc/eureka-svc 8761:8761

minikube dashboard
