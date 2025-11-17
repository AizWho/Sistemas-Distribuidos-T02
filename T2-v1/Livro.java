// Arquivo: Livro.java

import java.io.Serializable;

public class Livro implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public String isbn;
    public String titulo;
    public String autor;
    public boolean disponivel;

    public Livro(String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true; // Por padrão
    }
    
    @Override
    public String toString() {
        return "Livro [ISBN=" + isbn + ", Título=" + titulo + ", Autor=" + autor + ", Disponível=" + disponivel + "]";
    }
}