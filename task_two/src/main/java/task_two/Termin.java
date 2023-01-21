package task_two;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Termin
{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="DerStart")
    Date start;

    //@Column(name="DieDauer")
    int dauer;

    String beschreibung;

    public Termin(Date start, int d, String beschreibung)
    {
        this.start = start;
        this.dauer = d;
        this.beschreibung = beschreibung;
    }

    public Termin() {

    }

    @Override
    public String toString() {
        return "Name: " + beschreibung + "\n" +
                "Start: " + start.toString() + "\n" +
                "Dauer: " + dauer + "\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public int getDauer() {
        return dauer;
    }

    public void setDauer(int dauer) {
        this.dauer = dauer;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}
