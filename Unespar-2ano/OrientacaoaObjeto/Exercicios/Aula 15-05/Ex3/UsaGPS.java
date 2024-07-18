public class UsaGPS {
    public static void main(String[] args) {

        GPS gps1 = new GPS();
        gps1.mostrar();

        GPS gps2 = new GPS("Avenida Tiradentes, numero 123");
        gps2.mostrar();

        gps2.definirIdioma("Ingles");
        gps2.definirRota("Avenida Maringa, numero 456");
        gps2.mostrar();
    }
}