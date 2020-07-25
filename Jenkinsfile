pipeline{
    agent any
    stages{
        stage('Bild Backend'){
            steps{
                sh 'mvn clean package -DskipTests=true'
            }
        }
        stage('Unit Tests'){
            steps{
                sh 'mvn test'
            }
        }
        stage('Deploy Backend'){
            steps{
                deploy adapters: [tomcat8(credentialsId: 'TomcatLogin', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'
            }
        }
        stage('API Tests'){
            steps{
                dir('api-test'){
                    git credentialsId: 'github_login', url: 'https://github.com/foxraf3d/tasks-api-test'
                    sh 'mvn test'
                }
            }
        }
    }
}