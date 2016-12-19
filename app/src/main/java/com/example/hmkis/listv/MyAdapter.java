package com.example.hmkis.listv;

import android.support.v7.widget.RecyclerView;


import android.app.AlertDialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Biere> bieres;
    public MyAdapter(ArrayList<Biere> bieres){
        this.bieres=bieres;
    }





    @Override
    public int getItemCount() {
        return bieres.size();
    }
// on crée un compteur
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_cell, parent, false);
        return new MyViewHolder(view);
    }




    //appliquer une donnée à une vue
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Biere  biere = bieres.get(position);
        holder.display(biere);
    }

    //prend en parametre la vueà afficher
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView description;

        private Biere currentBiere;

        public MyViewHolder(final View itemView) {
            super(itemView);

            name = ((TextView) itemView.findViewById(R.id.name));
            description = ((TextView) itemView.findViewById(R.id.description));


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    new AlertDialog.Builder(itemView.getContext())

                            .setTitle(currentBiere.name)
                            .setMessage(currentBiere.description)
                            .show();
                }
            });
        }

        //affiher les données de la Bieree fournie
        public void display(Biere biere) {
            currentBiere = biere;
            name.setText(biere.name);
            description.setText(biere.description);
        }
    }




}