package com.Ecommerce.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommerce.model.Category;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>
{

	
    String GET_RECURSIVELY_ALL_SUBCATEGORIES_SQL = "WITH RECURSIVE ALL_SUBCATEGORIES(ID, PARENTID) AS (select c.id, c.parentid from app_category c where c.parentid is null union all select c.id, c.parentid from ALL_SUBCATEGORIES inner join app_category c on ALL_SUBCATEGORIES.id = c.parentid) select id, parentid from ALL_SUBCATEGORIES";

}
