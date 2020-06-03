//IV-73 Nahornyi V.V.
package genetic.algo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int a, b, c, d, y;
    public EditText get_a, get_b, get_c, get_d, get_y;
    public TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = (Button) findViewById(R.id.solve);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                get_a = (EditText) findViewById(R.id.a);
                get_b = (EditText) findViewById(R.id.b);
                get_c = (EditText) findViewById(R.id.c);
                get_d = (EditText) findViewById(R.id.d);
                get_y = (EditText) findViewById(R.id.y);

                if (correctInput()){
                    a = Integer.parseInt(get_a.getText().toString());
                    b = Integer.parseInt(get_b.getText().toString());
                    c = Integer.parseInt(get_c.getText().toString());
                    d = Integer.parseInt(get_d.getText().toString());
                    y = Integer.parseInt(get_y.getText().toString());
                    GeneticAlgo genetic = new GeneticAlgo(a, b, c, d, y);
                    String finalResult = genetic.solution();
                    System.out.println(finalResult);
                    //result.setText(finalResult);
                    Intent intent = new Intent(MainActivity.this, NewActivity.class);
                    startActivity(intent);
                };

            }
        });
    }

    boolean correctInput(){
        if (get_a.getText().toString().length() == 0 || get_b.getText().toString().length() == 0 ||
                get_c.getText().toString().length() == 0 || get_d.getText().toString().length() == 0 ||
                get_y.getText().toString().length() == 0) {
            String emptyInput = "Error! Enter a number!";
            result.setText(emptyInput);
            return false;
        }
        return true;
    }



}
