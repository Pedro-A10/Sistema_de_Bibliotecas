package entities;

public class Autor {

    private String name;
    private String nacionalidade;

    public Autor() {
    }

    public Autor(String name, String nacionalidade) {
        this.name = name;
        this.nacionalidade = nacionalidade;
    }

    public String getName() {
        return name;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }
}
