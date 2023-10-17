package ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_cabelereira_leila.R;

import Model.Cabelereiras;

public class CabelereiraViewHolder extends RecyclerView.ViewHolder {
    TextView t1;
    TextView t2;
    TextView t3;
    public CabelereiraViewHolder(@NonNull View itemView) {
        super(itemView);
         this.t1 = itemView.findViewById(R.id.item_nome_recycleView);

         this.t2 = itemView.findViewById(R.id.item_telefone_recycleView);

        this.t3 =  itemView.findViewById(R.id.item_avaliacao_recycleView);

    }
    public void bind(Cabelereiras cabelereiras){
        this.t1.setText(cabelereiras.Nome);
        this.t2.setText(cabelereiras.Telefone);
        this.t3.setText(String.valueOf(cabelereiras.Avaliacao));
    }
}
