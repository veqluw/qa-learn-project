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
                sh '''
                mvn clean test \
                -Dremote=http://host.docker.internal:4444/wd/hub
                '''
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