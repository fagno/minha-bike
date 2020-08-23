package br.edu.ifto.minhabike.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.edu.ifto.minhabike.MainActivity;
import br.edu.ifto.minhabike.R;
import br.edu.ifto.minhabike.ServicoCadastroActivity;
import br.edu.ifto.minhabike.entity.Bicicleta;

public class BikeAdapter extends RecyclerView.Adapter<BikeAdapter.MyViewHolder> {

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    ArrayList<Bicicleta> bicicletaArrayList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtmodelo, txtmarca, txttipo;
        LinearLayout addServico, verServicos, excluirBike;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmodelo = itemView.findViewById(R.id.idCardModelo);
            txtmarca = itemView.findViewById(R.id.idCardMarca);
            txttipo = itemView.findViewById(R.id.idCardTipo);
            addServico = itemView.findViewById(R.id.idAddSevico);
            verServicos = itemView.findViewById(R.id.idVerServicos);
            excluirBike = itemView.findViewById(R.id.idExcluirBike);
        }
    }

    public BikeAdapter(Context context, ArrayList<Bicicleta> bicicletaArrayList) {
        this.context = context;
        this.bicicletaArrayList = bicicletaArrayList;
    }

    @NonNull
    @Override
    public BikeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_bike_main, parent, false);
        return new BikeAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.txtmarca.setText(bicicletaArrayList.get(position).getMarca());
        holder.txtmodelo.setText(bicicletaArrayList.get(position).getModelo());
        holder.txttipo.setText(bicicletaArrayList.get(position).getTipo());
        holder.addServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ServicoCadastroActivity.class);
                intent.putExtra("modeloBike",bicicletaArrayList.get(position).getModelo());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                view.getContext().startActivity(intent);
            }
        });
        holder.excluirBike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Query apagar = database.child("bicicleta").orderByChild("modelo").equalTo(bicicletaArrayList.get(position).getModelo());
                apagar.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ex : dataSnapshot.getChildren()) {
                            ex.getRef().removeValue();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return bicicletaArrayList.size();
    }

}
