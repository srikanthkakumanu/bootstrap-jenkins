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
