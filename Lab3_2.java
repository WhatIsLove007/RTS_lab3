package ua.ezandroid.rtslab32;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private EditText mDeadlineEditText;
    private EditText mIterationsEditText;
    private EditText mRateEditText;
    private Button mCalculationButton;
    private TextView mResultTextView;

    private double p = 4.0;
    private int[][] points =  {{0, 6}, {1, 5}, {3, 3}, {2, 4}};
    private double w1, w2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDeadlineEditText = (EditText) findViewById(R.id.deadline_edit_text);
        mIterationsEditText = (EditText) findViewById(R.id.iterations_edit_text);
        mRateEditText = (EditText) findViewById(R.id.rate_edit_text);
        mCalculationButton = (Button) findViewById(R.id.calculation_button);
        mResultTextView = (TextView) findViewById(R.id.result_text_view);

        mCalculationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    int iter = Integer.parseInt(mIterationsEditText.getText().toString());
                    double learningRate = Double.parseDouble(mRateEditText.getText().toString());
                    long timeDeadline = (long) (Double.parseDouble(mDeadlineEditText.getText().toString()) * 1_000_000_000);
                    trainingFunction(learningRate, iter, timeDeadline);


                } catch (NumberFormatException e) {
                    mResultTextView.setText("Wrong input!");
                }
            }
        });
    }

    void trainingFunction(double r, int iter, long end) {
        double y;
        double dt;
        int iterations = 0 ;
        boolean done = false;
        w1 = 0;
        w2 = 0;
        long start = System.currentTimeMillis();
        int index = 0;

        while (iterations++ < iter && (System.currentTimeMillis() - start) < end) {
            index %= 4;
            y = points[index][0] * w1 + points[index][1] * w2;
            if (isDone()) {
                done = true;
                break;
            }
            dt = P - y;
            w1 += dt * points[index][0] * r;
            w2 += dt * points[index][1] * r;
            index++;
        }
        if (done) {
            long execTimeMcs = System.currentTimeMillis() - start;

            mResultTextView.setText(
                    String.format(
                            "Trained successfully!\n" +
                                    "w1 = %-6.3f w2 = %-6.3f\n" +
                                    "Execution time: %d mcs", w1, w2, execTimeMcs
                    )
            );

        } else {
            String reason = "Training unsuccessful!";
            if (iterations >= iter) {
                reason += "\nNeed more iterations";
            } else {
                reason += "\nNeed more time";
            }
            mResultTextView.setText(reason);
        }

    }

    private boolean isDone() {
        return P < points[0][0] * w1 + points[0][1] * w2 && P < points[1][0] * w1 + points[1][1] * w2 &&
                P > points[2][0] * w1 + points[2][1] * w2 && P > points[3][0] * w1 + points[3][1] * w2;
    }
}