package in.daaconline.doomshell.rahul.mainscreen;

import android.content.Context;
import android.content.res.Resources;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daac.R;

import java.util.ArrayList;

public class MyCustomAdapter extends RecyclerView.Adapter<MyHolder> {
  Context c;

  ArrayList<MyObjectGrid> obj;
  Resources r;
   public MyCustomAdapter(Context context, ArrayList<MyObjectGrid> grid,Resources v)
   {obj=grid;
       c=context;
       r=v;
   }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View v=LayoutInflater.from(c).inflate(R.layout.grid_layout,null,false);

       MyHolder holder=new MyHolder(v,obj,c);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
            myHolder.image.setImageResource(obj.get(i).img);
            myHolder.text.setText(obj.get(i).desc);

    }

    @Override
    public int getItemCount() {
        return obj.size();
    }
}
