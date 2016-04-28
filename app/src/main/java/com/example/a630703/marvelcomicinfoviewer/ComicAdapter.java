package com.example.a630703.marvelcomicinfoviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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
        Glide.with(context).load(comicBasicModel.getThumbnail()).into(comicViewHolder.thumbnail);

        return convertView;
    }

    static class ComicViewHolder {
        ImageView thumbnail;
    }
}
