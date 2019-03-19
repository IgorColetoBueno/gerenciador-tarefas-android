package ads.vilhena.ifro.edu.br.Tarefas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ads.vilhena.ifro.edu.br.Tarefas.model.Tarefa;

public class ListarTarefasMainActivity extends AppCompatActivity {

    private ListView listView;
    private List<Tarefa> tarefas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_tarefas);
        this.initializeComponents();

        tarefas =  new ArrayList<Tarefa>();

        ArrayAdapter<Tarefa> adapter = new ArrayAdapter<Tarefa>(this, android.R.layout.simple_list_item_1, tarefas);
        this.listView.setAdapter(adapter);
    }

    private void initializeComponents() {
        this.listView = findViewById(R.id.listview_tarefas);
    }
}
