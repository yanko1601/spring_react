package spring.react.jwt.model.view;

public class OutputMessageView {

    private boolean success;
    private String message;

    public OutputMessageView() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
