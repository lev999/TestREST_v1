package home.lev.data;

/**
 * Created by pc-users on 7/14/2015.
 */
public class RequestParams {
    private String one;
    private String two;
    private String three;

    @Override
    public String toString() {
        return one+two+three;
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }
}
