# Get the base Ubuntu image from Docker Hub
FROM ubuntu:18.04
 
# Update apps on the base image
RUN apt-get -y update && \
    apt-get install -y && \
    apt-get install -y dos2unix && \
    apt-get install -y openjdk-8-jdk

ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/

ADD execute_test.sh /usr/local/bin/execute_test.sh
RUN chmod 777 /usr/local/bin/execute_test.sh
RUN dos2unix /usr/local/bin/execute_test.sh
RUN ln -s /usr/local/bin/execute_test.sh / # backwards compat

ENTRYPOINT ["execute_test.sh"]