package ads.vilhena.ifro.edu.br.Tarefas;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import ads.vilhena.ifro.edu.br.Tarefas.DAO.AppDatabase;
import ads.vilhena.ifro.edu.br.Tarefas.model.Tarefa;

public class CadastroTarefaActivity extends AppCompatActivity {
    private Calendar dataHora = Calendar.getInstance();
    private TextInputLayout txtDescricao;
    private TextView txtData;
    private TextView txtHora;
    private Button btnSalvar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_tarefa);

        this.inicializarComponentes();
        this.definirCampoData();
        this.definirCampoHora();
    }

    private void inicializarComponentes() {
        this.txtDescricao = findViewById(R.id.cadastrar_tarefas_txt_descricao);
        this.txtData = findViewById(R.id.cadastrar_tarefas_txt_data);
        this.txtHora = findViewById(R.id.cadastrar_tarefas_txt_hora);
        this.btnSalvar = findViewById(R.id.cadastrar_tarefas_btn_salvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarDescricao()) {
                    Tarefa tarefa = new Tarefa();
                    tarefa.setDescricao(txtDescricao.getEditText().getText().toString().trim());
                    tarefa.setDataHora(dataHora.getTimeInMillis());
                    AppDatabase.getAppDatabase(CadastroTarefaActivity.this).tarefaDAO().inserir(tarefa);
                    Intent intent = new Intent();
                    intent.putExtra("resposta", "OK");
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    private void definirCampoHora() {
        final TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                dataHora.set(Calendar.HOUR, hourOfDay);
                dataHora.set(Calendar.MINUTE, minute);
                SimpleDateFormat formatacao = new SimpleDateFormat("HH:mm");
                txtHora.setText(formatacao.format(dataHora.getTime()));
            }
        };
        txtHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(CadastroTarefaActivity.this, t, dataHora.get(Calendar.HOUR), dataHora.get(Calendar.MINUTE), true).show();
            }
        });
    }

    private void definirCampoData() {
        final DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dataHora.set(Calendar.YEAR, year);
                dataHora.set(Calendar.MONTH, month);
                dataHora.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat formatacao = new SimpleDateFormat("dd/MM/yyyy");
                txtData.setText(formatacao.format(dataHora.getTime()));
            }
        };

        txtData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CadastroTarefaActivity.this, d, dataHora.get(Calendar.YEAR), dataHora.get(Calendar.MONTH), dataHora.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public boolean validarDescricao(){
        if (txtDescricao.getEditText().getText().toString().trim().equals("")){
            txtDescricao.setError("A descrição da tarefa não pode estar em branco!");
            return false;
        }
        txtDescricao.setError(null);
        return true;
    }
}
