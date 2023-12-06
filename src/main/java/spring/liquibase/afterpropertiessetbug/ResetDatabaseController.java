package spring.liquibase.afterpropertiessetbug;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResetDatabaseController {

    private final SpringLiquibase springLiquibase;

    public ResetDatabaseController(final SpringLiquibase springLiquibase) {
        this.springLiquibase = springLiquibase;
    }

    @GetMapping("/resetDatabase")
    public String resetDatabase() throws LiquibaseException {
        springLiquibase.setClearCheckSums(false);
        springLiquibase.setDropFirst(true);

        springLiquibase.afterPropertiesSet();

        springLiquibase.setClearCheckSums(true);
        springLiquibase.setDropFirst(false);

        return "Database has been reset successfully";
    }
}
