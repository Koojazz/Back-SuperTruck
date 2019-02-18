package cap.capgemini.poe.aston.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
    private String name;
//    @NotBlank
    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("products")
    private Category category;
//    @NotBlank
    private Double price;
    @Lob
    private String description;
    private String image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Order order;
    
}
