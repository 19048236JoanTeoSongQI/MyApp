package sg.edu.rp.c346.id19048236.myapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    EditText etID, etTitle, etActors, etYear;
    Button btnCancel, btnUpdate, btnDelete;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        setTitle(getTitle().toString() + " ~ " + getResources().getText(R.string.title_activity_third));

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        etID = (EditText) findViewById(R.id.etID);
        etTitle = (EditText) findViewById(R.id.etTitle);
        etActors = (EditText) findViewById(R.id.etActors);
        etYear = (EditText) findViewById(R.id.etYear);
        ratingBar = findViewById(R.id.ratingbarStars);

        Intent i = getIntent();
        final Movie currentMovie = (Movie) i.getSerializableExtra("movie");

        etID.setText(currentMovie.getId()+"");
        etTitle.setText(currentMovie.getTitle());
        etActors.setText(currentMovie.getActors());
        etYear.setText(currentMovie.getYearReleased()+"");

        ratingBar.setRating(currentMovie.getStars());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                currentMovie.setTitle(etTitle.getText().toString().trim());
                currentMovie.setActors(etActors.getText().toString().trim());
                int year = 0;
                try {
                    year = Integer.valueOf(etYear.getText().toString().trim());
                } catch (Exception e){
                    Toast.makeText(ThirdActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                    return;
                }
                currentMovie.setYearReleased(year);

                //int selectedRB = rg.getCheckedRadioButtonId();
                //RadioButton rb = (RadioButton) findViewById(selectedRB);
                //currentSong.setStars(Integer.parseInt(rb.getText().toString()));
                currentMovie.setStars((int) ratingBar.getRating());
                int result = dbh.updateMovie(currentMovie);
                if (result>0){
                    Toast.makeText(ThirdActivity.this, "Movie updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ThirdActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ThirdActivity.this);
                alertBuilder.setTitle("Danger");
                alertBuilder.setMessage("Are you sure you want to delete the movie\n" + currentMovie.getTitle());
                alertBuilder.setCancelable(true);
                alertBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DBHelper dbh = new DBHelper(ThirdActivity.this);
                        int result = dbh.deleteMovie(currentMovie.getId());
                        if (result>0){
                            Toast.makeText(ThirdActivity.this, "Movie deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(ThirdActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                alertBuilder.setPositiveButton("Cancel", null);
                AlertDialog alertDialog = alertBuilder.create();
                alertBuilder.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ThirdActivity.this);
                alertBuilder.setTitle("Danger");
                alertBuilder.setMessage("Are you sure you want discard the changes\n");
                alertBuilder.setCancelable(true);
                alertBuilder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                alertBuilder.setPositiveButton("Do NOT Discard", null);
                AlertDialog alertDialog = alertBuilder.create();
                alertBuilder.show();
            }
        });

    }


}