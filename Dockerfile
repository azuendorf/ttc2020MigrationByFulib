
FROM gradle:6.5.0-jdk14

WORKDIR /fulibsolution
COPY . .
RUN gradle compileJava compileTestJava
CMD gradle test && cat results.csv

