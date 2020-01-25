package in.daaconline.doomshell.rahul.videos;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daac.R;


public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeHolder> {
    Context c;
    public YoutubeAdapter(Context context)
    {
        c=context;
    }
    @NonNull
    @Override
    public YoutubeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(c).inflate(R.layout.quiz_layout_category,viewGroup,false);
        return new YoutubeHolder(v,c);
    }

    @Override
    public void onBindViewHolder(@NonNull YoutubeHolder youtubeHolder, int i) {
        youtubeHolder.textView.setText(YoutubeCategory.playlists.get(i).getCatname());
    }

    @Override
    public int getItemCount() {
        return YoutubeCategory.playlists.size();
    }
}
