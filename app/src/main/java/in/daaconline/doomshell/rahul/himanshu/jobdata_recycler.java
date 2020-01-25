package in.daaconline.doomshell.rahul.himanshu;

import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.slider.library.SliderLayout;
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

import static in.daaconline.doomshell.rahul.mainscreen.Welcome.u_id;


public class jobdata_recycler extends AppCompatActivity {
    RecyclerView r1;
    RecyclerView.LayoutManager lmanager;
    private RecycleAdaptor Adapter;
    MyBroadcast broadcast;
    ArrayList<JobSliderGET.Body> jobsliderarray=new ArrayList<>();
    public static SliderLayout jobDemoSlider;
  public static  ArrayList<AllJobs.Models> jobslist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobdata_recycler);

        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Find Job");
        toolbar.setTitleTextColor(getResources().getColor(R.color.status_welcome));


    }

    @Override
    protected void onResume() {
        super.onResume();
        broadcast=new MyBroadcast();
        IntentFilter filter=new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(broadcast,filter);

        Retrofit retrofit2 = RetrofitMethod.RetrofitMethod(RetrofitApi.Login_url);
        RetrofitApi api2 = retrofit2.create(RetrofitApi.class);
        final Observable<AllJobs> getSlider2 = api2.getAllJobList(u_id);
        getSlider2.subscribeOn(Schedulers.io())
                .

                        observeOn(AndroidSchedulers.mainThread())
                .

                        subscribe(new Observer<AllJobs>() {
                            @Override
                            public void onSubscribe (Disposable d){

                            }

                            @Override
                            public void onNext (AllJobs slideList){
                                jobslist = new ArrayList<>(slideList.getJobList());
                            }

                            @Override
                            public void onError (Throwable e){

                            }

                            @Override
                            public void onComplete () {


                                r1 = findViewById(R.id.jabdatarecycler);
                                lmanager = new LinearLayoutManager(getApplicationContext());
                                r1.setLayoutManager(lmanager);
                                Adapter = new RecycleAdaptor(jobslist, getApplicationContext());
                                r1.setAdapter(Adapter);
                            }


                            //   super.onCreate(savedInstanceState);
                            //setContentView(R.layout.activity_slider);


                        });
    }
}
