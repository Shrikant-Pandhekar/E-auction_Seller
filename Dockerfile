FROM openjdk:11
ADD target/Seller-0.0.1-SNAPSHOT.jar seller.jar
ENTRYPOINT ["java","-jar","seller.jar"]