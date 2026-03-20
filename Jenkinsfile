pipeline {
    agent any

    environment {
        IMAGE_NAME = "voting-system"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/NyanLinnZaw/VotingSystem.git'
            }
        }

        stage('Build JAR') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat "docker build -t ${IMAGE_NAME} ."
            }
        }

        stage('Deploy to Minikube') {
            steps {
                bat '''
                # Use Minikube docker-env
                eval $(minikube -p minikube docker-env)
                
                # Deploy
                kubectl apply -f spring-deployment.yaml
                kubectl apply -f mysql-service.yaml
                
                # Verify
                kubectl get pods
                '''
            }
        }
    }
}
