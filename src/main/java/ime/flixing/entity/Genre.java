package ime.flixing.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
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
@Table( name = "GENRES")
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Genre {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "genre_id")
	@Max(33)
	private Long genreId;
	
	@Column(unique = true, nullable = false, length = 50)
	@Size( min = 1, max = 50)
	@Pattern( regexp = "[a-zA-Z\\s\\-&]+")
	private String name;
	
	@Column(nullable = true, length = 100)
	@Size( min = 0, max = 100)
	@Pattern( regexp = "[a-zA-Z0-9\\s\\-&]+")
	private String description;
	
	@OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Flix>flixes = new HashSet<>();

	@Override
	public String toString() {
		return "Genre [genreId=" + genreId + ", name=" + name + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, genreId, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genre other = (Genre) obj;
		return Objects.equals(description, other.description) && Objects.equals(flixes, other.flixes)
				&& Objects.equals(genreId, other.genreId) && Objects.equals(name, other.name);
	}
	
	
}
