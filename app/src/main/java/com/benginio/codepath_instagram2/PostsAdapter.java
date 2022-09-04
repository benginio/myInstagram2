package com.benginio.codepath_instagram2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.parse.ParseFile;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
    //for refresh the page
    //Clean all element of recycler view
    public void clear(){
        posts.clear();
        notifyDataSetChanged();
    }
    //Add a list of item
    public void addAll(List<Post> postsList){
        posts.addAll(postsList);
        notifyDataSetChanged();

    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvDescription;
        private TextView tvCreatedAt;
        
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername=itemView.findViewById(R.id.tvUsernsme);
            ivImage=itemView.findViewById(R.id.ivImage);
            tvDescription=itemView.findViewById(R.id.tvDescription);
            tvCreatedAt=itemView.findViewById(R.id.tvCreatedAt);

        }

        public void bind(Post post) {
            tvDescription.setText(post.getDescription());
            tvUsername.setText(post.getUser().getUsername());
            tvCreatedAt.setText((CharSequence) post.getCreatedAt());
            ParseFile image = post.getImage();
            if(image!=null){
                int radius=5;
                Glide.with(context).load(image.getUrl()).transform(new CenterCrop(),
                                new RoundedCorners(radius))
                        .placeholder(R.drawable.ldload)
                        .into(ivImage);
            }else {
            ivImage.setVisibility(View.GONE);
        }


        }
    }
}
