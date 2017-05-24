package francocompany.fruitworld.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import francocompany.fruitworld.R;
import francocompany.fruitworld.adapters.myAdaptador1;
import francocompany.fruitworld.models.Frutas;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView lVFruta;
    private GridView gVFruta;
    private myAdaptador1 adaptadorListView;
    private myAdaptador1 adaptadorGirdView;
    private MenuItem iconoListView;
    private MenuItem iconoGridView;
    private MenuItem nombre_de_fruta;
    //Lista modelo
   private List<Frutas> frutas;

    private int CAMBIAR_A_LIST_VIEW=1;
    private int CAMBIAR_A_GRID_VIEW=0;

    private int contador=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Casteo
        lVFruta=(ListView)findViewById(R.id.lv_Frutas);
        gVFruta=(GridView)findViewById(R.id.gv_Frutas);

        //Action bar configuracion
        this.forzarIcono();

        //Llamamos al metodo para obtener las frutas de la clase Fruta
        this.obtenerfrutas();
        //Le designamos a la lista de List<Frutas> las frutas que tiene que cargar
        this.frutas=obtenerfrutas();


        //Adaptadores para cada View
        this.adaptadorListView = new myAdaptador1(this,R.layout.list_item_frutas,frutas);
        this.lVFruta.setAdapter(adaptadorListView);

        this.adaptadorGirdView = new myAdaptador1(this,R.layout.grid_item_frutas,frutas);
        this.gVFruta.setAdapter(adaptadorGirdView);

        //Configuramos los clicks en cada vista
        this.gVFruta.setOnItemClickListener(this);
        this.lVFruta.setOnItemClickListener(this);
        //Registramos el COntext Menu
        registerForContextMenu(lVFruta);
        registerForContextMenu(gVFruta);
    }


    //Forzar icono
    public void forzarIcono(){
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_fruta);
    }

    // CRUD action: crear añadir, borrar

    //Cargamos las frutas en el array de Lis<Frutas> utilizando la clase frutas
    public List<Frutas> obtenerfrutas(){
        List<Frutas> lista= new ArrayList<Frutas>(){
            {
                add(new Frutas("Banana",R.mipmap.ic_banana,"Cordoba"));
                add(new Frutas("Manzana",R.mipmap.ic_manzana,"Mendoza"));
                add(new Frutas("Cereza",R.mipmap.ic_cerezas,"Buenos Aires"));
                add(new Frutas("Naranja",R.mipmap.ic_naranja,"San Luis"));
                add(new Frutas("Pera",R.mipmap.ic_pera,"Tucuman"));

            }};
        return lista;
    }


//Click en las frutas
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.clickFruta(frutas.get(position));
    }
    public void clickFruta(Frutas frutas) {
        if(frutas.getOrigen().equals("Desconocido")){
            Toast.makeText(this, "Se desconoce el origen.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "La mejor fruta de: "+frutas.getOrigen()+" es "+frutas.getNombre(), Toast.LENGTH_SHORT).show();
        }
    }

    //inflamos el menu de contexto y luego ponemos las acciones del menu de contexto
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getMenuInflater();
        //Andtes de inflar obtenemos el nombre de la fruta para el titulo
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.frutas.get(info.position).getNombre());
        //Inflamos el contextMenu
        inflater.inflate(R.menu.menu_de_contexto,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //Para obtener la informacion de context menu del objeto que seleccione
        AdapterView.AdapterContextMenuInfo info =(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.borrar_fruta:
                 this.borrarFruta(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    // Configuracion de la Action Bar

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflamos el menu
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.action_bar_add,menu);
        //Luego de inflar buscamos la referencia de los objetos
        this.iconoListView= menu.findItem(R.id.ic_list_view);
        this.iconoGridView=menu.findItem(R.id.ic_grid_view);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Para los diferentes iconos
        switch (item.getItemId()){
            case R.id.add_fruta:
                this.añadirFruta(new Frutas("Fruta Nueva Nº: "+(++contador),R.mipmap.ic_fruta_nueva,"Desconocido"));
                return true;
            case R.id.ic_list_view:
                this.cambiarListGridView(this.CAMBIAR_A_LIST_VIEW);
                return true;
            case R.id.ic_grid_view:
                this.cambiarListGridView(this.CAMBIAR_A_GRID_VIEW);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


// para realizar el cambio de List View a Grid View
    public void cambiarListGridView(int opcion){
        if(opcion==CAMBIAR_A_LIST_VIEW){
           if(this.lVFruta.getVisibility()== View.INVISIBLE){ // Checkea si esta visible o invisible
               //al listview lo pongo visible y escondo el icono
               this.lVFruta.setVisibility(View.VISIBLE);
               this.iconoListView.setVisible(false);
               //al gridview lo hacmeos invisible y muestro el icono
               this.gVFruta.setVisibility(View.INVISIBLE);
               this.iconoGridView.setVisible(true);
           }
        }
        if(opcion == CAMBIAR_A_GRID_VIEW){
            if(this.gVFruta.getVisibility()==View.INVISIBLE){
                this.gVFruta.setVisibility(View.VISIBLE);
                this.iconoGridView.setVisible(false);
                //para el list
                this.lVFruta.setVisibility(View.INVISIBLE);
                this.iconoListView.setVisible(true);
            }
        }
    }

    //Para añadir o borrar frutas

    public void añadirFruta(Frutas fruta){
        this.frutas.add(fruta);
        //Avisamos a los adaptadores que se añadio una fruta
        this.adaptadorListView.notifyDataSetChanged();
        this.adaptadorGirdView.notifyDataSetChanged();
    }
    public void borrarFruta(int position){
        this.frutas.remove(position);

        this.adaptadorGirdView.notifyDataSetChanged();
        this.adaptadorListView.notifyDataSetChanged();
    }
}




