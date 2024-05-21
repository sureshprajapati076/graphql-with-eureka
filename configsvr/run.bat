minikube start --driver=docker
docker context use default

mvn clean install
docker build -t config-svr-example:1.0 .
minikube image load config-svr-example:1.0
kubectl apply -f .\deployment.yaml

#below command is only required for apigateway since its exposed to outside
kubectl port-forward svc/config-svr-svc 9871:9871

minikube dashboard
