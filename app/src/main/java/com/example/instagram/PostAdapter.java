package com.example.instagram;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagram.model.Post;
import com.parse.ParseImageView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{

    private Context context;
    private List<Post> posts;


    public PostAdapter(List<Post> posts){
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // create the view using the item_movie layout
        View postView = inflater.inflate(R.layout.item_post, parent, false);
        // return a new ViewHolder
        return new ViewHolder(postView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.tvHandle.setText(post.getUser().getUsername());
        holder.tvDescription.setText(post.getDescription());
        holder.ivImage.setParseFile(post.getImage());
        holder.ivImage.loadInBackground();
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


     public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

         public TextView tvHandle;
         public ParseImageView ivImage;
         public TextView tvDescription;

         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             tvHandle = itemView.findViewById(R.id.tvTime);
             ivImage = itemView.findViewById(R.id.ivImage);
             tvDescription = itemView.findViewById(R.id.tvDescription);
             itemView.setOnClickListener(this);

         }

         @Override
             public void onClick(View view) {
                 int pos = getAdapterPosition();
                 // get post at that pos
                 if(pos != RecyclerView.NO_POSITION){
                     //ge the tweet at that position
                     Post post = posts.get(pos);
                     // create an intent for it
                     Intent intent = new Intent(context, PostDetailActivity.class);
                     intent.putExtra("photo", post.getImage().getUrl());
                     intent.putExtra("description", post.getDescription());
                     intent.putExtra("time", getRelativeTimeAgo(post.getCreatedAt().toString()));
                     //create intent and do what ya gotta do
                     Toast.makeText(context,post.getDescription(), Toast.LENGTH_SHORT).show();
                     context.startActivity(intent);


                 }
     }
    }
    // getRelativeTimeAgo("Mon Apr 01 21:16:23 +0000 2014");
    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }
}
