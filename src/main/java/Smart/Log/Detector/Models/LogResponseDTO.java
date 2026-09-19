package Smart.Log.Detector.Models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogResponseDTO {
    private LogEntity log;
    private boolean anomalyDetected;
    private String alertMessage;

}