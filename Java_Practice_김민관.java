/**
 * Project Name : Java_Practice_김민관
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
 * Lastest Version V-1.1 (2022/11/11)
 * update history------------------------------
 * V-0.2 creating Person class and Student class
 * V-0.3 creating StudentArray to Generalization
 * V-0.4 creating genStudent() function
 * V-0.5 add sequential_search function in StudentArray
 * V-0.6 add end index in StudentAraay class
 * V-0.6.1 remove() functions mechanism changed
 * V-0.7 modifying
 * V-0.8 add getStatistics()
 * V-0.9 quicksort argorithm add in studentArray class
 * V-1.0 print avg in printAll()
 * V-1.1 All bug fixed
 * issue
 */


import java.util.Scanner;
import java.lang.runtime.SwitchBootstraps;
import java.util.Random;

public class Java_Practice_김민관{
    static class Person{
        private int age;
        private String name;
    //------------------------------------------------------------
        public Person() //default constructor
        { 
            this.age = 0;
            this.name = "Anonymous";
        }
        public Person(int age) //constructor 
        { 
            this.age = age;
            this.name = "Anonymous";
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
        public void setAge(int age ) 
        {
            if(age < 20 || age > 30)//check the valid input
            {
                System.out.println("WrongInput");
                return;
            }
            this.age = age;
        } //age mutator
        public void setName(String name) {this.name = name;}  //name mutator
        public int getAge() {return this.age;} //age accessor
        public String getName(){return this.name;} //name accessor
        //funtions------------------------------------------------
        public void printPerson()
        {
            /*[Name : Anonymous, Age : 0] */
            System.out.print("[Name : " + this.name);
            System.out.print(", Age : " + this.age);
            System.out.println("]");
        }
    }

    static class Student extends Person{
        private int javaScore;
        private int argoScore;
        private double avg;
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
        public void setJavaScore(int javaScore) //java score mutato
        {
            if(javaScore < 0 || javaScore > 100) //check the valid input
            {
                System.out.println("WrongInput");
                return;
            }
            this.javaScore = javaScore;
            setAvg();
        } 
        public void setargoScore(int argoScore) //argorithm score mutator
        {
            if(argoScore < 0 || argoScore > 100)//check the valid input
            {
                System.out.println("WrongInput");
                return;
            }
            this.argoScore = argoScore; 
            setAvg();
        } 
        public void setAvg() {this.avg = (this.javaScore + this.argoScore) / 2;} //set avg
        public int getJavaScore() {return javaScore;} // javaScore accessor
        public int getArgoScore() {return argoScore;} // argorithm accessor
        public double getAvg() {return this.avg;} //get both avg
        //function----------------------------------------------
        public void printStudent() //printing in console
        {
            /*[Name : Ananymous, Age : 0, Java Score : 0, Argorithm Score : 0] */
            System.out.print("[Name : " + this.getName());
            System.out.print(", Age : " + this.getAge());
            System.out.print(", Java Score : " + this.javaScore);
            System.out.print(", Argorithm Score : " + this.argoScore);
            System.out.print(", Avarage of both Subject : " + this.avg);
            System.out.println("]");
        }
    }

    static class StudentArray
    {
        private Student[] pStudent; //student pointer
        private int num_students; //size of array
        private int capacity; //allocated memory size 
        private int end;
    //----------------------------------------------------------
        public StudentArray() //constructor
        {
            this.num_students = 0;
            this.pStudent = new Student[1];
            this.capacity = 1;
            this.end = -1;
        }

        public StudentArray(int size) //constructor
        {
            this.num_students = 0;
            this.capacity = size;
            this.end = -1;
            this.pStudent = new Student[size];
        }

        public int size() {return this.capacity;} //get capacity
        public int getNumStudent() {return this.num_students;} //get Number of Student
        public int endIndex() {return this.end;} //get end index
        public boolean emty() {return this.num_students == 0;} //check array is empty

        public Student at(int index) //get pStudent[index]
        {
            if (isValidIndex(index))
                return pStudent[index];
            else
                return null;
        }

        public void insert(int index, Student element) //insert element in array
        {
            if (this.num_students >= this.capacity)
            {
                System.out.println("ERROR:No More Capacity!!");
                return;
            }
            if (this.isValidIndex(index))
            {
                for(int j = this.end; j >= index; j--)
                    this.pStudent[j + 1] = this.pStudent[j]; //shift up the position
                this.pStudent[index] = element;
                //this.pStudent[index].printStudent();
                this.num_students++;
                this.end++;
            }
        }

        public void remove(int index) //remove element in array
        {
            Student temp = new Student();
            //swap index element for end index element
            temp = pStudent[index];
            pStudent[index] = pStudent[this.end];
            pStudent[this.end] = temp;
            //----------------------------------------------------
            this.num_students--; //derease students size 
            this.end--; //derease end point
        }

        public boolean isValidIndex(int index) //check valid index
        {
            if(index < 0 || index >= capacity) 
                return false; 
            else //if is valid index
                return true;
        }

        public void printByIndex(int index) //print one Student
        {
            pStudent[index].printStudent();
        }

        public void printAll() //print all
        {
            System.out.println("Printing all Students");
            for(int i = 0; i < this.num_students; i++)
                pStudent[i].printStudent();
            System.out.println("End of Student");
        }

        
        public int sequential_search(String key) //search Student
        {
            for(int index = 0; index < this.end; index ++) //serching by index
            {
                if (this.pStudent[index].getName().equals(key)) //if find
                    return index; //retuen index
            }
            return -1; //didn't find, return -1
        }

        public int _partition(Student[] array, int size, int left, int right, int pivotIndex, int order)
        {
            Student temp;
            Student pivotValue;
            int newPI = 0;

            pivotValue = array[pivotIndex];
            array[pivotIndex] = array[right];
            array[right] = pivotValue; //move pivot value to right

            newPI = left; //searching start index

            for(int i = left; i <= (right - 1); i++)
            {
                if(order == 0) //0 is increase
                {
                    if(array[i].getAvg() <= pivotValue.getAvg())
                    {
                        temp = array[i];
                        array[i] = array[newPI];
                        array[newPI] = temp; //exchange the index
                        newPI = newPI + 1;
                    }
                }
                else //1 is decrease
                {
                    if(array[i].getAvg() >= pivotValue.getAvg())
                    {
                        temp = array[i];
                        array[i] = array[newPI];
                        array[newPI] = temp; //exchange the index
                        newPI = newPI + 1;
                    }
                } 
            } //end of for
            pivotValue = array[right];
            array[right] = array[newPI];
            array[newPI] = pivotValue; //change index 
            return newPI; //return new pivot point
        }

        public void _quick_sort(Student[] array, int size, int left, int right, int order)
        {
            int pI, newPI; //pivot index
            if(left > right)
                return;
            else
                pI = (left + right) / 2; //middle point will be pivot 
            newPI = _partition(array, size, left, right, pI, order); //get partition and get newPI
                
            if (left < (newPI - 1)) //left side
                _quick_sort(array, size, left, newPI - 1, order);
            if(right > (newPI + 1)) //right side
                _quick_sort(array, size, newPI + 1, right, order);
            //end of this recuresive
        }



        public void sort(int order)
        {   
            _quick_sort(this.pStudent, this.num_students, 0, this.end, order);
        }
    }



    public static Student genStudent() //generate Studendt imformation
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
        temp = new Student(age, javaScore, argoScore, name); //make object
        return temp;
    }

    public static String randName()
    {
        String name;
        Random rnd = new Random(); //random buffer
        StringBuffer temp = new StringBuffer(); //temp string buffer
        int length; //name lenght
        length = ((int)(Math.random()*1000) % 4) + 4;// 4~7 length name
        for (int i = 0; i < length; i++)
        {
            //if(rnd.nextBoolean())
            //{
            if(i == 0)
            {
                //System.out.println("first name gen");
                temp.append((char)((int)(rnd.nextInt(26)) + 65));// A ~ Z
                //System.out.println("first name : " + temp);
            }
            else
                temp.append((char)((int)(rnd.nextInt(26)) + 97));// a ~ z
        }
        //System.out.println("gen Name : " + temp); //check the name
        name = temp.toString(); //string buffer to string
        return name;
    }

    public static void printing(StudentArray students) //menu 1
    {
        System.out.println("//--------------List of Student---------------------------------");
        students.printAll();
    }

    public static void Inserting(StudentArray students) //menu 2
    {
        Scanner scanner = new Scanner(System.in);
        String name;
        int age;
        int javaScore;
        int argoScore;
        char YN;
        Student temp = new Student(); //make new object
        System.out.println("//--------------Inserting---------------------------------");
        while(true)
        {
            System.out.print("Input name >>"); //input name
            name = scanner.next();
            temp.setName(name);
            System.out.print("Input age >>"); //input age 
            age = scanner.nextInt();
            temp.setAge(age);
            System.out.print("Input java score >>"); //input java score
            javaScore = scanner.nextInt();
            temp.setJavaScore(javaScore);
            System.out.print("Input argorithm score >>"); //input argorithm score
            argoScore = scanner.nextInt();
            temp.setargoScore(argoScore);
            System.out.println("-------------------------------------------------------"); //check
            System.out.print("[Name : " + name); 
            System.out.print(", Age : " + age);
            System.out.print(", Java Score : " + javaScore);
            System.out.print(", Argorithm Score : " + argoScore);
            System.out.println("]");
            System.out.print("Are you sure the student to add? (Y/N) >> "); //Y / N
            YN = scanner.next().charAt(0);
            if(YN == 'Y') //if input Y
            {
                students.insert(students.endIndex(), temp); //insert in array
                break;
            }
            System.out.print("Do you want to continue adding? (Y/N) >> "); //asking continue
            YN = scanner.next().charAt(0);
            if(YN == 'Y') //if input y
            {
                continue;
            }
            else //or continue
                return;
        }
    }
        

    public static void Deleting(StudentArray students) //menu 3
    {
        Scanner scanner = new Scanner(System.in);
        String name;
        int temp;
        char YN;
        while(true)
        {
            System.out.print("Choose the student you want to delet(name) >> ");
            name = scanner.next(); //input name
            System.out.print(name);
            temp = students.sequential_search(name); //search the student
            if(temp == -1) //if student is not exist
            {
                System.out.println("Wrong Name ");
            }
            else // else student is exist
            {
                System.out.print("You choose ");
                students.printByIndex(temp);
                System.out.print("Are you sure to delete this Student? (Y/N)>> ");
                YN = scanner.next().charAt(0);
                if(YN == 'Y') //if input y
                {
                    students.remove(temp); //remove
                    break;
                }

            }

            System.out.print("Do you want to continue deleting? (Y/N) >> "); //asking continue
            YN = scanner.next().charAt(0);
            if(YN == 'Y') //if input y
            {
                continue;
            }
            else //or continue
                return;
        }
    }

    public static void Searching(StudentArray students) //menu 4
    {
        Scanner scanner = new Scanner(System.in);
        String name;
        int temp;
        char YN;
        System.out.println("//--------------Searching---------------------------------");
        while(true)
        {
            System.out.print("Searching the student(name) >> ");
            name = scanner.next(); //input name
            System.out.print(name);
            temp = students.sequential_search(name); //search the student
            if(temp == -1) //if student is not exist
            {
                System.out.println("No students with that name...");
            }
            else // else student is exist
            {
                System.out.print("You choose ");
                students.printByIndex(temp);
            }
            System.out.print("Do you want to continue Searching? (Y/N) >> "); //asking continue
            YN = scanner.next().charAt(0);
            if(YN == 'Y') //if input y
            {
                continue;
            }
            else //or continue
                return;
        }

    }

    public static boolean ModifyingCheck() //to shorten the length of code
    {
        Scanner scanner = new Scanner(System.in);
        char YN;
        System.out.print("Are you sure to modify this Student? (Y/N)>> "); //check
        YN = scanner.next().charAt(0);
        if(YN == 'Y') //if input y
        {
            return true;
        }
        return false;

    }

    public static void Modifying(StudentArray students)
    {
        Scanner scanner = new Scanner(System.in);
        String name;
        int temp;
        int menu = 0;
        int age;
        int javaScore;
        int argoScore;
        char YN; //Y or N

        System.out.println("//--------------Modifying---------------------------------");
        while(true)
        {
            System.out.print("Choose the student you want to modifying(name) >> ");
            name = scanner.next(); //input name
            System.out.println(name);
            temp = students.sequential_search(name); //search the student
            if(temp == -1) //if student is not exist
            {
                System.out.println("Wrong Name ");
            }
            else // else student is exist
            {
                System.out.print("You choose ");
                students.printByIndex(temp);
                while(true)
                {
                    System.out.println("Modifialbe List----------------------------");
                    System.out.println(" 1. Name\n 2. Age\n 3. Java Score\n 4. Argorithm Score");
                    System.out.print("Choose menu >> (1 ~ 4, -1 to exit)");
                    menu = scanner.nextInt(); //input name
                    switch(menu)
                    {
                        case 1: System.out.print("Insert new name >> ");
                                name = scanner.next(); //input name
                                if(ModifyingCheck())
                                    students.at(temp).setName(name);
                                break;
                        case 2: System.out.print("Insert new age >> ");
                                age = scanner.nextInt(); //input age
                                if(ModifyingCheck())
                                    students.at(temp).setAge(age);
                                break;
                        case 3: System.out.print("Insert new Java Score >> ");
                                javaScore = scanner.nextInt(); //input java score
                                if(ModifyingCheck())
                                    students.at(temp).setJavaScore(javaScore);
                                break;
                        case 4: System.out.print("Insert new Aragorithm Score>> ");
                                argoScore = scanner.nextInt(); //input argorithm score
                                if(ModifyingCheck())
                                    students.at(temp).setargoScore(argoScore);
                                break;
                        case -1: break; //break
                        default : continue;
                    } //end of switch
                    if(menu == -1) //break
                        break;
                } //end of while
            }//end of if

            if(menu == -1)
                return;
            else
            {
                System.out.print("Do you want to continue modifying? (Y/N) >> "); //asking continue
                YN = scanner.next().charAt(0);
                if(YN == 'Y') //if input y
                {
                    continue;
                }
                else //or continue
                    return;
            }
        } //end of while
    }

    public static void GetStatistics(StudentArray students)
    {
        int size = students.num_students;
        int javaTotal = 0;
        int argoTotal = 0;
        double bothTotal = 0;
        double javaAvg = 0;
        double argoAvg = 0;
        double totalAvg = 0;
        for(int i = 0; i < students.endIndex(); i++) //make sum 
        {
            javaTotal += students.at(i).getJavaScore(); //java score
            argoTotal += students.at(i).getArgoScore(); //argorithm score sum
            bothTotal += students.at(i).getAvg(); //avg sum
        }
        javaAvg = javaTotal / size; //get avg
        argoAvg = argoTotal / size;
        totalAvg = bothTotal / size;

        System.out.println("//--------------Avarage---------------------------------");
        System.out.println("The Avrage Score of java is : " + javaAvg);
        System.out.println("The Avarage Score of argorithm : " + argoAvg);
        System.out.println("The Avarage Socre of Individual's Avarage : " + totalAvg);
    }
    
    public static void GetTotal(StudentArray students)
    {
        int size = students.num_students;
        int javaTotal = 0;
        int argoTotal = 0;
        double bothTotal = 0;
        for(int i = 0; i < students.endIndex(); i++) //make sum
        {
            javaTotal += students.at(i).getJavaScore(); //java score
            argoTotal += students.at(i).getArgoScore(); //argorithm score sum
            bothTotal += students.at(i).getAvg(); //avg sum
        }

        System.out.println("//--------------total---------------------------------");
        System.out.println("The Total score of java is : " + javaTotal);
        System.out.println("The Total score of argorithm : " + argoTotal);
        System.out.println("The Total score Individual's Avarage : " + bothTotal);
    }

    public static void PrintWithSort(StudentArray students)
    {

    
        Scanner menuInput = new Scanner(System.in);
        int menu;

        while(true)
        {
            System.out.println("//--------------Sorted List---------------------------------");
            System.out.println("Chose sorting order");
            System.out.println("1. increase");
            System.out.println("2. decrease");
            System.out.println("3. return to menu");
            System.out.print(" >> ");
            menu = menuInput.nextInt();
            switch(menu)
            {
                case 1: students.sort(0);
                        break;
                case 2: students.sort(1);
                        break;
                case 3: break;
                default : continue;
            }
            if (menu == 3)
                break;
            else
            {
                students.printAll();
                break;
            }
        }
    }

    public static void main(String[] args) {
        int size = 40;
        System.out.println("hello World");
        System.out.println("Creat " + size + " Students");
        StudentArray Students = new StudentArray(size);
        System.out.println("Generate random Students...");
        for(int i = 0; i < 35; i++)
        {
            Students.insert(i, genStudent());
        }

        //Students.printAll();
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
            System.out.println("8. Sorting List");
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
                default: System.out.print("Wrong Menu...");
                        break;
            }
        }while(menu!= 9);
    }
}
