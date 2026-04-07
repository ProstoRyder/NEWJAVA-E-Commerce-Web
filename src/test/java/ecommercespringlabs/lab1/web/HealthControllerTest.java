package ecommercespringlabs.lab1.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HealthControllerTest {

    @Test
    void healthReturnsUpWhenDatabaseIsAvailable() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        when(jdbcTemplate.queryForObject("SELECT 1", Integer.class)).thenReturn(1);

        HealthController controller = new HealthController(jdbcTemplate);

        ResponseEntity<Map<String, String>> response = controller.health();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("UP", response.getBody().get("status"));
    }

    @Test
    void healthReturnsDownWhenDatabaseIsUnavailable() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        doThrow(new RuntimeException("db unavailable"))
                .when(jdbcTemplate)
                .queryForObject("SELECT 1", Integer.class);

        HealthController controller = new HealthController(jdbcTemplate);

        ResponseEntity<Map<String, String>> response = controller.health();

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
        assertEquals("DOWN", response.getBody().get("status"));
    }
}
