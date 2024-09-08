package MethodInvocation;

class Simple2 {
    public void x() {
        System.out.println((z() + 5));
    }

    public int z() {
        return 10;
    }
}