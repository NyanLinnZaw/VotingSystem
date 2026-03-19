pipeline {
    agent any

    environment {
        IMAGE_NAME = "voting-system"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/NyanLinnZaw/VotingSystem.git'
            }
        }

        stage('Build + Docker + K8s (WSL)') {
            steps {
                bat '''
                wsl bash -c "
                set -e

                cd /mnt/c/ProgramData/Jenkins/.jenkins/workspace/VotingSystem

                echo '== Build JAR =='
                mvn clean package -DskipTests

                echo '== Use Minikube Docker =='
                eval $(minikube docker-env)

                echo '== Build Docker Image =='
                docker build -t voting-system .

                echo '== Deploy to Kubernetes =='
                kubectl apply -f k8s/deployment.yaml
                kubectl apply -f k8s/service.yaml

                echo '== Verify =='
                kubectl get pods
                "
                '''
            }
        }
    }
}
