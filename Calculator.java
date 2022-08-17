package CalculatorRA;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {

        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String exp = scn.nextLine();
        if (!exp.matches("([-0-9]+|[IVXLCDM]+)[\\+\\-\\*/]([-0-9]+|[IVXLCDM]+)")) {
            throw new RuntimeException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

            int actionIndex = -1;
            for (int i = 0; i < actions.length; i++) {
                if (exp.contains(actions[i])) {
                    actionIndex = i;
                    break;
                }
            }

            if (actionIndex == -1) {
                System.out.println("Строка не является математической операцией");
                return;
            }

            String[] data = exp.split(regexActions[actionIndex]);
            if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
                int a, b;
                //Определяем, римские ли это числа
                boolean isRoman = converter.isRoman(data[0]);
                if (isRoman) {
                    if(actions[actionIndex].equals("-")){
                        throw new RuntimeException("В римской системе нет отрицательных чисел");
                    }

                    a = converter.romanToInt(data[0]);
                    b = converter.romanToInt(data[1]);

                } else {
                    a = Integer.parseInt(data[0]);
                    b = Integer.parseInt(data[1]);
                }

                int result;
                switch (actions[actionIndex]) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                        result = a * b;
                        break;
                    default:
                        result = a / b;
                        break;
                }
                if (isRoman) {
                    System.out.println(converter.intToRoman(result));
                } else {
                    System.out.println(result);
                }
            } else {
                System.out.println("Используются одновременно разные системы счисления");
            }
        }
    }
