package in.daaconline.doomshell.rahul.himanshu;
import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
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

import static in.daaconline.doomshell.rahul.mainscreen.Welcome.u_id;

public class RecycleAdaptor extends RecyclerView.Adapter <RecycleAdaptor.MyViewHolder>
{int msg=0;
    ArrayList<AllJobs.Models> joblist;
    private Context context;

    public RecycleAdaptor(ArrayList<AllJobs.Models> mList, Context context)
    {
        this.joblist = mList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.job_custom_layout, viewGroup, false);
        return new MyViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder vholder, int position) {
        vholder.company.setText(joblist.get(position).getCompanyName());

        vholder.vacancy.setText(joblist.get(position).getVacancy());
        vholder.contact.setText(joblist.get(position).getContact());
        // vholder.timestamp.setText(joblist.get(position).getTimeStamp());
        vholder.location.setText(joblist.get(position).getLocation());
        vholder.postdate.setText(joblist.get(position).getPostDate());
vholder.apply.setText(joblist.get(position).getStatus());
if(vholder.apply.getText().toString().equals("Applied"))
{
    vholder.apply.setEnabled(false);
}
else
{
    vholder.apply.setEnabled(true);
}
      Glide.with(context).asDrawable()
                .load(joblist.get(position).getLogo())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        vholder.logoimage.setImageResource(R.drawable.loader);
                        //vholder.progress.setVisibility(View.GONE);
                        vholder.logoimage.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        //vholder.progress.setVisibility(View.GONE);
                        vholder.logoimage.setVisibility(View.VISIBLE);
                        return false;
                    }
                })
                .into(vholder.logoimage);

    }

        @Override
        public int getItemCount ()
        {
            return joblist.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            Context context;
            TextView company, vacancy, timestamp, postdate, contact, location;
            Button apply;
          ImageView logoimage;
          public MyViewHolder(View itemView,Context context) {
                super(itemView);
                this.context = context;
                company = itemView.findViewById(R.id.txtcompany);
              vacancy = itemView.findViewById(R.id.txtvacancy);
              postdate = itemView.findViewById(R.id.txtpostdate);
              contact = itemView.findViewById(R.id.txtcontact);
              location = itemView.findViewById(R.id.txtlocation);
             apply=itemView.findViewById(R.id.btnapply);
             logoimage = itemView.findViewById(R.id.imageView);
               // itemView.setOnClickListener(this);
              apply.setOnClickListener(this);
          }
            @Override
            public void onClick(View v)
            {
                Retrofit retrofit = RetrofitMethod.RetrofitMethod(RetrofitApi.Login_url);
                RetrofitApi api = retrofit.create(RetrofitApi.class);

                Observable<Apply> getSlider=  api.senddata(u_id,joblist.get(getAdapterPosition()).getJobid());
                getSlider.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Apply>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Apply slideList) {
                               msg=slideList.getSuccess();
                               Toast.makeText(context,"here jkjkj",Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                if(msg==1) {
                                    Toast.makeText(context, "Successfully Applied", Toast.LENGTH_LONG).show();
                                apply.setText("Applied");
                                apply.setEnabled(false);
                                }
                                else
                                    Toast.makeText(context,"Something went wrong",Toast.LENGTH_LONG).show();
Toast.makeText(context,u_id,Toast.LENGTH_LONG).show();
                                }

                                // Intent I = new Intent(context, appliedsuccess.class);
               //context.startActivity(I);
                            });


    }
    }}
