package com.verygood.security.coding.dao;

import com.verygood.security.coding.model.Alias;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AliasDao {
    boolean saveAlias(Alias alias);
    Optional<Alias> getAliasByKey(String key);
}
