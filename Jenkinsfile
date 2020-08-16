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
        stage('Deploy Frontend'){
            steps{
                dir('frontend'){
                    git credentialsId: 'github_login', url: 'https://github.com/foxraf3d/tasks-frontend'
                    sh 'mvn clean package'
                    deploy adapters: [tomcat8(credentialsId: 'TomcatLogin', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks', war: 'target/tasks.war'

                }                
            }
        }
         stage('Functional Tests'){
            steps{
                dir('functional-test'){
                    git credentialsId: 'github_login', url: 'https://github.com/foxraf3d/tasks-functional-test'
                    sh 'mvn test'
                }
            }
         }
    }
    post {
        always {
           junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml, api-test/target/surefire-reports/*.xml, functional-test/target/surefire-reports/*.xml' 
        }
        unsuccessful{
        	emailext attachLog: true, body: 'Verifique o log em anexo', subject: 'Build has failed', to: 'rafael_sistema27@@hotmail.com'
        }
        fixed{
        	emailext attachLog: true, body: 'Build corrigido!', subject: 'Build has failed', to: 'rafael_sistema27@hotmail.com'
        }
    }
}