import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/*

 root variable: "inputBuffer" is: ""
 -> root "inputBuffer" "" variable:is:

 inputBuffer variable: "length' is: 0
 -> inputBuffer "length" 0 variable:is:

 root variable: "outerInterp"
 -> root "outerInterp" variable:

 outerInterp variable: "isWhiteSpace:at:" is: { | buffer pos | (buffer at: pos) == ' ' }
 -> outerInterp "isWhiteSpace:at:" { 0 paramVal 1 paramVal at: ' ' == } variable:is:

 outerInterp variable: "spaceCountOf:" is: {

 buffer |


 }
 -> outerInterp "isWhiteSpace:at:" { 0 paramVal 1 paramVal at: ' ' == } variable:is:

 outerInterp isWhatSpace: 'b'
 ( outerInterp isWhatSpace: ' ' ) if: { "It's white space" print }
 { outerInterp isWhatSpace: ' ' } if: { "It's white space" print }
 { outerInterp isWhatSpace: ' ' } whileTrue: { "true" }


 root subclass: "TestClass"
 TestClass int: "count" is: 0
 TestClass method: "sayHello" is: { count times: { "hello" print } }
 TestClass sayHello
 sayHello
 count: 7


 { | count | count times: { "hello" print } } executeWith: 4


 }

 Tasks
 - search root

 */

public class Main {	

	

	

	
	
	
	public static void main(String args[]) {
		Terminal t = new Terminal();
		System.out.println("Hello world.");
		while (true) {
			t.print(t.nextToken().toString());
			t.print("\n");
		}
	}
}
