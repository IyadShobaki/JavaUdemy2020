package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:D:\\JavaUdemy2020\\Music\\" + DB_NAME;

    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";
    public static final int INDEX_ALBUM_ID = 1;
    public static final int INDEX_ALBUM_NAME = 2;
    public static final int INDEX_ALBUM_ARTIST = 3;


    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTIST_ID = "_id";
    public static final String COLUMN_ARTIST_NAME = "name";
    public static final int INDEX_ARTIST_ID = 1;
    public static final int INDEX_ARTIST_NAME = 2;


    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_ID = "_id";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";
    public static final int INDEX_SONG_ID = 1;
    public static final int INDEX_SONG_TRACK = 2;
    public static final int INDEX_SONG_TITLE = 3;
    public static final int INDEX_SONG_ALBUM = 4;

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    public static final String QUERY_ALBUMS_BY_ARTIST_START =
            "SELECT " + TABLE_ALBUMS + '.' + COLUMN_ALBUM_NAME + " FROM " + TABLE_ALBUMS +
                    " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST +
                    " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID +
                    " WHERE " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + " = \"";

    public static final String QUERY_ALBUMS_BY_ARTIST_SORT =
            " ORDER BY " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " COLLATE NOCASE ";

    public static final String QUERY_ARTISTS_START =
            "SELECT * FROM " + TABLE_ARTISTS;
    public static final String QUERY_ARTISTS_SORT =
            " ORDER BY " + COLUMN_ARTIST_NAME + " COLLATE NOCASE ";


    //select artists.name, albums.name, songs.track from songs
    //inner join albums on songs.album = albums._id
    //inner join artists on albums.artist = artists._id
    //where songs.title = "She's On Fire"
    //order by artists.name, albums.name COLLATE nocase ASC
    public static final String QUERY_ARTIST_FOR_SONG_START =
            "SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
                    TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + ", " +
                    TABLE_SONGS + "." + COLUMN_SONG_TRACK + " FROM " + TABLE_SONGS +
                    " INNER JOIN " + TABLE_ALBUMS + " ON " +
                    TABLE_SONGS + "." + COLUMN_SONG_ALBUM + " = " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID +
                    " INNER JOIN " + TABLE_ARTISTS + " ON " +
                    TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID +
                    " WHERE " + TABLE_SONGS + "." + COLUMN_SONG_TITLE + " = \"";

    public static final String QUERY_ARTIST_FOR_SONG_SORT =
            " ORDER BY " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
                    TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " COLLATE NOCASE ";

    //create view if not EXISTS artist_list as
    //select artists.name, albums.name as album, songs.track, songs.title from songs
    //inner join albums on songs.album = albums._id
    //inner join artists on albums.artist = artists._id
    //order by artists.name, albums.name, songs.track;
    public static final String TABLE_ARTIST_SONG_VIEW = "artist_list";
    public static final String CREATE_ARTIST_FOR_SONG_VIEW = "CREATE VIEW IF NOT EXISTS " +
            TABLE_ARTIST_SONG_VIEW + " AS SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
            TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " AS " + COLUMN_SONG_ALBUM + ", " + TABLE_SONGS + "." +
            COLUMN_SONG_TRACK + ", " + TABLE_SONGS + "." + COLUMN_SONG_TITLE + " FROM " + TABLE_SONGS + " INNER JOIN " +
            TABLE_ALBUMS + " ON " + TABLE_SONGS + "." + COLUMN_SONG_ALBUM + " = " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID +
            " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = " +
            TABLE_ARTISTS + "." + COLUMN_ARTIST_ID +
            " ORDER BY " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
            TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + ", " +
            TABLE_SONGS + "." + COLUMN_SONG_TRACK;

    // SELECT name, album, track FROM artist_list WHERE title = "Go Your Own Way";
    public static final String QUERY_VIEW_SONG_INFO = "SELECT " + COLUMN_ARTIST_NAME + ", " +
            COLUMN_ALBUM_NAME + ", " + COLUMN_SONG_TRACK + " FROM " + TABLE_ARTIST_SONG_VIEW +
            " WHERE " + COLUMN_SONG_TITLE + " = \"";

    //create prepared statement
    //SELECT name, album, track FROM artist_list WHERE title = ?
    public static final String QUERY_VIEW_SONG_INFO_PREP = "SELECT " + COLUMN_ARTIST_NAME + ", " +
            COLUMN_ALBUM_NAME + ", " + COLUMN_SONG_TRACK + " FROM " + TABLE_ARTIST_SONG_VIEW +
            " WHERE " + COLUMN_SONG_TITLE + " = ?";  //? place holder character
    // and ? this will be parameterIndex 1.
    // If we have  SELECT name, album, track FROM artist_list WHERE title = ? ORDER BY ?, ?
                                                                        //  1          2  3




    private Connection conn;
    //we need to declare an instance variable for the prepared statement
    private PreparedStatement querySongInfoView;

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            querySongInfoView = conn.prepareStatement(QUERY_VIEW_SONG_INFO_PREP);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if(querySongInfoView != null){
                querySongInfoView.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }


    public List<Artist> queryArtists(int sortOrder) {

        StringBuilder sb = new StringBuilder(QUERY_ARTISTS_START);
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(QUERY_ARTISTS_SORT);
            if (sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }

        }

        //try with resources. We don't have to close the Statement or the ResultSet
        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {//"SELECT * FROM " + TABLE_ARTISTS);){


            List<Artist> artists = new ArrayList<>();
            while (results.next()) {
                Artist artist = new Artist();
                artist.setId(results.getInt(INDEX_ARTIST_ID));
                artist.setName(results.getString(INDEX_ARTIST_NAME));
                artists.add(artist);
            }

            return artists;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<String> queryAlbumsForArtist(String artistName, int sortOrder) {
        //SELECT albums.name from albums inner join artists on albums.artist = artists._id
        // where artists.name = "Carole King" order by albums.name COLLATE nocase ASC
        StringBuilder sb = new StringBuilder(QUERY_ALBUMS_BY_ARTIST_START);
        sb.append(artistName);
        sb.append("\"");

        if (sortOrder != ORDER_BY_NONE) {
            sb.append(QUERY_ALBUMS_BY_ARTIST_SORT);
            if (sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }

        System.out.println("SQL statement = " + sb.toString());

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {
            List<String> albums = new ArrayList<>();
            while (results.next()) {
                albums.add(results.getString(1));
            }
            return albums;
        } catch (SQLException e) {
            System.out.println("Query failed " + e.getMessage());
            return null;
        }
    }

    public List<SongArtist> queryArtistsForSong(String songName, int sortOrder) {
        StringBuilder sb = new StringBuilder(QUERY_ARTIST_FOR_SONG_START);
        sb.append(songName);
        sb.append("\"");

        if (sortOrder != ORDER_BY_NONE) {
            sb.append(QUERY_ARTIST_FOR_SONG_SORT);
            if (sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }
        System.out.println("SQL statement = " + sb.toString());

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<SongArtist> songArtists = new ArrayList<>();

            while (results.next()) {
                SongArtist songArtist = new SongArtist();
                songArtist.setArtistName(results.getString(1));
                songArtist.setAlbumName(results.getString(2));
                songArtist.setTrack(results.getInt(3));
                songArtists.add(songArtist);

            }

            return songArtists;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }

    }

    public void querySongsMetadata() {
        String sql = "SELECT * FROM " + TABLE_SONGS;

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sql)) {
            ResultSetMetaData meta = results.getMetaData();
            int numColumns = meta.getColumnCount();
            for (int i = 1; i <= numColumns; i++) {
                System.out.format("Column %d in the songs table is names %s\n",
                        i, meta.getColumnName(i));
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }

    public int getCount(String table) {
        String sql = "SELECT COUNT(*) AS count FROM " + table;
//        String sql = "SELECT COUNT(*) AS count, MIN(_id) AS min_id FROM " + table;
        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sql)) {

//            int count = results.getInt(1);
//            int min = results.getInt(2);
            int count = results.getInt("count");
//            int min = results.getInt("min_id");
//            System.out.format("Count %d, Min = %d\n", count , min);
            System.out.format("Count %d\n", count);
            return count;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return -1;
        }
    }

    public boolean createViewSongArtists() {
        try (Statement statement = conn.createStatement()) {
            statement.execute(CREATE_ARTIST_FOR_SONG_VIEW);
            return true;
        } catch (SQLException e) {
            System.out.println("Create View Failed: " + e.getMessage());
            return false;
        }
    }

    public List<SongArtist> querySongInfoView(String title) {


        //we have to don't use try with resources because we need to use the prepared statement
        //we need to close the statement in the close() method but we don't have to worry about closing ResultSet
        //in prepared statements
        try {
            querySongInfoView.setString(1, title);
            ResultSet results = querySongInfoView.executeQuery();
            List<SongArtist> songArtists = new ArrayList<>();
            while (results.next()) {
                SongArtist songArtist = new SongArtist();
                songArtist.setArtistName(results.getString(1));
                songArtist.setAlbumName(results.getString(2));
                songArtist.setTrack(results.getInt(3));
                songArtists.add(songArtist);

            }
            return songArtists;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }

//        StringBuilder sb = new StringBuilder(QUERY_VIEW_SONG_INFO);
//        sb.append(title);
//        sb.append("\"");
//
//        System.out.println(sb.toString());
//
//        try (Statement statement = conn.createStatement();
//             ResultSet results = statement.executeQuery(sb.toString())) {
//            List<SongArtist> songArtists = new ArrayList<>();
//            while (results.next()) {
//                SongArtist songArtist = new SongArtist();
//                songArtist.setArtistName(results.getString(1));
//                songArtist.setAlbumName(results.getString(2));
//                songArtist.setTrack(results.getInt(3));
//                songArtists.add(songArtist);
//
//            }
//
//            return songArtists;
//        } catch (SQLException e) {
//            System.out.println("Query failed: " + e.getMessage());
//            return null;
//        }

    }

}


// 1
//package model;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class DataSource {
//    public static final String DB_NAME = "music.db";
//    public static final String CONNECTION_STRING = "jdbc:sqlite:D:\\JavaUdemy2020\\Music\\" + DB_NAME;
//
//    public static final String TABLE_ALBUMS = "albums";
//    public static final String COLUMN_ALBUM_ID = "_id";
//    public static final String COLUMN_ALBUM_NAME = "name";
//    public static final String COLUMN_ALBUM_ARTIST = "artist";
//    public static final int INDEX_ALBUM_ID = 1;
//    public static final int INDEX_ALBUM_NAME = 2;
//    public static final int INDEX_ALBUM_ARTIST = 3;
//
//
//
//    public static final String TABLE_ARTISTS = "artists";
//    public static final String COLUMN_ARTIST_ID = "_id";
//    public static final String COLUMN_ARTIST_NAME = "name";
//    public static final int INDEX_ARTIST_ID = 1;
//    public static final int INDEX_ARTIST_NAME = 2;
//
//
//    public static final String TABLE_SONGS = "songs";
//    public static final String COLUMN_SONG_ID = "_id";
//    public static final String COLUMN_SONG_TRACK = "track";
//    public static final String COLUMN_SONG_TITLE = "title";
//    public static final String COLUMN_SONG_ALBUM = "album";
//    public static final int INDEX_SONG_ID = 1;
//    public static final int INDEX_SONG_TRACK = 2;
//    public static final int INDEX_SONG_TITLE = 3;
//    public static final int INDEX_SONG_ALBUM = 4;
//
//    public static final int ORDER_BY_NONE = 1;
//    public static final int ORDER_BY_ASC = 2;
//    public static final int ORDER_BY_DESC = 3;
//
//    private Connection conn;
//
//    public boolean open(){
//        try{
//            conn = DriverManager.getConnection(CONNECTION_STRING);
//            return true;
//        }catch(SQLException e){
//            System.out.println("Couldn't connect to database "+ e.getMessage());
//            return false;
//        }
//    }
//    public void close(){
//        try{
//            if(conn != null){
//                conn.close();
//            }
//        }catch(SQLException e){
//            System.out.println("Couldn't close connection: " + e.getMessage());
//        }
//    }
//
//
//    public List<Artist> queryArtists(int sortOrder){
//
//        StringBuilder sb = new StringBuilder("SELECT * FROM ");
//        sb.append(TABLE_ARTISTS);
//        if(sortOrder != ORDER_BY_NONE){
//            sb.append(" ORDER BY ");
//            sb.append(COLUMN_ARTIST_NAME);
//            sb.append(" COLLATE NOCASE ");
//            if(sortOrder == ORDER_BY_DESC){
//                sb.append("DESC");
//            }else{
//                sb.append("ASC");
//            }
//
//        }
//
//        //try with resources. We don't have to close the Statement or the ResultSet
//        try (Statement statement = conn.createStatement();
//        ResultSet results = statement.executeQuery(sb.toString())) {//"SELECT * FROM " + TABLE_ARTISTS);){
//
//
//            List<Artist> artists = new ArrayList<>();
//            while(results.next()){
//                Artist artist = new Artist();
////                artist.setId(results.getInt(COLUMN_ARTIST_ID));
////                artist.setName(results.getString(COLUMN_ARTIST_NAME));
//                artist.setId(results.getInt(INDEX_ARTIST_ID));
//                artist.setName(results.getString(INDEX_ARTIST_NAME));
//                artists.add(artist);
//            }
//
//            return artists;
//
//        }catch(SQLException e){
//            System.out.println("Query failed: " + e.getMessage());
//            return null;
//        }
//    }
//
//    public List<String> queryAlbumsForArtist(String artistName, int sortOrder){
//        //SELECT albums.name from albums inner join artists on albums.artist = artists._id
//        // where artists.name = "Carole King" order by albums.name COLLATE nocase ASC
//        StringBuilder sb = new StringBuilder("SELECT ");
//        sb.append(TABLE_ALBUMS);
//        sb.append('.');
//        sb.append(COLUMN_ALBUM_NAME);
//        sb.append(" FROM ");
//        sb.append(TABLE_ALBUMS);
//        sb.append(" INNER JOIN ");
//        sb.append(TABLE_ARTISTS);
//        sb.append(" ON ");
//        sb.append(TABLE_ALBUMS);
//        sb.append('.');
//        sb.append(COLUMN_ALBUM_ARTIST);
//        sb.append(" = ");
//        sb.append(TABLE_ARTISTS);
//        sb.append('.');
//        sb.append(COLUMN_ARTIST_ID);
//        sb.append(" WHERE ");
//        sb.append(TABLE_ARTISTS);
//        sb.append('.');
//        sb.append(COLUMN_ARTIST_NAME);
//        sb.append(" = \"");
//        sb.append(artistName);
//        sb.append("\"");
//
//        if(sortOrder != ORDER_BY_NONE){
//            sb.append(" ORDER BY ");
//            sb.append(TABLE_ALBUMS);
//            sb.append('.');
//            sb.append(COLUMN_ALBUM_NAME);
//            sb.append(" COLLATE NOCASE ");
//            if(sortOrder == ORDER_BY_DESC){
//                sb.append("DESC");
//            }else{
//                sb.append("ASC");
//            }
//        }
//
//        System.out.println("SQL statement = " + sb.toString());
//
//        try(Statement statement = conn.createStatement();
//        ResultSet results = statement.executeQuery(sb.toString())){
//            List<String> albums = new ArrayList<>();
//            while(results.next()){
//                albums.add(results.getString(1));
//            }
//            return albums;
//        }catch(SQLException e){
//            System.out.println("Query failed " + e.getMessage());
//            return null;
//        }
//    }
//
//}


//if we use the following method we have to close manually
//    public List<Artist> queryArtists(){
//        Statement statement = null;
//        ResultSet results = null;
//
//        try{
//
//            statement = conn.createStatement();
//            results = statement.executeQuery("SELECT * FROM " + TABLE_ARTISTS);
//
//            List<Artist> artists = new ArrayList<>();
//            while(results.next()){
//                Artist artist = new Artist();
//                artist.setId(results.getInt(COLUMN_ARTIST_ID));
//                artist.setName(results.getString(COLUMN_ARTIST_NAME));
//                artists.add(artist);
//            }
//
//            return artists;
//
//        }catch(SQLException e){
//            System.out.println("Query failed: " + e.getMessage());
//            return null;
//        }finally {
//            try{
//                if(results != null){
//                    results.close();
//                }
//            }catch (SQLException e){
//                System.out.println("Error closing ResultSet : " + e.getMessage());
//            }
//            try{
//                if(statement != null){
//                    statement.close();
//                }
//            }catch(SQLException e){
//                System.out.println("Error closing Statement : " + e.getMessage());
//            }
//
//        }
//    }