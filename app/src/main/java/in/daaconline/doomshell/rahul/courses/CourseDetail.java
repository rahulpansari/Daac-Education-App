package in.daaconline.doomshell.rahul.courses;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import com.example.daac.R;
import in.daaconline.doomshell.rahul.broadcast.MyBroadcast;
import in.daaconline.doomshell.rahul.retrofit.RetrofitApi;
import in.daaconline.doomshell.rahul.retrofit.RetrofitMethod;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


import static in.daaconline.doomshell.rahul.mainscreen.Welcome.u_id;

public class CourseDetail extends AppCompatActivity  implements View.OnClickListener {
ImageView image;
WebView webview;
String msg,cname;
String course_id;
String my_uid=u_id;
Button button;
Intent i;
MyBroadcast broadcast;

    boolean isbind=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        button=findViewById(R.id.course_detail_apply);
        cname=getIntent().getStringExtra("cname");
        Window window=getWindow();
        window.setStatusBarColor(getResources().getColor(android.R.color.transparent));
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        course_id=getIntent().getStringExtra("course_id");
        image=findViewById(R.id.image_course);
        Glide.with(getApplicationContext()).asDrawable().load(getIntent().getStringExtra("image")).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        }).into(image);
        webview=findViewById(R.id.webview);
        webview.loadData(getIntent().getStringExtra("webview"), "text/html; charset=utf-8", "UTF-8");
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Retrofit retrofit = RetrofitMethod.RetrofitMethod(RetrofitApi.Login_url);
        RetrofitApi api = retrofit.create(RetrofitApi.class);
        final Observable<CourseDetailGET> getSlider=api.getCourseDetailGET(my_uid,course_id);
        getSlider.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CourseDetailGET>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CourseDetailGET slideList) {
                        msg=slideList.getMessage();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();

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
    protected void onStop() {
        super.onStop();

        }

    }


