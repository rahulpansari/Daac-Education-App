package in.daaconline.doomshell.rahul.mainscreen;

import android.content.Context;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
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


import static in.daaconline.doomshell.rahul.mainscreen.Welcome.slider;

public class MainActivity extends AppCompatActivity {

    final int INTERNET_PERMISSION = 101;


    // **************Array list declaration unless it will show null pinter exception**********************

    RecyclerView recyclerView;
    int images[] = {R.drawable.courses_icon, R.drawable.interview_icon, R.drawable.video_icon,R.drawable.quiz_icon,R.drawable.find_job, R.drawable.contact_us,R.drawable.our_team,R.drawable.logout};
    String text[] = {"Courses", "Interview Questions", "Free Videos","Quiz","Find a Job","Contact Us","Our Team","Log Out"};
    // int color[]={R.color.grid1,R.color.grid2,R.color.grid3,R.color.grid4,R.color.grid2};
Context x;

    ArrayList<MyObjectGrid> a = new ArrayList<MyObjectGrid>();
ProgressBar progress_slider;
    public static SliderLayout mDemoSlider;
MyBroadcast broadcast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        x=this;
        setContentView(R.layout.activity_main);
        Window window=getWindow();
        window.setStatusBarColor(getResources().getColor(android.R.color.transparent));
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        final Resources resources = getResources();
        mDemoSlider = findViewById(R.id.slider);
        progress_slider=findViewById(R.id.progress_slider);
        progress_slider.setVisibility(View.VISIBLE);
        Retrofit retrofit = RetrofitMethod.RetrofitMethod(RetrofitApi.Login_url);
        RetrofitApi api = retrofit.create(RetrofitApi.class);
        Observable<SliderGET> getSlider=api.getSliderGET();
        getSlider.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SliderGET>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SliderGET slideList) {
                        slider = new ArrayList<>(slideList.getBody());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        progress_slider.setVisibility(View.GONE);
                        for (int i = 0; i < slider.size(); i++) {
                            // Toast.makeText(this,slider.size()+"",Toast.LENGTH_SHORT).show();
                            TextSliderView textSliderView = new TextSliderView(getApplicationContext());
                            textSliderView
                                    .description(slider.get(i).getDesc())
                                    .image(slider.get(i).getImage())
                                    .setScaleType(BaseSliderView.ScaleType.Fit);

                            mDemoSlider.addSlider(textSliderView);

                        }


                        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
                        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
                        mDemoSlider.setDuration(4000);

                    }
                });

        for (int i = 0; i < images.length; i++) {
            a.add(new MyObjectGrid(images[i], text[i]));
        }



        recyclerView = findViewById(R.id.recycler);





        MyCustomAdapter adapter = new MyCustomAdapter(this, a, resources);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

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



class MyObjectGrid
{
    int img;
    String desc;


    public MyObjectGrid(int img,String desc)
    {
        this.img=img;
        this.desc=desc;

    }
}
