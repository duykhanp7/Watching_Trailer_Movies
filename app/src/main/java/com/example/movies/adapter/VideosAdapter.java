package com.example.movies.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;
import com.example.movies.databinding.ItemYoutubeViewBinding;
import com.example.movies.listener.ITrailerItemClickListener;
import com.example.movies.model.TrailerObject;

import java.util.ArrayList;
import java.util.List;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.ViewHolder> {

    List<TrailerObject.Trailer> trailers;
    ITrailerItemClickListener iTrailerItemClickListener;

    public VideosAdapter(ITrailerItemClickListener iTrailerItemClickListener){
        trailers = new ArrayList<>();
        this.iTrailerItemClickListener = iTrailerItemClickListener;
    }

    public void setTrailers(List<TrailerObject.Trailer> trailers){
        this.trailers = trailers;
    }

    public void setITrailerItemClickListener(ITrailerItemClickListener iTrailerItemClickListener){
        this.iTrailerItemClickListener = iTrailerItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemYoutubeViewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_youtube_view,parent,false);
        return new ViewHolder(binding,iTrailerItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TrailerObject.Trailer trailer = trailers.get(position);
        holder.binding.setItem(trailer);
        holder.setTrailer(trailer);
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ItemYoutubeViewBinding binding;
        TrailerObject.Trailer trailer;
        ITrailerItemClickListener iTrailerItemClickListener;
        public ViewHolder(@NonNull ItemYoutubeViewBinding binding,ITrailerItemClickListener iTrailerItemClickListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.iTrailerItemClickListener = iTrailerItemClickListener;
            binding.getRoot().setOnClickListener(this);
        }

        public void setTrailer(TrailerObject.Trailer trailer){
            this.trailer = trailer;
        }

        @Override
        public void onClick(View view) {
            iTrailerItemClickListener.onTrailerItemClick(trailer);
        }
    }
}
