
public class test {

    private static String years(String year) {
        StringBuffer str = new StringBuffer();
        for (int i = 1; i < 4; i++) {
            str.append("'"+String.valueOf(Integer.parseInt(year) + i)+"'"+",");
            str.append("'"+String.valueOf(Integer.parseInt(year) - i)+"'"+",");
        }
        return str+"'"+year+"'";
    }
    public static void main(String[] args) {
        System.out.println(years(String.valueOf(6)));
    }
}
