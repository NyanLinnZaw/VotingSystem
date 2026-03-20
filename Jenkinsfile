pipeline {
    agent any

    environment {
        IMAGE_NAME = "voting-system"
    }

    stages {

        stage('Checkout') {
            steps {
                // Use Jenkins Git plugin instead of calling git via CMD
                git branch: 'main', url: 'https://github.com/NyanLinnZaw/VotingSystem.git'
            }
        }

        stage('Build JAR (Docker Maven)') {
            steps {
                bat '''
                docker run --rm ^
                    -v "%WORKSPACE%:/app" ^
                    -w /app ^
                    maven:3.9.6-eclipse-temurin-17 ^
                    mvn clean package -DskipTests
                '''
            }
        }

        stage('Set Minikube Docker Env') {
            steps {
                bat 'minikube -p minikube docker-env --shell cmd > env.cmd'
                bat 'call env.cmd'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t %IMAGE_NAME% "%WORKSPACE%"'
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                bat 'kubectl apply -f "%WORKSPACE%\\k8s\\deployment.yaml"'
                bat 'kubectl apply -f "%WORKSPACE%\\k8s\\service.yaml"'
            }
        }

        stage('Verify Deployment') {
            steps {
                bat 'kubectl get pods'
            }
        }
    }
}
