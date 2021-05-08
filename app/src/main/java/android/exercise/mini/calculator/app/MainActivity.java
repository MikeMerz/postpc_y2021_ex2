package android.exercise.mini.calculator.app;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  @VisibleForTesting
  public SimpleCalculator calculator;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (calculator == null) {
      calculator = new SimpleCalculatorImpl();
    }

    /*
    TODO:
    - find all views
    - initial update main text-view based on calculator's output
    - set click listeners on all buttons to operate on the calculator and refresh main text-view
     */
    TextView main = findViewById(R.id.textViewCalculatorOutput);
    main.setText(calculator.output());
    TextView b0 = findViewById(R.id.button0);
    TextView b1 = findViewById(R.id.button1);
    TextView b2 = findViewById(R.id.button2);
    TextView b3 = findViewById(R.id.button3);
    TextView b4 = findViewById(R.id.button4);
    TextView b5 = findViewById(R.id.button5);
    TextView b6 = findViewById(R.id.button6);
    TextView b7 = findViewById(R.id.button7);
    TextView b8 = findViewById(R.id.button8);
    TextView b9 = findViewById(R.id.button9);
    TextView equals = findViewById(R.id.buttonEquals);
    TextView plus = findViewById(R.id.buttonPlus);
    TextView minus = findViewById(R.id.buttonMinus);
    View delete = findViewById(R.id.buttonBackSpace);
    View clear = findViewById(R.id.buttonClear);
    setClickListener(b0,0);
    setClickListener(b1,1);
    setClickListener(b2,2);
    setClickListener(b3,3);
    setClickListener(b4,4);
    setClickListener(b5,5);
    setClickListener(b6,6);
    setClickListener(b7,7);
    setClickListener(b8,8);
    setClickListener(b9,9);
    equals.setOnClickListener(v->{
      calculator.insertEquals();
      main.setText(calculator.output());
    });
    plus.setOnClickListener(v->{
      calculator.insertPlus();
      main.setText(calculator.output());
    });
    minus.setOnClickListener(v->{
      calculator.insertMinus();
      main.setText(calculator.output());
    });
    delete.setOnClickListener(v->{
      calculator.deleteLast();
      main.setText(calculator.output());
    });
    clear.setOnClickListener(v->
    {
      calculator.clear();
      main.setText(calculator.output());
    });
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    // todo: save calculator state into the bundle
    outState.putSerializable("state",calculator.saveState());
  }

  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    // todo: restore calculator state from the bundle, refresh main text-view from calculator's output
    calculator.loadState(savedInstanceState.getSerializable("state"));
    TextView main = findViewById(R.id.textViewCalculatorOutput);
    main.setText(calculator.output());
  }
  private void setClickListener(View view,int digit)
  {
    view.setOnClickListener(view1 -> {
      TextView main = findViewById(R.id.textViewCalculatorOutput);
      calculator.insertDigit(digit);
      main.setText(calculator.output());
    });
  }
}