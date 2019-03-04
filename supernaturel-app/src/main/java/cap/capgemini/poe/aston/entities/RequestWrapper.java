package cap.capgemini.poe.aston.entities;

import java.util.List;

import lombok.Data;

@Data
public class RequestWrapper {
	
	List<Long> ids;
	Order order;

}
