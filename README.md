# BookingGo-Technical-Test
 Technical Test implementation for BookingGo

## Requirements
Java - 1.8.0

Apache Maven - 4.0.0

## How to use:
```mvn clean compile assembly:single```

## Part 1A
Run:
``` java -cp target/technicaltest-0.0.1-SNAPSHOT-jar-with-dependencies.jar warraich.mohsin.technicaltest.cmd.Part1A [lat1] [long1] [lat2] [long2] [passengers] ```

Replace [lat1] [long1] with pickup coordinates.

Replace [lat2] [long2] with dropoff coordinates.

Replace [passengers] with the desired number of passengers. This argument is optional.

e.g:

```java -cp target/technicaltest-0.0.1-SNAPSHOT-jar-with-dependencies.jar warraich.mohsin.technicaltest.cmd.Part1B 1.1 1.2 3.4 4.3 3```
## Part 1B
Run:
```java -cp target/technicaltest-0.0.1-SNAPSHOT-jar-with-dependencies.jar warraich.mohsin.technicaltest.cmd.Part1B [lat1] [long1] [lat2] [long2] [passengers]```

Replace [lat1] [long1] with pickup coordinates

Replace [lat2] [long2] with dropoff coordinates

Replace [passengers] with the desired number of passengers. This argument is optional.

E.g:
```java -cp target/technicaltest-0.0.1-SNAPSHOT-jar-with-dependencies.jar warraich.mohsin.technicaltest.cmd.Part1B 1.1 1.2 3.4 4.3 3```
## Part 2
Run this command to start the server:

```java -cp target/technicaltest-0.0.1-SNAPSHOT-jar-with-dependencies.jar warraich.mohsin.technicaltest.rest.Part2```

### Api documentation
Enter in the browser:

```http://localhost:8080/query?pickup=[lat1],[long1]&dropoff=[lat2,long2]&passengers=[passengers]```

Replace [lat1] [long1] with pickup coordinates

Replace [lat2] [long2] with dropoff coordinates.

Replace [passengers] with the desired number of passengers. Replace with 0 if you do not want to enter the number of passengers.

E.g:
```http://localhost:8080/query?pickup=2.2,1.3&dropoff=1.1,2.1&passengers=5```

##### NOTE: If the server does not start, then please use an IDE
