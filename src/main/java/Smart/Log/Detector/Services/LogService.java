package Smart.Log.Detector.Services;

import Smart.Log.Detector.Models.LogEntity;
import Smart.Log.Detector.Models.LogRequestDTO;
import Smart.Log.Detector.Models.LogResponseDTO;
import Smart.Log.Detector.Repository.LogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    private final LogRepository logRepository;
    private final AnomalyDetector anomalyDetector;

    public LogService(LogRepository logRepository, AnomalyDetector anomalyDetector) {
        this.logRepository = logRepository;
        this.anomalyDetector = anomalyDetector;
    }

    public LogResponseDTO ingestLog(LogRequestDTO dto) {
        LogEntity entity = LogEntity.builder()
                .serviceName(dto.getService())
                .logLevel(dto.getLevel().toUpperCase())
                .message(dto.getMessage())
                .build();

        LogEntity savedLog = logRepository.save(entity);

        boolean isAnomaly = anomalyDetector.checkSpike(savedLog.getServiceName(), savedLog.getLogLevel());
        String alert = isAnomaly ? "HIGH ERROR RATE DETECTED IN " + savedLog.getServiceName().toUpperCase() : null;

        return new LogResponseDTO(savedLog, isAnomaly, alert);
    }

    public List<LogEntity> getAllLogs(String service) {
        if (service != null && !service.isBlank()) {
            return logRepository.findByServiceNameOrderByCreatedAtDesc(service);
        }
        return logRepository.findAllByOrderByCreatedAtDesc();
    }
}