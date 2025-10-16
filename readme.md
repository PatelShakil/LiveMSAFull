# 🧩 MSA App – Dockerized with MySQL Database

This guide provides step-by-step instructions to set up and run the **MSA Application** using **Docker**, **Payara Micro**, and **MySQL 8.0** in a containerized environment.

---

## 🚀 1. Create Docker Network

```bash
docker network create msa-net
```

This network will be used to connect all MSA containers.

---

## 🗄️ 2. Create MySQL Container

Run the following command to create a MySQL container with a predefined database and credentials:

```bash
docker run -d   --name msamysql   --network msa-net   -e MYSQL_ROOT_PASSWORD=shakil   -e MYSQL_DATABASE=msadb   -v msa_data:/var/lib/mysql   -p 3406:3306   mysql:8.0
```

---

## ⚙️ 3. Resource Configuration

### 3.1 `domain.xml` Configuration

Add the following resource pool and resource reference:

```xml
<jdbc-connection-pool name="mysqlpool"
    datasource-classname="com.mysql.cj.jdbc.MysqlDataSource"
    res-type="javax.sql.DataSource">
    <property name="serverName" value="msamysql"/>
    <property name="portNumber" value="3306"/>
    <property name="databaseName" value="msadb"/>
    <property name="user" value="root"/>
    <property name="password" value="shakil"/>
    <property name="useSSL" value="false"/>
    <property name="allowPublicKeyRetrieval" value="true"/>
    <property name="requireSSL" value="false"/>
    <property name="sslMode" value="DISABLED"/>
</jdbc-connection-pool>

<jdbc-resource pool-name="mysqlpool" jndi-name="jdbc/mysql"/>
```

---

### 3.2 Initialize Database with Data

1. Access MySQL terminal:
   ```bash
   mysql -h localhost -P 3406 -uroot -p
   ```
2. Update root user for external access:
   ```sql
   ALTER USER 'root'@'%' IDENTIFIED BY 'shakil';
   ```
3. Export data from **phpMyAdmin** (SQL dump) and paste it in the terminal to initialize the schema and data.

---

### 3.3 Dockerfile for Resource Service

Create a `Dockerfile` in your **resource service** directory:

```dockerfile
# Fetch Payara Micro in Docker container
FROM payara/micro:6.2024.5-jdk17

# Deployment directory
ENV DEPLOY_DIR=/opt/payara/deployments

# Create a custom config folder
RUN mkdir ${PAYARA_HOME}/config
COPY --chown=payara:payara domain.xml ${PAYARA_HOME}/config/
COPY --chown=payara:payara mysql-connector-java-8.0.20.jar ${PAYARA_HOME}/config/

USER payara
WORKDIR ${PAYARA_HOME}

# Copy WAR file
COPY artifact/LiveMSAResource.war ${DEPLOY_DIR}/

EXPOSE 8080

# Start Payara Micro
CMD ["--addLibs","/opt/payara/config/mysql-connector-java-8.0.20.jar",
     "--deploymentDir","/opt/payara/deployments",
     "--rootDir","/opt/payara/config",
     "--domainConfig","/opt/payara/config/domain.xml"]
```

---

### 3.4 Build Resource Image

```bash
docker build -t msaresource .
```

---

### 3.5 Run Resource Container

```bash
docker run -d   --name msaresource   --network msa-net   -p 8085:8080   msaresource
```

---

### 3.6 Debugging Logs

To view logs from the Payara container:

```bash
docker logs -f msaresource
```

---

## 💻 4. Client Service Configuration

### 4.1 Update REST Client Base URL

In your client service, update the base URI:

```java
@RegisterRestClient(baseUri = "http://msaresource:8080/LiveMSAResource/rest/land")
```

---

### 4.2 Dockerfile for Client Service

```dockerfile
# Fetch Payara Micro base image
FROM payara/micro:6.2024.5-jdk17

# Deployment directory
ENV DEPLOY_DIR=/opt/payara/deployments

USER payara
WORKDIR ${PAYARA_HOME}

# Copy WAR file
COPY artifact/LiveMSAClient.war ${DEPLOY_DIR}/

# Expose Payara Micro default port
EXPOSE 8080

# Run Payara Micro with deployment
ENTRYPOINT ["java", "-jar", "/opt/payara/payara-micro.jar",
            "--deploy", "/opt/payara/deployments/LiveMSAClient.war"]
```

---

### 4.3 Build & Run Client Container

Build the image:

```bash
docker build -t msaclient .
```

Run the container:

```bash
docker run -d   --name msaclient   --network msa-net   -p 8086:8080   msaclient
```

---

## 🌐 5. Access Application

Once both containers are running, visit:

👉 http://localhost:8086/LiveMSAClient/faces/LandReport.xhtml

---

## 🧱 Summary

| Component       | Container Name | Port | Image Tag     | Description                   |
|-----------------|----------------|------|----------------|--------------------------------|
| MySQL Database  | msamysql       | 3406 | mysql:8.0      | Stores MSA data                |
| Resource Service| msaresource    | 8085 | msaresource    | Backend resource API service   |
| Client Service  | msaclient      | 8086 | msaclient      | Front-end client (JSF UI)      |

---

### 🧩 Network Topology

        +-------------------+
        |    msaclient      |
        |  (Port: 8086)     |
        +---------+---------+
                  |
            [ msa-net ]
                  |
        +---------+---------+
        |   msaresource     |
        |  (Port: 8085)     |
        +---------+---------+
                  |
            [ msa-net ]
                  |
        +---------+---------+
        |    msamysql       |
        |  (Port: 3406)     |
        +-------------------+

---

### 🧰 Useful Commands

| Command | Description |
|----------|-------------|
| docker ps | View running containers |
| docker logs -f <container> | Stream container logs |
| docker exec -it msamysql bash | Access MySQL container shell |
| docker network inspect msa-net | View network connections |

---

**Author:** M. Shakil Patel  
**Technologies:** Docker · Payara Micro · MySQL 8.0  
**Purpose:** Containerized Microservice Architecture Setup for MSA Application
