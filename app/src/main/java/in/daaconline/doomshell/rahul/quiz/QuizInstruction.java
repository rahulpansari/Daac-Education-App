package in.daaconline.doomshell.rahul.quiz;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.daac.R;

import in.daaconline.doomshell.rahul.mainscreen.MainActivity;
import in.daaconline.doomshell.rahul.retrofit.RetrofitApi;
import in.daaconline.doomshell.rahul.retrofit.RetrofitMethod;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import static in.daaconline.doomshell.rahul.quiz.QuizActivity.activity1;
import static in.daaconline.doomshell.rahul.quiz.QuizActivity.toolbar;
import static in.daaconline.doomshell.rahul.quiz.QuizCategoryHolder.c_name;
import static in.daaconline.doomshell.rahul.quiz.QuizCategoryHolder.course_id;

public class QuizInstruction extends Fragment implements View.OnClickListener {
    ProgressBar progressBar;
    static  ArrayList<QuizQuestionsGET.Output> questions;
CardView quiz_ins;
    static ArrayList<Questions> quizquestion;
    Button button;
    TextView cname,ctime,cquestion,quiz_marks,ins,c,tt,tq,mm;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.quiz_instruction,container,false);
       progressBar=v.findViewById(R.id.progress_instruction);
       progressBar.setVisibility(View.VISIBLE);
       quizquestion=new ArrayList<>();
       quiz_ins=v.findViewById(R.id.quiz_card);
       quiz_ins.setVisibility(View.INVISIBLE);
        cname=v.findViewById(R.id.c_name);
        ins=v.findViewById(R.id.quiz_in);
        c=v.findViewById(R.id.quiz_cn);
        tt=v.findViewById(R.id.quiz_tm);
        tq=v.findViewById(R.id.quiz_tq);
        mm=v.findViewById(R.id.quiz_mm);
        ctime=v.findViewById(R.id.c_time);
        quiz_marks=v.findViewById(R.id.quiz_marks);
        cquestion=v.findViewById(R.id.c_question);
       button=v.findViewById(R.id.button_instruction);
       button.setEnabled(false);
       button.setOnClickListener(this);
cname.setVisibility(View.INVISIBLE);
        ins.setVisibility(View.INVISIBLE);
        c.setVisibility(View.INVISIBLE);
        tt.setVisibility(View.INVISIBLE);
        tq.setVisibility(View.INVISIBLE);
        mm.setVisibility(View.INVISIBLE);
        ctime.setVisibility(View.INVISIBLE);
        quiz_marks.setVisibility(View.INVISIBLE);
        cquestion.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
toolbar.setVisibility(View.GONE);
        Retrofit retrofit = RetrofitMethod.RetrofitMethod(RetrofitApi.Login_url);
        RetrofitApi api = retrofit.create(RetrofitApi.class);
        Observable<QuizQuestionsGET> getSlider=api.getQuestionGET(course_id);
        getSlider.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QuizQuestionsGET>() {
                               @Override
                               public void onSubscribe(Disposable d) {

                               }

                               @Override
                               public void onNext(QuizQuestionsGET sliderlist) {
                                   questions = new ArrayList<>(sliderlist.getOutput());
                               }

                               @Override
                               public void onError(Throwable e) {

                               }

                               @Override
                               public void onComplete() {
                                   if (questions != null) {
                                       progressBar.setVisibility(View.GONE);
                                       cname.setVisibility(View.VISIBLE);
                                       ins.setVisibility(View.VISIBLE);
                                       c.setVisibility(View.VISIBLE);
                                       tt.setVisibility(View.VISIBLE);
                                       tq.setVisibility(View.VISIBLE);
                                       mm.setVisibility(View.VISIBLE);
                                       ctime.setVisibility(View.VISIBLE);
                                       quiz_marks.setVisibility(View.VISIBLE);
                                       quiz_ins.setVisibility(View.VISIBLE);
                                       cquestion.setVisibility(View.VISIBLE);
                                       button.setVisibility(View.VISIBLE);
                                       button.setEnabled(true);
                                       quiz_marks.setText(questions.size() + "");
                                       cquestion.setText(questions.size() + "");
                                       cname.setText(c_name);
                                       for (int i = 0; i < questions.size(); i++)
                                           quizquestion.add(new Questions(-1, null));

                                   }
                                   else
                                   {
                                       activity1.finish();
                                       Toast.makeText(activity1,"Unable to Fetch Questions",Toast.LENGTH_SHORT).show();
                                       Intent i=new Intent(activity1, MainActivity.class);
                                       startActivity(i);
                                   }
                               }




                           }
                );

        return v;
    }

    @Override
    public void onClick(View v) {


        QuizActivity.fragmentManager.beginTransaction().replace(R.id.quiz_frame,new QuestionFragment()).commit();
        QuizActivity.CountDownTimer(1800000);
    }
}
class Questions
{
    int mark;
    String answer;
    public Questions(int mark,String answer)
    {
        this.mark=mark;
        this.answer=answer;
    }
}