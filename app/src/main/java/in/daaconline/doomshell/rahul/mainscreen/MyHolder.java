package in.daaconline.doomshell.rahul.mainscreen;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daac.R;
import in.daaconline.doomshell.rahul.Interview.InterviewActivity;
import in.daaconline.doomshell.rahul.OurTeam;
import in.daaconline.doomshell.rahul.contactus.ContactRecycler;
import in.daaconline.doomshell.rahul.himanshu.jobdata_recycler;

import in.daaconline.doomshell.rahul.courses.CourseActivity;
import in.daaconline.doomshell.rahul.login.Login;
import in.daaconline.doomshell.rahul.quiz.QuizActivity;
import in.daaconline.doomshell.rahul.videos.YoutubeCategory;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
CardView cardView;
ImageView image;
TextView text;
List<MyObjectGrid> object;
Context x;

    public MyHolder(@NonNull View itemView, ArrayList<MyObjectGrid>o, Context context) {
        super(itemView);
        cardView=itemView.findViewById(R.id.card_view);
         image=itemView.findViewById(R.id.imageView);
        text=itemView.findViewById(R.id.textView);
        itemView.setOnClickListener(this);
        object=o;
        x=context;


    }

    @Override
    public void onClick(View v) {
        if(object.get(getAdapterPosition()).desc.equals("Courses"))
        {
            Intent intent=new Intent(x, CourseActivity.class);
            x.startActivity(intent);
        }
        else if(object.get(getAdapterPosition()).desc.equals("Free Videos"))
        {

                Intent intent=new Intent(x, YoutubeCategory.class);
                x.startActivity(intent);
        }
        else if(object.get(getAdapterPosition()).desc.equals("Log Out"))
        {
            final AlertDialog.Builder alertdialog=new AlertDialog.Builder(x);
            alertdialog.setTitle("Are You Sure Want To Log Out?");
            alertdialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    SharedPreferences preferences=x.getSharedPreferences("My Application",MODE_PRIVATE);
                    preferences.edit().putInt("login",-121).commit();
                    ((Activity)x).finish();
                    Intent intent=new Intent(x, Login.class);
                    x.startActivity(intent);

                }
            });
            alertdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                   dialog.dismiss();
                }
            });
            alertdialog.create().show();

        }
        else if(object.get(getAdapterPosition()).desc.equals("Quiz"))
        {
            Intent i=new Intent(x, QuizActivity.class);
            x.startActivity(i);


        }
        else if(object.get(getAdapterPosition()).desc.equals("Contact Us"))
        {
            Intent i=new Intent(x, ContactRecycler.class);
            x.startActivity(i);
        }
        else if(object.get(getAdapterPosition()).desc.equals("Find a Job"))
        {
            Intent i=new Intent(x, jobdata_recycler.class);
            x.startActivity(i);
        }
        else if(object.get(getAdapterPosition()).desc.equals("Our Team"))
        {
            Intent i=new Intent(x, OurTeam.class);
            x.startActivity(i);
        }
        else if(object.get(getAdapterPosition()).desc.equals("Interview Questions"))
        {
            Intent i=new Intent(x, InterviewActivity.class);
            x.startActivity(i);
        }

    }
}

