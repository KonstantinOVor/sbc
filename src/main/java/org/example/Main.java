package org.example;

public class Main {
    public static void main(String[] args) {

        MyStringBuilder sb = new MyStringBuilder();
        sb.append("Hello").append("world!");
        System.out.println(sb.toString());

        sb.insert(5, "-");
        System.out.println(sb.toString());

        sb.delete(0, 6);
        System.out.println(sb.toString());

        sb.undo();
        System.out.println(sb.toString());

        sb.reverse();
        System.out.println(sb.toString());

        sb.undo();
        System.out.println(sb.toString());
    }
}