package dataFacade;

import DTO.PersonDTO;
import entity.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class PersonFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu2", null);
    EntityManager em = emf.createEntityManager();

    public void addEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public Person addPerson(Person p) {
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }

    public Person deletePerson(int id) {
        try {
            em.getTransaction().begin();
            Person p = em.find(Person.class, id);
            em.remove(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }

    public Person updatePerson(Person p) {
        //Skal nok bruge DTO her

        try {
            em.getTransaction().begin();
            Person pers = em.find(Person.class, p.getId());

            if (pers != null) {
                pers = p;
                em.merge(pers);
                em.getTransaction().commit();
                return pers;
            }

        } finally {
            em.close();
        }
        return null;
    }

    public PersonDTO getPersonByID(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            //Person pers = null;
            PersonDTO persDTO = null;

            em.getTransaction().begin();
            Person pers = em.find(Person.class, id);

            if (pers != null) {
                persDTO = new PersonDTO(pers);
            }

            em.getTransaction().commit();
            return persDTO;

        } finally {
            em.close();
        }
    }
    
}
