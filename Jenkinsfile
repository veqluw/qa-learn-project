pipeline {

    agent any

    tools {
        maven 'Maven'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run tests') {
            steps {
                sh 'mvn clean test'
            }
        }

    }

    post {

        always {

            junit 'target/surefire-reports/*.xml'

            allure(
                includeProperties: false,
                results: [[path: 'target/allure-results']]
            )

        }

    }

}