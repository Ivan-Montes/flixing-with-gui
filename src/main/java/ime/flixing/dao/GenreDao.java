package ime.flixing.dao;

import java.util.List;
import ime.flixing.entity.Genre;

public interface GenreDao {
	
	List<Genre>getAllGenre();
	Genre getGenreById(Long id);
	Genre getGenreByIdEagger(Long id);
	Genre saveGenre(Genre genre);
	Genre updateGenre(Long id, Genre genre);
	void deleteGenre(Long id);
	List<Genre>getGenreByName(String name);
}
