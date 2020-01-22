package logs;

class LogStatements {

    public void m1() {
        log.info("log message");
    }

    public void m2() {
        // no log statements
    }

    public void m3() {
        log.info("asdasd").fluent("asdasdasd").fluent("adasd");

        log.error("oh no")
                .fluent("...");

        log.info("asdasd", 1);
    }
}