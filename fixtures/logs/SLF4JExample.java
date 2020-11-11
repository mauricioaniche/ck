package logs;

class SLF4JExample {
    void foo() {
        int newT = 15;
        int oldT = 16;

        // using traditional API
        logger.debug("Temperature set to {}. Old temperature was {}.", newT, oldT);

        // using fluent API, add arguments one by one and then log message
        logger.atDebug().addArgument(newT).addArgument(oldT).log("Temperature set to {}. Old temperature was {}.");

        // using fluent API, log message with arguments
        logger.atDebug().log("Temperature set to {}. Old temperature was {}.", newT, oldT);

        // using fluent API, add one argument and then log message providing one more argument
        logger.atDebug().addArgument(newT).log("Temperature set to {}. Old temperature was {}.", oldT);

        // using fluent API, add one argument with a Supplier and then log message with one more argument.
        // Assume the method t16() returns 16.
        logger.atDebug().addArgument(() -> t16()).log(msg, "Temperature set to {}. Old temperature was {}.", oldT);
    }
}