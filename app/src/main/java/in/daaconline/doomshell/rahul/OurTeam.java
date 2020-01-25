package in.daaconline.doomshell.rahul;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.IntentFilter;
import android.os.Bundle;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.daac.R;
import in.daaconline.doomshell.rahul.broadcast.MyBroadcast;
import in.daaconline.doomshell.rahul.retrofit.RetrofitApi;
import in.daaconline.doomshell.rahul.retrofit.RetrofitMethod;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class OurTeam extends AppCompatActivity  {
ProgressBar progress;
    RecyclerView ourteam;
    RecyclerView.LayoutManager manager;
    OurTeamAdapter adapter;
    TextView team;
    MyBroadcast broadcast;
public static ArrayList<TeamGET.Output> output=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_team);
        ourteam=findViewById(R.id.recycler_ourteam);


        Toolbar toolbar=findViewById(R.id.toolbar_team);
        toolbar.setTitle("Our Team");
        toolbar.setTitleTextColor(getResources().getColor(R.color.status_welcome));
       adapter=new OurTeamAdapter(getApplicationContext());
      manager=new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        Retrofit retrofit = RetrofitMethod.RetrofitMethod(RetrofitApi.Login_url);
        RetrofitApi api = retrofit.create(RetrofitApi.class);
        Observable<TeamGET> getSlider=api.getTeamGET();
        getSlider.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TeamGET>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TeamGET slideList) {

                        output=slideList.getOutput();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {




                        ourteam.setLayoutManager(manager);
                        ourteam.setAdapter(adapter);


                        }




                });



    }

    @Override
    protected void onResume() {
        super.onResume();
        broadcast=new MyBroadcast();
        IntentFilter filter=new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(broadcast,filter);
    }

    protected void onPause()
    { super.onPause();
        unregisterReceiver(broadcast);
    }
}
