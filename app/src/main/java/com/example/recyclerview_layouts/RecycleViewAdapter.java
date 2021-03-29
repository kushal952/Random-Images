package com.example.recyclerview_layouts;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecycleViewHolder>
{
    private LayoutInflater layoutInflater;
    private ArrayList<String> arrayListImgURLs;
    private FragmentManager fragmentManager;

    public RecycleViewAdapter(Context context, ArrayList<String> imgURLs, FragmentManager fragmentManager)
    {
        this.fragmentManager = fragmentManager;
        arrayListImgURLs = imgURLs;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = layoutInflater.inflate(R.layout.imageview_layout, parent, false);
        RecycleViewHolder recycleViewHolder = new RecycleViewHolder(view);

        return recycleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position)
    {
        Uri imgUri = Uri.parse(arrayListImgURLs.get(position));
        Glide.with(holder.imageView.getContext())
            .load(imgUri)
            .into(holder.imageView);
        String idx = String.valueOf(position);
        holder.imageView.setContentDescription(idx);
        holder.imageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ImageView imageView = (ImageView) v;
                String idx = (String) imageView.getContentDescription();
                int idxInt = Integer.valueOf(idx);
                ImageViewFragment imageViewFragment = new ImageViewFragment(imageView.getContext().getApplicationContext(), idxInt);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().addToBackStack(null);

                fragmentTransaction.replace(R.id.constraintLayout, imageViewFragment).commit();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return arrayListImgURLs.size();
    }

    class RecycleViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imageView;
        public RecycleViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
















