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


public class CourseHolderAccounts extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView textView1;
    TextView textView2;
    public ImageView imageView;
    public CardView cardView;
    public ProgressBar progressBar;
    Context c;
    public CourseHolderAccounts(@NonNull View itemView, Context context) {
        super(itemView);
        c=context;
        textView1=itemView.findViewById(R.id.accounts_textView1);
        //textView2=itemView.findViewById(R.id.accounts_textView2);
        cardView=itemView.findViewById(R.id.accounts_course_title);
        imageView=itemView.findViewById(R.id.accounts_imageView2);
        progressBar=itemView.findViewById(R.id.progress_accounts);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i=new Intent(c,CourseDetail.class);
        i.putExtra("course_id", CourseActivity.accounts.get(getAdapterPosition()).getId());
        i.putExtra("cname", CourseActivity.accounts.get(getAdapterPosition()).getCname());
        i.putExtra("image", CourseActivity.accounts.get(getAdapterPosition()).getImage());
        i.putExtra("webview", CourseActivity.accounts.get(getAdapterPosition()).getDesc());
        progressBar=itemView.findViewById(R.id.progress_design);
        c.startActivity(i);
    }
}
