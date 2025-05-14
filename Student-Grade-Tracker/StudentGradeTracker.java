import java.util.Scanner;

public class StudentGradeTracker{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of Students : ");
        int size = sc.nextInt();

        double[] gradeArray = new double[size];
        for(int i=0; i<size; i++){
            System.out.println("Enter gradeArray of Roll No. "+(i+1)+" : ");
            gradeArray[i] = sc.nextDouble();
        }

        double avg = 0.0;
        double sum = 0.0;
        for(int i=0; i<size; i++)
            sum += gradeArray[i];
        avg = sum/size;
        System.out.println("\nAverage gradeArray : "+avg);

        double min = gradeArray[0];
        double max = gradeArray[0];
        for(int i=0; i<size; i++){
            if(min>gradeArray[i])
                min = gradeArray[i];
            if(max<gradeArray[i])
                max = gradeArray[i];
        }
        System.out.println("Highest gradeArray : "+max);
        System.out.println("Lowest gradeArray : "+min);

        sc.close();
    }
}