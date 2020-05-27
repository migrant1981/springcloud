pipeline {
   agent any
   stages {
        stage('Fetch code') {
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
		
        stage('build mysql image & run') {
            steps {
                dir('/var/jenkins_home/workspace/test/src') {
                    sh 'ls -ltr'
                    sh 'pwd'
                    sh 'docker pull mysql:5.7'
					sh 'mkdir -p /opt/docker-mysql/conf.d'
					sh 'mkdir -p /opt/docker-mysql/logs'
					sh 'mkdir -p /opt/docker-mysql/data'
					sh 'docker run -p 3306:3306 --name mysql \
						-v /opt/docker-mysql/conf.d:/etc/mysql \
						-v /opt/docker-mysql/logs:/var/log/mysql \
						-v /opt/docker-mysql/data:/var/lib/mysql \
						-e MYSQL_ROOT_PASSWORD=111111 \
						-d mysql:5.7 \
						--character-set-server=utf8mb4 \
						--collation-server=utf8mb4_unicode_ci \
						-h fsd-stock-mysql'
					sh 'docker cp /var/jenkins_home/workspace/test/src/stock.sql mysql:/var/lib/mysql'
					sh 'docker ps -a'
                    sh 'docker exec -it mysql bash'
					sh 'mysql -uroot -p111111 < /var/lib/mysql/stock.sql'
					sh 'use stock'
					sh 'show tables;'
					sh 'exit;'
					sh 'pwd'				
                }
            }
        }
    }
}