//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class exam {
  public static int num;

  public static void showSelectedQuestions(Sual[] ALL) throws IOException {
    int start;
    int end;
    int xal = 0;
    Sual[] shuffled;
    Scanner s2 = new Scanner(System.in);
    System.out.println("Mod : 1 - 30 sual, 2 - 50 sual, 3 - araliq");
    int mode = s2.nextInt();
    if (mode == 1) {
      shuffled = randomiseArray(ALL, 0, num - 1);
      for (int i = 0; i < 30; i++) {
        Sual s = shuffled[i];
        s.number = i + 1;
        s.correctAnswers();
        s.show();
        System.out.print("Your Answer : ");

        int cavab;
        for (cavab = s2.nextInt(); cavab < 1 || cavab > 5; cavab = s2.nextInt()) {
          System.out.print("ENTER A VALID ANSWER : ");
        }

        if (cavab == s.correctAnswer) {
          ++xal;
          System.out.println("Correct -- points : " + xal);
        } else {
          System.out.println("Wrong -- points : " + xal);
          String var10001 = s.answers[s.correctAnswer - 1];
          System.out.println("Correct answer was : " + var10001 + "\n");
        }
      }
    } else if (mode == 2) {
      shuffled = randomiseArray(ALL, 0, num - 1);
      for (int i = 0; i < 50; i++) {
        Sual s = shuffled[i];
        s.number = i + 1;
        s.correctAnswers();
        s.show();
        System.out.print("Your Answer : ");

        int cavab;
        for (cavab = s2.nextInt(); cavab < 1 || cavab > 5; cavab = s2.nextInt()) {
          System.out.print("ENTER A VALID ANSWER : ");
        }

        if (cavab == s.correctAnswer) {
          ++xal;
          System.out.println("Correct -- points : " + xal);
        } else {
          System.out.println("Wrong -- points : " + xal);
          String var10001 = s.answers[s.correctAnswer - 1];
          System.out.println("Correct answer was : " + var10001 + "\n");
        }
      }

    } else {
      System.out.println(" Baslangic ve sonu daxil edin");
      start = s2.nextInt();
      end = s2.nextInt();
      xal = 0;
      shuffled = randomiseArray(ALL, start - 1, end - 1);

      for (int i = 0; i < end - start + 1; ++i) {
        Sual s = shuffled[i];
        s.number = i + 1;
        s.correctAnswers();
        s.show();
        System.out.print("Your Answer : ");

        int cavab;
        for (cavab = s2.nextInt(); cavab < 1 || cavab > 5; cavab = s2.nextInt()) {
          System.out.print("ENTER A VALID ANSWER : ");
        }

        if (cavab == s.correctAnswer) {
          ++xal;
          System.out.println("Correct -- points : " + xal);
        } else {
          System.out.println("Wrong -- points : " + xal);
          String var10001 = s.answers[s.correctAnswer - 1];
          System.out.println("Correct answer was : " + var10001 + "\n");
        }
      }
    }

  }

  public static Sual[] randomiseArray(Sual[] arr, int start, int end) throws IOException {
    Random r = new Random();
    int size = end - start + 1;
    Sual[] newArray = new Sual[size];
    ArrayList<Integer> randomHolder = new ArrayList();
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
      newArray[i] = arr[(Integer) randomHolder.get(i)];
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

    return allQuestions;
  }

}
