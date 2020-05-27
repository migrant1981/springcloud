pipeline {
   agent any
   stages {
        stage('Fetch spring code') {
            steps {
                sh 'ls -ltr ~/'
                checkout(
                    [
                        $class: 'GitSCM',
                        branches: [[ name: "*/master" ]],
                        doGenerateSubmoduleConfigurations: false,
                        extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: "src"]],
                        submoduleCfg: [],
                        userRemoteConfigs: [
                            [
                                credentialsId: "fsd-stock",
                                name: 'master',
                                refspec: "+refs/heads/master:+refs/remotes/origin/master",
                                url: "git@github.com:migrant1981/springcloud.git"	
                            ]
                        ]
                    ]
                )
                sh 'pwd'
            }
        }
		
		stage('build Eureka_Server package') {
            steps{
                dir('/var/jenkins_home/workspace/test/src/Eureka_Server') {
                    sh 'ls -ltr'
                    sh 'pwd'
                    sh '/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/maven-3.6.3/bin/mvn package'
                }
            }
        }
        stage('build Eureka_Server image & run') {
            steps {
                dir('/var/jenkins_home/workspace/test/src/Eureka_Server') {
                    sh 'ls -ltr'
                    sh 'pwd'
                    sh 'docker build . -t springeurekaserver'
                    sh 'docker run springeurekaserver'
                }
            }
        }
		
		stage('build Zuul_Server package') {
            steps{
                dir('/var/jenkins_home/workspace/test/src/Zuul_Server') {
                    sh 'ls -ltr'
                    sh 'pwd'
                    sh '/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/maven-3.6.3/bin/mvn package'
                }
            }
        }
        stage('build Zuul_Server image & run') {
            steps {
                dir('/var/jenkins_home/workspace/test/src/Zuul_Server') {
                    sh 'ls -ltr'
                    sh 'pwd'
                    sh 'docker build . -t springzuul'
                    sh 'docker run springzuul'
                }
            }
        }
		
		stage('build User_Service package') {
            steps{
                dir('/var/jenkins_home/workspace/test/src/User_Service') {
                    sh 'ls -ltr'
                    sh 'pwd'
                    sh '/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/maven-3.6.3/bin/mvn package'
                }
            }
        }
        stage('build User_Service image & run') {
            steps {
                dir('/var/jenkins_home/workspace/test/src/User_Service') {
                    sh 'ls -ltr'
                    sh 'pwd'
                    sh 'docker build . -t springuser'
                    sh 'docker run springuser'
                }
            }
        }		
		
        stage('build Admin_Service package') {
            steps{
                dir('/var/jenkins_home/workspace/test/src/Admin_Service') {
                    sh 'ls -ltr'
                    sh 'pwd'
                    sh '/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/maven-3.6.3/bin/mvn package'
                }
            }
        }
        stage('build Admin_Service image & run') {
            steps {
                dir('/var/jenkins_home/workspace/test/src/Admin_Service') {
                    sh 'ls -ltr'
                    sh 'pwd'
                    sh 'docker build . -t springadmin'
                    sh 'docker run springadmin'
                }
            }
        }
		
		stage('build Company_Service package') {
            steps{
                dir('/var/jenkins_home/workspace/test/src/Company_Service') {
                    sh 'ls -ltr'
                    sh 'pwd'
                    sh '/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/maven-3.6.3/bin/mvn package'
                }
            }
        }
        stage('build Company_Service image & run') {
            steps {
                dir('/var/jenkins_home/workspace/test/src/Company_Service') {
                    sh 'ls -ltr'
                    sh 'pwd'
                    sh 'docker build . -t springcompany'
                    sh 'docker run springcompany'
                }
            }
        }
    }
}