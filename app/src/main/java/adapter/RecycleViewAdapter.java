package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_cabelereira_leila.R;

import java.util.List;

import Model.Cabelereiras;
import ViewHolder.CabelereiraViewHolder;

public class RecycleViewAdapter extends RecyclerView.Adapter<CabelereiraViewHolder> {
    private List<Cabelereiras> mList;
    public RecycleViewAdapter(List<Cabelereiras> list) {
        this.mList= list;
    }

    @NonNull
    @Override
    public CabelereiraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_recycleview2,parent,false);
        return new CabelereiraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CabelereiraViewHolder holder, int position) {
        Cabelereiras cabelereiras = this.mList.get(position);
        holder.bind(cabelereiras);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
