package com.cts.MovieAppFse.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "MovieDetail")
public class Movie {
	
	public static class MovieId{
		private String movieName;
		private String theatreName;
		public MovieId(String movieName, String theatreName) {
			super();
			this.movieName = movieName;
			this.theatreName = theatreName;
		}
		public MovieId() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "MovieId [movieName=" + movieName + ", theatreName=" + theatreName + "]";
		}
		public String getMovieName() {
			return movieName;
		}
		public void setMovieName(String movieName) {
			this.movieName = movieName;
		}
		public String getTheatreName() {
			return theatreName;
		}
		public void setTheatreName(String theatreName) {
			this.theatreName = theatreName;
		}
		
	}
	
	@Id
	private MovieId mid;
	@Override
	public String toString() {
		return "Movie [mid=" + mid + ", allotedSeats=" + allotedSeats + "]";
	}
	public Movie(MovieId mid, int allotedSeats) {
		super();
		this.mid = mid;
		this.allotedSeats = allotedSeats;
	}
	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}
	private int allotedSeats;
	public MovieId getMid() {
		return mid;
	}
	public void setMid(MovieId mid) {
		this.mid = mid;
	}
	public int getAllotedSeats() {
		return allotedSeats;
	}
	public void setAllotedSeats(int allotedSeats) {
		this.allotedSeats = allotedSeats;
	}
}
