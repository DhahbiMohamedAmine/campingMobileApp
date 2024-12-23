package com.example.v2;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity implements TripAdapter.OnTripClickListener {
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private TripAdapter tripAdapter;
    private Button buttonAddTrip;
    private List<Trip> tripList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize views
        recyclerView = findViewById(R.id.recyclerView);
        buttonAddTrip = findViewById(R.id.buttonAddTrip);
        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tripList = new ArrayList<>();
        tripAdapter = new TripAdapter(tripList, this);
        recyclerView.setAdapter(tripAdapter);
        // Set up click listener for Add Trip button
        buttonAddTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {navigateToAddTrip();}});
        // Fetch trips
        fetchTrips();}
    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the trip list when returning to this activity
        fetchTrips();
    }
    private void fetchTrips() {
        TripApi tripApi = RetrofitClient.getRetrofitInstance().create(TripApi.class);
        Call<List<Trip>> call = tripApi.getAllTrips();
        call.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    tripList.clear();
                    tripList.addAll(response.body());
                    tripAdapter.notifyDataSetChanged();
                    Log.d(TAG, "Trips fetched: " + tripList.size());
                } else {
                    Log.e(TAG, "Error: " + response.code());
                    Toast.makeText(MainActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {
                Log.e(TAG, "Network Error: " + t.getMessage(), t);
                Toast.makeText(MainActivity.this, "Network Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onTripClick(Trip trip) {
        // Navigate to the TripDetailActivity when a trip is clicked
        Intent intent = new Intent(this, TripDetailActivity.class);
        intent.putExtra("TRIP_EXTRA", trip);
        startActivity(intent);
    }
    private void navigateToAddTrip() {
        // Navigate to AddTripActivity when the button is clicked
        Intent intent = new Intent(MainActivity.this, AddTripActivity.class);
        startActivity(intent);
    }
}

