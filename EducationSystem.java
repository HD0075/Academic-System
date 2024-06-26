import java.util.ArrayList;

interface EducationalInstitution {
    String displayInfo();
}

abstract class University implements EducationalInstitution {
    public String name;
    public int studentCount;
    public double tuitionFee;
    public ArrayList<Student> students;

    University(String name, double tuitionFee) {
        this.name = name;
        this.tuitionFee = tuitionFee;
        this.studentCount = 0;
        this.students = new ArrayList<>();
    }

    public boolean transferStudent(Student student, University university) {
        if (students.contains(student)) {
            students.remove(student);
            university.students.add(student);
            student.setUniversity(university);
            studentCount--;
            university.studentCount++;
            return true;
        }
        return false;
    }
}

class Student implements EducationalInstitution {
    private String name;
    public University university;
    private double balance;

    public Student(String name, double balance, University university) {
        this.name = name;
        this.balance = balance;
        if (balance >= university.tuitionFee) {
            this.university = university;
            university.students.add(this);
            university.studentCount++;
            this.balance -= university.tuitionFee;
        } else {
            System.out.println("Not Enough Balance");
        }
    }

    @Override
    public String displayInfo() {
        return "Name: " + name + "\nUniversity: " + university.name + "\nBalance: " + balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public University getUniversity() {
        return university;
    }

    public double getBalance() {
        return balance;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}

class SbuUniversity extends University {
    public SbuUniversity(String name, double tuitionFee) {
        super(name, tuitionFee);
    }

    @Override
    public String displayInfo() {
        return "SBU University\nTuition Fee: " + String.format("%.1f", tuitionFee) + "\nStudent Count: " + studentCount;
    }
}

class SutUniversity extends University {
    public SutUniversity(String name, double tuitionFee) {
        super(name, tuitionFee);
    }

    @Override
    public String displayInfo() {
        return "SUT University\nTuition Fee: " + String.format("%.1f", tuitionFee) + "\nStudent Count: " + studentCount;
    }
}

public class EducationSystem {
    public static void main(String[] args) {

        University sbu = new SbuUniversity("SBU", 4000.0);
        University sut = new SutUniversity("SUT", 5500.0);

        Student student1 = new Student("Ali", 6000.0, sbu);
        Student student2 = new Student("Zahra", 7000.0, sut);

        System.out.println(student1.displayInfo());
        System.out.println(student2.displayInfo());

        System.out.println(sbu.displayInfo());
        System.out.println(sut.displayInfo());

        boolean transferSuccess = sbu.transferStudent(student1, sut);
        System.out.println("Transfer success: " + transferSuccess);

        System.out.println(student1.displayInfo());
        System.out.println(sbu.displayInfo());
        System.out.println(sut.displayInfo());
    }
}