package com.petstorelt.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.petstorelt.model.Pet;
import com.petstorelt.persisitence.dao.PetDao;

@Service
public class PetService implements IPetService {
	
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
	
	public List<PetDao> findPetById(String petId) {
        return this.namedJdbcTemplate.getJdbcOperations().query(
                "select * from pet where id=" + petId,
                new RowMapper<PetDao>() {
                    @Override
                    public PetDao mapRow(ResultSet rs, int rowNum) throws SQLException {
                    	PetDao petDao = new PetDao();
                    	petDao.setId(rs.getString("id"));
                    	petDao.setName(rs.getString("name"));
                    	petDao.setPrice(rs.getString("price"));
                        return petDao;
                    }
                });
    }

	public void addPet(Pet pet) {
		this.namedJdbcTemplate.getJdbcOperations().execute("insert into pet(id, name, price) values('"
				+ pet.getId() + "','" + pet.getName() + "','" + pet.getPrice() + "')");
		
	}

	public void removePet(String petId) {
		this.namedJdbcTemplate.getJdbcOperations().execute("delete from pet where id=" + petId);
	}
	
	public List<PetDao> getAll() {
        return this.namedJdbcTemplate.getJdbcOperations().query(
                "select * from pet order by id",
                new RowMapper<PetDao>() {
                    @Override
                    public PetDao mapRow(ResultSet rs, int rowNum) throws SQLException {
                    	PetDao petDao = new PetDao();
                    	petDao.setId(rs.getString("id"));
                    	petDao.setName(rs.getString("name"));
                    	petDao.setPrice(rs.getString("price"));
                        return petDao;
                    }
                });
    }
}
