package controlador;

import modelo.Resultado;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioArchivo implements IRepositorioResultados {
    private static final String RUTA_ARCHIVO = "historial.dat";

    public RepositorioArchivo() {
        //constructor vacio
    }

    @Override
    public void agregarResultado(Resultado r) {
        List<Resultado> historial = getHistorial();
        historial.add(r);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_ARCHIVO))) {
            oos.writeObject(historial);
        } catch (IOException e) {
            System.err.println("Error al escribir archivo: " + e.getMessage());
        }
    }

    @Override
    public List<Resultado> getHistorial() {
        List<Resultado> historial = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_ARCHIVO))) {
            historial = (List<Resultado>) ois.readObject();
        }  catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado, cree uno nuevo");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer archivo: " + e.getMessage());
        }
        return historial;
    }
}
