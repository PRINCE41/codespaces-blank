package main.java.codility;

import java.util.*;

public class Brackets {


/*
    A string S consisting of N characters is considered to be properly nested if any of the following conditions is true:

    S is empty;
    S has the form "(U)" or "[U]" or "{U}" where U is a properly nested string;
    S has the form "VW" where V and W are properly nested strings.
    For example, the string "{[()()]}" is properly nested but "([)()]" is not.

    Write a function:

    class Solution { public int solution(String S); }

    that, given a string S consisting of N characters, returns 1 if S is properly nested and 0 otherwise.
*/

    public int solution(String S) {
        Stack<Character> stack = new Stack<>();

        for (char ch : S.toCharArray()) {

            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } 
            else {
                if (stack.isEmpty()) {
                    return 0;
                }

                char top = stack.pop();

                if (ch == ')' && top != '(') return 0;
                if (ch == ']' && top != '[') return 0;
                if (ch == '}' && top != '{') return 0;
            }
        }

        return stack.isEmpty() ? 1 : 0;
    }


}