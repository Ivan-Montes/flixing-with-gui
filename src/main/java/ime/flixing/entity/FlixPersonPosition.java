package ime.flixing.entity;


import java.util.Objects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table( name = "FLIX_PERSON_POSITION")
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FlixPersonPosition {

	@EmbeddedId
	private FlixPersonPositionId id;
	
	@ManyToOne
	@MapsId("flixId")
	@JoinColumn(name = "fx_id")
	private Flix flix;
	
	@ManyToOne
	@MapsId("personId")
	@JoinColumn(name = "per_id")
	private Person person;
	
	@ManyToOne
	@MapsId("positionId")
	@JoinColumn(name = "pos_id")
	private Position position;

	@Override
	public int hashCode() {
		return Objects.hash(flix, id, person, position);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlixPersonPosition other = (FlixPersonPosition) obj;
		return Objects.equals(flix, other.flix) && Objects.equals(id, other.id) && Objects.equals(person, other.person)
				&& Objects.equals(position, other.position);
	}

	
}
