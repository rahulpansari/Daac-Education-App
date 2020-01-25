package in.daaconline.doomshell.rahul.broadcast;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.daac.R;

public class NoInternet extends AppCompatActivity implements View.OnClickListener {
Button tryagain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);
        tryagain=findViewById(R.id.tryagain);
        tryagain.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
