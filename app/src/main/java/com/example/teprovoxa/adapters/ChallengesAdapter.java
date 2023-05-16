package com.example.teprovoxa.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teprovoxa.R;
import com.example.teprovoxa.data.Challenge;

import java.util.ArrayList;
import java.util.List;

public class ChallengesAdapter extends RecyclerView.Adapter<ChallengesAdapter.ViewHolder>{

    public interface OnChallengeClickedListener{
        public void onChallengeClicked(int position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView username, title, text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.itm_challenge_title);
            text = itemView.findViewById(R.id.itm_challenge_text);
            username = itemView.findViewById(R.id.itm_challenge_usr);
        }
        public void bind(Challenge challenge){
            title.setText(challenge.title);
            text.setText(challenge.text);
            username.setText(challenge.username);
        }
    }
    private OnChallengeClickedListener mListener;
    private List<Challenge> mChallenges;

    public ChallengesAdapter(List<Challenge> challenges, OnChallengeClickedListener listener){
        mChallenges = challenges;
        mListener = listener;
    }

    @NonNull
    @Override
    public ChallengesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_challenge, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChallengesAdapter.ViewHolder holder, int position) {
        Challenge challenge = mChallenges.get(position);
        holder.bind(challenge);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onChallengeClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mChallenges.size();
    }

    public void setData(List<Challenge> newData){
        mChallenges = new ArrayList<Challenge>(newData);
    }
}