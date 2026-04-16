pipeline {
    agent any

    options {
        timestamps()
    }

    environment {
        MAVEN_CMD = 'mvn'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                script {
                    if (isUnix()) {
                        sh "${env.MAVEN_CMD} -B clean compile"
                    } else {
                        bat "${env.MAVEN_CMD} -B clean compile"
                    }
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    if (isUnix()) {
                        sh "${env.MAVEN_CMD} -B test"
                    } else {
                        bat "${env.MAVEN_CMD} -B test"
                    }
                }
            }
        }

        stage('Package') {
            steps {
                script {
                    if (isUnix()) {
                        sh "${env.MAVEN_CMD} -B package -DskipTests"
                    } else {
                        bat "${env.MAVEN_CMD} -B package -DskipTests"
                    }
                }
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
        success {
            echo 'Pipeline completed successfully.'
        }
        failure {
            echo 'Pipeline failed. Check the build logs and test reports.'
        }
    }
}
