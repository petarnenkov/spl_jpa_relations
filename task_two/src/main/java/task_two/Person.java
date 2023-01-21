package task_two;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public String vorname;
    public String nachname;
    public String email;
    public String username;
    public String passwort;

    @Transient
    public int age;

    // Wird von Hibernate befuellt
    @OneToMany (cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
    public List<Termin> terminplan = new ArrayList<Termin>();

    public Person(String vorname, String nachname)
    {
        this.vorname = vorname;
        this.nachname = nachname;
        this.age = 5;
    }

    public Person() {

    }

    public String toString()
    {
        return "HibernateUser: " + vorname +  " " + nachname + "(" + id + ")" + terminplan.size();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public List<Termin> getTerminplan() {
        return terminplan;
    }

    public void setTerminplan(List<Termin> terminplan) {
        this.terminplan = terminplan;
    }
}
