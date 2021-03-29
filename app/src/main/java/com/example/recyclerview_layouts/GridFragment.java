package com.example.recyclerview_layouts;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GridFragment extends Fragment
{
    private RecyclerView recyclerView;
    private Context context;
    private LayoutInflater layoutInflater;

    public GridFragment(Context context)
    {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = layoutInflater.inflate(R.layout.recyclerview, container, false);
        recyclerView = view.findViewById(R.id.recycleview);

        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();

        ArrayList<String> arrayListImgURLs = new ArrayList<>();
        NetworkRequester networkRequester = new NetworkRequester(context);

        networkRequester.getImgURLs(new NetworkRequester.ImageURLResponseListener()
        {
            @Override
            public void onResponse(ArrayList<String> imgURLs)
            {
                arrayListImgURLs.addAll(imgURLs);
                RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(context, arrayListImgURLs, getFragmentManager());
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 4);

                layoutManager.setAutoMeasureEnabled(false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(recycleViewAdapter);
            }

            @Override
            public void onError(String msg)
            {
                Snackbar.make(getContext(), getView(), msg, Snackbar.LENGTH_INDEFINITE).show();
            }
        }, NetworkRequester.ImageSize.REGULAR);
    }
}
