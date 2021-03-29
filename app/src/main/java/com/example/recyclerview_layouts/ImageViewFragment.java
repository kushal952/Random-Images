package com.example.recyclerview_layouts;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class ImageViewFragment extends Fragment
{
    private int idx;
    private LayoutInflater layoutInflater;

    public ImageViewFragment(Context context, int idx)
    {
        this.idx = idx;
//        this.layoutInflater = LayoutInflater.from(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.viewpager, container, false);
        ViewPager2 viewPager2 = view.findViewById(R.id.viewpage2);
        NetworkRequester networkRequester = new NetworkRequester(getContext());

        networkRequester.getImgURLs(new NetworkRequester.ImageURLResponseListener()
        {
            @Override
            public void onResponse(ArrayList<String> imgURLs)
            {

                ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getContext(), imgURLs);
                Log.i("&&**&&", "onResponse: " + imgURLs);
                viewPager2.setAdapter(viewPagerAdapter);
                viewPager2.setCurrentItem(idx);
            }

            @Override
            public void onError(String msg)
            {

            }
        }, NetworkRequester.ImageSize.REGULAR);

        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }
}
