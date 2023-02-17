package Task2;

import java.util.Arrays;

public class Student {
    private String name;
    private String surname;
    private int[] grades;

    public Student(String name, String surname, int... grades) {
        this.name = name;
        this.surname = surname;
        this.fillGrades(grades);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int[] getGrades() {
        return this.grades;
    }

    public void setGrades(int... grades) {
        this.fillGrades(grades);
    }

    public void addGrade(int grade) {
        for(int i = 1; i < this.grades.length; ++i) {
            this.grades[i - 1] = this.grades[i];
        }

        this.grades[this.grades.length - 1] = grade;
    }

    public double getAverageGrade() {
        double sumOfGrades = 0.0;
        int countOfNulls = 0;

        for(int i = 1; i < this.grades.length; ++i) {
            if (this.grades[i] == 0) {
                ++countOfNulls;
            } else {
                sumOfGrades += (double)this.grades[i];
            }
        }

        return sumOfGrades / (double)(this.grades.length - countOfNulls);
    }

    private void fillGrades(int[] grades) {
        this.grades = new int[10];
        if (grades.length == this.grades.length) {
            this.grades = grades;
        } else {
            int i;
            if (grades.length < this.grades.length) {
                for(i = 0; i < grades.length; ++i) {
                    this.grades[i + 10 - grades.length] = grades[i];
                }
            } else {
                for(i = 0; i < this.grades.length; ++i) {
                    this.grades[i] = grades[i + grades.length - 10];
                }
            }
        }

    }

    public String toString() {
        String var10000 = this.surname;
        return "| " + var10000 + " | " + this.name + " | оценки: " + Arrays.toString(this.grades) + "\n";
    }
}


