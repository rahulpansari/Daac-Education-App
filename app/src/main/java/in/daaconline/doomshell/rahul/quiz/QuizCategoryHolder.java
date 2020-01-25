package in.daaconline.doomshell.rahul.quiz;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;


import com.example.daac.R;

public class QuizCategoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

ImageView imageView;
static  String course_id;
static String c_name;
    public QuizCategoryHolder(@NonNull View itemView) {
        super(itemView);
        //textView=itemView.findViewById(R.id.textView);
        imageView=itemView.findViewById(R.id.imageView);
        itemView.setOnClickListener(this);
    }
    public void onClick(View v)
    {   c_name= QuizCategoryFragment.output.get(getAdapterPosition()).getCourse_name();
        course_id= QuizCategoryFragment.output.get(getAdapterPosition()).getCourse_id();
        QuizActivity.fragmentManager.beginTransaction().replace(R.id.quiz_frame,new QuizInstruction()).commit();
    }


}
