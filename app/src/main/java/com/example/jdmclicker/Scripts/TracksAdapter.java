package com.example.jdmclicker.Scripts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jdmclicker.R;
import com.example.jdmclicker.Scripts.GameScripts.GameManager;
import com.example.jdmclicker.Scripts.GameScripts.Track;

import java.util.List;

public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.ViewHolder>{
    private List<Track> _tracks;

    public TracksAdapter(List<Track> tracks){
        _tracks = tracks;
    }

        @NonNull
        @Override
        public TracksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View trackView = inflater.inflate(R.layout.item_track, parent, false);

            // Return a new holder instance
            TracksAdapter.ViewHolder viewHolder = new TracksAdapter.ViewHolder(trackView);
            return viewHolder;
        }


    @Override
        public void onBindViewHolder(@NonNull TracksAdapter.ViewHolder holder, int position) {
            // Get the data model based on position
            Track track = _tracks.get(position);

            String description =
                            track.getName() + "\n" +
                            "Cost: " + track.getCurrentCost() + "$" + "\n" +
                            "Length: " + track.getTrackLength() + "km" + "\n" +
                            "Reward: " + track.getCurrentCompletionReward()+ "$";

            // Set item views based on your views and data model
            TextView textView = holder.nameTextView;
            textView.setText(description);
            Button button = holder.buyButton;
            button.setText(track.isBought() ? "Upgrade" : "Buy");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GameManager.Instance.getShop().BuyTrack(track);
                    notifyItemChanged(holder.getAdapterPosition());
                }
            });
            //button.setEnabled(contact.isOnline());
        }

        @Override
        public int getItemCount() {
            return _tracks.size();
        }

    public class ViewHolder extends RecyclerView.ViewHolder{
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public Button buyButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.track_description);
            buyButton = (Button) itemView.findViewById(R.id.buy_button);
        }
    }

}



