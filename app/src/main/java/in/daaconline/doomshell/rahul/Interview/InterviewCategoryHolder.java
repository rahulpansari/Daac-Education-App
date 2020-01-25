package in.daaconline.doomshell.rahul.Interview;


import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daac.R;


public class InterviewCategoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView imageView;
    static  String inter_course_id;
    static String inter_c_name;
    public InterviewCategoryHolder(@NonNull View itemView) {
        super(itemView);
        // textView=itemView.findViewById(R.id.textView);
        imageView = itemView.findViewById(R.id.imageView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        inter_c_name= InterviewCategoryFragment.output1.get(getAdapterPosition()).getCourse_name();
        inter_course_id= InterviewCategoryFragment.output1.get(getAdapterPosition()).getCourse_id();
        InterviewActivity.inter_fragmentManager.beginTransaction().replace(R.id.frame_inter,new InterviewInstruction()).commit();
    }
}