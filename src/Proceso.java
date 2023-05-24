import javax.swing.JOptionPane;

public class Proceso {
    String[] nombres;
    Double[] imc;
    Double peso, talla;
    int cantPersonas;

    public Proceso() {
        iniciar();
    }

    public void iniciar() {
        System.out.println("Iniciar");
        String menu = "IMC PERSONAS\n";
        menu += "1. Llenar Datos\n";
        menu += "2. Imprimir IMC\n";
        menu += "3. Eliminar persona\n";
        menu += "4. Actualizar\n";
        menu += "5. Limpiar lista\n";
        menu += "6. Buscar por nombre\n";
        menu += "7. Salir\n";
        menu += "Ingrese una opción:\n";

        int vlrMenu = 0;
        do {
            vlrMenu = Integer.parseInt(JOptionPane.showInputDialog(menu));

            validarMenu(vlrMenu);

        } while (vlrMenu != 8);
    }

    public void validarMenu(int vlrMenu) {

        switch (vlrMenu) {
            case 1:
                llenarDatos();
                break;
            case 2:
                imprimirImc();
                break;
            case 3:
                eliminarPersona();
                break;
            case 4:
                actualizar();
                break;
            case 5:
                limpiarLista();
                break;
            case 6:
                buscarPorNombre();
                break;
            case 7:
                break;
            default:
                System.out.println("Ingrese una opción de la lista que sea valida");
                break;
        }
    }

    public void llenarDatos() {
        int n = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de personas que desea registrar"));
        nombres = new String[n];
        imc = new Double[n];

        for (int i = 0; i < n; i++) {
            nombres[i] = JOptionPane.showInputDialog("Ingrese el nombre de la persona" + (i + 1));
            double peso = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el peso de: " + nombres[i]));
            double talla = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la talla de: " + nombres[i]));
            imc[i] = calcularImc(peso, talla);
        }
    }

    public void imprimirImc() {
        for (int i = 0; i < nombres.length; i++) {
            System.out.println(nombres[i] + " Su IMC es: " + imc[i]);
        }
    }

    public void limpiarLista() {
        nombres = null;
        imc = null;
        System.out.println("Su lista ha sido limpiada con exito.");
    }

    public void eliminarPersona() {
        System.out.println("Esta es su lista:");
        imprimirImc();

        String eliminar = JOptionPane.showInputDialog("Ingrese el nombre de la lista que desea eliminar.");

        int indice = -1;
        for (int i = 0; i < nombres.length; i++) {
            if (nombres[i].equalsIgnoreCase(eliminar)) {
                indice = i;
                break;
            }
        }

        if (indice != -1) {
            eliminarPer(indice);
            System.out.println("La persona fue eliminada.");
        } else {
            System.out.println("El nombre que esta buscando pertenece a la lista.");
        }
    }

    public void eliminarPer(int indice) {
        String[] newListNombres = new String[nombres.length - 1];
        Double[] newListImc = new Double[imc.length - 1];

        int j = 0;
        for (int i = 0; i < nombres.length; i++) {
            if (i != indice) {
            	newListNombres[j] = nombres[i];
            	newListImc[j] = imc[i];
                j++;
            }
        }

        nombres = newListNombres;
        imc = newListImc;
    }

    public void actualizar() {
        String Actualizarnom = JOptionPane.showInputDialog("Ingrese el nombre que desea actualizar");

        int indice = -1;
        for (int i = 0; i < nombres.length; i++) {
            if (nombres[i].equalsIgnoreCase(Actualizarnom)) {
                indice = i;
                break;
            }
        }

        if (indice != -1) {
            String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre");
            nombres[indice] = nuevoNombre;
            System.out.println("Nombre actualizado correctamente.");
        } else {
            System.out.println("El nombre no se encuentra en la lista.");
        }
    }

    public void buscarPorNombre() {
        String nombreBuscar = JOptionPane.showInputDialog("Ingrese el nombre a buscar");

        boolean encontrado = false;
        for (int i = 0; i < nombres.length; i++) {
            if (nombres[i].equalsIgnoreCase(nombreBuscar)) {
                System.out.println("El nombre: " + nombreBuscar + " se encontró en la posición: " + i);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("El nombre que busca no ha sido registrado");
        }
    }

    public double calcularImc(double peso, double talla) {
        double resultado = peso / (talla * talla);

        if (resultado < 18) {
            System.out.println("Usted tiene anorexia");
        } else if (resultado >= 18 && resultado < 20) {
            System.out.println("Usted tiene delgadez");
        } else if (resultado >= 20 && resultado < 27) {
            System.out.println("Usted está en su normalidad");
        } else if (resultado >= 27 && resultado < 30) {
            System.out.println("Usted tiene obesidad grado 1");
        } else if (resultado >= 30 && resultado < 35) {
            System.out.println("Usted tiene obesidad grado 2");
        } else if (resultado >= 35 && resultado < 40) {
            System.out.println("Usted tiene obesidad grado 3");
        } else {
            System.out.println("Usted tiene obesidad mórbida");
        }

        return resultado;
    }
}