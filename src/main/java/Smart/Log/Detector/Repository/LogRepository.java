package Smart.Log.Detector.Repository;

import Smart.Log.Detector.Models.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<LogEntity, Long> {

    List<LogEntity> findAllByOrderByCreatedAtDesc();

    List<LogEntity> findByServiceNameOrderByCreatedAtDesc(String serviceName);

    // Make sure this exact method signature exists:
    long countByServiceNameAndLogLevelAndCreatedAtAfter(String serviceName, String logLevel, LocalDateTime timeWindow);
}