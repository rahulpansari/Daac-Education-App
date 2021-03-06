package in.daaconline.doomshell.rahul.quiz;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.daac.R;

public class QuizDialog extends DialogFragment implements View.OnClickListener {
    TextView quiz_text;
    Button yes,no;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.quiz_dialog, container, false);
quiz_text=v.findViewById(R.id.quiz_d_text);
yes=v.findViewById(R.id.quiz_d_yes);
no=v.findViewById(R.id.quiz_d_no);
yes.setOnClickListener(this);
no.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.quiz_d_yes) {
            dismiss();
            QuestionFragment.quizfinish();
        }
        else
        {
            dismiss();
        }

    }
}
