# Usar a imagem base do OpenJDK 21
FROM openjdk:21-jdk-slim

# Definir o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar o JAR gerado para dentro do contêiner
COPY target/pwaapp-0.0.1-SNAPSHOT.jar app.jar

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]

# Expôr a porta que a aplicação estará escutando
EXPOSE 8080