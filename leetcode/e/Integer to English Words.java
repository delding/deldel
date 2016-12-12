/**
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
 * <p>
 * For example,
 * 123 -> "One Hundred Twenty Three"
 * 12345 -> "Twelve Thousand Three Hundred Forty Five"
 * 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 */

public class Solution {
  public String numberToWords(int num) {
    if (num == 0) return "Zero"; // edge case..
    String word = "";
    int billion = num / 1000000000;
    if (billion != 0) word += threeDigits(billion) + " Billion ";
    num %= 1000000000;
    int million = num / 1000000;
    if (million != 0) word += threeDigits(million) + " Million ";
    num %= 1000000;
    int thousand = num / 1000;
    if (thousand != 0) word += threeDigits(thousand) + " Thousand ";
    num %= 1000;
    if (num != 0) word += threeDigits(num);
    return word.trim();
  }

  String threeDigits(int num) {
    String words = "";
    if (num / 100 != 0) words += oneDigit(num / 100) + " Hundred ";
    num %= 100;
    if (num < 20 && num >= 10) return words + twoDigits2(num);
    if (num / 10 != 0) words += twoDigits1(num / 10) + " ";
    num %= 10;
    if (num != 0) words += oneDigit(num);
    return words.trim();
  }

  String twoDigits1(int num) {
    switch (num) {
      case 2: return "Twenty";
      case 3: return "Thirty";
      case 4: return "Forty";
      case 5: return "Fifty";
      case 6: return "Sixty";
      case 7: return "Seventy";
      case 8: return "Eighty";
      case 9: return "Ninety";
      default: return "";
    }
  }

  String twoDigits2(int num) {
    switch (num) {
      case 10: return "Ten";
      case 11: return "Eleven";
      case 12: return "Twelve";
      case 13: return "Thirteen";
      case 14: return "Fourteen";
      case 15: return "Fifteen";
      case 16: return "Sixteen";
      case 17: return "Seventeen";
      case 18: return "Eighteen";
      case 19: return "Nineteen";
      default: return "";
    }
  }

  String oneDigit(int num) {
    switch (num) {
      case 1: return "One";
      case 2: return "Two";
      case 3: return "Three";
      case 4: return "Four";
      case 5: return "Five";
      case 6: return "Six";
      case 7: return "Seven";
      case 8: return "Eight";
      case 9: return "Nine";
      default: return "";
    }
  }
}

public class Solution {

  static Map<Integer, String> map = new HashMap();

  static {
    map.put(1, "One");
    map.put(2, "Two");
    map.put(3, "Three");
    map.put(4, "Four");
    map.put(5, "Five");
    map.put(6, "Six");
    map.put(7, "Seven");
    map.put(8, "Eight");
    map.put(9, "Nine");
    map.put(10, "Ten");
    map.put(11, "Eleven");
    map.put(12, "Twelve");
    map.put(13, "Thirteen");
    map.put(14, "Fourteen");
    map.put(15, "Fifteen");
    map.put(16, "Sixteen");
    map.put(17, "Seventeen");
    map.put(18, "Eighteen");
    map.put(19, "Nineteen");
    map.put(20, "Twenty");
    map.put(30, "Thirty");
    map.put(40, "Forty");
    map.put(50, "Fifty");
    map.put(60, "Sixty");
    map.put(70, "Seventy");
    map.put(80, "Eighty");
    map.put(90, "Ninety");
  }

  public String numberToWords(int num) {
    if (num == 0) return "Zero"; // Error: edge case

    StringBuilder en = new StringBuilder();
    int billion = num / 1000000000;
    if (billion != 0) en.append(hundredToEnglish(billion) + " Billion ");
    num %= 1000000000;
    int million = num / 1000000;
    if (million != 0) en.append(hundredToEnglish(million) + " Million ");
    num %= 1000000;
    int thousand = num / 1000;
    if (thousand != 0) en.append(hundredToEnglish(thousand) + " Thousand ");
    num %= 1000;
    if (num != 0) en.append(hundredToEnglish(num));
    if (en.charAt(en.length() - 1) == ' ')
      en.deleteCharAt(en.length() - 1); // Error: may have tailing ' '
    return en.toString();
  }

  private String hundredToEnglish(int num) {
    StringBuilder en = new StringBuilder();
    int hundred = num / 100;
    if (hundred != 0) en.append(map.get(hundred) + " Hundred ");
    num %= 100;
    if (num > 10 && num < 20) { // Error: notice 11 to 19 are special
      en.append(map.get(num));
      return en.toString();
    }
    int ten = num / 10;
    if (ten != 0) en.append(map.get(ten * 10) + " ");
    num %= 10;
    if (num != 0) en.append(map.get(num));
    if (en.charAt(en.length() - 1) == ' ') en.deleteCharAt(en.length() - 1);
    return en.toString();
  }
}

