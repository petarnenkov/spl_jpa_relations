package task_two;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import task_two.Person;
import task_two.Termin;

public class Init
{
    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException
    {

        // Entitymanagerfactory besorgen
        EntityManagerFactory programmaticEmf;
        programmaticEmf = Persistence.createEntityManagerFactory("dev");
        EntityManager em = programmaticEmf.createEntityManager();

        // Tetsobjekt erzeugen
        Person gue = new Person("Guenther", "Hutter");
        gue.email = "guenther.hutter@fh-joanneum.at";
        gue.username = "ghutter";
        gue.passwort = "pass";
        System.out.println(gue);


        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        Termin weihnachten = new Termin(df.parse("24/12/2013"), 3, "Bescherung");
        Termin silvester = new Termin(df.parse("31/12/2013"), 3, "Silvester");

        gue.terminplan.add(weihnachten);
        gue.terminplan.add(silvester);


        // Eine Transaktion starten und sas Objekt speichern (cascade!!)
        em.getTransaction().begin();
        em.persist(gue);
        em.getTransaction().commit();

    }

}
