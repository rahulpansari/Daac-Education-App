package in.daaconline.doomshell.rahul.courses;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.daac.R;


import static in.daaconline.doomshell.rahul.courses.CourseActivity.degree;


public class CourseHolderDegree extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView textView1;
    TextView textView2;
    public ImageView imageView;
    public CardView cardView;
    public ProgressBar progressBar;
    Context c;
    public CourseHolderDegree(@NonNull View itemView,Context context) {
        super(itemView);
        c=context;
        textView1=itemView.findViewById(R.id.degree_textView1);
        //textView2=itemView.findViewById(R.id.degree_textView2);
        cardView=itemView.findViewById(R.id.degree_course_title);
        imageView=itemView.findViewById(R.id.degree_imageView2);
        progressBar=itemView.findViewById(R.id.progress_degree);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i=new Intent(c,CourseDetail.class);
        i.putExtra("course_id",degree.get(getAdapterPosition()).getId());
        i.putExtra("cname",degree.get(getAdapterPosition()).getCname());
        i.putExtra("image",degree.get(getAdapterPosition()).getImage());
        i.putExtra("webview",degree.get(getAdapterPosition()).getDesc());
        c.startActivity(i);
    }
}
