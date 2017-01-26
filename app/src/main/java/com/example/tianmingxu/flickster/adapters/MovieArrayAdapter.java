package com.example.tianmingxu.flickster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tianmingxu.flickster.R;
import com.example.tianmingxu.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import static android.R.attr.type;
import static com.example.tianmingxu.flickster.R.id.backdrop;
import static com.example.tianmingxu.flickster.R.id.lvImage;
import static com.example.tianmingxu.flickster.R.id.lvTitle;

/**
 * Created by tianmingxu on 1/23/17.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {
    private static class ViewHolder{
        TextView title;
        TextView overview;
        ImageView image;
    }

    private static class ViewHolder2{
        ImageView image;
    }
    public MovieArrayAdapter(Context context, List<Movie> movies){
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Movie movie = getItem(position);
        ViewHolder viewHolder = new ViewHolder();
        ViewHolder2 viewHolder2 = new ViewHolder2();
        int type = getItemViewType(position);
        if (convertView == null){
            if (type == 0) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.item_movie, parent, false);
                viewHolder.image = (ImageView) convertView.findViewById(lvImage);
                viewHolder.title = (TextView) convertView.findViewById(R.id.lvTitle);
                viewHolder.overview = (TextView) convertView.findViewById(R.id.lvOverview);
                convertView.setTag(viewHolder);
            }
            else {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.item_movie_2, parent, false);
                viewHolder2.image = (ImageView) convertView.findViewById(backdrop);
                convertView.setTag(viewHolder2);
            }
        }
        else {
            if (type == 0) {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            else {
                viewHolder2 = (ViewHolder2) convertView.getTag();
            }
        }
        if (type == 0) {
            viewHolder.image.setImageResource(0);
            viewHolder.title.setText(movie.getOriginalTitle());
            viewHolder.overview.setText(movie.getOverView());
            int orientation = getContext().getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                Picasso.with(getContext()).load(movie.getPosterPath()).into(viewHolder.image);
            } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Picasso.with(getContext()).load(movie.getBackdropPath()).into(viewHolder.image);
            }
        }
        else {
            viewHolder2.image.setImageResource(0);
            Picasso.with(getContext()).load(movie.getBackdropPath()).into(viewHolder2.image);

        }
        return convertView;
    }

    @Override
    public int getViewTypeCount(){
        return 2;
    }

    @Override
    public int getItemViewType(int position){
        Movie movie = getItem(position);
        if (movie.getScore() >= 5.0){
            return 1;
        }
        else {
            return 0;
        }
    }
}
