package com.verygood.security.coding;

import com.verygood.security.coding.aliases.AliasesService;
import com.verygood.security.coding.aliases.AliasesServiceImpl;
import com.verygood.security.coding.dao.MockAliasRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@Slf4j
public class AliasesServiceTest {
    private AliasesService aliasesService = new AliasesServiceImpl(new MockAliasRepository());

    @Test
    public void inputIsEncryted() {
        String input = "1111 2222 3333 4567";
        String redacted = aliasesService.redact(input);
        log.info("{} => {}", input, redacted);
        assertNotEquals(input, redacted);
    }

    @Test(expected = NullPointerException.class)
    public void inputIsValid() {
        String redacted = aliasesService.redact(null);
        fail();
    }

    @Test
    public void canDecryptAlias() {
        String input = "1111 2222 3333 4567";
        String redacted = aliasesService.redact(input);
        String revealed = aliasesService.reveal(redacted);
        assertEquals(input, revealed);
    }
}
