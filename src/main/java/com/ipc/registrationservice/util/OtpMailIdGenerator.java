package com.ipc.registrationservice.util;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OtpMailIdGenerator implements IdentifierGenerator {
    /**
     * The Logger
     */
    final Logger logger = LoggerFactory.getLogger(OtpMailIdGenerator.class);

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        String prefix = "omid";
        Connection connection = session.connection();

        try (Statement statement=connection.createStatement()){

            ResultSet rs=statement.executeQuery("select count(otpmailid) as Id from otpmail");

            if(rs.next())
            {
                int id=rs.getInt(1)+501;
                // +  new Integer(id).toString()
                String generatedId = prefix + Integer.toString(id) ;
                logger.info("Generated Id: {}", generatedId);
                return generatedId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
