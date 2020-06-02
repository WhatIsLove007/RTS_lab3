package ua.ezandroid.rtslab31;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private EditText mNumberInputEditText;
    private Button mCalculationButton;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNumberInputEditText = (EditText) findViewById(R.id.number_input_edit_text);
        mCalculationButton = (Button) findViewById(R.id.calculation_button);
        mResultTextView = (TextView) findViewById(R.id.result_text_view);

        mCalculationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int number = Integer.parseInt(mNumberInputEditText.getText().toString());
                    if (isPrime(number)) mResultTextView.setText("Число просте");
                    else if (number % 2 == 0) mResultTextView.setText("Число парне");
                    else {

                        int[] result = factorization(number);
                        mResultTextView.setText(String.format(
                                "A = %d, B = %d\nA * B = %d"
                                , result[0], result[1], number));
                    }
                } catch (Exception e) {
                    mResultTextView.setText("Невірно введене значення!");
                }
            }
        });
    }

    private boolean isPrime(int n) {

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0)
                return false;
        }

        return true;
    }

    private int[] factorization(int number) {

        int[] result = new int[2];
        int x, k = 0;
        double y;

        do {
            x = (int) (Math.sqrt(number) + k);
            y = Math.sqrt(Math.pow(x, 2) - number);
            k++;
        } while (y % 1 != 0);

        result[0] = (int) (x + y);
        result[1] = (int) (x - y);

        return result;
    }
}