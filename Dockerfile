#FROM jenkins/jenkins:alpine
FROM jenkins/jenkins:2.289.1-jdk11

# To Run Docker inside Jenkins (To Run Docker inside Jenkins Docker container)
USER root
RUN curl -sSL https://get.docker.com/ | sh
RUN usermod -a -G docker jenkins
RUN service docker start
USER jenkins
# end

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt

RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

COPY seedJob.xml /usr/share/jenkins/ref/jobs/seed-job/config.xml


ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
