package ime.flixing.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import ime.flixing.tool.CheckerPattern;
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
@Table( name = "POSITIONS")
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Position {

	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "position_id")
	@Max(33)
	private Long positionId;
	
	@Column(unique = true, nullable = false, length = 50)
	@Size( min = 1, max = 50)
	@Pattern( regexp = CheckerPattern.NAME_BASIC)
	private String name;
	
	@Column(nullable = true, length = 100)
	@Size( min = 0, max = 100)
	@Pattern( regexp = CheckerPattern.DESCRIPTION_BASIC)
	private String description;
	
	@OneToMany( mappedBy = "position")
	private Set<FlixPersonPosition>flixPersonPosition = new HashSet<>();

	@Override
	public String toString() {
		return "Position [positionId=" + positionId + ", name=" + name + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, name, positionId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		return Objects.equals(description, other.description)
				&& Objects.equals(flixPersonPosition, other.flixPersonPosition) && Objects.equals(name, other.name)
				&& Objects.equals(positionId, other.positionId);
	}
	
	
}
