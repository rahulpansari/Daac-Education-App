package in.daaconline.doomshell.rahul.videos;

import android.content.Context;
import android.content.IntentFilter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
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



public class YoutubeCategory extends AppCompatActivity {
RecyclerView recyclerView;
Context c;
ProgressBar progress;
Toolbar ytoolbar;
    MyBroadcast broadcast;
public static ArrayList<VideoGET.Body> playlists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_youtube_category);
ytoolbar=findViewById(R.id.y_toolbar);
ytoolbar.setTitleTextColor(getResources().getColor(R.color.status_welcome));
ytoolbar.setTitle("Select Topic");

        recyclerView=findViewById(R.id.youtube_cat_recycler);
        progress=findViewById(R.id.progress_category);
        progress.setVisibility(View.VISIBLE);
        c=this;
        Retrofit retrofit = RetrofitMethod.RetrofitMethod(RetrofitApi.Login_url);
        RetrofitApi api = retrofit.create(RetrofitApi.class);
        Observable<VideoGET> getSlider=api.getVideoDetailGET();
        getSlider.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VideoGET>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VideoGET slideList) {
                        playlists=new ArrayList<>(slideList.getBody());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                        progress.setVisibility(View.GONE);
                        YoutubeAdapter adapter=new YoutubeAdapter(c);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
                        recyclerView.setAdapter(adapter);
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

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcast);
    }
}
