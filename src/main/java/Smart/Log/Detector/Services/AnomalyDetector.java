package Smart.Log.Detector.Services;

import Smart.Log.Detector.Repository.LogRepository;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class AnomalyDetector {

    private final LogRepository logRepository;

    public AnomalyDetector(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public boolean checkSpike(String serviceName, String level) {
        if (!"ERROR".equalsIgnoreCase(level) && !"CRITICAL".equalsIgnoreCase(level)) {
            return false;
        }

        LocalDateTime window = LocalDateTime.now().minusSeconds(60);
        long errorCount = logRepository.countByServiceNameAndLogLevelAndCreatedAtAfter(serviceName, level, window);

        return errorCount >= 5;
    }
}