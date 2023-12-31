package com.example.jdmclicker.Scripts.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jdmclicker.R;
import com.example.jdmclicker.Scripts.GameScripts.GameManager;
import com.example.jdmclicker.Scripts.GameScripts.Track;

import java.text.DecimalFormat;
import java.util.List;

public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.ViewHolder>{
    private List<Track> _tracks;

    private int[] _images;
    private int _chosenTrackPosition;

    public TracksAdapter(List<Track> tracks, int[] images){
        _tracks = tracks;
        _images = images;
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
                            "Cost: " + new DecimalFormat("##.##").format(track.getCurrentCost()) + "$" + "\n" +
                            "Length: " + track.getTrackLength() + "km" + "\n" +
                            "Reward: " + track.getCurrentCompletionReward()+ "$";

            // Set item views based on your views and data model
            ImageView imageView = holder.trackImageView;
            TextView textView = holder.nameTextView;
            Button button = holder.buyButton;

            if(position < _images.length)
                imageView.setImageResource(_images[position]);

            textView.setText(description);

        boolean isBought = track.isBought();
        boolean isSelected = GameManager.Instance.getShop().getCurrentTrack() == track;

        if(!isBought){
            button.setText("Buy");
        }
        else if(isBought && isSelected){
            button.setText("Upgrade");
        }
        else if(isBought && !isSelected){
            button.setText("Select");
        }

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GameManager.Instance.getShop().BuyUpgradeSelectTrack(track);
                    notifyDataSetChanged();
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
        public ImageView trackImageView;
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
            trackImageView = itemView.findViewById(R.id.trackImageView);
        }
    }

}



