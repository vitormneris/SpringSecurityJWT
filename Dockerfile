FROM gradle:8.7-jdk21-alpine AS build

WORKDIR /home/gradle/src

# Copiar arquivos gradle e código-fonte
COPY build.gradle .
COPY settings.gradle .
COPY src/ src/

# Executar o build, desabilitando os testes
RUN gradle build --no-daemon -x test

# Etapa de produção
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copiar o arquivo JAR gerado durante o build
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar

# Expor a porta 8080
EXPOSE 8080

# Definir o comando de entrada
CMD ["java", "-jar", "app.jar"]
