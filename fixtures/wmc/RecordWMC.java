package wmc;

record RecordWMC(){

    public void m1(boolean x) {
        int a = 0;

        if(x) a++;
        else a--;
    }

    public void m2(boolean x) {
        int a = 0;

        if(x == true) a++;
        else a--;
    }
}