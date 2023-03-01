package DZ.Task3;

import DZ.Task2.Student;
import java.util.Arrays;
import java.util.Comparator;

public class StudentService {
   public static Student bestStudent(Student[] students) {
        double maxAverageGrade = students[0].getAverageGrade();
        int indexOfMax = 0;

        for(int i = 1; i < students.length; ++i) {
            if (students[i].getAverageGrade() > maxAverageGrade) {
                maxAverageGrade = students[i].getAverageGrade();
                indexOfMax = i;
            }
        }

        return students[indexOfMax];
    }

    public static void sortBySurname(Student[] students) {

            Arrays.sort(students, new Comparator<Student>() {
                @Override
                public int compare(Student student1, Student student2) {
                    return student1.getSurname().compareTo(student2.getSurname());
                }
            });
            }
            private StudentService() {
    }
}
