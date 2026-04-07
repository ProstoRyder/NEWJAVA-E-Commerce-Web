package ecommercespringlabs.lab1.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GracefulShutdownLogger {

    @EventListener
    public void onContextClosed(ContextClosedEvent event) {
        log.info("SIGTERM received. Starting graceful shutdown...");
    }
}
