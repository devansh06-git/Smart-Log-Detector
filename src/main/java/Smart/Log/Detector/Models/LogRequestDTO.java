package Smart.Log.Detector.Models;

public class LogRequestDTO {
    private String service;
    private String level;
    private String message;

    public LogRequestDTO() {
    }

    public LogRequestDTO(String service, String level, String message) {
        this.service = service;
        this.level = level;
        this.message = message;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}