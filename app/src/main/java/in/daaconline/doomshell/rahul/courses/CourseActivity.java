package in.daaconline.doomshell.rahul.courses;

import android.content.IntentFilter;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;

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



public class CourseActivity extends AppCompatActivity {
RecyclerView recyclerView1,recyclerView2,recyclerView3,recyclerView4,recyclerView5,recyclerView6,recyclerView7;

    static ArrayList<CoursesGET.Body.CourseDesc> accounts = new ArrayList<CoursesGET.Body.CourseDesc>();
    static ArrayList<CoursesGET.Body.CourseDesc> advance = new ArrayList<>();
    static ArrayList<CoursesGET.Body.CourseDesc> degree = new ArrayList<CoursesGET.Body.CourseDesc>();
    public static ArrayList<CoursesGET.Body.CourseDesc> designing = new ArrayList<CoursesGET.Body.CourseDesc>();
    static ArrayList<CoursesGET.Body.CourseDesc> mobile = new ArrayList<CoursesGET.Body.CourseDesc>();
    static ArrayList<CoursesGET.Body.CourseDesc> programming = new ArrayList<CoursesGET.Body.CourseDesc>();
    static ArrayList<CoursesGET.Body.CourseDesc> security = new ArrayList<CoursesGET.Body.CourseDesc>();
    MyBroadcast broadcast;
ProgressBar progress_course;
static String c_id=null;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    Window window=getWindow();
    window.setStatusBarColor(getResources().getColor(android.R.color.background_dark));
        setContentView(R.layout.activity_course);
    progress_course=findViewById(R.id.progress_course);
    progress_course.setVisibility(View.VISIBLE);
        recyclerView1=findViewById(R.id.recycler_view1);
        recyclerView2=findViewById(R.id.recycler_view2);
        recyclerView3=findViewById(R.id.recycler_view3);
        recyclerView4=findViewById(R.id.recycler_view4);
        recyclerView5=findViewById(R.id.recycler_view5);
        recyclerView6=findViewById(R.id.recycler_view6);
        recyclerView7=findViewById(R.id.recycler_view7);
        RecyclerView.LayoutManager manager1=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager manager2=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager manager3=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager manager4=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager manager5=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager manager6=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager manager7=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setLayoutManager(manager1);
        recyclerView2.setLayoutManager(manager2);
        recyclerView3.setLayoutManager(manager3);
        recyclerView4.setLayoutManager(manager4);
        recyclerView5.setLayoutManager(manager5);
        recyclerView6.setLayoutManager(manager6);
        recyclerView7.setLayoutManager(manager7);

      final CourseAdapterAccounts adapter1=new CourseAdapterAccounts(this);
      final CourseAdapterAdvance adapter2=new CourseAdapterAdvance(this);
      final CourseAdapterDegree adapter3=new CourseAdapterDegree(this);
      final CourseAdapterDesigning adapter4=new CourseAdapterDesigning(this);
      final CourseAdapterMobile adapter5=new CourseAdapterMobile(this);
       final CourseAdapterProgramming adapter6=new CourseAdapterProgramming(this);
      final CourseAdapterSecurity adapter7=new CourseAdapterSecurity(this);
        Retrofit retrofit = RetrofitMethod.RetrofitMethod(RetrofitApi.Login_url);
        RetrofitApi api = retrofit.create(RetrofitApi.class);
        Observable<CoursesGET> getSlider=api.getCoursesGET();
        getSlider.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CoursesGET>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CoursesGET slideList) {
                        accounts = slideList.getBody().getAccounts();
                        advance = slideList.getBody().getAdvance();
                        degree = slideList.getBody().getDegree();
                        designing = slideList.getBody().getDesigning();
                        mobile = slideList.getBody().getMobile();
                        programming = slideList.getBody().getProgramming();
                        security = slideList.getBody().getSecurity();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                        recyclerView1.setAdapter(adapter1);
                        recyclerView2.setAdapter(adapter2);
                        recyclerView3.setAdapter(adapter3);
                        recyclerView4.setAdapter(adapter4);
                        recyclerView5.setAdapter(adapter5);
                        recyclerView6.setAdapter(adapter6);
                        recyclerView7.setAdapter(adapter7);
                        progress_course.setVisibility(View.GONE);
                        }});



    }

    @Override
    protected void onResume() {
        super.onResume();
        broadcast=new MyBroadcast();
        IntentFilter filter=new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(broadcast,filter);

    }

    @Override
    protected void onStop() {
        super.onStop();

        //unregisterReceiver(broadCast);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcast);
    }
}
