package ads.vilhena.ifro.edu.br.Tarefas;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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

public class CadastroTarefaActivity extends AppCompatActivity {
    private Calendar dataHora = Calendar.getInstance();
    private TextInputLayout txtDescricao;
    private TextView txtData;
    private TextView txtHora;
    private Button btnSalvar;
    private FloatingActionButton fabCadastrarTarefa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_tarefa);

        this.definirCampoData();
        this.definirCampoHora();
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
}
