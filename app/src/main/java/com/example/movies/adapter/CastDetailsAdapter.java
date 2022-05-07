package com.example.movies.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;
import com.example.movies.databinding.ItemCastDetailsLayoutBinding;
import com.example.movies.listener.ICastItemClickListener;
import com.example.movies.model.Cast;

import java.util.ArrayList;
import java.util.List;

public class CastDetailsAdapter extends RecyclerView.Adapter<CastDetailsAdapter.ViewHolder> {

    List<Cast> casts;
    ICastItemClickListener iCastItemClickListener;

    public CastDetailsAdapter(ICastItemClickListener iCastItemClickListener){
        casts = new ArrayList<>();
        this.iCastItemClickListener = iCastItemClickListener;
    }

    public void setCasts(List<Cast> casts){
        this.casts = casts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCastDetailsLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_cast_details_layout,parent,false);
        return new ViewHolder(binding, iCastItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cast cast = casts.get(position);
        holder.binding.setItem(cast);
        holder.setCast(cast);
    }

    @Override
    public int getItemCount() {
        return casts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ItemCastDetailsLayoutBinding binding;
        Cast cast;
        ICastItemClickListener iCastItemClickListener;

        public ViewHolder(@NonNull ItemCastDetailsLayoutBinding binding, ICastItemClickListener iCastItemClickListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.iCastItemClickListener = iCastItemClickListener;
            binding.getRoot().setOnClickListener(this);
        }

        public void setCast(Cast cast){
            this.cast = cast;
        }

        @Override
        public void onClick(View view) {
            iCastItemClickListener.onItemCastClick(cast);
        }
    }
}
