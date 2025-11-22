package modelo;

public class ApuestaRojo extends ApuestaBase {
    public ApuestaRojo(int monto) { super(monto, "Rojo"); }
    @Override
    public boolean acierta(int numero, String color) { return color.equals("ROJO"); }
}