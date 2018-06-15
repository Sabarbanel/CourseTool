package com.example.sarah.coursetool.CourseListing;

import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;

public class viewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Listing> inputData = new ArrayList<Listing>();

    public viewAdapter(ArrayList<Listing> someData){
        inputData = someData;
    }


}
