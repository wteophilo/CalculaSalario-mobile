package br.com.wtcode.mobile.qtorecebo;

import br.com.wtcode.mobile.qtorecebo.modelo.Desconto;
import br.com.wtcode.mobile.qtorecebo.modelo.DescontoDependente;
import br.com.wtcode.mobile.qtorecebo.modelo.Inss;
import br.com.wtcode.mobile.qtorecebo.modelo.Irrf;
import br.com.wtcode.mobile.qtorecebo.modelo.Salario;
import br.com.wtcode.mobile.qtorecebo.modelo.Transporte;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class RespostaActivity extends Activity {
	private String TAG = "Resposta-Activity";
	private TextView respPInss;
	private TextView respPIrrf;
	private TextView respPTrans;
	private TextView respDescInss;
	private TextView respDescIrrf;
	private TextView respDescTrans;
	private TextView respSalario;
	private Salario salario;
	private Desconto inss;
	private Desconto irrf;
	private Desconto dependente;
	private Transporte transporte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	setContentView(R.layout.respostacalculo);
        iniciaComponentes();
        iniciaObjetos();
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	showConsulta();
    }

	private void iniciaObjetos() {
		Intent intent = getIntent();
        salario = (Salario) intent.getSerializableExtra("salario");
        inss = (Inss) intent.getSerializableExtra("inss");
        irrf = (Irrf) intent.getSerializableExtra("irrf");
        dependente = (DescontoDependente) intent.getSerializableExtra("dependente");
        transporte = (Transporte) intent.getSerializableExtra("transporte");
	}

	private void iniciaComponentes() {
		this.respPInss = (TextView) findViewById(R.id.resPInss);
        this.respPIrrf = (TextView) findViewById(R.id.respPIrrf);
        this.respPTrans = (TextView) findViewById(R.id.resPTransporte);
        this.respDescInss = (TextView) findViewById(R.id.respDescInss);
        this.respDescIrrf = (TextView) findViewById(R.id.respDescIrrf);
        this.respDescTrans = (TextView) findViewById(R.id.respDescTrans);
        this.respSalario = (TextView) findViewById(R.id.respSalario);
	}

	private void showConsulta() {
		Log.i(TAG, "Mostrando valores");
		this.respPInss.setText(inss.getPorcentagem()+"%");
		this.respPIrrf.setText(irrf.getPorcentagem()+"%");
		if (transporte == null){
			this.respPTrans.setText("0%");
			this.respDescTrans.setText("0");
		}else{
			this.respPTrans.setText(transporte.getPorcentagem()+"%");
			this.respDescTrans.setText(transporte.getDesconto().toString());
		}
		this.respDescInss.setText(inss.getDesconto().toString());
		this.respDescIrrf.setText(irrf.getDesconto().toString());
		this.respSalario.setText(salario.getLiquido().toString());
    }

	public String getTAG() {
		return TAG;
	}

	public TextView getRespPInss() {
		return respPInss;
	}

	public TextView getRespPIrrf() {
		return respPIrrf;
	}

	public TextView getRespPTrans() {
		return respPTrans;
	}

	public TextView getRespDescInss() {
		return respDescInss;
	}

	public TextView getRespDescIrrf() {
		return respDescIrrf;
	}

	public TextView getRespDescTrans() {
		return respDescTrans;
	}

	public TextView getRespSalario() {
		return respSalario;
	}
	public Desconto getDependente() {
		return dependente;
	}
}
