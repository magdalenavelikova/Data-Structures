package implementations;

import interfaces.Solvable;

import java.util.ArrayDeque;

public class BalancedParentheses implements Solvable {
    private String parentheses;

    public BalancedParentheses(String parentheses) {
        this.parentheses = parentheses;
    }

    @Override
    public Boolean solve() {
       ArrayDeque<String> brackets = new ArrayDeque<>();
        boolean isBalanced = true;
        if (parentheses.length() % 2 != 0 || parentheses.isEmpty()) {
            isBalanced = false;
        } else {
            for (int i = 0; i < parentheses.length(); i++) {
                if (parentheses.charAt(i) == '{' || parentheses.charAt(i) == '(' || parentheses.charAt(i) == '[') {
                    brackets.push(String.valueOf(parentheses.charAt(i)));
                } else {
                    if (brackets.isEmpty()) {
                        isBalanced = false;
                        break;
                    }
                    if (String.valueOf(parentheses.charAt(i)).equals(")") && !brackets.pop().equals("(")) {
                        isBalanced = false;
                        break;
                    } else if (String.valueOf(parentheses.charAt(i)).equals("]") && !brackets.pop().equals("[")) {
                        isBalanced = false;
                        break;
                    } else if (String.valueOf(parentheses.charAt(i)).equals("}") && !brackets.pop().equals("{")) {
                        isBalanced = false;
                        break;
                    }
                }
            }
        }
        if (!brackets.isEmpty()) {
            isBalanced = false;
        }
        return isBalanced;
    }

}
