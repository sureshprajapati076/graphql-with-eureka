minikube start --driver=docker
docker context use default

mvn clean install
docker build -t apigateway-example:1.0 .
minikube image load apigateway-example:1.0
kubectl apply -f .\deployment.yaml

#below command is only required for apigateway and eureka since its exposed to outside
kubectl port-forward svc/apigateway-svc 8888:8888

minikube dashboard
