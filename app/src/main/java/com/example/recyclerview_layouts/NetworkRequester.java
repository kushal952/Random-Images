package com.example.recyclerview_layouts;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NetworkRequester
{
    private Context context;

    public NetworkRequester(Context context)
    {
        this.context = context;
    }

    public void getImgURLs(ImageURLResponseListener imageURLResponseListener, ImageSize imageSize)
    {
        String UNSPLASH_URL = context.getString(R.string.imgs_url);
        String URL = context.getString(R.string.urls);
        String REGULAR = context.getString(R.string.regular);
        String FULL = context.getString(R.string.full);
        String ERROR = context.getString(R.string.error_msg);
        JSONArray jsonArray = new JSONArray();
        ArrayList<String> arrayList = new ArrayList<>();
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, UNSPLASH_URL, jsonArray, new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                try
                {
                    for (int i = 0; i < response.length(); i++)
                    {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        JSONObject jsonURLObject = (JSONObject) jsonObject.get(URL);

                        String imgURL = "";
                        if(imageSize == ImageSize.REGULAR)
                        {
                            imgURL = jsonURLObject.getString(REGULAR);
                        } else if(imageSize == ImageSize.FULL_SCREEN)
                        {
                            imgURL = jsonURLObject.getString(FULL);
                        }
                        arrayList.add(imgURL);
                    }
                    imageURLResponseListener.onResponse(arrayList);
                } catch (JSONException jsonException)
                {
                    jsonException.printStackTrace();
                }
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                imageURLResponseListener.onError(ERROR);
            }
        });

        Volley.newRequestQueue(context).add(arrayRequest);
    }

    interface ImageURLResponseListener
    {
        void onResponse(ArrayList<String> imgURLs);
        void onError(String msg);
    }

    enum ImageSize
    {
        REGULAR, FULL_SCREEN
    }
}

