package com.example.v2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.TripViewHolder> {

    private List<Trip> trips;
    private OnTripClickListener listener;

    public interface OnTripClickListener {
        void onTripClick(Trip trip);
    }

    public TripAdapter(List<Trip> trips, OnTripClickListener listener) {
        this.trips = trips;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trip, parent, false);
        return new TripViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
        Trip trip = trips.get(position);
        holder.bind(trip, listener);
    }

    @Override
    public int getItemCount() {
        return trips != null ? trips.size() : 0;
    }

    public static class TripViewHolder extends RecyclerView.ViewHolder {
        TextView textViewType;
        TextView textViewDeparture;
        TextView textViewDestination;
        TextView textViewDepartureDate;
        TextView textViewPrice;

        public TripViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewType = itemView.findViewById(R.id.textViewType);
            textViewDeparture = itemView.findViewById(R.id.textViewDeparture);
            textViewDestination = itemView.findViewById(R.id.textViewDestination);
            textViewDepartureDate = itemView.findViewById(R.id.textViewDepartureDate);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
        }

        public void bind(final Trip trip, final OnTripClickListener listener) {
            textViewType.setText(trip.getType() != null ? trip.getType() : "Type non spécifié");
            textViewDeparture.setText("Départ: " + (trip.getLieuDepart() != null ? trip.getLieuDepart() : "Non spécifié"));
            textViewDestination.setText("Destination: " + (trip.getLieuDestination() != null ? trip.getLieuDestination() : "Non spécifié"));

            // Format the date
            String formattedDate = "Non spécifié";
            if (trip.getDateDepart() != null && !trip.getDateDepart().isEmpty()) {
                try {
                    String[] dateParts = trip.getDateDepart().split("T");
                    formattedDate = dateParts[0]; // twali form yyyy-mm-dd
                } catch (Exception e) {
                    formattedDate = trip.getDateDepart(); // snn format 3adi
                }
            }
            textViewDepartureDate.setText("Date de départ: " + formattedDate);

            textViewPrice.setText(String.format("%.2f DT", trip.getPrix()));

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onTripClick(trip);
                }
            });
        }
    }
}
