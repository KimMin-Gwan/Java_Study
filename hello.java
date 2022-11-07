/**
 * Project Name : Java_Practice_1
 * Detail 
 *  1. creat random student
 *  2. insert student
 *  3. search student
 *  4. modify student detail
 *  5. print student array
 *  6. get statistics (total score, average of score)
 *  7. get total score
 *  8. sorting by score and print
 *  - using three types class
 * Author : Kim Mingwan (21912229, Information and Communication Engineering)
 * Lastest Version 0.5
 * update history------------------------------
 * V-0.2 creating Person class and Student class
 * V-0.3 creating StudentArray to Generalization
 * V-0.4 creating genStudent() function
 * V-0.5 add sequential_search function in StudentArray
 * V-0.6 
 * 
 * 
 * issue
 * remove()사용 후 인덱스가 비어서 정상 출력이 안됨  = 해결법 : binary tree 형태 사용
 */


import java.util.Scanner;
import java.lang.runtime.SwitchBootstraps;
import java.util.Random;

public class hello{
    static class Person{
        private int age;
        private String name;
    //------------------------------------------------------------
        public Person() //default constructor
        { 
            this.age = 0;
            this.name = "Ananymous";
        }
        public Person(int age) //constructor 
        { 
            this.age = age;
            this.name = "Ananymous";
        }
        public Person (String name) //constructor
        {
            this.age = 0;
            this.name = name;
        }
        public Person(int age, String name) //constructor
        { 
            this.age = age;
            this.name = name;
        }
        //Utils---------------------------------------------------
        public void setAge(int age ) {this.age = age;} //age mutator
        public void setName(String name) {this.name = name;}  //name mutator
        public int getAge() {return this.age;} //age accessor
        public String getName(){return this.name;} //name accessor
        //funtions------------------------------------------------
        public void printPerson()
        {
            /*[Name : Ananymous, Age : 0] */
            System.out.print("[Name : " + this.name);
            System.out.print(", Age : " + this.age);
            System.out.println("]");
        }
    }

    static class Student extends Person{
        private int javaScore;
        private int argoScore;
        private int avg;
    //-----------------------------------------------------------
        public Student() //default constructor
        {
            this.javaScore = 0;
            this.argoScore = 0;
            this.avg = 0;
        }
        public Student(int javaScore, int argoScore) //constructor
        {
            this.javaScore = javaScore;
            this.argoScore = argoScore;
            setAvg();
        }
        public Student(int age, int javaScore, int argoScore, String name)
        {
            setAge(age);
            setJavaScore(javaScore);
            setargoScore(argoScore);
            setName(name);
        }

        //utils-------------------------------------------------
        public void setJavaScore(int javaScore) {this.javaScore = javaScore; setAvg();} //java score mutato
        public void setargoScore(int argoScore) {this.argoScore = argoScore; setAvg();} //argorithm score mutator
        public void setAvg() {this.avg = (this.javaScore + this.argoScore) / 2;}
        public int getJavaScore() {return javaScore;} // javaScore accessor
        public int getArgoScore() {return argoScore;} // argorithm accessor
        //function----------------------------------------------
        public void printStudent() //printing in console
        {
            /*[Name : Ananymous, Age : 0, Java Score : 0, Argorithm Score : 0] */
            System.out.print("[Name : " + this.getName());
            System.out.print(", Age : " + this.getAge());
            System.out.print(", Java Score : " + this.javaScore);
            System.out.print(", Argorithm Score : " + this.argoScore);
            System.out.println("]");
        }
    }

    static class StudentArray
    {
        private Student[] pStudent; //student pointer
        private int num_students; //size of array
        private int capacity; //allocated memory size 
    //----------------------------------------------------------
        public StudentArray() //constructor
        {
            this.num_students = 1;
            this.pStudent = new Student[1];
            this.capacity = 1;
        }

        public StudentArray(int size) //constructor
        {
            this.num_students = 0;
            this.capacity = size;
            this.pStudent = new Student[size];
        }

        public int size() {return num_students;}
        public boolean emty() {return num_students == 0;}
        public void insert(int index, Student element) 
        {
            if (this.num_students >= this.capacity)
            {
                System.out.println("ERROR:No More Capacity!!");
                return;
            }
            if (this.isValidIndex(index))
            {
                for(int j = this.num_students - 1; j >= index; j--)
                    this.pStudent[j + 1] = this.pStudent[j]; //shift up the position
                this.pStudent[index] = element;
                //this.pStudent[index].printStudent();
                this.num_students++;
            }
        }

        public void remove(int index)
        {
            this.pStudent[index] = null;
            this.num_students--;
        }

        public boolean isValidIndex(int index) //check valid index
        {
            if(index < 0 || index >= capacity) 
                return false; 
            else //if is valid index
                return true;
        }

        public void printByIndex(int index)
        {
            pStudent[index].printStudent();
        }

        public void printAll()
        {
            System.out.println("Printing all Students");
            for(int i = 0; i < num_students; i++)
                pStudent[i].printStudent();
            System.out.println("End of Student");
        }

        
        public int sequential_search(String key)
        {
            for(int index = 0; index < this.num_students; index ++)
            {
                if (this.pStudent[index].getName().equals(key))
                    return index;
            }
            return -1;
        }
    }



    public static Student genStudent()
    {
        Student temp;
        int age;
        int javaScore;
        int argoScore;
        String name;

        age = ((int)(Math.random()*1000) % 10) + 20; //20 ~ 29
        javaScore = (int)(Math.random()*100); //0 ~ 100
        argoScore = (int)(Math.random()*100); //0 ~ 100
        name = randName(); //ex) Kimmingwan
        temp = new Student(age, javaScore, argoScore, name);
        return temp;
    }

    public static String randName()
    {
        String name;
        Random rnd = new Random(); //random buffer
        StringBuffer temp = new StringBuffer(); //temp string buffer
        int length;
        length = ((int)(Math.random()*1000) % 4) + 4;// 4~7
        for (int i = 0; i < length; i++)
        {
            //if(rnd.nextBoolean())
            //{
            if(i == 0)
            {
                //System.out.println("first name gen");
                temp.append((char)((int)(rnd.nextInt(26)) + 65));// a ~ z
                //System.out.println("first name : " + temp);
            }
            else
                temp.append((char)((int)(rnd.nextInt(26)) + 97));// a ~ z
        }
        //System.out.println("gen Name : " + temp); //check the name
        name = temp.toString();
        return name;
    }

    public static void printing(StudentArray students)
    {
        students.printAll();
    }

    public static void Inserting(StudentArray students)
    {
        
    }

    public static void Deleting(StudentArray students)
    {
        Scanner scanner = new Scanner(System.in);
        String name;
        int temp;
        char YN;
        while(true)
        {
            System.out.print("Choose the student you want to delet(name) >> ");
            name = scanner.nextLine(); //input name
            System.out.print(name);
            temp = students.sequential_search(name); //search the student
            if(temp == -1) //if student is not exist
            {
                System.out.println("Wrong Name "); 
                continue;
            }
            else // else student is exist
            {
                System.out.print("You choose ");
                students.printByIndex(temp);
                System.out.print("If you want to delete this Student, input y >> ");
                YN = scanner.next().charAt(0);
                if(YN == 'y') //if input y
                {
                    students.remove(temp); //remove
                    break;
                }
                else //or continue
                    break;
            }
        }
    }

    public static void Searching(StudentArray students)
    {

    }

    public static void Modifying(StudentArray students)
    {

    }

    public static void GetStatistics(StudentArray students)
    {

    }
    
    public static void GetTotal(StudentArray students)
    {

    }

    public static void PrintWithSort(StudentArray students)
    {

    }

    public static void main(String[] args) {
        int size = 20;
        System.out.println("hello World");
        System.out.println("Creat " + size + " Students");
        StudentArray Students = new StudentArray(size);
        System.out.println("Generate random Students...");
        for(int i = 0; i < size; i++)
        {
            Students.insert(i, genStudent());
        }
        //여기까지는 정상작동함

        Students.printAll();
        /*
         * // ** Managing Java programing Student ** //
         * ------------------Menu----------------------
         * 1. Print all Student
         * 2. Insert Student
         * 3. Delete Student
         * 4. Search Student
         * 5. Modify Student
         * 6. Get Statistics
         * 7. Get Total Score
         * 8. Print Score-sequential
         * 9. Exit
         * --------------------------------------------
         */
        int menu = 0;
        Scanner menuInput = new Scanner(System.in);
        do
        {
            System.out.println(" //** Managing Java programing Student ** //");
            System.out.println("----------------Menu------------------------");
            System.out.println("1. Pring all Student");
            System.out.println("2. Insert Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. Modify Student");
            System.out.println("6. Get Statistics");
            System.out.println("7. Get Total Score");
            System.out.println("8. Print Score-sequentail");
            System.out.println("9. Exit");
            System.out.print("Choose menu >> ");
            menu = menuInput.nextInt();
            System.out.println("----------------------------------------------");
            switch (menu)
            {
                case 1: printing(Students);
                        break;
                case 2: Inserting(Students);
                        break;
                case 3: Deleting(Students);
                        break;
                case 4: Searching(Students);
                        break;
                case 5: Modifying(Students);
                        break;
                case 6: GetStatistics(Students);
                        break;
                case 7: GetTotal(Students);
                        break;
                case 8: PrintWithSort(Students);
                        break;
                case 9: System.exit(0);
                        break;

            }
            


            

        }while(menu!= 9);


    }
}