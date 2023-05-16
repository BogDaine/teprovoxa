package com.example.teprovoxa.fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teprovoxa.ApplicationController;
import com.example.teprovoxa.adapters.CommentsAdapter;
import com.example.teprovoxa.R;
import com.example.teprovoxa.data.Comment;
import com.example.teprovoxa.data.DataRepository;
import com.example.teprovoxa.data.DbOnCommentQueryListener;
import com.example.teprovoxa.data.DbOnSuccessListener;
import com.example.teprovoxa.utils.MyUtilityFunctions;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChallengeCommentsFragment extends Fragment {

    RecyclerView commentsRv;
    ArrayList<Comment> comments;
    CommentsAdapter commentsAdapter;
    DataRepository dataRepository = new DataRepository();

    EditText commentText;
    byte[] commentImage;


    public ChallengeCommentsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_challenge_comments, container, false);
        Button postButton = (Button)view.findViewById(R.id.btn_post_comment);
        Button chooseImgButton = (Button)view.findViewById(R.id.btn_choose_commentimage);
        commentText = (EditText)view.findViewById(R.id.new_comment_text);
        commentsRv= view.findViewById(R.id.comments_rv);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPostCommentClicked();
            }
        });
        chooseImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChooseImageClicked();
            }
        });
        setupRecyclerView();
        return view;
    }

    private void setupRecyclerView(){
        Context context = this.getContext();
        dataRepository.findChallengeComments(ApplicationController.getInstance().getLastAccessedChallenge().uid, new DbOnCommentQueryListener() {
            @Override
            public void onSuccess(List<Comment> items) {
                comments = new ArrayList<Comment>(items);
                commentsAdapter = new CommentsAdapter(comments);

                commentsRv.setAdapter(commentsAdapter);
                commentsRv.setLayoutManager(new LinearLayoutManager(context));
            }
        });
    }

    private void onPostCommentClicked(){
        String text = commentText.getText().toString();
        if(text.length()==0){
            return;
        }
        int challengeId = ApplicationController.getInstance().getLastAccessedChallenge().uid;
        String username = ApplicationController.getInstance().getLoggedUser();

        Comment newComment = new Comment(challengeId, username, text, commentImage);

        dataRepository.insertComment(newComment, new DbOnSuccessListener() {
            @Override
            public void onSuccess() {
                onPostCommentSuccess();
            }
        });

        commentText.setText("");
    }

    private void onPostCommentSuccess(){
        Context context = ApplicationController.getInstance().getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, R.string.comment_post_success, duration);
        toast.show();

        getAllComments();
    }

    private void onChooseImageClicked(){
       //new AlertDialog.Builder(getContext())
       //        .setTitle(R.string.choose_img)
       //        .setMessage(R.string.where_from)
       //        .setPositiveButton(R.string.camera, new DialogInterface.OnClickListener() {
       //            public void onClick(DialogInterface dialog, int which) {
       //                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
       //                startActivityForResult(takePicture, 0);
       //            }
       //        }).setNegativeButton(R.string.gallery, new DialogInterface.OnClickListener() {
       //            @Override
       //            public void onClick(DialogInterface dialog, int which) {

       //                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
       //                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
       //                startActivityForResult(pickPhoto, 1);
       //            }
       //        })
       //        .setIcon(android.R.drawable.ic_dialog_alert)
       //        .show();
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, 1);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if(resultCode == RESULT_OK){
            Uri selectedImage = imageReturnedIntent.getData();
            try {
                String path = MyUtilityFunctions.getPath(getContext(), selectedImage);
                if(path != null) {
                    commentImage = FileUtils.readFileToByteArray(new File(path));
                    Context context = ApplicationController.getInstance().getApplicationContext();
                    Toast toast = Toast.makeText(context, R.string.img_pick_success, Toast.LENGTH_SHORT);
                    toast.show();
                }
            } catch (IOException e) {
                Log.d("BBB",             e.getMessage());
            }

        }
    }
    private void getAllComments(){
        dataRepository.findChallengeComments(ApplicationController.getInstance().getLastAccessedChallenge().uid,
                new DbOnCommentQueryListener() {
            @Override
            public void onSuccess(List<Comment> items) {
                setComments(items);
            }
        });
    }
    private void setComments(List<Comment> items){
        comments = new ArrayList<Comment>(items);
        commentsAdapter.setData(comments);
        commentsAdapter.notifyDataSetChanged();
    }
}