package com.example.data.net;

import android.content.Context;
import android.util.Log;

import com.example.data.entity.ComicBasicEntity;
import com.example.data.entity.ComicEntity;
import com.example.data.entity.mapper.ComicBasicEntityPojoMapper;
import com.example.data.entity.mapper.ComicEntityPojoMapper;
import com.example.data.entity.pojos.ComicPojo;
import com.example.data.entity.pojos.ResultPojo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.Path;

/**
 * Created by a630703 on 25/04/2016.
 */
public class RestApiImpl {

    public interface ComicDetailsCallback{
        void onComicEntityLoaded(ComicEntity comicEntity);
    }

    public interface ComicListCallback{
        void onComicListLoaded(ArrayList<ComicBasicEntity> comicBasicEntities);
    }

    private final Context context;

    public RestApiImpl(Context context) {
        this.context = context.getApplicationContext();
    }


    public void getComicById(final int id, final ComicDetailsCallback comicDetailsCallback){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(RestApi.API_BASE_URL).build();

        RestApi restApi = restAdapter.create(RestApi.class);

        restApi.getComicFeed(id, md5(RestApi.TS,RestApi.APIKEY), new Callback<ResultPojo>() {

            @Override
            public void success(ResultPojo resultPojo, Response response) {
                ComicEntityPojoMapper comicEntityPojoMapper = new ComicEntityPojoMapper();
                ComicEntity comicEntity =
                        comicEntityPojoMapper.transform(resultPojo.getData().getResults().get(0));
                comicDetailsCallback.onComicEntityLoaded(comicEntity);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("RestApiImpl", "failure: "+error.getMessage());
            }
        });
    }

    public void getComicList(final ComicListCallback comicListCallback){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(RestApi.API_BASE_URL).build();

        RestApi restApi = restAdapter.create(RestApi.class);

        Random r = new Random();
        int randInt = Math.abs(r.nextInt(5));

        restApi.getListFeed(RestApi.ORDERBY[randInt],md5(RestApi.TS,RestApi.APIKEY), new Callback<ResultPojo>() {

            @Override
            public void success(ResultPojo resultPojo, Response response) {
                ArrayList<ComicPojo> comicPojos = new ArrayList(resultPojo.getData().getResults());
                ComicBasicEntityPojoMapper comicBasicEntityPojoMapper = new ComicBasicEntityPojoMapper();
                ArrayList<ComicBasicEntity> comicBasicEntities =
                        comicBasicEntityPojoMapper.transformComicBasicList(comicPojos);
                comicListCallback.onComicListLoaded(comicBasicEntities);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("RestApiImpl", "failure: "+error.getMessage());
            }
        });
    }

    private String md5(String ts, String key){
        String in = ts+"d3be00649bde92c40f6260a611b1cd0e20b13bb9"+key;
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(in.getBytes());
            byte[] a = digest.digest();
            int len = a.length;
            StringBuilder sb = new StringBuilder(len << 1);
            for (int i = 0; i < len; i++) {
                sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
                sb.append(Character.forDigit(a[i] & 0x0f, 16));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
        return null;
    }
}
