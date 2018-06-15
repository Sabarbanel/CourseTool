package com.example.sarah.coursetool.CourseListing;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.util.Log;

import com.example.sarah.coursetool.R;

public class CourseHolder extends RecyclerView.ViewHolder {
    CardView cv;
    TextView title;
    TextView description;
    TextView imageView;

    CourseHolder(View itemView){
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView);
        title = (TextView) itemView.findViewById(R.id.title);
        description = (TextView) itemView.findViewById(R.id.description);
        imageView = (TextView) itemView.findViewById(R.id.imageView);
    }


}
