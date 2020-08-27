package br.edu.ifto.minhabike.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import br.edu.ifto.minhabike.MainActivity;
import br.edu.ifto.minhabike.R;
import br.edu.ifto.minhabike.ServicoCadastroActivity;
import br.edu.ifto.minhabike.entity.Servico;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ServicosAdapter extends RecyclerView.Adapter<ServicosAdapter.MyViewHolder> {

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    ArrayList<Servico> servicosArrayList;
    Context context;

    public ServicosAdapter(ArrayList<Servico> servicosArrayList, Context context) {
        this.servicosArrayList = servicosArrayList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtServico,txtData,txtKm,txtValor,txtDescricao;
        ImageView imgEditar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtServico = itemView.findViewById(R.id.idCardServicoServico);
            txtData = itemView.findViewById(R.id.idCardServicoData);
            txtKm = itemView.findViewById(R.id.idCardServicoKm);
            txtValor = itemView.findViewById(R.id.idCardServicoValor);
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
        holder.txtData.setText(String.valueOf(servicosArrayList.get(position).getData()));
        holder.txtDescricao.setText(servicosArrayList.get(position).getDescricao());
        holder.txtKm.setText(servicosArrayList.get(position).getKmAtual());
        holder.txtValor.setText(servicosArrayList.get(position).getValor());
        holder.imgEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), ServicoCadastroActivity.class);
//                intent.putExtra("servicoBike",servicosArrayList.get(position).getBikeServico());
//                intent.putExtra("servicoData",servicosArrayList.get(position).getData());
//                intent.putExtra("servicoDescricao",servicosArrayList.get(position).getDescricao());
//                intent.putExtra("servicoKm",servicosArrayList.get(position).getKmAtual());
//                intent.putExtra("servicoValor",servicosArrayList.get(position).getValor());
//                intent.putExtra("nomeBike",servicosArrayList.get(position).getBikeNome());
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                view.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return servicosArrayList.size();
    }


}
