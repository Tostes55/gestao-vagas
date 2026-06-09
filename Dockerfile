# Estágio 1: Build da aplicação usando Maven com Amazon Corretto 21
FROM maven:3.9.6-amazoncorretto-21 AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia os arquivos do projeto para o container
COPY . .

# Executa o build gerando o arquivo .jar (pulando os testes para agilizar)
RUN mvn clean package -DskipTests

# Estágio 2: Execução da aplicação usando a imagem leve do Amazon Corretto 21 (Alpine)
FROM amazoncorretto:21-alpine
WORKDIR /app

EXPOSE 8080

# Copia o jar gerado no estágio de build para a imagem final
COPY --from=build /app/target/gestao-vagas-0.0.1.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]