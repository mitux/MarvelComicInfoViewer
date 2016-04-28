package com.example.data.entity.mapper;

import com.example.data.entity.ComicEntity;
import com.example.data.entity.pojos.ComicPojo;
import com.example.data.entity.pojos.ItemCharacterPojo;
import com.example.data.entity.pojos.ItemCreatorPojo;

import java.util.ArrayList;

/**
 * Created by a630703 on 22/04/2016.
 */
public class ComicEntityPojoMapper {

    public ComicEntity transform (ComicPojo comicPojo){
        ComicEntity comicEntity = new ComicEntity(comicPojo.getId());

        ArrayList<String> characters = new ArrayList<>();
        for (ItemCharacterPojo itemCh : comicPojo.getCharacters().getItems()){
            characters.add(itemCh.getName());
        }
        comicEntity.setCharacters(characters);

        ArrayList<String> creators = new ArrayList<>();
        for (ItemCreatorPojo itemCh : comicPojo.getCreators().getItems()){
            String name = itemCh.getName();
            String role = itemCh.getRole();
            creators.add(name+" ("+role+")");
        }
        comicEntity.setCreators(creators);

        String thumbnailPath = comicPojo.getThumbnail().getPath();
        String thumbnailExtension = comicPojo.getThumbnail().getExtension();
        comicEntity.setThumbnail(thumbnailPath+"."+thumbnailExtension);

        // I only take YYYY-MM-DD from date
        String date = comicPojo.getDates().get(0).getDate().substring(0,10);
        comicEntity.setDate(date);

        comicEntity.setDescription(comicPojo.getDescription());
        comicEntity.setFormat(comicPojo.getFormat());
        comicEntity.setPrice(comicPojo.getPrices().get(0).getPrice());
        comicEntity.setTitle(comicPojo.getTitle());
        comicEntity.setUrl(comicPojo.getUrls().get(0).getUrl());

        return comicEntity;
    }

}
