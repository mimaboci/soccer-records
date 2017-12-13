/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import soccer.records.entity.AppUser;
import soccer.records.enums.AppRole;
import soccer.records.exceptions.dao.DataAccessExceptions;

/**
 *
 * @author Michaela Bocanova
 */
@Repository
public class AppUserDaoImpl extends DefaultCrudDaoImpl<AppUser,Long> implements AppUserDao {
       
    public AppUserDaoImpl() {        
        super(AppUser.class, Long.class);
    }
      
    @Override        
    public List<AppUser> findAll() throws DataAccessExceptions {
        try{
            return em.createQuery("select u from AppUser u", AppUser.class).getResultList();
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());          
        }        
    }
    
    @Override
    public AppUser findByUsername(String name) throws DataAccessExceptions {
        try {
            return em.createQuery("select u from AppUser u where u.username = :name", AppUser.class).setParameter("name", name).getSingleResult();
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }
    
    //works?
    @Override
    public List<AppUser> findByRole(AppRole role) throws DataAccessExceptions {
        try {
            return em.createQuery("select u from AppUser u where :role member of u.roles", AppUser.class).setParameter("role", role).getResultList();
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }

}
