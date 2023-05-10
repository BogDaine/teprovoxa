package com.example.teprovoxa;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AnimalsAdapter extends
        RecyclerView.Adapter<AnimalsAdapter.ViewHolder> {
    OnAnimalClickedListener mOnAnimalClickedListener;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_animal, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Animal animal = mAnimals.get(position);

        TextView nameTextView = holder.nameTextView;
        nameTextView.setText(animal.getName());
        TextView continentTextView = holder.continentTextView;
        continentTextView.setText(animal.getmContinent());
        ConstraintLayout animalLayout = holder.getAnimalLayout();
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(animalLayout);

        holder.mAnimalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnAnimalClickedListener.onAnimalClicked(position);
            }
        });

        switch(animal.getmContinent())
        {
            case "Europa":
                holder.getAnimalLayout().setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.good_green));
                constraintSet.connect(R.id.animal_name, ConstraintSet.TOP, R.id.animal_layout, ConstraintSet.TOP, 0);
                constraintSet.connect(R.id.continent, ConstraintSet.TOP, R.id.animal_name, ConstraintSet.BOTTOM, 0);
                constraintSet.connect(R.id.animal_name, ConstraintSet.LEFT, R.id.animal_layout, ConstraintSet.LEFT, 0);
                constraintSet.connect(R.id.continent, ConstraintSet.LEFT, R.id.animal_layout, ConstraintSet.LEFT, 0);

                break;
            case "Africa":
                holder.getAnimalLayout().setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.good_yellow));


                View guideline = new View(holder.itemView.getContext());
                animalLayout.addView(guideline);
                guideline.setBackgroundColor(Color.BLACK);
                guideline.setMinimumHeight(4);

                guideline.setId(View.generateViewId());
                guideline.setVisibility(View.VISIBLE);

                constraintSet.connect(R.id.animal_name, ConstraintSet.TOP, R.id.animal_layout, ConstraintSet.TOP, 0);
                constraintSet.connect(guideline.getId(), ConstraintSet.TOP, R.id.animal_name, ConstraintSet.BOTTOM);
                constraintSet.connect(R.id.continent, ConstraintSet.TOP, guideline.getId(), ConstraintSet.BOTTOM, 0);

                constraintSet.connect(R.id.animal_name, ConstraintSet.LEFT, R.id.animal_layout, ConstraintSet.LEFT, 0);
                constraintSet.connect(R.id.continent, ConstraintSet.RIGHT, R.id.animal_layout, ConstraintSet.RIGHT, 0);
                constraintSet.connect(guideline.getId(), ConstraintSet.RIGHT, R.id.animal_layout, ConstraintSet.RIGHT, 0);
                constraintSet.connect(guideline.getId(), ConstraintSet.LEFT, R.id.animal_layout, ConstraintSet.LEFT, 0);
                //NU STIU DE CE NU SE VEDE LINIA -.-

                break;
            case "Asia":
                holder.getAnimalLayout().setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.good_red));
                constraintSet.connect(R.id.animal_name, ConstraintSet.LEFT, R.id.animal_layout, ConstraintSet.LEFT, 0);
                constraintSet.connect(R.id.continent, ConstraintSet.LEFT, R.id.animal_name, ConstraintSet.RIGHT, 0);
                constraintSet.connect(R.id.continent, ConstraintSet.RIGHT, R.id.animal_layout, ConstraintSet.RIGHT, 0);

                constraintSet.constrainPercentWidth(R.id.animal_name, 50.0f);
                constraintSet.constrainPercentWidth(R.id.continent, 50.0f);

                nameTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                continentTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                break;
            case "America":
                holder.getAnimalLayout().setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.good_blue));
                constraintSet.connect(R.id.animal_name, ConstraintSet.TOP, R.id.animal_layout, ConstraintSet.TOP, 0);
                constraintSet.connect(R.id.continent, ConstraintSet.TOP, R.id.animal_name, ConstraintSet.BOTTOM, 0);
                constraintSet.connect(R.id.animal_name, ConstraintSet.RIGHT, R.id.animal_layout, ConstraintSet.RIGHT, 0);
                constraintSet.connect(R.id.continent, ConstraintSet.RIGHT, R.id.animal_layout, ConstraintSet.RIGHT, 0);
                break;
            case "Australia":
                holder.getAnimalLayout().setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.good_orange));
                constraintSet.connect(R.id.animal_name, ConstraintSet.TOP, R.id.animal_layout, ConstraintSet.TOP, 0);
                constraintSet.connect(R.id.continent, ConstraintSet.TOP, R.id.animal_name, ConstraintSet.BOTTOM, 0);

                constraintSet.connect(R.id.animal_name, ConstraintSet.LEFT, R.id.animal_layout, ConstraintSet.LEFT, 0);
                constraintSet.connect(R.id.animal_name, ConstraintSet.RIGHT, R.id.animal_layout, ConstraintSet.RIGHT, 0);

                constraintSet.connect(R.id.continent, ConstraintSet.LEFT, R.id.animal_layout, ConstraintSet.LEFT, 0);
                constraintSet.connect(R.id.continent, ConstraintSet.RIGHT, R.id.animal_layout, ConstraintSet.RIGHT, 0);
                break;
        }
        constraintSet.applyTo(animalLayout);
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return mAnimals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView continentTextView;
        ConstraintLayout mAnimalLayout;
        ConstraintLayout getAnimalLayout(){return mAnimalLayout;}

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.animal_name);
            continentTextView = (TextView) itemView.findViewById(R.id.continent);
            mAnimalLayout = (ConstraintLayout) itemView.findViewById(R.id.animal_layout);
        }
    }
    private List<Animal> mAnimals;

    public AnimalsAdapter(List<Animal> animals, OnAnimalClickedListener listener) {
        mAnimals = animals;
        mOnAnimalClickedListener = listener;
    }

}