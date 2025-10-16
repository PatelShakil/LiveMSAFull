FROM payara/micro:6.2024.5-jdk17

ENV DEPLOY_DIR=/opt/payara/deployments

COPY artifact/LiveMSAClient.war ${DEPLOY_DIR}/

EXPOSE 8080

ENTRYPOINT ["java","-jar","/opt/payara/payara-micro.jar","--deploy","/opt/payara/deployments/LiveMSAClient.war"]
