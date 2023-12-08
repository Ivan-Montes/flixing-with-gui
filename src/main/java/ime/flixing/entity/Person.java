package ime.flixing.entity;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "PEOPLE")
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Person {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column( name = "person_id")
	@Max(33)
	private Long personId;
	
	@Column( nullable = false, length = 50 )
	@Size( min = 1, max = 50)
	@Pattern( regexp = "[a-zA-Z\\s\\-&]+")
	private String name;
	
	@Column( nullable = false, length = 50 )
	@Size( min = 1, max = 50 )
	@Pattern( regexp = "[a-zA-Z\\s\\-&]+" )
	private String surname;
	
	@OneToMany( mappedBy = "person")
	private Set<FlixPersonPosition>flixPersonPosition = new HashSet<>();

	@Override
	public String toString() {
		return "Person [personId=" + personId + ", name=" + name + ", surname=" + surname + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, personId, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(flixPersonPosition, other.flixPersonPosition) && Objects.equals(name, other.name)
				&& Objects.equals(personId, other.personId) && Objects.equals(surname, other.surname);
	}
	
	
}
