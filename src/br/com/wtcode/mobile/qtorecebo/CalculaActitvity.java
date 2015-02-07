package br.com.wtcode.mobile.qtorecebo;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Properties;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import br.com.wtcode.mobile.qtorecebo.modelo.Desconto;
import br.com.wtcode.mobile.qtorecebo.modelo.DescontoDependente;
import br.com.wtcode.mobile.qtorecebo.modelo.Inss;
import br.com.wtcode.mobile.qtorecebo.modelo.Irrf;
import br.com.wtcode.mobile.qtorecebo.modelo.Salario;
import br.com.wtcode.mobile.qtorecebo.modelo.Transporte;

/**
 * Created by Willian on 28/01/2015.
 */
@SuppressLint("UseValueOf")
public class CalculaActitvity extends Activity implements View.OnClickListener {
	private EditText editSalarioBruto;
	private EditText editNumDependente;
	private CheckBox chTransporte;
	private EditText editOutroDesconto;
	private Button btCalcular;
	private Button btLimpar;
	private Salario salario;
	private String TAG = "CalculaActitvity";
	private Resources resources;
	private TextWatcher textWatcher;
	private Desconto inss;
	private Desconto irrf;
	private Desconto transporte;
	private Desconto dependente;
	private Properties arquivo;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calculasalario);
		initViews();
		arquivo = new Properties();
		try{
			arquivo.load(readRaw(this, R.raw.aliquotas));
		}catch (IOException e) {
			e.printStackTrace();
		}catch(NullPointerException e){
			throw new NullPointerException("Nenhum arquivo encontrado");
		}
		
	}
	
	public InputStream readRaw(Context context, int rawResId) {
	    return context.getResources().openRawResource(rawResId);
	}
	
	
	private void initViews() {
		resources = getResources();
		textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
 
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
 
            @Override
            public void afterTextChanged(Editable s) {
                callClearErrors(s);
            }
        };
		
		editSalarioBruto = (EditText) findViewById(R.id.iSalarioBruto);
		editNumDependente = (EditText) findViewById(R.id.iDependente);
		chTransporte = (CheckBox) findViewById(R.id.chTransporte);
		editOutroDesconto = (EditText) findViewById(R.id.iOutroDesconto);
		btCalcular = (Button) findViewById(R.id.bCalcular);
		btLimpar = (Button) findViewById(R.id.bLimpar);
		btCalcular.setOnClickListener(this);
		btLimpar.setOnClickListener(this);
	}

	@SuppressLint("UseValueOf") @Override
	public void onClick(View v) {
		if (v.getId() == R.id.bCalcular) {
			if (validateFields()) {
			   calculaSalario(); 
			}else{
				Toast.makeText(this, resources.getString(R.string.campos_vazios),Toast.LENGTH_LONG).show();
			}             
		}
		
		if (v.getId() == R.id.bLimpar){
		    limpaCampos();
        }
	}


	private void calculaSalario() {
	   salario = new Salario();
	   salario.setBruto(new BigDecimal(editSalarioBruto.getText().toString()));
	   salario.setNumeroDeDependentes(new Integer(Integer.parseInt(editNumDependente.getText().toString())));
	   inss = calculaDesconto(salario, new Inss(salario,arquivo));
	   dependente = calculaDesconto(salario, new DescontoDependente(salario, arquivo));
	   irrf = calculaDesconto(salario, new Irrf(salario,arquivo));
	   if (chTransporte.isChecked()){
			this.transporte = calculaDesconto(salario, new Transporte(salario,arquivo));
	   }
	}


	private void limpaCampos() {
		Log.i(TAG," Limpando os campos");
		editSalarioBruto.setText("");
		editNumDependente.setText("0");
		chTransporte.setChecked(false);
		editOutroDesconto.setText("0");
	}
		
	private Desconto calculaDesconto(Salario salario, Desconto desconto){
		salario.setLiquido(desconto.calculaValorDesconto());
		desconto = alteraPorcetagem(desconto);
		return desconto;
	}
	
	private Desconto alteraPorcetagem(Desconto desconto){
		desconto.setPorcentagem(desconto.getPorcentagem().multiply(new BigDecimal("100")));
		return desconto;
	}

	private void callClearErrors(Editable s) {
        if (!s.toString().isEmpty()) {
            clearErrorFields(editSalarioBruto);
        }
    }

	/**
	 * Efetua a validação dos campos.Nesse caso, valida se os campos não estão
	 * vazios e se tem tamanho permitido. Nesse método você poderia colocar
	 * outros tipos de validações de acordo com a sua necessidade.
	 * @return boolean que indica se os campos foram validados com sucesso ou não
	 */
	private boolean validateFields() {
		String salarioBruto = editSalarioBruto.getText().toString().trim();
		String numDependente = editNumDependente.getText().toString().trim();
		
		return (!isEmptyFields(salarioBruto,numDependente) && hasSizeValid(salarioBruto,numDependente));
	}

	private boolean hasSizeValid(String salarioBruto,String numDependente) {
		if (!(salarioBruto.length() > 0)) {
			editSalarioBruto.requestFocus();
			editSalarioBruto.setError(resources.getString(R.string.salario_bruto_required));
			return false;
		}
		if (!(numDependente.length() > 0)) {
			editNumDependente.requestFocus();
			editNumDependente.setError(resources.getString(R.string.numero_dependente_required));
			return false;
		}
		return true;
	}

	private boolean isEmptyFields(String salarioBruto,String numDependente) {
		if (TextUtils.isEmpty(salarioBruto)) {
			editSalarioBruto.requestFocus(); // seta o foco para o campo user
			editSalarioBruto.setError(resources.getString(R.string.salario_bruto_required));
			return true;
		}
		
		if (TextUtils.isEmpty(numDependente)) {
			editNumDependente.requestFocus(); // seta o foco para o campo user
			editNumDependente.setError(resources.getString(R.string.numero_dependente_required));
			return true;
		}
		return false;
	}

	/**
	 * Limpa os ícones e as mensagens de erro dos campos desejados
	 * @param editTexts
	 */
	private void clearErrorFields(EditText... editTexts) {
		for (EditText editText : editTexts) {
			editText.setError(null);
		}
	}
	
	public TextWatcher getTextWatcher() {
		return textWatcher;
	}

	public Desconto getInss() {
		return inss;
	}

	public Desconto getIrrf() {
		return irrf;
	}

	public Desconto getTransporte() {
		return transporte;
	}


}
