//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.ArrayList;
import java.util.Random;

public class Sual {
    String question;
    int number;
    String[] answers = new String[5];
    int correctAnswer;

    public Sual(String head, String[] ans, int num) {
        this.number = num;
        this.question = head;
        this.answers = ans;
        this.mixAnswers();

        for(int e = 0; e < 5; ++e) {
            if (ans[e].contains("√")) {
                this.correctAnswer = e + 1;
                break;
            }
        }

    }

    public void show() {
        System.out.println(this.question + "\n");

        for(int i = 0; i < 5; ++i) {
            System.out.println(this.answers[i]);
        }

    }

    public void mixAnswers() {
        Random r = new Random();
        ArrayList<Integer> randomHolder = new ArrayList();
        int e = 0;
        int nextRandimNumber = r.nextInt(5);

        while(e < 5) {
            if (randomHolder.contains(nextRandimNumber)) {
                nextRandimNumber = r.nextInt(5);
            } else {
                randomHolder.add(nextRandimNumber);
                nextRandimNumber = r.nextInt(5);
                ++e;
            }
        }

        for(int i = 0; i < 5; ++i) {
            String temp = this.answers[i];
            this.answers[i] = this.answers[(Integer)randomHolder.get(i)];
            this.answers[(Integer)randomHolder.get(i)] = temp;
            randomHolder.set(randomHolder.indexOf(i), (Integer)randomHolder.get(i));
        }

    }

    public void correctAnswers() {
        for(char c = this.question.charAt(0); Character.isDigit(c); c = this.question.charAt(0)) {
            this.question = this.question.substring(1);
        }

        this.question = this.number + " " + this.question.strip();

        for(int i = 0; i < 5; ++i) {
            this.answers[i] = i + 1 + ". " + this.answers[i].substring(1).strip();
        }

    }
}
