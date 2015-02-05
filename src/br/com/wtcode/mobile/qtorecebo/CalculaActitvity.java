package br.com.wtcode.mobile.qtorecebo;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import br.com.wtcode.mobile.qtorecebo.modelo.Salario;

/**
 * Created by Willian on 28/01/2015.
 */
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calculasalario);
		initViews();
	}

	private void initViews() {
		resources = getResources();
		TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
 
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
 
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
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.bCalcular) {
			if (validateFields()) {
				Toast.makeText(this, resources.getString(R.string.app_name),
						Toast.LENGTH_LONG).show();
			}
		}
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
	 *
	 * @return boolean que indica se os campos foram validados com sucesso ou
	 *         não
	 */
	private boolean validateFields() {
		String salarioBruto = editSalarioBruto.getText().toString().trim();
		return (!isEmptyFields(salarioBruto) && hasSizeValid(salarioBruto));
	}

	private boolean hasSizeValid(String salarioBruto) {
		if (!(salarioBruto.length() > 0)) {
			editSalarioBruto.requestFocus();
			editSalarioBruto.setError(resources.getString(R.string.app_name));
			return false;
		}
		return true;
	}

	private boolean isEmptyFields(String field) {
		if (TextUtils.isEmpty(field)) {
			editSalarioBruto.requestFocus(); // seta o foco para o campo user
			editSalarioBruto.setError(resources.getString(R.string.app_name));
			return true;
		}
		return false;
	}

	/**
	 * Limpa os ícones e as mensagens de erro dos campos desejados
	 * 
	 * @param editTexts
	 */
	private void clearErrorFields(EditText... editTexts) {
		for (EditText editText : editTexts) {
			editText.setError(null);
		}
	}

}
