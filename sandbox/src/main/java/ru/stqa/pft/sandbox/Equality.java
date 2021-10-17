package ru.stqa.pft.sandbox;

public class Equality {
  public static void main(String [] args){
    String s1="firefox1";
    String s2="firefox"+"1";
    System.out.println(s1==s2);
    System.out.println(s1.equals(s2));
  }
}
