package ime.flixing.dao;

import java.util.List;

import ime.flixing.entity.FlixPersonPosition;

public interface FlixPersonPositionDao {

	List<FlixPersonPosition>getAllFlixPersonPosition();
	FlixPersonPosition getFlixPersonPositionById(Long flixId, Long personId, Long positionId);
	FlixPersonPosition saveFlixPersonPosition(Long flixId, Long personId, Long positionId);
	void deleteFlixPersonPosition(Long flixId, Long personId, Long positionId);
	
}
