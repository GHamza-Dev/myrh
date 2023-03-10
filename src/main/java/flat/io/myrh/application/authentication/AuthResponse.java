package flat.io.myrh.application.authentication;


import flat.io.myrh.response.Response;

public class AuthResponse extends Response {
    private String token;

    public AuthResponse() {
    }

    public AuthResponse(String token) {
        this.token = token;
    }

    public AuthResponse(String message, Integer status, String token) {
        super(message, status);
        this.token = token;
    }

    public AuthResponse(String message, String token) {
        super(message);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
