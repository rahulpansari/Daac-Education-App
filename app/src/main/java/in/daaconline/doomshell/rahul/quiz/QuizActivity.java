package in.daaconline.doomshell.rahul.quiz;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.CountDownTimer;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.daac.R;
import in.daaconline.doomshell.rahul.broadcast.MyBroadcast;


import static in.daaconline.doomshell.rahul.quiz.QuestionFragment.AllQuestion;
import static in.daaconline.doomshell.rahul.quiz.QuestionFragment.quizfinish;
import static in.daaconline.doomshell.rahul.quiz.QuestionFragment.timetext_h;
import static in.daaconline.doomshell.rahul.quiz.QuestionFragment.timetext_m;
import static in.daaconline.doomshell.rahul.quiz.QuestionFragment.timetext_s;

public class QuizActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static int pos=0;
    public static Menu m;
    public static CountDownTimer countDownTimer=null;
    public static FragmentManager fragmentManager;
    MyBroadcast broadcast;
    static Activity activity1;
   static Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        pos=0;
         toolbar = findViewById(R.id.toolbar_frag);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Marked Questions");
activity1=this;
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        m=navigationView.getMenu();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction().add(R.id.quiz_frame,new QuizCategoryFragment());
        transaction.commit();
        navigationView.setNavigationItemSelectedListener(this);
        toolbar.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        Fragment fragment=fragmentManager.findFragmentById(R.id.quiz_frame);
        if(fragment instanceof QuestionFragment)
        {
            Fragment prev = fragmentManager.findFragmentByTag("dialog");
            DialogFragment dialogFragment = new QuizDialog();
            dialogFragment.show(fragmentManager.beginTransaction(), "dialog");
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drawer_2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_finish) {
            if(fragmentManager.findFragmentById(R.id.quiz_frame) instanceof QuestionFragment)
                quizfinish();
            else
                Toast.makeText(getApplicationContext(),"Start Quiz First",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        String s[]=item.getTitle().toString().split(" ");
        int position=Integer.parseInt(s[1]);
        pos=position-1;
        //fragmentManager.beginTransaction().replace(R.id.quiz_frame, new QuestionFragment()).commit();
        AllQuestion();
        DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;

    }

public static  void CountDownTimer(long milli)
    {
        countDownTimer=new CountDownTimer(milli,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timetext_s.setText((millisUntilFinished/1000)%60+"");
                timetext_m.setText(((millisUntilFinished/(1000*60))%60)+"");
                timetext_h.setText((((millisUntilFinished/(1000*60*60))%24)+""));
            }

            @Override
            public void onFinish() {
               quizfinish();
            }
        }.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        broadcast=new MyBroadcast();
        IntentFilter filter=new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(broadcast,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcast);
    }
}
