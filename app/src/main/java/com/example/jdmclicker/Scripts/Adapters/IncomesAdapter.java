package com.example.jdmclicker.Scripts.Adapters;

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
import com.example.jdmclicker.Scripts.GameScripts.PassiveIncome;

import java.text.DecimalFormat;
import java.util.List;

public class IncomesAdapter extends RecyclerView.Adapter<IncomesAdapter.ViewHolder>{

    private List<PassiveIncome> _incomes;

    public IncomesAdapter(List<PassiveIncome> incomes){
        _incomes = incomes;
    }

    @NonNull
    @Override
    public IncomesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_income, parent, false);

        // Return a new holder instance
        IncomesAdapter.ViewHolder viewHolder = new IncomesAdapter.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IncomesAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        PassiveIncome income = _incomes.get(position);

        String description =
                        income.getName() +"\n" +
                        "Cost: " + new DecimalFormat("##.##").format(income.getCurrentCost()) + "$" + "\n" +
                                "Money Per Seconds: " + new DecimalFormat("##.##").format(income.getCurrentMoneyPerSecond())+"$/s";

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(description);
        Button button = holder.buyButton;
        button.setText(income.isBought() ? "Upgrade" : "Buy");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameManager.Instance.getShop().BuyUpgradeIncome(income);
                notifyItemChanged(holder.getAdapterPosition());
            }
        });
        //button.setEnabled(contact.isOnline());
    }

    @Override
    public int getItemCount() {
        return _incomes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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

            nameTextView = (TextView) itemView.findViewById(R.id.income_description);
            buyButton = (Button) itemView.findViewById(R.id.buy_button);
        }
    }

}
