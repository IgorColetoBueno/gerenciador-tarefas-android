package ads.vilhena.ifro.edu.br.Tarefas;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ads.vilhena.ifro.edu.br.Tarefas.DAO.AppDatabase;
import ads.vilhena.ifro.edu.br.Tarefas.model.Tarefa;

public class ListarTarefasMainActivity extends AppCompatActivity {

    private ListView listView;
    private List<Tarefa> tarefas;
    private FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_tarefas);
        this.initializeComponents();

        tarefas =  new ArrayList<Tarefa>();

        ArrayAdapter<Tarefa> adapter = new ArrayAdapter<Tarefa>(this, android.R.layout.simple_list_item_1, tarefas);
        this.listView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        tarefas = AppDatabase.getAppDatabase(this).tarefaDAO().listarTodos();
        ListarTarefasAdapter adapter = new ListarTarefasAdapter(tarefas, this);
        listView.setAdapter(adapter);
    }


    private void initializeComponents() {

        this.listView = findViewById(R.id.listview_tarefas);
        this.btnAdd = findViewById(R.id.listar_tarefas_btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListarTarefasMainActivity.this, CadastroTarefaActivity.class);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarTarefasMainActivity.this, CadastroTarefaActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1){
            Snackbar.make(findViewById(R.id.listview_tarefas), "Tarefa cadastrada com sucesso!", Snackbar.LENGTH_LONG).show();
        }
    }
}
