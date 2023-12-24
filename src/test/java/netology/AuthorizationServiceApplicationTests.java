package netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NetologyConditionalApplicationTests {

    private static final String HOST = "http://localhost:";

    @Autowired
    TestRestTemplate testRestTemplate;

    private static GenericContainer<?> devApp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    private static GenericContainer<?> prodApp = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

    @BeforeAll
    public static void startContainers() {
        devApp.start();
        prodApp.start();
    }

    @Test
    public void contextLoadsDevApp() {
        ResponseEntity<String> forDevApp = testRestTemplate
                .getForEntity(HOST + devApp.getMappedPort(8080) + "/profile", String.class);
        Assertions.assertEquals(forDevApp.getBody(), "Current profile is dev");
    }

    @Test
    public void contextLoadsProdApp() {
        ResponseEntity<String> forProdApp = testRestTemplate
                .getForEntity(HOST + prodApp.getMappedPort(8081) + "/profile", String.class);
        Assertions.assertEquals(forProdApp.getBody(), "Current profile is production");
    }
}