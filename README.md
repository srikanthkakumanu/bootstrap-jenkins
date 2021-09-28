# bootstrap-jenkins

This project goal is to create Jenkins docker image, install recommended plugins, skips setup wizard.

This Gradle project does the following:

- Creates docker image for jenkins.
- Installs all recommended plugins.
- Disables Jenkins login.

## Instructions

1. Build Docker image

  `./gradlew docker`

  It builds Gradle project and creates a docker image.
  e.g. bootstrap-jenkins with TAG 0.1.0-SNAPSHOT

2. Run the docker image

  `./gradlew dockerRun`

  Gradle task dockerRun executes the generated docker image.

3. Jenkins is accessible at http://localhost:8001
4. Create a Jenkins pipeline Seed Job (job name is **seed**). (Therefore, we don't need to create any jobs manually from next time)
  4.1 Select **Git** in **SCM** section, assign bootstrap-jenkins GitHub URL.
  4.2 In **Build** section, select **'Process Job DSLs'** and then select **Look on Filesystem**.
  4.3 In **DSL Scripts**, specify Groovy file name i.e. createJobs.groovy.
  4.4 Save the job configuration.
5. Copy the seed job configuration that we just created.
  5.1 To grab the job config from inside the running docker container:
      `docker cp <CONTAINER ID>:/var/jenkins_home/jobs/seed/config.xml seedJob.xml`
      Note: *seed* is job name we created in Jenkins and *seedJob.xml* is the file we want to name in our local host environment.
6. Execute `docker stop <CONTAINER ID>` to avoid port collisions.
7. Execute `./gradlew docker dockerRun` at project home directory.
