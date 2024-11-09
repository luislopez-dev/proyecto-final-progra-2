# Usa una imagen base de Amazon Corretto 17
FROM amazoncorretto:17

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR construido al contenedor
COPY build/libs/app-0.0.1-SNAPSHOT.jar /app/app.jar

# Exponemos el puerto que utiliza la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
