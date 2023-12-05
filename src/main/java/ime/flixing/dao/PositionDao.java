package ime.flixing.dao;

import java.util.List;

import ime.flixing.entity.Position;

public interface PositionDao {

	List<Position> getAllPosition();
	Position getPositionById(Long id);
	Position getPositionByIdEagger(Long id);
	Position savePosition(Position position);
	Position updatePosition(Long id, Position position);
	void deletePosition(Long id);
	List<Position> getPositionByNameId(String name);
	
}
