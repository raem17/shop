package movies.shop.constantsSQL;

public class SQLConstantsMovies {
	
	public final static String GET_MOVIES_FOR_JSON = "SELECT m.id, m.length_min, m.price, m.release_year, m.synopsis, "
			+ "m.title, m.director, m.country, m.ean, m.editor, m.format, g.name AS 'genre' FROM movies as m INNER JOIN genres as "
			+ "g ON m.genre_id = g.id WHERE m.activated = 1 ORDER BY m.id DESC";
	
	public final static String GET_MOVIES_BY_TITLE_FOR_JSON = "SELECT m.id, m.length_min, m.price, m.release_year, m.synopsis, "
			+ "m.title, m.director, m.country, m.ean, m.editor, m.format, g.name AS 'genre' FROM movies as m INNER JOIN genres as g "
			+ "ON m.genre_id = g.id WHERE m.activated = 1 and lower(m.title) like lower(:title) ORDER BY m.id DESC";
	
	public final static String GET_GENRES_FOR_DROPDOWN = "SELECT genres.id, genres.name, genres.description FROM genres order by id desc";

	public static final String GET_CART_PRODUCTS = "SELECT movies.id AS movie_id, movies.title, "
			+ "movies.price AS movie_price, cart_products.quantity FROM movies INNER JOIN cart_products ON movies.id "
			+ "= cart_products.movie_id AND cart_products.cart_id = :cart_id "
			+ "ORDER BY cart_products.id ASC";

	public static final String GET_MOVIE_DETAILS = "SELECT m.id, m.length_min, m.price, m.release_year, m.synopsis, "
			+ "m.title, m.director, m.country, m.ean, m.editor, m.format, g.name AS 'genre' FROM movies as m INNER JOIN genres as g "
			+ "ON m.genre_id = g.id WHERE m.id = :id";

	public static final String DELETE_CART_PRODUCTS = "delete from cart_products where cart_id = :cart_id";
	
	public static final String DELETE_CART_PRODUCT = "DELETE FROM cart_products WHERE cart_id = :cart_id "
			+ "AND movie_id = :movie_id";
	
	public static final String GET_TOTAL_MOVIES = "SELECT COUNT(id) from movies WHERE movies.activated = 1"
			+ " and movies.title like :title";
	
}
