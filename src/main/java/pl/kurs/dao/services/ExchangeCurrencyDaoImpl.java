package pl.kurs.dao.services;

import pl.kurs.dao.interfaces.ExchangeCurrencyDao;
import pl.kurs.model.ExchangeCurrency;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ExchangeCurrencyDaoImpl implements ExchangeCurrencyDao {

    private EntityManagerFactory factory;
    private EntityManager entityManager;

    public ExchangeCurrencyDaoImpl() {
        factory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        entityManager = factory.createEntityManager();
    }

    @Override
    public void save(ExchangeCurrency exchangeCurrency) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(exchangeCurrency);
        tx.commit();
    }

    @Override
    public ExchangeCurrency load(Long id) {
        return entityManager.find(ExchangeCurrency.class,id);
    }

    @Override
    public void cleanUp() {
        entityManager.close();
        factory.close();
    }
}
