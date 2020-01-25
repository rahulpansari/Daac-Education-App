package in.daaconline.doomshell.rahul.Interview;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.daac.R;
import in.daaconline.doomshell.rahul.broadcast.MyBroadcast;

public class InterviewActivity extends AppCompatActivity {
    public static int position = 0;
    public static FragmentManager inter_fragmentManager;
    static Activity activity2;
MyBroadcast broadcast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);
        activity2=this;
        inter_fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = inter_fragmentManager.beginTransaction().add(R.id.frame_inter, new InterviewCategoryFragment());
        transaction.commit();


    }

    @Override
    public void onBackPressed() {

        Fragment fragment=inter_fragmentManager.findFragmentById(R.id.frame_inter);
        if(fragment instanceof InterviewQuesFragment)
        {
            Fragment prev = inter_fragmentManager.findFragmentByTag("dialog");
            DialogFragment dialogFragment = new InterviewDialog();
            dialogFragment.show(inter_fragmentManager.beginTransaction(), "dialog");
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        broadcast=new MyBroadcast();
        IntentFilter filter=new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(broadcast,filter);
    }
}
