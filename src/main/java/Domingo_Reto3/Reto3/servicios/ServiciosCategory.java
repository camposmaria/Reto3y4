/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domingo_Reto3.Reto3.servicios;

import Domingo_Reto3.Reto3.Modelo.Category;
import Domingo_Reto3.Reto3.Repositorio.RepositorioCategory;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ServiciosCategory {
    @Autowired
    private RepositorioCategory metodosCrud;

    public List<Category> getAll() {
        return metodosCrud.getAll();
    }

    public Optional<Category> getCategory(int CategoryId) {
        return metodosCrud.getCategory(CategoryId);
    }

    public Category save(Category category) {
        if (category.getId()== null) {
            return metodosCrud.save(category);
        } else {
            Optional<Category> categoria1 = metodosCrud.getCategory(category.getId());
            if (categoria1.isEmpty()) {
                return metodosCrud.save(category);
            } else {
                return category;
            }
        }
    }

    public Category update(Category category){
        if(category.getId()!=null){
            Optional<Category>g=metodosCrud.getCategory(category.getId());
            if(!g.isEmpty()){
                if(category.getDescription()!=null){
                    g.get().setDescription(category.getDescription());
                }
                if(category.getName()!=null){
                    g.get().setName(category.getName());
                }
                return metodosCrud.save(g.get());
            }
        }
        return category;
    }
    public boolean deletecategory(int categoryId){
        Boolean d=getCategory(categoryId).map(category -> {
            metodosCrud.delete(category);
            return true;
        }).orElse(false);
        return d;
    }
}
