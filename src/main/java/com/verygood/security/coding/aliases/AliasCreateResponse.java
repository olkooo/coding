package com.verygood.security.coding.aliases;

import lombok.Data;

@Data
public class AliasCreateResponse {
    private String alias;

    public AliasCreateResponse(String alias) {
        this.alias = alias;
    }
}
