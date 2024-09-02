package views;

import dao.DataDAO;
import dao.UserDAO;
import model.Data;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class UserView {


    private String name;
    private String email;

    public UserView(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void home() {

        do {
            System.out.println("Welcome " + this.name);
            System.out.println("Press 1 to show hidden files\nPress 2 to hide a new file\nPress 3 to unhide a " +
                    "file\nPress 0 to Exit");
            Scanner sc = new Scanner(System.in);
            int ch = Integer.parseInt((sc.nextLine()));
            switch (ch) {
                case 1 -> {
                    try {
                        List<Data> files = DataDAO.getallFiles(this.email);
                        System.out.println("Id  - Filename");
                        for (Data file : files) {
                            System.out.println(file.getId() + " - " + file.getFilename());
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                case 2 -> {
                    System.out.println("Enter the fiel path ");
                    String path = sc.nextLine();
                    File f = new File(path);
                    Data file = new Data(0, f.getName(), path, this.email);
                    try {
                        DataDAO.hideFile(file);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                case 3 -> {
                    try {
                        List<Data> files = DataDAO.getallFiles(this.email);
                        System.out.println("Id  - Filename");
                        for (Data file : files) {
                            System.out.println(file.getId() + " - " + file.getFilename());
                        }
                        System.out.println("Enter the id of file to unhide");
                        int id = Integer.parseInt(sc.nextLine());
                        boolean isValid = false;
                        for(Data file : files)
                        {
                            if(file.getId() == id)
                            {
                                isValid = true;
                                break;
                            }
                        }
                        if(isValid) {
                            try {
                                int ans = DataDAO.unhide(id);
                                System.out.println(ans == 1 ? "Unhide Successfully" : "There is problem in Unhidding the " +
                                        "file...");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else
                        {
                            System.out.println("Id is not Valid...");
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                case 4 -> {
                    System.exit(0);
                }
            }

        }
        while (true);
    }


}
