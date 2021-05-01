package test.android.exercise.mini.calculator.app;

import android.exercise.mini.calculator.app.SimpleCalculatorImpl;

import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.*;

public class SimpleCalculatorImplTest {

  @Test
  public void when_noInputGiven_then_outputShouldBe0(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_inputIsPlus_then_outputShouldBe0Plus(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertPlus();
    assertEquals("0+", calculatorUnderTest.output());
  }


  @Test
  public void when_inputIsMinus_then_outputShouldBeCorrect(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertMinus();
    String expected = "0-"; // TODO: decide the expected output when having a single minus
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_callingInsertDigitWithIllegalNumber_then_exceptionShouldBeThrown(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    try {
      calculatorUnderTest.insertDigit(357);
      fail("should throw an exception and not reach this line");
    } catch (RuntimeException e) {
      // good :)
    }
  }


  @Test
  public void when_callingDeleteLast_then_lastOutputShouldBeDeleted(){
    // todo: implement test
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.deleteLast();
    assertEquals(calculatorUnderTest.output(),"5+");
  }

  @Test
  public void when_callingClear_then_outputShouldBeCleared(){
    // todo: implement test
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.clear();
    assertEquals(calculatorUnderTest.output(),"0");
  }

  @Test
  public void when_savingState_should_loadThatStateCorrectly(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // give some input
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);

    // save current state
    Serializable savedState = calculatorUnderTest.saveState();
    assertNotNull(savedState);

    // call `clear` and make sure calculator cleared
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());

    // load the saved state and make sure state was loaded correctly
    calculatorUnderTest.loadState(savedState);
    assertEquals("5+7", calculatorUnderTest.output());
  }

  @Test
  public void when_savingStateFromFirstCalculator_should_loadStateCorrectlyFromSecondCalculator(){
    SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
    SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();
    // TODO: implement the test based on this method's name.
    //  you can get inspiration from the test method `when_savingState_should_loadThatStateCorrectly()`
    // give some input
    firstCalculator.insertDigit(5);
    firstCalculator.insertPlus();
    firstCalculator.insertDigit(7);

    secondCalculator.insertDigit(9);
    secondCalculator.insertDigit(9);
    secondCalculator.insertPlus();
    secondCalculator.insertDigit(1);

    // save current state
    Serializable savedStateFirst = firstCalculator.saveState();
    assertNotNull(savedStateFirst);

    Serializable savedStateSecond = secondCalculator.saveState();
    assertNotNull(savedStateSecond);

    // call `clear` and make sure calculator cleared
    firstCalculator.clear();
    assertEquals("0", firstCalculator.output());

    secondCalculator.clear();
    assertEquals("0", secondCalculator.output());

    // load the saved state and make sure state was loaded correctly
    secondCalculator.loadState(savedStateFirst);
    assertEquals("5+7", secondCalculator.output());

    firstCalculator.loadState(savedStateSecond);
    assertEquals("99+1", firstCalculator.output());
  }

  // TODO:
  //  the existing tests are not enough since they only test simple use-cases with small inputs.
  //  write at least 10 methods to test correct behavior with complicated inputs or use-cases.
  //  examples:
  //  - given input "5+7-13<DeleteLast>25", expected output is "5+17-125"
  //  - given input "9<Clear>12<Clear>8-7=", expected output is "1"
  //  - given input "8-7=+4=-1=", expected output is "4"
  //  - given input "999-888-222=-333", expected output is "-111-333"
  //  - with 2 calculators, give them different inputs, then save state on first calculator and load the state into second calculator, make sure state loaded well
  //  etc etc.
  //  feel free to be creative in your tests!
  @Test
  public void when_deleteLast_andAdd_then_shouldAdd()
  {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(5);
    assertEquals("5+7-125",calculatorUnderTest.output());
  }
  @Test
  public void when_clear_andAdd_then_shouldAdd()
  {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.clear();
    assertEquals("0",calculatorUnderTest.output());
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(2);
    assertEquals("12",calculatorUnderTest.output());
    calculatorUnderTest.clear();
    assertEquals("0",calculatorUnderTest.output());
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(7);
    assertEquals("8-7",calculatorUnderTest.output());
    calculatorUnderTest.insertEquals();
    assertEquals("1",calculatorUnderTest.output());
  }
  @Test
  public void when_mulEquals_then_shouldAdd()
  {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(4);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertEquals();
    assertEquals("4",calculatorUnderTest.output());
  }
  @Test
  public void when_minusMul_then_shouldMinus()
  {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(3);
    assertEquals("-111-333",calculatorUnderTest.output());
  }
  @Test
  public void when_longInput_then_shouldCalc()
  {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    int excpected=0;
    for(int j=0;j<100;++j)
    {
      for(int i=0;i<10;++i)
      {
        calculatorUnderTest.insertDigit(i);
        calculatorUnderTest.insertPlus();
        excpected = excpected + i;
      }
    }
    calculatorUnderTest.insertEquals();
    assertEquals(Integer.toString(excpected),calculatorUnderTest.output());
    for(int i=0;i<10;++i)
    {
      calculatorUnderTest.insertMinus();
      calculatorUnderTest.insertDigit(i);
      excpected = excpected - i;
    }
    calculatorUnderTest.insertEquals();
    assertEquals(Integer.toString(excpected),calculatorUnderTest.output());
  }
  @Test
  public void when_insertWhenEndsWithOperator_then_ignoreLastOperator() {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertEquals();
    assertEquals("15",calculatorUnderTest.output());
  }
  @Test
  public void when_OnlyOperator_then_doNothing() {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertEquals();
    assertEquals("0",calculatorUnderTest.output());
  }
  @Test
  public void when_inputEmptyDeleteLast_then_doNothing() {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.deleteLast();
    assertEquals("0",calculatorUnderTest.output());
  }
  @Test
  public void when_OnlyOperatorAndLastDigit_then_doNothing() {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertEquals();
    assertEquals("1",calculatorUnderTest.output());
  }
  @Test
  public void when_deleteToOperator_then_calc() {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertEquals();
    assertEquals("13",calculatorUnderTest.output());
  }
  @Test
  public void when_noEquals_then_showHistory() {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.insertDigit(1);
    assertEquals("12+1",calculatorUnderTest.output());
  }
  @Test
  public void when_EqualsAfterClear_then_showHistory() {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.clear();
    calculatorUnderTest.insertEquals();
    assertEquals("0",calculatorUnderTest.output());
  }
}