package nestedblocks;

class NestedBlocks9 {

    public void m1() {
        int a = 0;
        int b = 0;

        while(a>0)b++;
        if(a > 0) {
            if (b > 0) {
                System.out.println("xxx");
            }
        }
    }

    public void m2() {
        int a = 0;
        int b = 0;

        for(;;)b++;
        if(a > 0) {
            if (b > 0) {
                System.out.println("xxx");
            }
        }
    }

    public void m3() {
        int a = 0;
        int b = 0;
        public int[] exampleVariableOne = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        for (int count : exampleVariableOne) a += exampleVariableOne[count];
        if(a > 0) {
            if (b > 0) {
                System.out.println("xxx");
            }
        }
    }

    public void m4() {
        int a = 0;
        int b = 0;

        do a++; while(a==2);
        if(a > 0) {
            if (b > 0) {
                System.out.println("xxx");
            }
        }
    }

    public void m5() {
        int a = 0;
        int b = 0;
        public int[] exampleVariableOne = {0, 1, 2, 3, 4, 5, 6, 7, 8};

        if(a > 0) {
            if (b > 0) {
                for (int count : exampleVariableOne) a += exampleVariableOne[count];
                System.out.println("xxx");
            }
        }
    }

    public void m6() {
        int a = 0;
        int b = 0;

        if(a > 0) {
            if (b > 0) {
                do a++; while(a==2);
                System.out.println("xxx");
            }
        }
    }
}