package com.verygood.security.coding.aliases;

public interface AliasesService {

    String redact(String data);

    String reveal(String redacted);
}
