package rfc;


class RFC5 {
    public void x() {
        FunctionInterface fobj = (int x) -> System.out.println(2 * x + z());
        fobj.dummyFun(5);
    }

    public int z() {
        return 10;
    }
}

interface FunctionInterface {
    void dummyFun(int x);
}