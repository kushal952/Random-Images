package com.example.recyclerview_layouts;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>
{
    private int idx;
    private LayoutInflater layoutInflater;
    private ArrayList<String> arrayList;
    private Context context;

    public ViewPagerAdapter(Context context, ArrayList<String> imgURLs)
    {
        this.layoutInflater = LayoutInflater.from(context);
        this.arrayList = imgURLs;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = layoutInflater.inflate(R.layout.fullscreen_img,parent, false);
        ViewPagerViewHolder viewPagerViewHolder = new ViewPagerViewHolder(view);

        return viewPagerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerViewHolder holder, int position)
    {
        String imgPath = arrayList.get(position);
        Uri uri = Uri.parse(imgPath);
        Glide.with(holder.imageView.getContext())
                .load(uri)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount()
    {
        return arrayList.size();
    }

    class ViewPagerViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imageView;
        public ViewPagerViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewfullScreen);
        }
    }
}
