package com.example.recyclerview_layouts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridFragment gridFragment = new GridFragment(getApplicationContext());
        String RECYCLER_VIEW = getString(R.string.grid_fragment);

        getSupportFragmentManager().beginTransaction().addToBackStack(null).add(R.id.constraintLayout ,gridFragment, RECYCLER_VIEW).commit();
    }
}