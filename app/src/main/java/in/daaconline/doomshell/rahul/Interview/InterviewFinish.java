package in.daaconline.doomshell.rahul.Interview;

import android.os.Bundle;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.daac.R;

public class InterviewFinish extends AppCompatActivity {
    TextView interfinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview_finish);
        interfinish = findViewById(R.id.inter_finish);
    }
}
