/*
17. Letter Combinations of a Phone Number
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
*/

class Solution {
    
    List<String> result = new ArrayList<String>();
    Map<String, String> hm = new HashMap<String, String>();
    
    public List<String> letterCombinations(String digits) {        
        hm.put("2", "abc");
        hm.put("3", "def");        
        hm.put("4", "ghi");
        hm.put("5", "jkl");
        hm.put("6", "mno");
        hm.put("7", "pqrs");
        hm.put("8", "tuv");
        hm.put("9", "wxyz");
        if(digits.isEmpty()){
            return result;
        }
        backtrack("", digits);
        return result;
    }
    
    public void backtrack(String combination, String digits){
        
        if(digits.isEmpty()){
            result.add(combination);
            return;
        }
       
        String digit = digits.substring(0, 1);
        String letters = hm.get(digit); //abc
        for(int i = 0; i < letters.length(); i++){
            String letter = letters.substring(i, i + 1);
            backtrack(combination + letter, digits.substring(1));
        }        
    }
}
