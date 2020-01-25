package in.daaconline.doomshell.rahul.quiz;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.daac.R;

import in.daaconline.doomshell.rahul.retrofit.RetrofitApi;
import in.daaconline.doomshell.rahul.retrofit.RetrofitMethod;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;



public class QuizCategoryFragment extends Fragment {
    RecyclerView recyclerfragment;
    public static ProgressBar progress;
    Context c;
    Toolbar toolbar;
    static ArrayList<QuizCategoryGET.Output> output=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.quiz_category,container,false);
        c=v.getContext();
        recyclerfragment=v.findViewById(R.id.quiz_category_recycler);
        progress=v.findViewById(R.id.progressBar);
        toolbar=v.findViewById(R.id.toolbar);
        toolbar.setTitle("Select Topic");
        toolbar.setTitleTextColor(getResources().getColor(R.color.status_welcome));
        //  setSupportActionBar(toolbar);
        progress.setVisibility(View.VISIBLE);
        Retrofit retrofit = RetrofitMethod.RetrofitMethod(RetrofitApi.Login_url);
        RetrofitApi api = retrofit.create(RetrofitApi.class);
        Observable<QuizCategoryGET> getSlider=api.getQuestionCategoryGET();
        getSlider.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QuizCategoryGET>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(QuizCategoryGET slideList) {
                        output = new ArrayList<>(slideList.getOutput());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        progress.setVisibility(View.GONE);
                        RecyclerView.LayoutManager manager=new GridLayoutManager(c,2);
                        recyclerfragment.setLayoutManager(manager);
                        QuizCategoryAdapter adapter=new QuizCategoryAdapter(c);
                        recyclerfragment.setAdapter(adapter);

                    }
                });


        return v;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }

}