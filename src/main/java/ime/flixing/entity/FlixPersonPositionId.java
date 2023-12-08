package ime.flixing.entity;


import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class FlixPersonPositionId implements Serializable{
	
	private static final long serialVersionUID = -1510794315465005872L;

	@Column( name = "flix_id")
	private Long flixId;
	
	@Column( name = "person_id")
	private Long personId;
	
	@Column( name = "position_id")
	private Long positionId;
}
