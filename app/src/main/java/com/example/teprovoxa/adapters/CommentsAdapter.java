package com.example.teprovoxa.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teprovoxa.R;
import com.example.teprovoxa.data.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder>{

    public interface OnChallengeClickedListener{
        public void onChallengeClicked(int position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView username, text;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.itm_comment_usr);
            image = itemView.findViewById(R.id.itm_comment_img);
            text = itemView.findViewById(R.id.itm_comment_text);

        }
        public void bind(Comment comment){
            username.setText(comment.username);
            text.setText(comment.text);

            if(comment.image != null) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inMutable = true;
                Bitmap bmp = BitmapFactory.decodeByteArray(comment.image, 0, comment.image.length, options);

                image.setImageBitmap(bmp);
            }
        }
    }
    private List<Comment> mComments;

    public CommentsAdapter(List<Comment> comments){
        mComments = comments;
    }

    @NonNull
    @Override
    public CommentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_commment, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsAdapter.ViewHolder holder, int position) {
        Comment comment = mComments.get(position);
        holder.bind(comment);
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    public void setData(List<Comment> newData){
        mComments = new ArrayList<Comment>(newData);
    }
}