package in.daaconline.doomshell.rahul.Interview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.daac.R;

import static in.daaconline.doomshell.rahul.Interview.InterviewActivity.activity2;
import static in.daaconline.doomshell.rahul.Interview.InterviewActivity.position;


public class InterviewQuesFragment extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    static int checkst=0;
    TextView ques,number;
    TextView previous,finish;
    static RadioGroup radioGroup;
    static RadioButton op1, op2, op3, op4;
    Button next,finishBtn;
    static Context c;
    static Activity activity;
    public static int correct;


    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.interviewques_fragment, container, false);
        ques = v.findViewById(R.id.question_text);
        number = v.findViewById(R.id.quiz_question_no);
        radioGroup = v.findViewById(R.id.radioGroup);
        op1 = v.findViewById(R.id.op1);
        op2 = v.findViewById(R.id.op2);
        op3 = v.findViewById(R.id.op3);
        op4 = v.findViewById(R.id.op4);
        //answer = v.findViewById(R.id.answer);
        c = getContext();
        activity = getActivity();
        previous = v.findViewById(R.id.ques_prev);
        next = v.findViewById(R.id.ques_next);
        finishBtn = v.findViewById(R.id.finishButton);
        finishBtn.setVisibility(View.GONE);
        finish=v.findViewById(R.id.mark);
       // prev_2.setOnClickListener(this);
        //next_2.setOnClickListener(this);
        previous.setEnabled(false);
        previous.setOnClickListener(this);
        next.setOnClickListener(this);
        finish.setOnClickListener(this);
        finishBtn.setOnClickListener(this);
        number.setText("Question " + (position + 1));


        //AllQuesInter();
        radioGroup.setOnCheckedChangeListener(this);

        ques.setText(InterviewInstruction.inter_ques.get(position).getQuestion());
        //int n = inter_ques.get(position).getAnswer();
        op1.setText(InterviewInstruction.inter_ques.get(position).getOption1());
        op2.setText(InterviewInstruction.inter_ques.get(position).getOption2());
        op3.setText(InterviewInstruction.inter_ques.get(position).getOption3());
        op4.setText(InterviewInstruction.inter_ques.get(position).getOption4());
        number.setText("Question " + (position + 1));

        return v;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ques_prev) {
            --position;
            //inter_fragmentManager.beginTransaction().replace(R.id.frame_inter, new InterviewQuesFragment()).commit();
            AllQuesInter();

        } else if (v.getId() == R.id.ques_next) {
            //++position;
            //inter_fragmentManager.beginTransaction().replace(R.id.frame_inter, new InterviewQuesFragment()).commit();
            if (next.getText().toString().equals("Finish")) {

                interfinish();
            }else {
                ++position;
                //fragmentManager.beginTransaction().replace(R.id.quiz_frame, new QuestionFragment()).commit();
                AllQuesInter();
            }
        }

        else if(v.getId()== R.id.mark)
        {
            interfinish();
        }
        else if(v.getId()== R.id.finishButton)
        {
            interfinish();
        }
        else if (v.getId() == R.id.ques_prev) {
            --position;
            // fragmentManager.beginTransaction().replace(R.id.quiz_frame, new QuestionFragment()).commit();
            AllQuesInter();
        }
    }

    public void AllQuesInter() {
        checkst = 1;
        radioGroup.clearCheck();
op1.setTextColor(getResources().getColor(R.color.status_youtube));
        op2.setTextColor(getResources().getColor(R.color.status_youtube));
        op3.setTextColor(getResources().getColor(R.color.status_youtube));
        op4.setTextColor(getResources().getColor(R.color.status_youtube));
        ques.setText(InterviewInstruction.inter_ques.get(position).getQuestion());
        //int n = inter_ques.get(position).getAnswer();
        op1.setText(InterviewInstruction.inter_ques.get(position).getOption1());
        op2.setText(InterviewInstruction.inter_ques.get(position).getOption2());
        op3.setText(InterviewInstruction.inter_ques.get(position).getOption3());
        op4.setText(InterviewInstruction.inter_ques.get(position).getOption4());
        number.setText("Question "+(position+1));

        if (position == 0) {
            previous.setEnabled(false);
            next.setText(">");
            //finishBtn.setEnabled(false);
            //next.setVisibility(View.VISIBLE);
            //finish.setVisibility(View.VISIBLE);
        } else {
            previous.setEnabled(true);

        }
        if (position == InterviewInstruction.inter_ques.size() - 1) {
            next.setEnabled(false);
            next.setVisibility(View.GONE);
            finish.setVisibility(View.GONE);
            finishBtn.setVisibility(View.VISIBLE);
        } else {
            next.setEnabled(true);
            next.setVisibility(View.VISIBLE);
            finishBtn.setVisibility(View.GONE);
        }

            if (InterviewInstruction.interQuestions.get(position).answer1 != null) {

                if (InterviewInstruction.interQuestions.get(position).answer1.equals("1") == true)
                    ((RadioButton) radioGroup.getChildAt(0)).setChecked(true);

                else if (InterviewInstruction.interQuestions.get(position).answer1.equals("2") == true)
                    ((RadioButton) radioGroup.getChildAt(1)).setChecked(true);
                else if (InterviewInstruction.interQuestions.get(position).answer1.equals("3") == true)
                    ((RadioButton) radioGroup.getChildAt(2)).setChecked(true);
                else if (InterviewInstruction.interQuestions.get(position).answer1.equals("4") == true)
                    ((RadioButton) radioGroup.getChildAt(3)).setChecked(true);

            }

    }


    public static void interfinish(){
        int complete =0;
        correct = 0;
        for(int i = 0; i< InterviewInstruction.inter_ques.size(); i++)
        {
            if (InterviewInstruction.interQuestions.get(i).answer1 != null){
                complete++;
                if (InterviewInstruction.interQuestions.get(i).answer1.equals(InterviewInstruction.inter_ques.get(i).getAnswer())){
                    correct++;
                }
            }
        }
        position=0;
        activity2.finish();
        Intent intent=new Intent(c, InterviewFinish.class);

        c.startActivity(intent);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkst == 1) {
            checkst = 0;
        } else {
            switch (checkedId) {
                case R.id.op1:
                    if(InterviewInstruction.inter_ques.get(position).getAnswer().equals("1"))
                    {
                        op1.setTextColor(getResources().getColor(R.color.grid2));

                    }
                    else
                    {
                        op1.setTextColor(getResources().getColor(R.color.security));
                    }
                    InterviewInstruction.interQuestions.get(position).answer1 = "1";
                    break;
                case R.id.op2:
                    if(InterviewInstruction.inter_ques.get(position).getAnswer().equals("2"))
                    {

                        op2.setTextColor(getResources().getColor(R.color.grid2));

                    }
                    else
                    {
                        op2.setTextColor(getResources().getColor(R.color.security));
                    }
                    InterviewInstruction.interQuestions.get(position).answer1 = "2";
                    break;
                case R.id.op3:
                    if(InterviewInstruction.inter_ques.get(position).getAnswer().equals("3"))
                    {

                        op3.setTextColor(getResources().getColor(R.color.grid2));

                    }
                    else
                    {
                        op3.setTextColor(getResources().getColor(R.color.security));
                    }
                    InterviewInstruction.interQuestions.get(position).answer1 = "3";
                    break;
                case R.id.op4:
                    if(InterviewInstruction.inter_ques.get(position).getAnswer().equals("4"))
                    {

                        op4.setTextColor(getResources().getColor(R.color.grid2));
                    } else
                    {
                        op4.setTextColor(getResources().getColor(R.color.security));
                    }
                    InterviewInstruction.interQuestions.get(position).answer1 = "4";
                    break;


            }

        }
    }
}
