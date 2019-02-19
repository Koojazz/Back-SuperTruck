package cap.capgemini.poe.aston.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="contact")
public class Contact {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contact_id")
	private Long id;
	@Email
	private String email;
	private String tel;


}
