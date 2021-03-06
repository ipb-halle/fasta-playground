#
# Build web project with Maven
#
# Dockerfile: https://github.com/carlossg/docker-maven/blob/master/adoptopenjdk-8/Dockerfile
FROM maven:3.8.3-adoptopenjdk-8 AS mavenBuild
LABEL stage=fasta-playground-builder

WORKDIR /usr/src/app

COPY ./ui/ .

RUN mvn -B -e -C package

#
# Build fasta36
#
# Note:
# The build and runtime environments need to be the same. Different Debian 
# versions come with different versions of glibc, thus, this build and the
# TomEE images use the same Debian version.
#
FROM debian:buster-slim AS fasta36Build
LABEL stage=fasta-playground-builder

RUN set -ex \
	&& apt-get update \
	&& apt-get -y install gcc make git \
	&& mkdir -p /build \
	&& cd /build \
	&& git clone https://github.com/wrpearson/fasta36.git \
	&& cd fasta36 \
	&& git checkout 8036daa401eb5420202bd0d216d047a0cd2a61fe \
	&& cd src \
	&& make -f ../make/Makefile.linux_sse2 all

#
# Prepare a TomEE container with empty webapps directory and start TomEE as unprivileged user
#
# Dockerfile: https://github.com/tomitribe/docker-tomee/blob/master/TomEE-7.1/jre8/webprofile/Dockerfile
# inherits from https://github.com/docker-library/openjdk/blob/master/8/jre/buster/Dockerfile
FROM tomee:8-jre-7.1.4-webprofile

ENV PATH /usr/local/tomee/bin:$PATH

RUN set -ex \
	&& rm -r /usr/local/tomee/webapps/* \
	&& groupadd tomee \
	&& useradd -g tomee tomee \
	&& chown -R tomee:tomee /usr/local/tomee

COPY --from=mavenBuild /usr/src/app/target/Fasta-Playground.war /usr/local/tomee/webapps/
COPY --from=fasta36Build /build /usr/local/

USER tomee
EXPOSE 8080
CMD ["catalina.sh", "run"]