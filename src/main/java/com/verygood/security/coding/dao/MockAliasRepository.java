package com.verygood.security.coding.dao;

import com.verygood.security.coding.model.Alias;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository("mock")
public class MockAliasRepository implements AliasDao {
    private final Map<String, String> db = new ConcurrentHashMap<>();

    @Override
    public boolean saveAlias(Alias alias) {
        db.put(alias.getAlias(), alias.getData());
        return false;
    }

    @Override
    public Optional<Alias> getAliasByKey(String key) {
        if(db.containsKey(key)) {
            Alias alias = new Alias();
            alias.setAlias(key);
            alias.setData(db.get(key));
            return Optional.of(alias);
        }
        return Optional.empty();
    }


}
