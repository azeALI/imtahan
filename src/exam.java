//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class exam {
  public static int num;

  public static void showSelectedQuestions(Sual[] ALL) throws IOException {
    int start = 1;
    int end = num;
    int count = 0;
    int xal = 0;
    char mode = '0';
    Sual[] shuffled;
    Scanner s2 = new Scanner(System.in);
    System.out.println("Sual Sayı - " + num);
    System.out.println("Mod : 1 - 30 sual, 2 - 50 sual, 3 - aralıq");

    for (String md = s2.nextLine().strip();; md = s2.nextLine().strip()) {
      if (md.length() == 1) {
        Character m = md.charAt(0);
        if (m == '1' | m == '2' | m == '3') {
          mode = m;
          break;
        }
      }
      System.out.println("DÜZGÜN MOD SEÇIN!!!" + "\n");
      System.out.println("Sual Sayı - " + num);
      System.out.println("Mod : 1 - 30 sual, 2 - 50 sual, 3 - aralıq");
    }
    if (mode == '1') {
      count = 30;
    } else if (mode == '2') {
      count = 50;
    } else if (mode == '3') {
      System.out.println(" BaşlanĞıc və sonu daxil edin");
      out: for (String[] s = s2.nextLine().strip().split("\\s+");; s = s2.nextLine().strip().split("\\s+")) {
        if (s.length == 2) {

          for (Character c : s[0].toCharArray()) {
            if (Character.isLetter(c)) {
              System.out.println("DÜZGÜN ARALIQ DAXIL EDIN!!!");
              continue out;
            }
          }
          for (Character c : s[1].toCharArray()) {
            if (Character.isLetter(c)) {
              System.out.println("DÜZGÜN ARALIQ DAXIL EDIN!!!");
              continue out;
            }
          }
          start = Integer.parseInt(s[0]);
          end = Integer.parseInt(s[1]);
          if (start >= end) {
            System.out.println("DÜZGÜN ARALIQ DAXIL EDIN!!!");
            continue out;
          }
          count = end - start + 1;
          break out;
        }
        System.out.println("DÜZGÜN ARALIQ DAXIL EDIN!!!");
      }
    }
    System.out.println();
    System.out.println("-------------------------------");
    shuffled = randomiseArray(ALL, start - 1, end - 1);
    for (int i = 0; i < count; ++i) {
      Sual s = shuffled[i];
      s.number = i + 1;
      s.correctAnswers();
      s.show();
      System.out.print("CAVABINIZ : ");
      int cavab = 0;

      for (String c = s2.nextLine().strip();; c = s2.nextLine().strip()) {
        if (c.length() == 1) {
          if (c.equals("1") || c.equals("2") || c.equals("3") || c.equals("3") || c.equals("4") || c.equals("5")) {
            cavab = Integer.parseInt(c);
            break;
          }
        }
        System.out.println("CAVABI DÜZGÜN DAXİL EDİN!!!");
      }

      if (cavab == s.correctAnswer) {
        ++xal;
        System.out.println("\u001B[32m" + "DOĞRU" + "\u001B[0m");
        System.out.println("XAL : " + xal);
      } else {
        System.out.println("\u001B[31m" + "YANLIŞ" + "\u001B[0m");
        System.out.println("XAL : " + xal);
        String correct = s.answers[s.correctAnswer - 1];
        System.out.println("DOĞRU CAVAB : " + correct + "\n");
      }
      System.out.println("-------------------------------");
      System.out.println();
    }
  }

  public static Sual[] randomiseArray(Sual[] arr, int start, int end) throws IOException {
    Random r = new Random();
    int size = end - start + 1;
    Sual[] newArray = new Sual[size];
    ArrayList<Integer> randomHolder = new ArrayList<>();
    int v = 0;
    int nextRandomNumber = r.nextInt(start, end);

    while (v < size) {
      if (randomHolder.contains(nextRandomNumber)) {
        nextRandomNumber = r.nextInt(start, end + 1);
      } else {
        randomHolder.add(nextRandomNumber);
        nextRandomNumber = r.nextInt(start, end + 1);
        ++v;
      }
    }

    for (int i = 0; i < size; ++i) {
      newArray[i] = arr[randomHolder.get(i)];
    }

    return newArray;
  }

  public static Sual[] prepareQuestionArray() throws Exception {
    Scanner sc = new Scanner(new File("test.txt"));
    num = Integer.parseInt(sc.nextLine());
    Sual[] allQuestions = new Sual[num];

    for (int i = 0; i < num; ++i) {
      String head = new String(sc.nextLine().getBytes(), StandardCharsets.UTF_8).strip();
      String[] ans = new String[5];

      for (int j = 0; j < 5; ++j) {
        ans[j] = new String(sc.nextLine().getBytes(), StandardCharsets.UTF_8).strip();
      }

      allQuestions[i] = new Sual(head, ans, i);

    }
    sc.close();

    return allQuestions;
  }

}
