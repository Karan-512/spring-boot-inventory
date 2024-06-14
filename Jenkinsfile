pipeline {
    agent any
    
    environment {
        DOCKER_IMAGE = 'spring-boot-inventory'
    }

    stages {
        stage('Build') {
            steps {
                script {
                    sh 'mvn clean package -DskipTests'
                    sh 'sudo docker build -t $DOCKER_IMAGE .'
                }
            }
        }
        // stage('Test') {
        //     steps {
        //          script {
        //             sh 'mvn test'
                   
        //         }
        //     }
        // }
        stage('Deploy') {
            steps {
                sh 'sudo docker compose up -d'
            }
        }
    }
}
