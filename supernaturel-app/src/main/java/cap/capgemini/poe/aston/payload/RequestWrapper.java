package cap.capgemini.poe.aston.payload;

import java.util.List;

import lombok.Data;

@Data
public class RequestWrapper {

	List<Long> ids;
	String user_id;

}
