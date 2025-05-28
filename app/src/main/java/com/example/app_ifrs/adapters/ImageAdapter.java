package com.example.app_ifrs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app_ifrs.R;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private Context context;
    private List<Integer> imageResources;
    private OnItemClickListener listener;


    public ImageAdapter(Context context, List<Integer> imageResources) {
        this.context = context;
        this.imageResources = imageResources;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.image_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Carrega imagem local usando Glide ou setImageResource
        Glide.with(context)
                .load(imageResources.get(position))
                .into(holder.imageView);

        holder.imageView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(holder.imageView, imageResources.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageResources.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView  imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.list_item_image);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int resId);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
