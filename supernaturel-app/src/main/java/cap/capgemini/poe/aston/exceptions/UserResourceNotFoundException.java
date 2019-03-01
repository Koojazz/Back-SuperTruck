package cap.capgemini.poe.aston.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserResourceNotFoundException() {
        super();
    }

    public UserResourceNotFoundException(String message) {
        super(message);
    }

    public UserResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
