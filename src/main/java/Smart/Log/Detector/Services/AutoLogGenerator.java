package Smart.Log.Detector.Services;

import Smart.Log.Detector.Models.LogRequestDTO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service; // 1. Add this import

import java.util.Random;

@Service // 2. ADD THIS ANNOTATION HERE!
public class AutoLogGenerator {
    private final LogService logService;
    private final Random random = new Random();

    private final String[] services = {"user-service", "payment-service", "auth-service", "notification-service"};
    private final String[] infoMessages = {"User session validated", "Database query executed in 12ms", "Cache refreshed successfully"};
    private final String[] warnMessages = {"High memory usage detected (82%)", "API response time > 500ms", "Connection pool near capacity"};
    private final String[] errorMessages = {"Database connection timeout", "Payment gateway 503 error", "JWT signature verification failed"};

    public AutoLogGenerator(LogService logService) {
        this.logService = logService;
    }

    // Runs automatically every 3 seconds (3000ms)
    @Scheduled(fixedRate = 3000)
    public void generateNormalTraffic() {
        String service = services[random.nextInt(services.length)];

        // 70% chance INFO, 20% WARN, 10% ERROR
        int chance = random.nextInt(100);
        String level;
        String message;

        if (chance < 70) {
            level = "INFO";
            message = infoMessages[random.nextInt(infoMessages.length)];
        } else if (chance < 90) {
            level = "WARN";
            message = warnMessages[random.nextInt(warnMessages.length)];
        } else {
            level = "ERROR";
            message = errorMessages[random.nextInt(errorMessages.length)];
        }

        logService.ingestLog(new LogRequestDTO(service, level, message));
    }

    // Automatically triggers a 5-error spike every 30 seconds for demonstration
    @Scheduled(fixedRate = 30000)
    public void generateAutomatedAnomalySpike() {
        String targetService = "payment-service";
        System.out.println("⚡ AUTOMATED ANOMALY SIMULATION: Generating error spike for " + targetService);

        for (int i = 1; i <= 5; i++) {
            logService.ingestLog(new LogRequestDTO(
                    targetService,
                    "ERROR",
                    "Automated system outage event #" + i
            ));
        }
    }
}