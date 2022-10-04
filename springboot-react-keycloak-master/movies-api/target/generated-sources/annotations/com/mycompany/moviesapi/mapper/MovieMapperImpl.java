package com.mycompany.moviesapi.mapper;

import com.mycompany.moviesapi.model.Movie;
import com.mycompany.moviesapi.model.Movie.Comment;
import com.mycompany.moviesapi.rest.dto.CreateMovieRequest;
import com.mycompany.moviesapi.rest.dto.MovieDto;
import com.mycompany.moviesapi.rest.dto.MovieDto.CommentDto;
import com.mycompany.moviesapi.rest.dto.UpdateMovieRequest;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-24T20:11:53-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class MovieMapperImpl extends MovieMapper {

    @Override
    public Movie toMovie(CreateMovieRequest createMovieRequest) {
        if ( createMovieRequest == null ) {
            return null;
        }

        Movie movie = new Movie();

        movie.setImdbId( createMovieRequest.getImdbId() );
        movie.setTitle( createMovieRequest.getTitle() );
        movie.setDirector( createMovieRequest.getDirector() );
        movie.setYear( createMovieRequest.getYear() );
        movie.setPoster( createMovieRequest.getPoster() );

        return movie;
    }

    @Override
    public void updateMovieFromDto(UpdateMovieRequest updateMovieRequest, Movie movie) {
        if ( updateMovieRequest == null ) {
            return;
        }

        if ( updateMovieRequest.getTitle() != null ) {
            movie.setTitle( updateMovieRequest.getTitle() );
        }
        if ( updateMovieRequest.getDirector() != null ) {
            movie.setDirector( updateMovieRequest.getDirector() );
        }
        if ( updateMovieRequest.getYear() != null ) {
            movie.setYear( updateMovieRequest.getYear() );
        }
        if ( updateMovieRequest.getPoster() != null ) {
            movie.setPoster( updateMovieRequest.getPoster() );
        }
    }

    @Override
    public MovieDto toMovieDto(Movie movie) {
        if ( movie == null ) {
            return null;
        }

        MovieDto movieDto = new MovieDto();

        movieDto.setImdbId( movie.getImdbId() );
        movieDto.setTitle( movie.getTitle() );
        movieDto.setDirector( movie.getDirector() );
        movieDto.setYear( movie.getYear() );
        movieDto.setPoster( movie.getPoster() );
        movieDto.setComments( commentListToCommentDtoList( movie.getComments() ) );

        return movieDto;
    }

    @Override
    public CommentDto toMovieDtoCommentDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDto commentDto = new CommentDto();

        commentDto.setUsername( comment.getUsername() );
        commentDto.setText( comment.getText() );
        commentDto.setTimestamp( comment.getTimestamp() );

        commentDto.setAvatar( userExtraService.getUserExtra(comment.getUsername()).isPresent() ? userExtraService.getUserExtra(comment.getUsername()).get().getAvatar() : comment.getUsername() );

        return commentDto;
    }

    protected List<CommentDto> commentListToCommentDtoList(List<Comment> list) {
        if ( list == null ) {
            return null;
        }

        List<CommentDto> list1 = new ArrayList<CommentDto>( list.size() );
        for ( Comment comment : list ) {
            list1.add( toMovieDtoCommentDto( comment ) );
        }

        return list1;
    }
}
