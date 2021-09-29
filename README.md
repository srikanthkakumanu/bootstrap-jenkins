# bootstrap-jenkins

This Gradle project goal is to:

- Create Docker image for Jenkins.
- Installs all recommended Jenkins plugins.
- Disables Jenkins login.
- Setup a seed Job in Jenkins.
- Configure seed Job to create a Pipeline Job using Groovy scripts.

## Instructions

1. Build Docker image `./gradlew docker` </br>
    It builds Gradle project and creates a docker image. e.g. bootstrap-jenkins with TAG 0.1.0-SNAPSHOT

2. Run the docker image `./gradlew dockerRun` </br>
    Gradle task dockerRun executes the generated docker image.

3. Jenkins is accessible at http://localhost:8001

4. Create a Jenkins pipeline Seed Job (job name is **seed**). (Therefore, we don't need to create any jobs manually from next time) </br>
    4.1 Select **Git** in **SCM** section, assign bootstrap-jenkins GitHub URL. </br>
    4.2 In **Build** section, select **'Process Job DSLs'** and then select **Look on Filesystem**. </br>
    4.3 In **DSL Scripts**, specify Groovy file name i.e. createJobs.groovy. </br>
    4.4 Save the job configuration. </br>

5. Copy the seed job configuration that we just created. </br>
    5.1 To grab the job config from inside the running docker   container: </br> `docker cp <CONTAINER ID>:/var/jenkins_home/jobs/seed/config.xml seedJob.xml` </br>
        Note: *seed* is job name we created in Jenkins and *seedJob.xml* is the file we want to name in our local host environment.

6. Execute `docker stop <CONTAINER ID>` to avoid port collisions.
7. Execute `./gradlew docker dockerRun` at project home directory.

After Jekins docker image restart, seed-job still exist in Jenkins which help us in avoiding manual pipeline job creation each time we restart Jenkins Docker container.

## Creating New Pipeline Jobs

Once Bootstrap Jenkins is setup and running, then creating new pipeline jobs are easy. You just need to add new pipeline job code block in ***createJobs.groovy*** script.

For more details, please verify rapi-job branch in this Git repo.

e.g.

```Groovy
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
```
