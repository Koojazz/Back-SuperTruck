package cap.capgemini.poe.aston.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="home")
public class Home {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "home_id")
	private Long id;

	@NotNull
	@Lob
	private String paragraphe;
}
