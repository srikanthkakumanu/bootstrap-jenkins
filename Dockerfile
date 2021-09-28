FROM jenkins/jenkins:alpine
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt

COPY SeedJob.xml /usr/share/jenkins/ref/jobs/seed-job/config.xml

ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
