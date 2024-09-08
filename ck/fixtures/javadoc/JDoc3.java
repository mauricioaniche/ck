package javadoc;

/**
 * class javadoc should be ignored for now
 */
class JDoc3 {

    public void m0() {

        Thread t = new Thread()
        {
            /**
             * javadoc here
             * ignore!!
             */
            public void run()
            {
                System.out.println("Child Thread");
            }
        };
    }

    /**
     * some description here
     * line 2
     * line 3
     * line 4
     */
    public void m1() {
        Thread t = new Thread()
        {
            /**
             * javadoc here
             * ignore!!
             */
            public void run()
            {
                System.out.println("Child Thread");
            }
        };
    }

}