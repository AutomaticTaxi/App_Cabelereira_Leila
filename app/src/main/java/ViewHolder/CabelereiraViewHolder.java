package ViewHolder;

import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_cabelereira_leila.R;

import Model.Cabelereiras;

public class CabelereiraViewHolder extends RecyclerView.ViewHolder {
    TextView textView1;
    TextView textView2;
    RatingBar ratingBar;
    public CabelereiraViewHolder(@NonNull View itemView) {
        super(itemView);
         this.textView1 = itemView.findViewById(R.id.TextViewRecycleView2);

         this.textView2 = itemView.findViewById(R.id.TextView2RecycleView2);

        this.ratingBar =  itemView.findViewById(R.id.ratingBarRecycleView2);

    }
    public void bind(Cabelereiras cabelereiras){
        this.textView1.setText("Nome: "+cabelereiras.getNome().toString());
        this.textView2.setText("Telefone: "+cabelereiras.getTelefone().toString());
        this.ratingBar.setRating(cabelereiras.getAvaliacao().floatValue());
    }
}
