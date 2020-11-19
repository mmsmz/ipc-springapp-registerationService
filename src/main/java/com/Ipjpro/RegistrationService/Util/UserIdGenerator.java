package com.Ipjpro.RegistrationService.Util;

import com.Ipjpro.RegistrationService.Controller.HomeController;
import com.Ipjpro.RegistrationService.Entity.StudentEntity;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserIdGenerator implements IdentifierGenerator {

    /**
     * The Logger
     */
    final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String prefix = "userid";
        Connection connection = session.connection();

        try {
            Statement statement=connection.createStatement();
            StudentEntity student = new StudentEntity();

            ResultSet rs=statement.executeQuery("select count(userid) as Id from users");

            if(rs.next())
            {
                int id=rs.getInt(1)+101;
                String generatedId = prefix + new Integer(id).toString();
                logger.info("Generated Id: " + generatedId);
                return generatedId;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

}
