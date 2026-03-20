pipeline {
    agent any

    environment {
        IMAGE_NAME = "voting-system"
    }

    stages {

        stage('Checkout') {
            steps {
                bat 'git clone -b main https://github.com/NyanLinnZaw/VotingSystem.git %WORKSPACE%\\VotingSystem'
            }
        }

        stage('Build JAR (Maven in Docker)') {
            steps {
                // Use Maven Docker image to build project without installing Maven on host
                bat '''
                docker run --rm ^
                    -v "%WORKSPACE%\\VotingSystem:/app" ^
                    -w /app ^
                    maven:3.9.6-eclipse-temurin-17 ^
                    mvn clean package -DskipTests
                '''
            }
        }

        stage('Set Minikube Docker Env') {
            steps {
                // Windows version of minikube docker-env
                bat 'minikube -p minikube docker-env --shell cmd > env.cmd'
                bat 'call env.cmd'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t %IMAGE_NAME% %WORKSPACE%\\VotingSystem'
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                bat 'kubectl apply -f %WORKSPACE%\\VotingSystem\\spring-deployment.yaml'
                bat 'kubectl apply -f %WORKSPACE%\\VotingSystem\\myql-deployment.yaml'
            }
        }

        stage('Verify Deployment') {
            steps {
                bat 'kubectl get pods'
            }
        }
    }
}
