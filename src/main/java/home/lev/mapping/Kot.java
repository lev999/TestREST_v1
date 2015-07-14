package home.lev.mapping;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Kot {
    private String name="Mursik";
    private int length=1000000;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "kot name="+name+" length="+length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
