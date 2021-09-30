pipelineJob('pipelineJob') {
    definition {
        cps {
            script(readFileFromWorkspace('pipelineJob.groovy'))
            sandbox()
        }
    }
}
pipelineJob('rapi-job') {
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url 'https://github.com/srikanthkakumanu/RAPI.git'
                    }
                    branch 'main'
                }
            }
        }
    }
}
// Jenkins run this job as docker container i.e. Building micro-service in Jenkins itself
pipelineJob('rapi-job-docker') {
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url 'https://github.com/srikanthkakumanu/RAPI.git'
                    }
                    branch 'main'
                    // to custom Jenkinsfile i.e. Jenkinsfile-Docker
                    // It builds micro-service docker image and push
                    // to DockerHub
                    scriptPath 'Jenkinsfile-Docker'
                }
            }
        }
    }
}
