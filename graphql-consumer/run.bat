minikube start --driver=docker
docker context use default

mvn clean install
docker build -t graphql-consumer-example:1.0 .
minikube image load graphql-consumer-example:1.0
kubectl apply -f .\deployment.yaml

#below command is only required for apigateway since its exposed to outside
kubectl port-forward svc/graphql-consumer-svc 8088:8088

minikube dashboard
