pipeline {
    agent any

    environment {
        IMAGE_NAME = "voting-system"
        WORKSPACE_DIR = "/var/jenkins_home/workspace/VotingSystem"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/NyanLinnZaw/VotingSystem.git'
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${IMAGE_NAME} ."
            }
        }

        stage('Deploy to Minikube') {
            steps {
                sh '''
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
