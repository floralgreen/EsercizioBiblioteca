package esercizi.EsercizioBiblioteca.entities.DTO;

public class BaseResponse {

    private int status;
    private String statusMessage;
    private String message;
    private Object data;

    public BaseResponse(){}

    public BaseResponse(int status, String statusMessage, String message, Object data) {
        this.status = status;
        this.statusMessage = statusMessage;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
