
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws  Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение ");
        String expression = scanner.nextLine();
        System.out.println(calc(expression));
    }
    public static String calc(String expression) throws Exception{
        int num1;
        int num2;
        String operator;
        String result;
        boolean isRoman;
        String[] operands = expression.split("[+\\-*/]");
        if (operands.length != 2){
            throw new Exception("Должно быть два числа и математический знак");
        }
        if (operands[0].contains(" ")){
            String[] temp = operands[0].split(" ");
            operands[0] = temp[0];
        }
        if (operands[1].contains(" ")){
            String[] temp = operands[1].split(" ");
            operands[1] = temp[temp.length - 1];
        }
        operator = detectOperation(expression);
        if (operator == null){
            throw new Exception("Неподдерживаемая операция");
        }
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])){
            num1 = Roman.convertToArab(operands[0]);
            num2 = Roman.convertToArab(operands[1]);
            isRoman = true;
        }
        else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])){
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[1]);
            isRoman = false;
        }
        else{
            throw new Exception("Числа должны быть в одном формате");
        }
        if (num1 >10 || num2 >10){
            throw new Exception("Числа должны быть не больше 10");
        }
        int arabian = preCalc(num1,num2,operator);
        if(isRoman){
            if(arabian <1){
                throw new Exception("Римские цифры не могут быть меньше 1");
            }
            result = Roman.convertToRoman(arabian);
        }
        else{
            result = String.valueOf(arabian);
        }
        return result;
    }
    public static String detectOperation(String expression){
        if (expression.contains("+")){
            return "+";
        }
        if (expression.contains("-")){
            return "-";
        }
        if (expression.contains("*")){
            return "*";
        }
        if(expression.contains("/")){
            return "/";
        }
        else return null;
    }
    public static int preCalc(int num1, int num2, String operator){
        switch (operator){
            case "+":
                return num1 + num2;
            case "-" :
                return num1 - num2;
            case "/":
                return  num1 / num2;
            default:
                return num1 * num2;
        }
    }
}
class Roman{
    static String[] romansArray = {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};
    public static boolean isRoman(String value){
        for(int i=0;i<romansArray.length;i++){
            if (value.equals(romansArray[i])){
                return true;
            }
        }
        return false;
    }
    public static String convertToRoman(int arabian){
        return romansArray[arabian];
    }
    public static int convertToArab(String operand){
        for (int i = 0; i<romansArray.length; i++){
            if (operand.equals(romansArray[i])){
                return i;
            }
        }
        return -1;
    }
}