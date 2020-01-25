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


public class CourseHolderProgramming extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView textView1;
    TextView textView2;
    public ImageView imageView;
    public CardView cardView;
    Context c;
    public ProgressBar progressBar;
    public CourseHolderProgramming(@NonNull View itemView,Context context) {
        super(itemView);
        c=context;
        textView1=itemView.findViewById(R.id.programming_textView1);
       // textView2=itemView.findViewById(R.id.programming_textView2);
        cardView=itemView.findViewById(R.id.programming_course_title);
        progressBar=itemView.findViewById(R.id.progress_programming);
        imageView=itemView.findViewById(R.id.programming_imageView2);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i=new Intent(c,CourseDetail.class);
        i.putExtra("course_id", CourseActivity.programming.get(getAdapterPosition()).getId());
        i.putExtra("cname", CourseActivity.programming.get(getAdapterPosition()).getCname());
        i.putExtra("image", CourseActivity.programming.get(getAdapterPosition()).getImage());
        i.putExtra("webview", CourseActivity.programming.get(getAdapterPosition()).getDesc());
        c.startActivity(i);
    }
}