package com.example.bakingbarons;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class RecycleAdapter extends FirebaseRecyclerAdapter<CakeModel, RecycleAdapter.MyViewHolder> {

    ProgressBar progressBar;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RecycleAdapter(@NonNull FirebaseRecyclerOptions<CakeModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull CakeModel model) {
        holder.cakeName.setText(model.getCakeName());
        holder.cakePrice.setText(model.getCakePrice());

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new MyViewHolder(view);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView cakeName, cakePrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_image);
            cakeName = (TextView) itemView.findViewById(R.id.item_title);
            cakePrice = (TextView) itemView.findViewById(R.id.item_price);

        }
    }
}
