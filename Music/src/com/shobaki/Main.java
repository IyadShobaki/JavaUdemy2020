package com.shobaki;

import model.Artist;
import model.DataSource;
import model.SongArtist;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        DataSource dataSource = new DataSource();
        if(!dataSource.open()){
            System.out.println("Can't open datasource");
            return;
        }

        List<Artist> artists = dataSource.queryArtists(5); //if we pass any number the result will be DESC by default
        if(artists == null){
            System.out.println("No artists");
            return;
        }
        for(Artist artist : artists){
            System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());
        }

        List<String> albumsForArtist =
                dataSource.queryAlbumsForArtist("Carole King", DataSource.ORDER_BY_ASC);

        for(String album : albumsForArtist){
            System.out.println(album);
        }

//        List<SongArtist> songArtists = dataSource.queryArtistsForSong("Heartless", DataSource.ORDER_BY_ASC);
//        List<SongArtist> songArtists = dataSource.queryArtistsForSong("She's On Fire", DataSource.ORDER_BY_ASC);

        List<SongArtist> songArtists = dataSource.queryArtistsForSong("Go Your Own Way", DataSource.ORDER_BY_ASC);
        if(songArtists == null){
            System.out.println("Couldn't find the artist for the song");
            return;
        }
        for(SongArtist artist : songArtists){
            System.out.println("Artist name = " + artist.getArtistName() +
                    " Album name = " + artist.getAlbumName() +
                    " Track = " + artist.getTrack());
        }

        dataSource.querySongsMetadata();

        dataSource.close();
    }
}
