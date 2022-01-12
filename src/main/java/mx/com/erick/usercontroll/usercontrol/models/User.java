package mx.com.erick.usercontroll.usercontrol.models;


import javax.persistence.*;

@Entity
@Table(name = "Users")

public class User {

    @Column(name = "id_user")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_user")
    private String name;
    @Column(name = "email_user")
    private String email;
    @Column(name = "pass_user")
    private String pass;

    public Long getId() {
        return id;
    }



    public User(String name, String email, String pass) {
        this.name = name;
        this.email = email;
        this.pass = pass;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
