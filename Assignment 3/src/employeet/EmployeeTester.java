package employeet;

import java.io.File;

public class EmployeeTester {

    public static void main(String[] args){
        FileIO info = new FileIO();

        File currFile = new File("employee.txt");

        info.fileReadMethod(currFile);
        info.setEmployeeRecord();
        info.getEmployeeRecord().setFirstName("Sarah");
        info.getEmployeeRecord().setLastName("Lewis");
        info.getEmployeeRecord().setAddress("45 NewPort Dr.");
        info.getEmployeeRecord().setCity("Merrickville");
        info.getEmployeeRecord().setPosition("sales");
        info.setIncreaseCount();
        System.out.println(info);
        File backFile = new File("emplBackUp.txt");

        info.writeFileMethod(backFile);
        info.fileReadMethod(backFile);
        System.out.println(info);

        File newFile = new File("employee.dat");

        info.writeObjectMethod(newFile);
        info.objectInputMethod(newFile);

        System.out.println("There are "+ info.getCounter() +" employee records saved in the array.\n" + "The records are: ");

        for(int c=0; c<info.getCounter(); c++){
            System.out.print(c+":\n");
            System.out.print(info.getSaveObjRecord(c));
            System.out.println();
        }
    }

}

