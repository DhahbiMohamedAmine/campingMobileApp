package com.example.v2;

import com.example.v2.Trip;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.*;

public interface TripApi {
    //les api
    @GET("trip/get")
    Call<List<Trip>> getAllTrips();

    @GET("trip/get/{id}")
    Call<Trip> getTripById(@Path("id") int id);

    @POST("trip/create")
    Call<Trip> createTrip(@Body Trip trip);

    @PUT("trip/update/{id}")
    Call<Trip> updateTrip(@Path("id") int id, @Body Trip trip);

    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
}
