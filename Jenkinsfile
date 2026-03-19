pipeline {
    agent docker { image 'maven:3.9.5-openjdk-17' }

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

        stage('Start Docker') {
            steps {
                bat 'wsl sudo service docker start'
            }
        }

        stage('Set Minikube Docker Env') {
            steps {
                bat 'wsl minikube -p minikube docker-env --shell cmd > env.cmd'
                bat 'call env.cmd'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t %IMAGE_NAME% .'
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                bat 'wsl kubectl apply -f k8s/deployment.yaml'
                bat 'wsl kubectl apply -f k8s/service.yaml'
            }
        }

        stage('Verify') {
            steps {
                bat 'wsl kubectl get pods'
            }
        }
    }
}
