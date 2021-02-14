package cbo;

class Coupling11 {
    @MyField
    private A a;

    @MyField2(key = "sample")
    private A a;

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.Type)
    public @interface MySerializable {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Init {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface MyField {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface MyField2 {
        public String key() default "";
    }

    @Init
    public void m2(){

    }

    @MySerializable
    class InnerClass1 {
    }
}