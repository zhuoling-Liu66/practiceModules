package practice;

public class StringPractie {
    public static void main(String[] args) {
        String c1 = "Hello";
        String c2 = new String("Hello");
        String c3 = new String("Hello");
        System.out.printf("c1: %s\nc2: %s\nc3: %s\n", c1, c2,c3);
        int hashCode1 = c1.hashCode();
        int hashCode2 = c2.hashCode();
        int hashCode3 = c3.hashCode();

        int address=System.identityHashCode(c1);
        int address2=System.identityHashCode(c2);
        int  address3=System.identityHashCode(c3);
        System.out.printf("hashCode1: %s\nhashCode2: %s\nhashcode3:%s\n", hashCode1, hashCode2,hashCode3);
        System.out.printf("address: %s\naddress2: %s\nadress3:%S", address, address2,address3);
        if(c1==c2) System.out.printf("1,地址相等");
        if(hashCode2==hashCode1) System.out.printf("2,hashcode相等");
        if(c1.equals(c2)) System.out.printf("");
    }
}
