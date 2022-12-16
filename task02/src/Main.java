import java.io.Console;

public class Main {
  public static void main(String[] args) {
    Console cons = System.console();
    System.out.println("Welcome.");
    String input="";
    Float result = 0.0f;
    while(!input.equals("exit")){
      input = cons.readLine("> ");
      if(input.equals("exit"))break;

      // if $last is used, replace all instances of $last with current result`
      if(input.contains("$last")){
        input = input.replace("$last", String.format("%f", result));
      }
      String[] inputList = input.split(" ");
      if(inputList.length<3){
        System.out.println("Format: <Number> <Space> <Operator> <Space> <Number>, use $last to access previous result.");
        continue;
      }
      String operation = inputList[1];
      Float num1 = Float.parseFloat(inputList[0]);
      Float num2 = Float.parseFloat(inputList[2]);
      
      result = calculation(operation,num1,num2);
      if(result.toString().endsWith(".0")){
        System.out.println(result.toString().replace(".0", ""));
      }else{
        System.out.println(result);
      }
      
    }
    System.out.println("Bye bye");
  }

  public static Float calculation(String operation,Float num1, Float num2 ){
    Float result=0.0f;
    switch(operation){
      
      case "+":
      result = addition(num1, num2);
      break;

      case "-":
      result = subtraction(num1, num2);
      break;

      case "*":
      result = multiplication(num1, num2);
      break;

      case "/":
      result = division(num1, num2);
      break;

      default:
      break;
    }
    return result;
  }
  public static Float addition(Float num1, Float num2){
    return num1 + num2;
  }
  public static Float subtraction(Float num1, Float num2){
    return num1 - num2;
  }
  public static Float multiplication(Float num1, Float num2){
    return num1 * num2;
  }
  public static Float division(Float num1, Float num2){
    return num1 / num2;
  }
  
}
