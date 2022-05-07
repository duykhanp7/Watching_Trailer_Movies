package com.example.movies.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;
import com.example.movies.databinding.ItemCastLayoutBinding;
import com.example.movies.listener.ICastItemClickListener;
import com.example.movies.model.Cast;

import java.util.ArrayList;
import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder> {

    public List<Cast> casts;
    ICastItemClickListener iCastItemClickListener;

    public CastAdapter(ICastItemClickListener iCastItemClickListener){
        casts = new ArrayList<>();
        this.iCastItemClickListener = iCastItemClickListener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCasts(List<Cast> casts){
        this.casts = casts;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCastLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_cast_layout,parent,false);
        return new ViewHolder(binding,iCastItemClickListener);
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
        ItemCastLayoutBinding binding;
        ICastItemClickListener iCastItemClickListener;
        Cast cast;

        public ViewHolder(@NonNull ItemCastLayoutBinding binding,ICastItemClickListener iCastItemClickListener) {
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
