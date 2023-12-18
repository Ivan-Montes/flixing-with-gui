package ime.flixing.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import ime.flixing.tool.CheckerPattern;

import java.util.HashSet;
import java.util.Objects;

import jakarta.persistence.OneToMany;

@Entity
@Table( name = "FLIXES" )
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Flix {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "flix_id" )
	@Max(33)
	private Long flixId;
	
	@Column( nullable = false, length = 50 )
	@Size(min=1, max=50)
	@Pattern( regexp = CheckerPattern.TITLE_FLIX_BASIC)
	private String title;
		
	@ManyToOne
	@JoinColumn( name = "genre_id", nullable= false)
	private Genre genre;
	
	@OneToMany( mappedBy = "flix")
	private Set<FlixPersonPosition> flixPersonPosition = new HashSet<>();

	@Override
	public String toString() {
		return "Flix [flixId=" + flixId + ", title=" + title + ", genre=" + genre + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(flixId, genre, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flix other = (Flix) obj;
		return Objects.equals(flixId, other.flixId) && Objects.equals(flixPersonPosition, other.flixPersonPosition)
				&& Objects.equals(genre, other.genre) && Objects.equals(title, other.title);
	}
		
	
}
