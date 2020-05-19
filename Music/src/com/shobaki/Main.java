package com.shobaki;

import model.Artist;
import model.DataSource;
import model.SongArtist;

import java.util.List;
import java.util.Scanner;

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

        int count = dataSource.getCount(DataSource.TABLE_SONGS);
        System.out.println("Number of songs is " + count);

        dataSource.createViewSongArtists();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a song title: ");
        //Go Your Own Way" or 1=1 or "  SQL injection attack
        //SELECT name, name, track FROM artist_list WHERE title = "Go Your Own Way" or 1=1 or ""
        //We should use the prepared statements class to prevent this attack
        String title = scanner.nextLine();
        songArtists = dataSource.querySongInfoView(title);
        if(songArtists.isEmpty()){  //using isEmpty is much better way for checking
            System.out.println("Couldn't find the artist for the song");
            return;
        }
        for(SongArtist artist : songArtists){
            System.out.println("FROM VIEW - Artist name = " + artist.getArtistName() +
                    " Album name = " + artist.getAlbumName() +
                    " Track number = " + artist.getTrack());
        }

        dataSource.close();
    }
}
