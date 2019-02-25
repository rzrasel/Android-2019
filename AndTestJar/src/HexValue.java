/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author developer
 */
public class HexValue {

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        String textValue, hexVaule;
        textValue = "HJIছবি";
        int i, len = textValue.length();
        for (i = 0; i < len; i++) {
            char character = textValue.charAt(i);
            int ascii = (int) character;
            //String hex = Integer.toHexString(ascii);
            String hex = String.format("%04x", ascii);
            stringBuilder.append(hex);
            int value = Integer.parseInt(hex, 16);
            character = (char) value;
            System.out.println("VALUE: " + String.valueOf(Character.toChars(value)));
        }
    }

    public void stringToHexToString() {
        StringBuilder stringBuilder = new StringBuilder();
        String textValue, hexVaule;
        textValue = "HJIছবি";
        int i, len = textValue.length();
        for (i = 0; i < len; i++) {
            hexVaule = String.format("%04x", (int) textValue.charAt(i));
            //System.out.println("VALUE: " + hexVaule);
            stringBuilder.append(hexVaule);
            /*hexVaule = Integer.toHexString((int) textValue.charAt(i));
            System.out.println("VALUE: " + hexVaule);*/
        }
        /*int num = Integer.parseInt(text, 16);
        String bin = Integer.toString(num, 2);*/
 /*Integer.toHexString((int) 'a');
        Integer.toBinaryString((int) 'b');*/
        System.out.println("VALUE: " + stringBuilder.toString());
        textValue = stringBuilder.toString();
        stringBuilder.setLength(0);
        for (i = 0; i < textValue.length(); i += 4) {
            String output = textValue.substring(i, (i + 4));
            int decimal = Integer.parseInt(output, 16);
            char outChar = (char) decimal;
            stringBuilder.append(outChar);
            //System.out.println("VALUE: " + outChar);
        }
        System.out.println("VALUE: " + stringBuilder.toString());
        //String hex = String.format("%02X", (int) array[i]);
    }
}
//http://www.java2novice.com/java-fundamentals/integer/hex-value/
