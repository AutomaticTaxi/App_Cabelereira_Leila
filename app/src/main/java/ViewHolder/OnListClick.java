package ViewHolder;

import Model.Cabelereiras;

public interface OnListClick {
    void onClickDelete(Cabelereiras cabelereiras,int pos);
    void onClickEditar(Cabelereiras cabelereiras,int pos);
}
