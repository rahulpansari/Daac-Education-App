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


import static in.daaconline.doomshell.rahul.courses.CourseActivity.security;


public class CourseHolderSecurity extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView textView1;
    TextView textView2;
    public ImageView imageView;
    public CardView cardView;
    Context c;
    public ProgressBar progressBar;
    public CourseHolderSecurity(@NonNull View itemView,Context context) {
        super(itemView);
        c=context;
        progressBar=itemView.findViewById(R.id.progress_security);
        textView1=itemView.findViewById(R.id.security_textView1);
       // textView2=itemView.findViewById(R.id.security_textView2);
        cardView=itemView.findViewById(R.id.security_course_title);
        imageView=itemView.findViewById(R.id.security_imageView2);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
Intent i=new Intent(c,CourseDetail.class);
        i.putExtra("course_id",security.get(getAdapterPosition()).getId());
        i.putExtra("cname",security.get(getAdapterPosition()).getCname());
i.putExtra("image",security.get(getAdapterPosition()).getImage());
i.putExtra("webview",security.get(getAdapterPosition()).getDesc());
c.startActivity(i);
    }
}