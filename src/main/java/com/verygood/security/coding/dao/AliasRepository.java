package com.verygood.security.coding.dao;

import com.verygood.security.coding.model.Alias;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("db")
public interface AliasRepository extends AliasDao, CrudRepository<Alias, String> {

    default boolean saveAlias(Alias alias) {
        save(alias);
        return true;
    }

    default Optional<Alias> getAliasByKey(String key) {
        return findById(key);
    }
}

