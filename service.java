```dockerfile
# Dockerfile

# Use a specific version of OpenJDK
FROM openjdk:11-jdk-slim as build 

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Create the final image
FROM openjdk:11-jre-slim
COPY --from=build /app/target/myapp.jar /app/myapp.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app/myapp.jar"]
```

```yaml
# application.yml

spring:
  datasource:
    url: ${DATABASE_URL}
    username: ${DATABASE_USERNAME}
    password: ${DATABASE_PASSWORD}
  # Other properties grouped under spring key
```

```xml
<!-- pom.xml -->
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <version>2.5.4</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
        <version>2.5.4</version>
    </dependency>
    <!-- Other dependencies -->
</dependencies>
```

```java
// StockServiceImpl.java

@Service
public class StockServiceImpl implements StockService {

    @Override
    public void updateStockPrice(String stockName, double newPrice) {
        // Implementation for updating stock price
    }

    public void updateMultipleStocks(Map<String, Double> stockUpdates) {
        stockUpdates.forEach(this::updateStockPrice);
    }
}
```

```java
// StockController.java

@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStock(@PathVariable String id) {
        try {
            Stock stock = stockService.findStockById(id);
            return ResponseEntity.ok(stock);
        } catch (StockNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
```

```java
// Logging example with SLF4J

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StockServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

    public void updateStockPrice(String stockName, double newPrice) {
        logger.info("Updating stock price for {} to {}", stockName, newPrice);
        // Implementation for updating stock price
    }
}
```

```java
// StockServiceTest.java

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StockServiceTest {

    @Autowired
    private StockService stockService;

    @Test
    public void testUpdateStockPrice() {
        // Test updating stock price
    }
}
```

```javascript
// stock-table-init.js

document.addEventListener("DOMContentLoaded", function() {
    // Initialization logic for the stock table
});
``` 

Make sure to add error handling, security features, WebSocket handling, and other areas of improvement as per your project needs and dependencies.