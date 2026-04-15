import java.util.*;

public class DataBay {

    static class Contenedor {
        String id;
        double peso;
        int prioridad;

        public Contenedor(String id, double peso, int prioridad) {
            this.id = id;
            this.peso = peso;
            this.prioridad = prioridad;
        }

        public String toString() {
            return id + " (Peso: " + peso + ", Prio: " + prioridad + ")";
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // MÓDULO 1
        int n = 10; // cantidad de contenedores
        Contenedor[] manifiesto = new Contenedor[n];
        double totalPeso = 0;

        for (int i = 0; i < n; i++) {
            String id = "C" + i;

            // peso con 2 decimales
            double peso = Math.round(Math.random() * 100 * 100.0) / 100.0;

            int prio = (int) (Math.random() * 3); // 0, 1 o 2

            manifiesto[i] = new Contenedor(id, peso, prio);
            totalPeso += peso;
        }

        System.out.println("TOTAL MANIFIESTO: " + totalPeso + " tons");

        // MÓDULO 2
        Contenedor[][] patio = new Contenedor[3][3];

        // MÓDULO 3
        Queue<Contenedor> cola = new LinkedList<>();

        // MÓDULO 4
        Stack<Contenedor> pila = new Stack<>();

        // FLUJO CORRECTO
        for (Contenedor c : manifiesto) {

            // PATIO
            boolean puesto = false;
            for (int i = 0; i < patio.length && !puesto; i++) {
                for (int j = 0; j < patio[i].length; j++) {
                    if (patio[i][j] == null) {
                        patio[i][j] = c;
                        puesto = true;
                        break;
                    }
                }
            }

            if (!puesto) {
                System.out.println("PUERTO SATURADO");
                continue;
            }

            // COLA
            if (c.prioridad >= 2) {
                cola.add(c);
            }

            // PILA
            if (pila.isEmpty() || c.peso <= pila.peek().peso) {
                pila.push(c);
            } else {
                System.out.println("No cumple estabilidad: " + c);
            }
        }

        // INSPECCIÓN
        System.out.println("\nINSPECCIÓN:");
        while (!cola.isEmpty()) {
            System.out.println(cola.poll());
        }

        // BUQUE
        System.out.println("\nBUQUE (de arriba hacia abajo):");
        Stack<Contenedor> aux = new Stack<>();

        while (!pila.isEmpty()) {
            Contenedor c = pila.pop();
            System.out.println(c);
            aux.push(c);
        }

        // restaurar pila
        while (!aux.isEmpty()) {
            pila.push(aux.pop());
        }

        sc.close();
    }
}