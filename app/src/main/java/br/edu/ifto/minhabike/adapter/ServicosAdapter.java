package br.edu.ifto.minhabike.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import br.edu.ifto.minhabike.R;
import br.edu.ifto.minhabike.entity.Servico;

public class ServicosAdapter extends RecyclerView.Adapter<ServicosAdapter.MyViewHolder> {

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    ArrayList<Servico> servicosArrayList;
    Context context;

    public ServicosAdapter(ArrayList<Servico> servicosArrayList, Context context) {
        this.servicosArrayList = servicosArrayList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtServico,txtData,txtAcessorio,txtcomponente,txtKm,txtValor,txtDescricao;
        ImageView imgEditar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtServico = itemView.findViewById(R.id.idCardServicoServico);
            txtData = itemView.findViewById(R.id.idCardServicoData);
            txtcomponente = itemView.findViewById(R.id.idCardServicoComponente);
            txtKm = itemView.findViewById(R.id.idCardServicoKm);
            txtValor = itemView.findViewById(R.id.idCardServicoValor);
            txtAcessorio = itemView.findViewById(R.id.idCardServicoAcessorio);
            txtDescricao = itemView.findViewById(R.id.idCardServicoDescricao);
            imgEditar = itemView.findViewById(R.id.imgEditarServico);
        }
    }

    @NonNull
    @Override
    public ServicosAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_servicos, parent, false);
        return new ServicosAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicosAdapter.MyViewHolder holder, final int position) {
        holder.txtServico.setText(servicosArrayList.get(position).getTipo());
        holder.txtAcessorio.setText(servicosArrayList.get(position).getAcessorio());
        holder.txtcomponente.setText(servicosArrayList.get(position).getComponenteTroca());
        holder.txtData.setText(servicosArrayList.get(position).getData());
        holder.txtDescricao.setText(servicosArrayList.get(position).getDescricao());
        holder.txtKm.setText(servicosArrayList.get(position).getKmAtual());
        holder.txtValor.setText(servicosArrayList.get(position).getValor());
        holder.imgEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }

    @Override
    public int getItemCount() {
        return servicosArrayList.size();
    }


}
