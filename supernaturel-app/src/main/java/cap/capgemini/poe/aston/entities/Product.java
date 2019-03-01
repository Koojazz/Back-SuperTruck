package cap.capgemini.poe.aston.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;
	@NotBlank
    private String name;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn
//    @JsonIgnoreProperties("products")
    private Category category;
    @NotNull
    private Double price;
    @Lob
    private String description;
    private String image;
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinTable(name = "products_orders",
    	joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"),
    	inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "order_id"))
    private List<Order> order;
    private String picture;
    
}
