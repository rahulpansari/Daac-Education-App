package in.daaconline.doomshell.rahul.Interview;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

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

import static in.daaconline.doomshell.rahul.Interview.InterviewActivity.activity2;
import static in.daaconline.doomshell.rahul.Interview.InterviewCategoryHolder.inter_c_name;
import static in.daaconline.doomshell.rahul.Interview.InterviewCategoryHolder.inter_course_id;


public class InterviewInstruction extends Fragment implements View.OnClickListener  {
    ProgressBar progressBar;
    static ArrayList<InterviewQuestionGET.Model> inter_ques;
    static ArrayList<InterQuestions> interQuestions;
    Button button;
    CardView quizins;
    TextView cname,cquestion;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.interview_instruction,container,false);
        progressBar=v.findViewById(R.id.progress_instruction);
        progressBar.setVisibility(View.VISIBLE);
        interQuestions=new ArrayList<>();
        cname=v.findViewById(R.id.c_name);
        cquestion=v.findViewById(R.id.c_question);
        quizins=v.findViewById(R.id.quiz_ins_card);
        button=v.findViewById(R.id.button_instruction);
        button.setEnabled(false);
        button.setOnClickListener(this);
        quizins.setVisibility(View.INVISIBLE);
button.setVisibility(View.INVISIBLE);
cquestion.setVisibility(View.INVISIBLE);
cname.setVisibility(View.INVISIBLE);
        Retrofit retrofit = RetrofitMethod.RetrofitMethod(RetrofitApi.Login_url);
        RetrofitApi api = retrofit.create(RetrofitApi.class);
        Observable<InterviewQuestionGET> getSlider=api.getInterviewQuestionGET(inter_course_id);
        getSlider.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InterviewQuestionGET>() {
                               @Override
                               public void onSubscribe(Disposable d) {

                               }

                               @Override
                               public void onNext(InterviewQuestionGET sliderlist) {
                                   inter_ques = new ArrayList<>(sliderlist.getOutput());
                               }

                               @Override
                               public void onError(Throwable e) {

                               }

                               @Override
                               public void onComplete() {
                                   if(inter_ques!=null)
                                   {
                                   progressBar.setVisibility(View.GONE);
                                   button.setEnabled(true);
                                   quizins.setVisibility(View.VISIBLE);
                                   button.setVisibility(View.VISIBLE);
                                   cquestion.setVisibility(View.VISIBLE);
                                   cname.setVisibility(View.VISIBLE);
                                   cquestion.setText(inter_ques.size()+"");
                                   cname.setText(inter_c_name);
                                   for(int i=0;i<inter_ques.size();i++)
                                       interQuestions.add(new InterQuestions(null));

                               }
                               else
                                   {activity2.finish();
                                   Toast.makeText(activity2,"Unable to Fetch Questions",Toast.LENGTH_SHORT).show();
                                       Intent i=new Intent(activity2, MainActivity.class);
                                       startActivity(i);
                                   }

                               }




                           }
                );

        return v;
    }

    @Override
    public void onClick(View v) {
        InterviewActivity.inter_fragmentManager.beginTransaction().replace(R.id.frame_inter,new InterviewQuesFragment()).commit();
    }
}

class InterQuestions
{

    String answer1;
    public InterQuestions(String answer2)
    {
        this.answer1=answer2;
    }
}