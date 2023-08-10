package org.example.calculator.domain;

import org.example.NewArithmeticOperator;
import org.example.calculator.tobe.AdditionOperator;
import org.example.calculator.tobe.DivisionOperator;

import java.util.List;

public class Calculator {

  private static final List<NewArithmeticOperator> arithmeticOperators=List.of(new AdditionOperator(),new SubtractionOperator(),new MultiplicationOperator(),new DivisionOperator());


    public static int calculate(int operand1,String operator,int operand2) {


      return  arithmeticOperators.stream()
              .filter(arithmeticOperator ->arithmeticOperator.supports(operator))
              .map(arithmeticOperator->arithmeticOperator.calculate(operand1,operand2))
              .findFirst()
              .orElseThrow(() ->new IllegalArgumentException("올바른 사칙연산이 아닙니다."));

    }
}
