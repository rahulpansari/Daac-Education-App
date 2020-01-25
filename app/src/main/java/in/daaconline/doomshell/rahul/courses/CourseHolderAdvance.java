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


public class CourseHolderAdvance extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView textView1;
    TextView textView2;
    public ImageView imageView;
    public ProgressBar progressBar;
    public CardView cardView;
    Context c;
    public CourseHolderAdvance(@NonNull View itemView,Context context) {
        super(itemView);
        c=context;

        textView1=itemView.findViewById(R.id.advance_textView1);
       // textView2=itemView.findViewById(R.id.advance_textView2);
        cardView=itemView.findViewById(R.id.advance_course_title);
        progressBar=itemView.findViewById(R.id.progress_advance);
        imageView=itemView.findViewById(R.id.advance_imageView2);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i=new Intent(c,CourseDetail.class);
        i.putExtra("course_id", CourseActivity.advance.get(getAdapterPosition()).getId());
        i.putExtra("cname", CourseActivity.advance.get(getAdapterPosition()).getCname());
        i.putExtra("image", CourseActivity.advance.get(getAdapterPosition()).getImage());
        i.putExtra("webview", CourseActivity.advance.get(getAdapterPosition()).getDesc());
        c.startActivity(i);
    }
}
