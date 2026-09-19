package Smart.Log.Detector.Controller;

import Smart.Log.Detector.Models.LogEntity;
import Smart.Log.Detector.Models.LogRequestDTO;
import Smart.Log.Detector.Models.LogResponseDTO;
import Smart.Log.Detector.Services.LogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/logs")
@CrossOrigin(origins = "*") // Allows the HTML frontend to send requests without CORS blocking
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping
    public ResponseEntity<LogResponseDTO> createLog(@RequestBody LogRequestDTO request) {
        return ResponseEntity.ok(logService.ingestLog(request));
    }

    @GetMapping
    public ResponseEntity<List<LogEntity>> getLogs(@RequestParam(required = false) String service) {
        return ResponseEntity.ok(logService.getAllLogs(service));
    }
}