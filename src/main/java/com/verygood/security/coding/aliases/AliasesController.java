package com.verygood.security.coding.aliases;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/aliases")
public class AliasesController {

    private final AliasesService aliasesService;

    @Autowired
    public AliasesController(AliasesService aliasesService) {
        this.aliasesService = aliasesService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AliasCreateResponse> createAlias(@RequestBody  AliasCreateRequest createRequest) {
        log.info("Creating alias for: " + createRequest.getSecret());
        String alias = aliasesService.redact(createRequest.getSecret());
        log.info("Alias created: {} => {}", createRequest.getSecret(), alias);
        return new ResponseEntity(new AliasCreateResponse(alias), HttpStatus.CREATED);
    }

}
