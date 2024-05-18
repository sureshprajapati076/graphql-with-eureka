minikube start --driver=docker
docker context use default

mvn clean install
docker build -t graphql-example:1.0 .
minikube image load graphql-example:1.0
kubectl apply -f .\graphql-deployment.yaml

#below command is only required for apigateway since its exposed to outside
kubectl port-forward svc/graphql-svc 9090:9090

minikube dashboard
