package logs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4JHelloWorld {
    private static final Logger logger = LogManager.getLogger("HelloWorld");
    public static void main(String[] args) {
        logger.info("Hello, World!");
        if (logger.isDebugEnabled()) {
            logger.debug("Logging in user " + user.getName() + " with birthday " + user.getBirthdayCalendar());
        }
        logger.debug("Logging in user {} with birthday {}", user.getName(), user.getBirthdayCalendar());

        // Java-8 style optimization: no need to explicitly check the log level:
        // the lambda expression is not evaluated if the TRACE level is not enabled
        logger.trace("Some long-running operation returned {}", () -> expensiveOperation());

        // pre-Java 8 style optimization: explicitly check the log level
        // to make sure the expensiveOperation() method is only called if necessary
        if (logger.isTraceEnabled()) {
            logger.trace("Some long-running operation returned {}", expensiveOperation());
        }

        // Fluent API/Builder Pattern
        logger.atError().withThrowable(exception).log("Unable to process request due to {}", code);
        logger.atInfo().withMarker(marker).withLocation().withThrowable(exception).log("Login for user {} failed", userId);
    }
}