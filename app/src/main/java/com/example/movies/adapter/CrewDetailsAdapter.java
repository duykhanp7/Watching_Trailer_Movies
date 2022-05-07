package com.example.movies.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;
import com.example.movies.databinding.ItemCrewDetailsLayoutBinding;
import com.example.movies.listener.ICrewItemClickListener;
import com.example.movies.model.Crew;

import java.util.ArrayList;
import java.util.List;

public class CrewDetailsAdapter extends RecyclerView.Adapter<CrewDetailsAdapter.ViewHolder> {

    List<Crew> crews;
    ICrewItemClickListener iCrewItemClickListener;

    public CrewDetailsAdapter(ICrewItemClickListener iCrewItemClickListener){
        crews = new ArrayList<>();
        this.iCrewItemClickListener = iCrewItemClickListener;
    }

    public void setCrews(List<Crew> crews){this.crews = crews;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCrewDetailsLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_crew_details_layout,parent,false);
        return new ViewHolder(binding, iCrewItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Crew crew = crews.get(position);
        holder.binding.setItem(crew);
        holder.setCrew(crew);
    }

    @Override
    public int getItemCount() {
        return crews.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ItemCrewDetailsLayoutBinding binding;
        Crew crew;
        ICrewItemClickListener iCrewItemClickListener;
        public ViewHolder(@NonNull ItemCrewDetailsLayoutBinding binding, ICrewItemClickListener iCrewItemClickListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.iCrewItemClickListener = iCrewItemClickListener;
            binding.getRoot().setOnClickListener(this);
        }

        public void setCrew(Crew crew){this.crew = crew;}

        @Override
        public void onClick(View view) {
            iCrewItemClickListener.onItemCrewClick(crew);
        }
    }
}
