pipeline{
    agent any
    stages{
        stage('Bild Backend'){
            steps{
                sh 'mvn clean package -DskipTests=true'
            }
        }
    }
}