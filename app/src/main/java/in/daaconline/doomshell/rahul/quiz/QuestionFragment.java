package in.daaconline.doomshell.rahul.quiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.daac.R;

public class QuestionFragment extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    static int checkst = 0;
    static TextView question, no;
    public static TextView timetext_h;
    public static TextView timetext_m;
    public static TextView timetext_s;
    static RadioButton op1, op2, op3, op4;
    static Button next,finish;
    static TextView prev,mark;
    static RadioGroup radioGroup;
    public static int correct;
    static Context c;
    static Activity activity;
    static View v;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.quiz_question_fragment, container, false);
        this.v = v;
        finish=v.findViewById(R.id.ques_finished);
        finish.setOnClickListener(this);
        finish.setVisibility(View.GONE);
        question = v.findViewById(R.id.question_text);
        no = v.findViewById(R.id.quiz_question_no);
        timetext_h = v.findViewById(R.id.timertext_h);
        timetext_m = v.findViewById(R.id.timertext_m);
        timetext_s = v.findViewById(R.id.timertext_s);
        c = getContext();
        activity = getActivity();
        radioGroup = v.findViewById(R.id.radioGroup);
        mark = v.findViewById(R.id.mark);
        op1 = v.findViewById(R.id.op1);
        op2 = v.findViewById(R.id.op2);
        op3 = v.findViewById(R.id.op3);
        op4 = v.findViewById(R.id.op4);
        prev = v.findViewById(R.id.ques_prev);
        next = v.findViewById(R.id.ques_next);
        // prev_2=v.findViewById(R.id.ques_prev);
        // next_2=v.findViewById(R.id.ques_next_2);
        //prev_2.setOnClickListener(this);
        //next_2.setOnClickListener(this);
        prev.setEnabled(false);
        prev.setOnClickListener(this);
        next.setOnClickListener(this);
        mark.setOnClickListener(this);
        QuizActivity.toolbar.setVisibility(View.VISIBLE);
        no.setText("Question " + (QuizActivity.pos + 1));
        if (QuizInstruction.quizquestion.get(QuizActivity.pos).mark == -1)
            mark.setText("Mark");
        else
            mark.setText("Unmark");

        radioGroup.setOnCheckedChangeListener(this);

        question.setText(QuizInstruction.questions.get(QuizActivity.pos).getQuestion());
        op1.setText(QuizInstruction.questions.get(QuizActivity.pos).getOption1());
        op2.setText(QuizInstruction.questions.get(QuizActivity.pos).getOption2());
        op3.setText(QuizInstruction.questions.get(QuizActivity.pos).getOption3());
        op4.setText(QuizInstruction.questions.get(QuizActivity.pos).getOption4());
        no.setText("Question " + (QuizActivity.pos + 1));
        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.ques_finished)
        {
            quizfinish();
        }
        if (v.getId() == R.id.ques_prev) {
            --QuizActivity.pos;
            // fragmentManager.beginTransaction().replace(R.id.quiz_frame, new QuestionFragment()).commit();
            AllQuestion();

        } else if (v.getId() == R.id.ques_next) {
            if (next.getText().toString().equals("Finish")) {
                quizfinish();

            } else {
                ++QuizActivity.pos;
                //fragmentManager.beginTransaction().replace(R.id.quiz_frame, new QuestionFragment()).commit();
                AllQuestion();
            }
        } else if (v.getId() == R.id.mark) {
            if (QuizInstruction.quizquestion.get(QuizActivity.pos).mark == -1) {
                QuizInstruction.quizquestion.get(QuizActivity.pos).mark = 1;

                QuizActivity.m.clear();

                mark.setText("Unmark");
            } else {
                QuizInstruction.quizquestion.get(QuizActivity.pos).mark = -1;

                QuizActivity.m.clear();
                mark.setText("Mark");
            }
            for (int i = 0; i < QuizInstruction.quizquestion.size(); i++) {
                if (QuizInstruction.quizquestion.get(i).mark == 1)
                    QuizActivity.m.add("Question " + (i + 1));
            }
        } else if (v.getId() == R.id.ques_prev) {
            --QuizActivity.pos;
            // fragmentManager.beginTransaction().replace(R.id.quiz_frame, new QuestionFragment()).commit();
            AllQuestion();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (checkst == 1) {
            checkst = 0;
        } else {
            switch (checkedId) {
                case R.id.op1:
                    QuizInstruction.quizquestion.get(QuizActivity.pos).answer = "1";
                    break;
                case R.id.op2:
                    QuizInstruction.quizquestion.get(QuizActivity.pos).answer = "2";
                    break;
                case R.id.op3:
                    QuizInstruction.quizquestion.get(QuizActivity.pos).answer = "3";
                    break;
                case R.id.op4:
                    QuizInstruction.quizquestion.get(QuizActivity.pos).answer = "4";
                    break;


            }

        }
    }

    public static void AllQuestion() {
        checkst = 1;
        radioGroup.clearCheck();


        question.setText(QuizInstruction.questions.get(QuizActivity.pos).getQuestion());
        op1.setText(QuizInstruction.questions.get(QuizActivity.pos).getOption1());
        op2.setText(QuizInstruction.questions.get(QuizActivity.pos).getOption2());
        op3.setText(QuizInstruction.questions.get(QuizActivity.pos).getOption3());
        op4.setText(QuizInstruction.questions.get(QuizActivity.pos).getOption4());
        no.setText("Question " + (QuizActivity.pos + 1));
        if (QuizInstruction.quizquestion.get(QuizActivity.pos).mark == -1)
            mark.setText("Mark");
        else
            mark.setText("Unmark");

        if (QuizActivity.pos == 0) {
            prev.setEnabled(false);
            // prev_2.setEnabled(false);
            next.setText(">");
        } else {
            prev.setEnabled(true);
            // prev_2.setEnabled(true);
        }
        if (QuizActivity.pos == QuizInstruction.questions.size() - 1) {
            next.setEnabled(false);
            next.setVisibility(View.GONE);
            finish.setVisibility(View.VISIBLE);
            // next_2.setEnabled(false);
           // next.setText("Finish");
        } else {
            finish.setVisibility(View.GONE);
            next.setEnabled(true);
            next.setVisibility(View.VISIBLE);
            // next_2.setEnabled(true);
            }
            if (QuizInstruction.quizquestion.get(QuizActivity.pos).answer != null) {

                if (QuizInstruction.quizquestion.get(QuizActivity.pos).answer.equals("1") == true)
                    ((RadioButton) radioGroup.getChildAt(0)).setChecked(true);

                else if (QuizInstruction.quizquestion.get(QuizActivity.pos).answer.equals("2") == true)
                    ((RadioButton) radioGroup.getChildAt(1)).setChecked(true);
                else if (QuizInstruction.quizquestion.get(QuizActivity.pos).answer.equals("3") == true)
                    ((RadioButton) radioGroup.getChildAt(2)).setChecked(true);
                else if (QuizInstruction.quizquestion.get(QuizActivity.pos).answer.equals("4") == true)
                    ((RadioButton) radioGroup.getChildAt(3)).setChecked(true);

            }

            QuizActivity.m.clear();
            for (int i = 0; i < QuizInstruction.quizquestion.size(); i++) {
                if (QuizInstruction.quizquestion.get(i).mark == 1)
                    QuizActivity.m.add("Question " + (i + 1));
            }
        }
        public static void quizfinish()
        {
            if (QuizActivity.countDownTimer != null)
                QuizActivity.countDownTimer.cancel();
            int mark = 0;
            int completed = 0;
            correct = 0;
            for (int i = 0; i < QuizInstruction.questions.size(); i++) {
                if (QuizInstruction.quizquestion.get(i).mark >= 0)
                    mark++;
                if (QuizInstruction.quizquestion.get(i).answer != null) {
                    completed++;
                    if (QuizInstruction.quizquestion.get(i).answer.equals(QuizInstruction.questions.get(i).getAnswer())) {
                        correct++;
                    }
                }

            }

            Intent intent = new Intent(c, QuizFinish.class);
            intent.putExtra("mark", mark + "");
            intent.putExtra("completed", completed + "");
            intent.putExtra("correct", correct + "");
            activity.finish();
            c.startActivity(intent);
        }
    }

