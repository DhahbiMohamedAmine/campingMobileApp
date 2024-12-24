    package com.example.v2;
    import android.content.Intent;
    import android.os.Bundle;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.Toast;
    import androidx.appcompat.app.AppCompatActivity;
    import retrofit2.Call;
    import retrofit2.Callback;
    import retrofit2.Response;
    public class UpdateTripActivity extends AppCompatActivity {
        //attribue
        private EditText editTextNbPlace;
        private EditText editTextDescription;
        private Trip trip;
        @Override
        //oncreate
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_update_trip);
            editTextNbPlace = findViewById(R.id.editTextNbPlace);
            editTextDescription = findViewById(R.id.editTextDescription);
            Button buttonUpdate = findViewById(R.id.buttonUpdate);
            trip = (Trip) getIntent().getSerializableExtra("TRIP_EXTRA");
            if (trip != null) {
                editTextNbPlace.setText(String.valueOf(trip.getNbPlace()));
                editTextDescription.setText(trip.getDescription());
            }
            buttonUpdate.setOnClickListener(v -> updateTrip());
        }
        //update
        private void updateTrip() {
            String nbPlaceStr = editTextNbPlace.getText().toString();
            String description = editTextDescription.getText().toString();
            if (nbPlaceStr.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            int nbPlace = Integer.parseInt(nbPlaceStr);
            trip.setNbPlace(nbPlace);
            trip.setDescription(description);
            TripApi tripApi = RetrofitClient.getRetrofitInstance().create(TripApi.class);
            Call<Trip> call = tripApi.updateTrip(trip.getId(), trip);
            call.enqueue(new Callback<Trip>() {
                @Override
                public void onResponse(Call<Trip> call, Response<Trip> response) {
                    if (response.isSuccessful()) {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("UPDATED_TRIP", response.body());
                        setResult(RESULT_OK, resultIntent);
                        Toast.makeText(UpdateTripActivity.this, "Trip updated successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(UpdateTripActivity.this, "Failed to update trip", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                //failure
                public void onFailure(Call<Trip> call, Throwable t) {
                    Toast.makeText(UpdateTripActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });}}

