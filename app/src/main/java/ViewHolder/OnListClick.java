package ViewHolder;

public interface OnListClick {
    void onClickDelete(String nome,String telefone, Float avaliacao);
    void onClickEditar(String nome,String telefone, Float avaliacao);
}
