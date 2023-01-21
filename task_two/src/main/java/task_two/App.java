package task_two;

import org.apache.commons.cli.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class App {

    static EntityManagerFactory factory;

    public static void main(String[] args) {

        Options options = new Options();
        options.addOption("list", false, "list all options of specified person");
        options.addOption("p", true, "specified person");
        options.addOption("add", false, "adds a new activity");
        options.addOption("t", true, "Name of activity");
        options.addOption("datum", true, "date of activity");

        CommandLineParser parser = new DefaultParser();
        CommandLine cli;
        try {
            cli = parser.parse(options, args);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        if (cli.hasOption("list")) {
            if (cli.hasOption("p")) {
                listPersonActivities(cli.getOptionValue("p"));
            } else {
                System.out.println("List requires to have a person specified with -p");
            }
        } else if (cli.hasOption("add")) {
            if (cli.hasOption("p") && cli.hasOption("t") && cli.hasOption("datum")) {
                addNewActivity(cli.getOptionValue("p"), cli.getOptionValue("t"), cli.getOptionValue("datum"));
            } else {
                System.out.println("Add requires to have a person specified - p, a activity name -t and a date -datum");
            }
        } else {
            System.out.println("Unknown command");
        }
    }

    public static void listPersonActivities(String personName) {
        List<Person> personList;
        factory = Persistence.createEntityManagerFactory("dev");
        EntityManager manager = factory.createEntityManager();
        Query getPerson = manager.createQuery("select p from Person as p where p.vorname like ?1");
        getPerson.setParameter(1, "%" + personName + "%");
        personList = getPerson.getResultList();

        if (personList != null) {
            for (Person person: personList) {
                System.out.println(person.terminplan.size());
                System.out.println("Termine von " + person.vorname + ":\n");
                for (Termin termin: person.terminplan) {
                    System.out.println(termin.toString());
                }
            }
        }
    }

    public static void addNewActivity(String personName, String activityName, String stringDate) {
        factory = Persistence.createEntityManagerFactory("dev");
        EntityManager manager = factory.createEntityManager();

        Person person = new Person(personName, "doe");
        person.email = "placeholder@email";
        person.passwort = "123456";
        person.username = "john_doe";
        Termin termin = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd.mm.yyyy");
            termin = new Termin(df.parse(stringDate), 3, activityName);
        } catch (java.text.ParseException e) {
            throw new RuntimeException(e);
        }
        person.terminplan.add(termin);
        manager.getTransaction().begin();
        manager.persist(person);
        manager.getTransaction().commit();
    }
}
