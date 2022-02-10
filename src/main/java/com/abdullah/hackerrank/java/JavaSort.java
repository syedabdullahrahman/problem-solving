package com.abdullah.hackerrank.java;

/**
 * Created On:  12:24 AM 11-Feb-22
 *
 * @author Syed Abdullah
 */
import java.util.*;

// Way-2: Implementing Comparator<T> then make use of the  `int compare(T o1, T o2)

class Student2 implements Comparator<Student2> {
    private int id;
    private String fname;
    private double cgpa;
    public Student2(int id, String fname, double cgpa) {
        super();
        this.id = id;
        this.fname = fname;
        this.cgpa = cgpa;
    }
    public int getId() {
        return id;
    }
    public String getFname() {
        return fname;
    }
    public double getCgpa() {
        return cgpa;
    }

    @Override
    public int compare(Student2 o1, Student2 o2) {
        return Double.compare(o1.getCgpa(),o2.getCgpa());
    }
}

//Complete the code
public class JavaSort
{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int testCases = Integer.parseInt(in.nextLine());

        List<Student2> studentList = new ArrayList<Student2>();
        while(testCases>0){
            int id = in.nextInt();
            String fname = in.next();
            double cgpa = in.nextDouble();

            Student2 st = new Student2(id, fname, cgpa);
            studentList.add(st);

            testCases--;
        }

        // Way: 1

        Comparator studentComparator = Comparator
                .comparing(Student2::getCgpa,(o1, o2) -> -1 * Double.compare(o1,o2))
                .thenComparing(Student2::getFname,String::compareTo)
                .thenComparing(Student2::getId,(o1, o2) -> o2-o1);

        studentList.sort(studentComparator);

        for(Student2 st: studentList){
            System.out.println(st.getFname());
        }
    }
}




