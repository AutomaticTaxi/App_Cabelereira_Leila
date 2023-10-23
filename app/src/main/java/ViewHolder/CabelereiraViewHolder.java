package ViewHolder;



import android.view.View;
import android.widget.ImageButton;
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
    ImageButton BtExcluir;
    ImageButton BtEditar;
    public CabelereiraViewHolder(@NonNull View itemView) {
        super(itemView);
         this.textView1 = itemView.findViewById(R.id.TextViewRecycleView2);

         this.textView2 = itemView.findViewById(R.id.TextView2RecycleView2);

        this.ratingBar =  itemView.findViewById(R.id.ratingBarRecycleView2);
        this.BtEditar =  itemView.findViewById(R.id.ItemBtEditar);
        this.BtExcluir = itemView.findViewById(R.id.ItemBtExcluir);

    }

    public void bind(Cabelereiras cabelereiras, OnListClick listener, int position){
        this.textView1.setText("Nome: " + cabelereiras.getNome().toString());
        this.textView2.setText("Telefone: " + cabelereiras.getTelefone().toString());
        this.ratingBar.setRating(cabelereiras.getAvaliacao().floatValue());
        this.BtExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickDelete(cabelereiras,position);

            }
        });
        this.BtEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickEditar(cabelereiras,position);


                }


        });
    }
}
