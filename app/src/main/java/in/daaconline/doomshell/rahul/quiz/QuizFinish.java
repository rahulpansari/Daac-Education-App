package in.daaconline.doomshell.rahul.quiz;


import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daac.R;
import in.daaconline.doomshell.rahul.mainscreen.MainActivity;

import static in.daaconline.doomshell.rahul.quiz.QuestionFragment.correct;
import static in.daaconline.doomshell.rahul.quiz.QuizInstruction.quizquestion;

public class QuizFinish extends AppCompatActivity {
TextView quiz_finish,quiz_text;
ImageView trophy;
Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_finish);
        quiz_text=findViewById(R.id.textView10);
        quiz_finish=findViewById(R.id.quiz_score);
        Window window=getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.status_youtube));
        trophy=findViewById(R.id.trophy);
        trophy.setImageResource(R.drawable.trophy2);
        //share = findViewById(R.id.share);
        final Intent[] i = {getIntent()};
        String mark=null,completed=null,correct1=null;
        mark= i[0].getStringExtra("mark");
        completed= i[0].getStringExtra("completed");
        correct1= i[0].getStringExtra("correct");
        quiz_finish.setText(correct1 + "/" + quizquestion.size());

        done = findViewById(R.id.done);
         if (correct <= quizquestion.size()/2){
             trophy.setColorFilter(Color.argb(150,200,200,200));
             quiz_finish.setBackgroundColor(Color.RED);
             quiz_text.setText("You Have Not Passed the Quiz in 30 Minutes");
             quiz_finish.setTextColor(Color.WHITE);
             done.setText("Try Again");
             done.setBackgroundColor(Color.RED);
             done.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(),QuizActivity.class);
                    startActivity(i);
                 }
             });
         }
         else {
             trophy.setColorFilter(null);
             quiz_finish.setBackgroundColor(Color.GREEN);
             quiz_finish.setTextColor(Color.WHITE);
             done.setText("Done");
             done.setBackgroundColor(Color.GREEN);
             done.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                     startActivity(intent);
                 }
             });
         }

        /* share.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent shareIntent = new Intent(Intent.ACTION_SEND);
                 shareIntent.setType("text/plain");
                 shareIntent.putExtra(Intent.EXTRA_SUBJECT,"DAAC");
                 shareIntent.putExtra(Intent.EXTRA_TEXT,"Testing");
                 startActivity(Intent.createChooser(shareIntent,"Share via"));
             }
         });
         */
    }
}
