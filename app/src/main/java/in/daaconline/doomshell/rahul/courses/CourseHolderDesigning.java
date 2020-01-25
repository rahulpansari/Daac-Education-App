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


public class CourseHolderDesigning extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView textView1;
    TextView textView2;
    public ImageView imageView;
    public CardView cardView;
    public ProgressBar progressBar;
    Context c;
    public CourseHolderDesigning(@NonNull View itemView,Context context) {
        super(itemView);
        c=context;
        progressBar=itemView.findViewById(R.id.progress_design);

        textView1=itemView.findViewById(R.id.designing_textView1);
       // textView2=itemView.findViewById(R.id.designing_textView2);
        cardView=itemView.findViewById(R.id.designing_course_title);
        imageView=itemView.findViewById(R.id.designing_imageView2);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i=new Intent(c,CourseDetail.class);
        i.putExtra("course_id", CourseActivity.designing.get(getAdapterPosition()).getId());
        i.putExtra("cname", CourseActivity.designing.get(getAdapterPosition()).getCname());
        i.putExtra("image", CourseActivity.designing.get(getAdapterPosition()).getImage());
        i.putExtra("webview", CourseActivity.designing.get(getAdapterPosition()).getDesc());
        c.startActivity(i);
    }
}
