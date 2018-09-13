FROM tomcat:8
EXPOSE 8080

RUN mkdir -p /dhis2/home
ENV DHIS2_HOME /dhis2/home
COPY scripts/wait-for-it.sh *.war /dhis2/
COPY dhis2app/dhis.conf /dhis2/home

RUN apt-get update \ 
    && apt-get install -y curl netcat \
    && rm -rf /var/lib/apt/lists/*

RUN if [ ! -f /dhis2/dhis.war ]; \
    then \
        curl -L https://s3-eu-west-1.amazonaws.com/releases.dhis2.org/2.30/dhis.war -o /dhis2/dhis.war; \
    fi; \
    rm -rf /usr/local/tomcat/webapps/* \
    && unzip /dhis2/dhis.war -d /usr/local/tomcat/webapps/ROOT
