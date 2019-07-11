package com.example.instagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagram.model.Post;
import com.parse.ParseImageView;

import java.util.List;
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


     public class ViewHolder extends RecyclerView.ViewHolder {

         public TextView tvHandle;
         public ParseImageView ivImage;
         public TextView tvDescription;

         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             tvHandle = itemView.findViewById(R.id.tvHandle);
             ivImage = itemView.findViewById(R.id.ivImage);
             tvDescription = itemView.findViewById(R.id.tvDescription);

         }
         
     }
}