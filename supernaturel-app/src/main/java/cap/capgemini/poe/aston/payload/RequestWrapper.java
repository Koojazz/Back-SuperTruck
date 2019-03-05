package cap.capgemini.poe.aston.payload;

import java.util.List;

import cap.capgemini.poe.aston.entities.Order;
import lombok.Data;

@Data
public class RequestWrapper {
	
	List<Long> ids;
	Order order;

}
