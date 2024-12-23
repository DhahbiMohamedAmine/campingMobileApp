package com.example.v2;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class TripDetailActivity extends AppCompatActivity {
    private Trip trip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_detail);
        // Get trip details from intent
        trip = (Trip) getIntent().getSerializableExtra("TRIP_EXTRA");
        if (trip != null) {
            TextView textViewType = findViewById(R.id.textViewDetailType);
            TextView textViewDeparture = findViewById(R.id.textViewDetailDeparture);
            TextView textViewDestination = findViewById(R.id.textViewDetailDestination);
            TextView textViewDepartureDate = findViewById(R.id.textViewDetailDepartureDate);
            TextView textViewDateDestination = findViewById(R.id.textViewDetailDateDestination);
            TextView textViewPrice = findViewById(R.id.textViewDetailPrice);
            TextView textViewDescription = findViewById(R.id.textViewDetailDescription);
            TextView textViewNbPlace = findViewById(R.id.textViewDetailNbPlace);
            TextView textViewRating = findViewById(R.id.textViewDetailRating);
            textViewType.setText(trip.getType());
            textViewDeparture.setText("Départ: " + trip.getLieuDepart());
            textViewDestination.setText("Destination: " + trip.getLieuDestination());
            textViewDepartureDate.setText("Date de départ: " + formatDate(trip.getDateDepart()));
            textViewDateDestination.setText("Date de retour: " + formatDate(trip.getDateDestination()));
            textViewPrice.setText(String.format("Prix: %.2f DT", trip.getPrix()));
            textViewDescription.setText("Description: " + trip.getDescription());
            textViewNbPlace.setText("Nombre de places: " + trip.getNbPlace());
            textViewRating.setText("Rating: " + trip.getAverageRating());
            // Add buttons
            Button buttonBack = findViewById(R.id.buttonBack);
            Button buttonUpdate = findViewById(R.id.buttonUpdate);
            buttonBack.setOnClickListener(v -> {
                Intent intent = new Intent(TripDetailActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            });
            buttonUpdate.setOnClickListener(v -> {
                Intent intent = new Intent(TripDetailActivity.this, UpdateTripActivity.class);
                intent.putExtra("TRIP_EXTRA", trip);
                startActivity(intent);  // Request code 1
            });

        }
    }

    private String formatDate(String date) {
        // Implement date formatting logic here
        return date;
    }
}

