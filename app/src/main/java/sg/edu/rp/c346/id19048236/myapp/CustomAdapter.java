package sg.edu.rp.c346.id19048236.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Movie> movies;

    public CustomAdapter(Context context, int resource, ArrayList<Movie> objects) {
        super(context, resource, objects);
        this.parent_context = context;
        this.layout_id = resource;
        this.movies = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvTitle = rowView.findViewById(R.id.tvTitle);
        TextView tvActors = rowView.findViewById(R.id.tvActors);
        //TextView tvStars = rowView.findViewById(R.id.tvStars);
        TextView tvYears = rowView.findViewById(R.id.tvYears);

        ImageView imageView = rowView.findViewById(R.id.imageView);

        RatingBar ratingBar = rowView.findViewById(R.id.ratingBar);

        // Obtain the Android Version information based on the position
        Movie currentMovie = movies.get(position);

        // Set values to the TextView to display the corresponding information
        tvTitle.setText(currentMovie.getTitle());
        tvActors.setText(currentMovie.getActors());
        String stars = "";
        for(int i = 0; i < currentMovie.getStars(); i++){
            stars += " * ";
        }
        //tvStars.setText(stars);

        ratingBar.setRating(currentMovie.getStars());

        tvYears.setText(currentMovie.getYearReleased() + "");

        if(currentMovie.getYearReleased() >= 2019){
            imageView.setImageResource(R.drawable.newmovies);
        }
        else {
            imageView.setVisibility(View.GONE);
        }

        return rowView;
    }

}