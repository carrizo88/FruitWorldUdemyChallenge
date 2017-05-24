package francocompany.fruitworld.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import francocompany.fruitworld.R;
import francocompany.fruitworld.models.Frutas;

public class myAdaptador1 extends BaseAdapter {

    //definicion de variables
    private Context contexto;
    private int layout;
    private List<Frutas> lista;

    //creo el oncreate para este adaptador
    public myAdaptador1(Context contexto, int layout, List<Frutas> lista){
        this.contexto= contexto;
        this.layout=layout;
        this.lista=lista;

    }

    @Override
    public int getCount() {
        return this.lista.size();
    }

    @Override
    public Frutas getItem(int position) {
        return this.lista.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      /*  //copiamos la vista
        View vista=convertView;

        //Modificamos la vista con el layout personalizado
        LayoutInflater layourInflater=LayoutInflater.from(this.contexto);
        vista=layourInflater.inflate(R.layout.list_item_frutas,null);
        //Nos traemos el valor actual dependiente de la position
        String nombreActual=frutas.get(position);
        String origenActual=origen.get(position);
        //Referenciamos el elemento a modificar y lo rellenamos
        TextView tv_NombreFruta=(TextView)vista.findViewById(R.id.nombre_fruta);
        tv_NombreFruta.setText(nombreActual);
        TextView tv_OrigenFruta=(TextView)vista.findViewById(R.id.origen_fruta);
        tv_OrigenFruta.setText(origenActual);
        return vista;
        */
      //View Holder Patter
        ViewHolder holder;
        if(convertView== null){
            //inflamos la vista
            LayoutInflater layoutInflater=LayoutInflater.from(this.contexto);
            convertView=layoutInflater.inflate(this.layout,null);

            holder= new ViewHolder();

           holder.nombreTextView=(TextView)convertView.findViewById(R.id.nombre_fruta);
            holder.origenTextView=(TextView)convertView.findViewById(R.id.origen_fruta);
            holder.imagenView=(ImageView)convertView.findViewById(R.id.imageFruta);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }


        final Frutas FrutaActual =getItem(position);
        holder.nombreTextView.setText(FrutaActual.getNombre());
        holder.origenTextView.setText(FrutaActual.getOrigen());
        holder.imagenView.setImageResource(FrutaActual.getImagen());
        return convertView;


    }

    static class ViewHolder{
        private TextView nombreTextView;
        private TextView origenTextView;
        private ImageView imagenView;
    }
}
