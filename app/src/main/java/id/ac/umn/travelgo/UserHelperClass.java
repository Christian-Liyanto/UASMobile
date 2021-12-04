package id.ac.umn.travelgo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserHelperClass {

    String user, em, pass, repass;

    public UserHelperClass() {

    }

    public UserHelperClass(String username, String email, String password, String repassword) {
        this.user = username;
        this.em = email;
        this.pass = password;
        this.repass = repassword;
    }

    public String getUsername() {
        return user;
    }

    public void setUsername(String username) {
        this.user = username;
    }

    public String getEmail() {
        return em;
    }

    public void setEmail(String email) {
        this.em = email;
    }

    public String getPassword() {
        return pass;
    }

    public void setPassword(String password) {
        this.pass = password;
    }

    public String getRepassword() {
        return repass;
    }

    public void setRepassword(String repassword) {
        this.repass = repassword;
    }

}
