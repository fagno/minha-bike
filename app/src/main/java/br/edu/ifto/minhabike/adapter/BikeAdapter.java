package br.edu.ifto.minhabike.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import br.edu.ifto.minhabike.BikeActivity;
import br.edu.ifto.minhabike.ConsultaServicoActivity;
import br.edu.ifto.minhabike.R;
import br.edu.ifto.minhabike.ServicoCadastroActivity;
import br.edu.ifto.minhabike.entity.Bicicleta;

public class BikeAdapter extends BaseAdapter {

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    LayoutInflater inflater;
    ArrayList<Bicicleta> bicicletaArrayList;
    Context context;

    public BikeAdapter(Context context, ArrayList<Bicicleta> bicicletaArrayList) {
        this.context = context;
        this.bicicletaArrayList = bicicletaArrayList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return bicicletaArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return bicicletaArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final Bicicleta bike = bicicletaArrayList.get(position);

        convertView = inflater.inflate(R.layout.card_bike_main, null);
        final View finalConvertView = convertView;

        ((TextView) convertView.findViewById(R.id.idCardModelo)).setText(bike.getModelo());
        ((TextView) convertView.findViewById(R.id.idCardMarca)).setText(bike.getMarca());
        ((TextView) convertView.findViewById(R.id.idCardTipo)).setText(bike.getTipo());

        ((LinearLayout) convertView.findViewById(R.id.idAddSevico)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ServicoCadastroActivity.class);
                intent.putExtra("modeloBike", bike.getModelo());
                intent.putExtra("nomeBike", bike.getNome());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                view.getContext().startActivity(intent);
            }
        });

        ((LinearLayout) convertView.findViewById(R.id.idVerServicos)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ConsultaServicoActivity.class);
                intent.putExtra("modeloBike", bike.getModelo());
                intent.putExtra("tipoBike", bike.getTipo());
                intent.putExtra("marcaBike", bike.getMarca());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                view.getContext().startActivity(intent);
            }
        });

        ((ImageView) convertView.findViewById(R.id.idDetalhes)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Intent intent = new Intent(view.getContext(), BikeActivity.class);
                intent.putExtra("bikeNome", bike.getNome());
                intent.putExtra("bikeModelo", bike.getModelo());
                intent.putExtra("bikeTipo", bike.getTipo());
                intent.putExtra("bikePeso", bike.getPeso());
                intent.putExtra("bikeNotas", bike.getNotas());
                intent.putExtra("bikeMarca", bike.getMarca());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                view.getContext().startActivity(intent);

            }
        });


        return convertView;
    }

    

}
