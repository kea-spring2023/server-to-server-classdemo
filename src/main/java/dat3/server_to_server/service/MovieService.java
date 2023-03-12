package dat3.server_to_server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import dat3.server_to_server.api_facade.TranslateFacade;
import dat3.server_to_server.entity.Movie;
import dat3.server_to_server.repository.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

//ADD DTO CLASSES HERE

@Service
public class MovieService {

  MovieRepository movieRepository;
  TranslateFacade translateFacade;

  public MovieService(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
    translateFacade = new TranslateFacade();
  }

  public Movie getMovieByImdbId(String imdbId) {
    return movieRepository.findByImdbID(imdbId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));
  }

  public Movie getMovieById(int id) {
    return movieRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));
  }

  public Movie addMovie(String imdbId) throws JsonProcessingException {
    RestTemplate restTemplate = new RestTemplate();

    String API_KEY = "5b9f96f4";
    String plot = "full";
    String url = "http://www.omdbapi.com/?apikey=" + API_KEY + "&i=" + imdbId + "&plot=" + plot;

    //Fetch the movie
    String response = restTemplate.getForObject(url, String.class);
    System.out.println(response);

    //ADD code from snippet file here
    return null;
  }

}
