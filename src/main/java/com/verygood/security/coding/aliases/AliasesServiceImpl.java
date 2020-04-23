package com.verygood.security.coding.aliases;

import com.verygood.security.coding.dao.AliasDao;
import com.verygood.security.coding.model.Alias;
import com.verygood.security.coding.util.CipherUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AliasesServiceImpl implements AliasesService {
    final String secretKey = "Very__$ecure#@Key!@";

    private final AliasDao db;

    @Autowired
    public AliasesServiceImpl(AliasDao db) {
        this.db = db;
    }

    @Override
    public String redact(String data) {
        String encryptedString = CipherUtils.encrypt(data, secretKey);
        String id = UUID.randomUUID().toString();

        Alias alias = new Alias();
        alias.setAlias(id);
        alias.setData(encryptedString);
        db.saveAlias(alias);

        return id;

    }

    @Override
    public String reveal(String uuid) {
        Optional<Alias> encrypted = db.getAliasByKey(uuid);
        return encrypted.map(alias -> CipherUtils.decrypt(alias.getData(), secretKey)).orElse(null);
    }
}
