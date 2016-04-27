package com.example.data.net;

import com.example.data.entity.ComicBasicEntity;
import com.example.data.entity.ComicEntity;
import com.example.data.entity.pojos.ComicPojo;
import com.example.data.entity.pojos.ResultPojo;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by a630703 on 25/04/2016.
 */
public interface RestApi {

    interface ComicDetailsCallback {
        void onComicEntityLoaded(ComicEntity comicEntity);
    }

    interface ComicListCallback {
        void onComicListLoaded(ArrayList<ComicBasicEntity> comicBasicEntities);
    }

    static final String APIKEY = "7ffd5aa38537bcb175e26df29a6f8c21";
    static final String TS = "1";
    static final String LIMIT = "10";

    static final String HULKID = "1009351";

    static final String[] ORDERBY = {"focDate","onsaleDate","title","issueNumber","modified"};

    @GET("/comics/{id}?ts="+TS+"&apikey="+APIKEY+"&hash={hash}")
    public void getComicFeed(@Path("id") int id, @Path("hash") String hash, Callback<ResultPojo> response);

//    @GET("/comics?characters="+HULKID+"&orderBy={orderBy}&ts="+TS+"&apikey="+APIKEY+"&hash={hash}")
//    public void getListFeed(@Path("orderBy") String orderBy, @Path("hash") String hash, Callback<ResultPojo> response);

    @GET("/comics?characters="+HULKID+"&ts="+TS+"&apikey="+APIKEY)
    public void getListFeed(@Query("orderBy") String orderBy, @Query("hash") String hash, Callback<ResultPojo> response);

    static final String API_BASE_URL = "http://gateway.marvel.com:80/v1/public";

}
