package be.ehb.demoinput;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //UI components uit layout
    private EditText etEuro, etUSD;
    private TextInputLayout euroLayout;
    private Button btnConvert;
    private CheckBox cbTos;
    //listener op UI componenten
    private View.OnClickListener convertListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (checkEuro()) {
                //1 vraag waarde uit euro
                String eurStr = etEuro.getText().toString();
                //2 zet om naar double
                double eur = Double.parseDouble(eurStr);
                //3 converteer naar dollar
                double usd = eur * 1.18;
                //4 pas tekstveld dollar aan
                //proper
                etUSD.setText(String.format(Locale.getDefault(), "%.2f", usd));
                //zoals op de werkvloer, not preferred
                //etUSD.setText(""+usd);
            }
        }
    };

    private TextWatcher euroTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            checkEuro();
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            checkEuro();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private CompoundButton.OnCheckedChangeListener tosListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(b){
                //komt nog terug bij popups
                Toast.makeText(getApplication(), "Check", Toast.LENGTH_SHORT).show();
            }
        }
    };


    private boolean checkEuro() {
        String euro = etEuro.getText().toString();
        if(euro.length() <= 0){
            euroLayout.setError("Field must be filled in");
            return false;
        }else{
            euroLayout.setError(null);
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEuro = findViewById(R.id.et_euro);
        etUSD = findViewById(R.id.et_usd);
        btnConvert = findViewById(R.id.btn_convert);
        euroLayout = findViewById(R.id.til_euro);
        cbTos = findViewById(R.id.cb_tos);

        btnConvert.setOnClickListener(convertListener);
        etEuro.addTextChangedListener(euroTextWatcher);
        cbTos.setOnCheckedChangeListener(tosListener);
    }
}