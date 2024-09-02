package model;

public class Data {
    private int id;
    private String filename;
    private String path;
    private String email;
    public Data(int id, String filename, String path) {
        this.id = id;
        this.filename = filename;
        this.path = path;
    }

    public Data(int id, String filename, String path, String email) {
        this.id = id;
        this.filename = filename;
        this.path = path;
        this.email = email;
    }



    public int getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public String getPath() {
        return path;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
