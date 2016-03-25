package com.petstorelt.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.petstorelt.model.Pet;

@Component
@Transactional(propagation = Propagation.SUPPORTS)
public class PetStoreDao {

	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public void addPet(Pet pet) {
		this.namedJdbcTemplate.getJdbcOperations().execute("insert into pet(id, name, price) values('" + pet.getId()
				+ "','" + pet.getName() + "','" + pet.getPrice() + "')");
	}

	public List<Pet> findPetById(String petId) {
		return this.namedJdbcTemplate.getJdbcOperations().query("select * from pet where id='" + petId + "'",
				new RowMapper<Pet>() {
					@Override
					public Pet mapRow(ResultSet rs, int rowNum) throws SQLException {
						Pet pet = new Pet(rs.getString("id"), rs.getString("name"), rs.getString("price"));
						return pet;
					}
				});
	}

	public List<Pet> getAll() {
		return this.namedJdbcTemplate.getJdbcOperations().query("select * from pet order by id", new RowMapper<Pet>() {
			@Override
			public Pet mapRow(ResultSet rs, int rowNum) throws SQLException {
				Pet pet = new Pet(rs.getString("id"), rs.getString("name"), rs.getString("price"));
				return pet;
			}
		});
	}

	public void deletePet(String petId) {
		this.namedJdbcTemplate.getJdbcOperations().execute("delete from pet where id='" + petId + "'");
	}
}
