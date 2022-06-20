package demoexam.testJar;

public class Calculation {
    public static void main(String[] args) {
        Calculation calculation = new Calculation();
        System.out.println(calculation.getQuantity(1, 2, 3, 4, 5));
    }

    public int getQuantity(int type1, int type2, float w, float l, float h)
    {
        double d = 1;

        switch (type1)
        {
            case 1:
                d*=1.1;
                break;
            case 2:
                d*=2.2;
                break;
            case 3:
                d*=3.3;
                break;
            default:
                return -1;
        }

        switch (type2)
        {
            case 1:
                d/=99;
                d*=100;
                break;
            case 2:
                d/=98;
                d*=100;
                break;
            default:
                return -1;
        }

        if (w <= 0 || l <= 0 || h <= 0) {
            return -1;
        }

        d*=w;
        d*=l;
        d*=h;

        return (int) Math.ceil(d);
    }
}