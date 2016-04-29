package com.example.a630703.marvelcomicinfoviewer.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.a630703.marvelcomicinfoviewer.R;
import com.example.a630703.marvelcomicinfoviewer.model.ComicBasicModel;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Collection;
import java.util.List;

/**
 * Created by a630703 on 27/04/2016.
 */
public class ComicAdapter extends BaseAdapter {

    private List<ComicBasicModel> comicsCollecion;
    private final LayoutInflater layoutInflater;
    private final Context context;

    public ComicAdapter(Context context, Collection<ComicBasicModel> usersCollection) {
        this.layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.comicsCollecion = (List<ComicBasicModel>) usersCollection;
        this.context = context;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (this.comicsCollecion != null && !this.comicsCollecion.isEmpty()) {
            count = this.comicsCollecion.size();
        }
        return count;
    }

    @Override
    public Object getItem(int position) {
        return this.comicsCollecion.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.comicsCollecion.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ComicViewHolder comicViewHolder;

        if (convertView == null) {
            convertView = this.layoutInflater.inflate(R.layout.row_comic, parent, false);

            comicViewHolder = new ComicViewHolder();
            comicViewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.thumbnail);

            convertView.setTag(comicViewHolder);
        } else {
            comicViewHolder = (ComicViewHolder) convertView.getTag();
        }

        ComicBasicModel comicBasicModel = this.comicsCollecion.get(position);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(comicBasicModel.getThumbnail(), comicViewHolder.thumbnail);

        return convertView;
    }

    static class ComicViewHolder {
        ImageView thumbnail;
    }
}
