FROM openjdk:8
EXPOSE 8080
ADD target/SpringStockMvcSec-0.0.1-SNAPSHOT.jar SpringStockMvcSec-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/SpringStockMvcSec-0.0.1-SNAPSHOT.jar"]