package in.daaconline.doomshell.rahul.Interview;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class InterviewCategoryFragment extends Fragment {
    RecyclerView recyclerfragment_inter;
    public static ProgressBar progress;
    Toolbar toolbar;
    Context c;
    static ArrayList<InterviewCategoryGET.Output> output1 = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.interview_category, container, false);
        c = v.getContext();
        recyclerfragment_inter= v.findViewById(R.id.quiz_category_recycler);
        progress = v.findViewById(R.id.progressBar);
        progress.setVisibility(View.VISIBLE);
        toolbar = v.findViewById(R.id.toolbar1);
        toolbar.setTitle("Interview Preparation");
        toolbar.setTitleTextColor(getResources().getColor(R.color.status_welcome));

        Retrofit retrofit = RetrofitMethod.RetrofitMethod(RetrofitApi.Login_url);
        RetrofitApi api = retrofit.create(RetrofitApi.class);
        Observable<InterviewCategoryGET> getSlider=api.getInterviewCategoryGET();
        getSlider.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InterviewCategoryGET>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(InterviewCategoryGET slideList) {
                        output1 = new ArrayList<>(slideList.getOutput());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        progress.setVisibility(View.GONE);
                        RecyclerView.LayoutManager manager=new GridLayoutManager(c,2);
                        recyclerfragment_inter.setLayoutManager(manager);
                        InterviewCategoryAdapter adapter1=new InterviewCategoryAdapter(c);
                        recyclerfragment_inter.setAdapter(adapter1);

                    }
                });


        return v;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }

}