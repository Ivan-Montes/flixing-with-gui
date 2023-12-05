package ime.flixing.dao;

import java.util.List;

import ime.flixing.entity.Flix;

public interface FlixDao {

	List<Flix>getAllFlix();
	Flix getFlixById(Long id);
	Flix getFlixByIdEagger(Long id);
	Flix saveFlix(Flix flix);
	Flix updateFlix(Long id, Flix flix);
	void deleteFlix(Long id);
	
}
