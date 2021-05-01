package android.exercise.mini.calculator.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class SimpleCalculatorImpl implements SimpleCalculator {

  // todo: add fields as needed
   private ArrayList<String> calc = new ArrayList<>();
   private boolean endsWithOperator = false;

  @Override
  public String output() {
    // todo: return output based on the current state
    if (calc.isEmpty())
    {
      return "0";
    }
    String finalRet = "";
    for(int i=0;i<calc.size();++i)
    {
      finalRet = finalRet.concat(calc.get(i));
    }
    return finalRet;
  }

  @Override
  public void insertDigit(int digit) {
    // todo: insert a digit
    if (digit>=0 && digit<10)
    {
      calc.add(Long.toString(digit));
      endsWithOperator = false;
    }
    else
    {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public void insertPlus() {
    // todo: insert a plus
    if (calc.isEmpty())
    {
      calc.add("0");
    }
    if (!endsWithOperator)
    {
      calc.add("+");
      endsWithOperator = true;
    }
  }

  @Override
  public void insertMinus() {
    // todo: insert a minus
    if (calc.isEmpty())
    {
      calc.add("0");
    }
    if (!endsWithOperator)
    {
      calc.add("-");
      endsWithOperator = true;
    }
  }

  @Override
  public void insertEquals() {
    // todo: calculate the equation. after calling `insertEquals()`, the output should be the result
    //  e.g. given input "14+3", calling `insertEquals()`, and calling `output()`, output should be "17"
    String all ="";
    for(int k=0;k<calc.size();++k)
    {
      all = all.concat(calc.get(k));
    }
    if(all.equals("")){calc.add("0");return;}
    String[] nums = all.replaceAll("\\s", "").split("\\+|(?=-)");
    int sum = 0;
    for (String num : nums) {
      long i = Long.parseLong(num);
      sum += i;
    }
    this.clear();
    calc.add(Long.toString(sum));
  }

  @Override
  public void deleteLast() {
    // todo: delete the last input (digit, plus or minus)
    //  e.g.
    //  if input was "12+3" and called `deleteLast()`, then delete the "3"
    //  if input was "12+" and called `deleteLast()`, then delete the "+"
    //  if no input was given, then there is nothing to do here
    if(calc.size() == 1)
    {
      this.clear();
      return;
    }
    if(!calc.isEmpty())
    {
      calc.remove(calc.size()-1);
      try{
        endsWithOperator = Long.parseLong(calc.get(calc.size()-1)) <= -1 || Long.parseLong(calc.get(calc.size()-1)) >= 10;
      }catch (NumberFormatException e)
      {
        endsWithOperator = true;
      }
    }
  }

  @Override
  public void clear() {
    // todo: clear everything (same as no-input was never given)
    calc.clear();
    endsWithOperator = false;
  }

  @Override
  public Serializable saveState() {
    CalculatorState state = new CalculatorState();
    state.calc = new ArrayList<>();
    // todo: insert all data to the state, so in the future we can load from this state
    state.calc.addAll(calc);
    state.endsWithOperator = endsWithOperator;
    return state;
  }

  @Override
  public void loadState(Serializable prevState) {
    if (!(prevState instanceof CalculatorState)) {
      return; // ignore
    }
    CalculatorState casted = (CalculatorState) prevState;
    // todo: use the CalculatorState to load
    calc = casted.calc;
    endsWithOperator = casted.endsWithOperator;
  }

  private static class CalculatorState implements Serializable {
    private ArrayList<String> calc;
    private boolean endsWithOperator;
  }
}
