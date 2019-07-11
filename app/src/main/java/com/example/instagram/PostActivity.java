package com.example.instagram;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagram.model.Post;
import com.parse.FindCallback;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

public class PostActivity extends AppCompatActivity {

    List<Post> posts;
    RecyclerView rvPosts;
    PostAdapter adapter;
    //SwipeRefreshLayout swipeContainer;
    private EndlessRecyclerViewScrollListener scrollListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        posts = new ArrayList<>();

        rvPosts = (RecyclerView) findViewById(R.id.rvPosts);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvPosts.setLayoutManager(linearLayoutManager);
        adapter = new PostAdapter(posts);
        rvPosts.setAdapter(adapter);

        loadToPosts();

    }
    private void loadToPosts() {
        final Post.Query postQuery = new Post.Query();
        postQuery.getTop().withUser().orderByDescending("createdAt");

        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if (e == null) {
                    for(int i = 0; i < objects.size(); i++){
                        Post post = objects.get(i);
                        posts.add(post);
                        adapter.notifyItemInserted(posts.size() - 1);
                    }



                } else {
                    Log.e("PostActivity", "Failed");
                }
            }
        });



    }
}
