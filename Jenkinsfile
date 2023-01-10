pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                // Build
                echo 'Build'
                sh '''#!/bin/bash
                ./gradlew build
                '''
            }
        }
        stage('Test') {
            steps {
                // Test
                echo 'Test'
            }
        }
        stage('Deploy') {
            steps {
                // Deploy
                echo 'Deploy'
            }
        }
    }
}
