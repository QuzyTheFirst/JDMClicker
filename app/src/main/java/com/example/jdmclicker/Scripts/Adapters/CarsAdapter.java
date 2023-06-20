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
import com.example.jdmclicker.Scripts.GameScripts.Car;
import com.example.jdmclicker.Scripts.GameScripts.GameManager;

import java.text.DecimalFormat;
import java.util.List;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.ViewHolder>{

    private List<Car> _cars;
    private int[] _images;

    public CarsAdapter(List<Car> cars, int[] images) {
        _cars = cars;
        _images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_car, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data model based on position
        Car car = _cars.get(position);

        String description = 
                car.getName() +"\n" + 
                        "Cost: " + new DecimalFormat("##.##").format(car.getCurrentCost()) + "$" + "\n" +
                        "Speed: " + car.getCurrentSpeed()+" km per click";

        // Set item views based on your views and data model
        ImageView imageView = holder.carImageView;
        TextView textView = holder.nameTextView;
        Button button = holder.buyButton;


        imageView.setImageResource(_images[position]);
        textView.setText(description);
        button.setText(car.isBought() ? "Upgrade" : "Buy");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameManager.Instance.getShop().BuyCar(car);
                notifyItemChanged(holder.getAdapterPosition());
            }
        });
        //button.setEnabled(contact.isOnline());
    }

    @Override
    public int getItemCount() {
        return _cars.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public ImageView carImageView;
        public TextView nameTextView;
        public Button buyButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            carImageView = itemView.findViewById(R.id.carImageView);
            nameTextView = (TextView) itemView.findViewById(R.id.car_description);
            buyButton = (Button) itemView.findViewById(R.id.buy_button);
        }
    }
}
