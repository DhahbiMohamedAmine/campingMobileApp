package com.example.v2;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class AddTripActivity extends AppCompatActivity {
    private EditText editTextType, editTextLieuDepart, editTextLieuDestination,
            editTextDateDepart, editTextDateDestination, editTextPrix,
            editTextNbPlace, editTextDescription;
    private Button buttonSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
        // Initialize views
        editTextType = findViewById(R.id.editTextType);
        editTextLieuDepart = findViewById(R.id.editTextLieuDepart);
        editTextLieuDestination = findViewById(R.id.editTextLieuDestination);
        editTextDateDepart = findViewById(R.id.editTextDateDepart);
        editTextDateDestination = findViewById(R.id.editTextDateDestination);
        editTextPrix = findViewById(R.id.editTextPrix);
        editTextNbPlace = findViewById(R.id.editTextNbPlace);
        editTextDescription = findViewById(R.id.editTextDescription);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    submitTrip();}}});}
    private boolean validateInput() {
        if (editTextType.getText().toString().trim().isEmpty() ||
                editTextLieuDepart.getText().toString().trim().isEmpty() ||
                editTextLieuDestination.getText().toString().trim().isEmpty() ||
                editTextDateDepart.getText().toString().trim().isEmpty() ||
                editTextDateDestination.getText().toString().trim().isEmpty() ||
                editTextPrix.getText().toString().trim().isEmpty() ||
                editTextNbPlace.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs obligatoires", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private void submitTrip() {
        Trip newTrip = new Trip();
        newTrip.setType(editTextType.getText().toString().trim());
        newTrip.setLieuDepart(editTextLieuDepart.getText().toString().trim());
        newTrip.setLieuDestination(editTextLieuDestination.getText().toString().trim());
        newTrip.setDateDepart(editTextDateDepart.getText().toString().trim());
        newTrip.setDateDestination(editTextDateDestination.getText().toString().trim());
        newTrip.setPrix(Double.parseDouble(editTextPrix.getText().toString().trim()));
        newTrip.setNbPlace(Integer.parseInt(editTextNbPlace.getText().toString().trim()));
        newTrip.setDescription(editTextDescription.getText().toString().trim());
        TripApi tripApi = RetrofitClient.getRetrofitInstance().create(TripApi.class);
        Call<Trip> call = tripApi.createTrip(newTrip);
        call.enqueue(new Callback<Trip>() {
            @Override
            public void onResponse(Call<Trip> call, Response<Trip> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddTripActivity.this, "Voyage ajouté avec succès", Toast.LENGTH_SHORT).show();
                    finish(); // Close the activity and return to the previous screen
                } else {
                    Toast.makeText(AddTripActivity.this, "Erreur lors de l'ajout du voyage", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Trip> call, Throwable t) {
                Toast.makeText(AddTripActivity.this, "Erreur réseau: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

