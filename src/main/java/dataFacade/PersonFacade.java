package dataFacade;

import entity.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Alek
 */
public class PersonFacade {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu2", null);
    EntityManager em = emf.createEntityManager();
    
    public Person addPerson(Person p){
        try{
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }
    
    public Person deletePerson(int id){
        try{
            em.getTransaction().begin();
            Person p = em.find(Person.class, id);
            em.remove(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }
    
    public Person updatePerson(Person p){
        //Skal nok bruge DTO her
        return null;
    }
    
}
